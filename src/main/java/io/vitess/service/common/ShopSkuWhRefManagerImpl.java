package io.vitess.service.common;

import io.vitess.command.SalesOrderLineCommand;
import io.vitess.command.ShopSkuWhRefCommand;
import io.vitess.dao.base.CompanyShopDao;
import io.vitess.dao.base.ShopSkuWhRefDao;
import io.vitess.dao.base.ShopSkuWhRefLogDao;
import io.vitess.dao.base.SkuDao;
import io.vitess.dao.so.ShopWhDao;
import io.vitess.model.base.ShopSkuWhRef;
import io.vitess.model.base.ShopSkuWhRefLog;
import io.vitess.model.base.Sku;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.base.ShopWh;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 店铺商品仓库编码关系维护
 * 
 * @className com.jumbo.manager.baseinfo.ShopSkuWhRefManagerImpl
 * @author hailiang.jiang
 * @date 2014年10月9日 下午8:58:08
 */
@Service("shopSkuWhRefManager")
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class ShopSkuWhRefManagerImpl extends BaseManagerImpl implements ShopSkuWhRefManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ShopSkuWhRefDao shopSkuWhRefDao;
	@Autowired
	private CompanyShopManager companyShopManager;
	@Autowired
	private ShopSkuWhRefLogDao shopSkuWhRefLogDao;
    @Autowired
    private CompanyShopDao companyShopDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private ShopWhDao shopWhDao;
	@Autowired
	private ShopWhManager shopWhManager;
	
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public ShopSkuWhRef findByShopAndSku(CompanyShop shop, String extCode, Date paymentTime) throws BusinessException {
		return shopSkuWhRefDao.findByShopAndSku(shop.getId(), extCode, paymentTime);
	}
	
	
	@Override
	public Map<String, List<SalesOrderLineCommand>> matchWarehouse(CompanyShop shop, List<SalesOrderLineCommand> salesOrderLineCommandList) throws BusinessException {
		Map<String, List<SalesOrderLineCommand>> whSoMap = new HashMap<String, List<SalesOrderLineCommand>>();
		
		ShopWh shopWh = shopWhManager.getDefaultWarehouse(shop.getId());
    	for (SalesOrderLineCommand originalSolCmd : salesOrderLineCommandList) {
    		ShopSkuWhRef shopSkuWhRef = null;

    		Sku sku = skuDao.findById(originalSolCmd.getSku());
    		if (sku != null) {
    			shopSkuWhRef = findByShopAndSku(shop, sku.getExtensionCode1(), new Date());
    		}
			splitGroupSalesOrderLine(whSoMap, shopSkuWhRef, shopWh, originalSolCmd);
    	}
		
		return whSoMap;
	}
	
	
	private CompanyShop getShop(Long ouId) {
		CompanyShop shop = companyShopManager.getByOuId(ouId);
		return shop;
	}
	
	private void splitGroupSalesOrderLine(Map<String, List<SalesOrderLineCommand>> whSoMap, ShopSkuWhRef shopSkuWhRef, ShopWh shopWh, SalesOrderLineCommand solCmd) {
    	if (shopSkuWhRef != null) {
    		if (!whSoMap.containsKey(shopSkuWhRef.getWhCode())) {
				whSoMap.put(shopSkuWhRef.getWhCode(), new ArrayList<SalesOrderLineCommand>());
			}
			whSoMap.get(shopSkuWhRef.getWhCode()).add(solCmd);
			return;
    	}
    	
    	if (!whSoMap.containsKey(shopWh.getWhCode())) {
			whSoMap.put(shopWh.getWhCode(), new ArrayList<SalesOrderLineCommand>());
		}
		whSoMap.get(shopWh.getWhCode()).add(solCmd);
    }


	
	/**
	 * 校验数据有效性
	 * @param refCommandList
	 * @param companyShop
	 * @return
	 * @throws Exception
	 */
	private boolean validateShopSkuWhRefCommand(List<ShopSkuWhRefCommand> refCommandList, CompanyShop companyShop) throws Exception {
	    boolean flag = true;
	    for(ShopSkuWhRefCommand refCommand : refCommandList){
            refCommand.setShopName(companyShop.getShopName()); 
            //校验Sku是否为集团下已创建商品
            Sku sku = skuDao.findSkuByExtCodeBrtCode(refCommand.getExtCode().trim(), companyShop.getBusinessRegionType(),companyShop.getWhCustomerCode());
            if(sku == null){
                refCommand.setShowResult("Error：商品不存在!");
                flag = false;
                continue;
            }
            //WH（关联仓库）必包含于当前店铺关联仓库
            ShopWh shopWh = shopWhDao.findShopWhByShopIdAndWhCode(companyShop.getId(), refCommand.getWhCode().trim());
            if(shopWh == null){
                refCommand.setShowResult("Error：关联仓库不包含于当前店铺!");
                flag = false;
                continue;
            }
            //校验  时间
            Date[] dateArray = checkDate(refCommand.getEffectiveTimeBeginStr(),refCommand.getEffectiveTimeEndStr());
            if(dateArray == null){
                refCommand.setShowResult("Error：时间格式转换错误!");
                flag = false;
                continue;
            }
            Date startTime = dateArray[0];
            Date endTime = dateArray[1];
            Date newDate = sdf.parse(sdf.format(new Date()));
            if(startTime.getTime() == endTime.getTime()){
                refCommand.setShowResult("Error：开始时间等于结束时间!");
                flag = false;
                continue;
            }else if(startTime.getTime() > endTime.getTime()){
                refCommand.setShowResult("Error：开始时间大于结束时间!");
                flag = false;
                continue;
            }else if(endTime.getTime() < newDate.getTime()){
                refCommand.setShowResult("Error：结束时间小于当前时间!");
                flag = false;
                continue;
            }                    
        }
	    return flag;
	}
	
	/**
	 * 校验通过保存数据
	 * @param companyShop
	 * @param refCommand
	 * @param shopWh
	 * @param userId
	 * @throws Exception
	 */
	private void saveDate(CompanyShop companyShop, ShopSkuWhRefCommand refCommand, ShopWh shopWh, Long userId) throws Exception {
	    Date createDate = new Date();
        //创建数据
        ShopSkuWhRef ref = new ShopSkuWhRef();
        ref.setShop(companyShop);
        ref.setShopName(companyShop.getShopName());
        ref.setExtCode(refCommand.getExtCode());
        ref.setWhCode(refCommand.getWhCode());
        ref.setWhName(shopWh.getWhName());
        ref.setEffectiveTimeBegin(sdf.parse(refCommand.getEffectiveTimeBeginStr()));
        ref.setEffectiveTimeEnd(sdf.parse(refCommand.getEffectiveTimeEndStr()));
        ref.setActiveOff("Y");
        ref.setRemark("商品-自动拆单规则维护");
        ref.setCreateTime(createDate);
        ref.setCreateUserNo(String.valueOf(userId));
        shopSkuWhRefDao.insert(ref);
        
        ShopSkuWhRefLog log = new ShopSkuWhRefLog();
        log.setShopSkuWhRef(ref);  
        log.setShop(companyShop);
        log.setShopName(companyShop.getShopName());
        log.setExtCode(refCommand.getExtCode());
        log.setWhCode(refCommand.getWhCode());
        log.setWhName(shopWh.getWhName());
        log.setEffectiveTimeBegin(sdf.parse(refCommand.getEffectiveTimeBeginStr()));
        log.setEffectiveTimeEnd(sdf.parse(refCommand.getEffectiveTimeEndStr()));
        log.setActiveOff("Y");
        log.setRemark("商品-自动拆单规则维护日志");
        log.setCreateTime(createDate);
        log.setCreateUserNo(String.valueOf(userId));
        shopSkuWhRefLogDao.insert(log);
	}
	
    private Date[] checkDate(String strDate, String endDate) {
        Date[] date = new Date[2];
        try {
            date[0] = sdf.parse(strDate);
            date[1] = sdf.parse(endDate);
            return date;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public Map<String, Object> findShopSkuWhRefByShopId(Long ouId) {
        Map<String, Object> maps = new HashMap<String, Object>();
        
        CompanyShop companyShop = companyShopDao.getByOuId(ouId); 
        List<ShopSkuWhRefCommand> resultList = shopSkuWhRefDao.findShopSkuWhRefByShopId(companyShop.getId());
        
        maps.put("shopSkuWhRefData", resultList);
        String shopName = companyShop.getShopName();
        maps.put("shopName", shopName);
        
        return maps;
    }
}
