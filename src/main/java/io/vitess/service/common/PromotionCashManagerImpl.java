package io.vitess.service.common;

import io.vitess.command.PromotionActivityCommand2;
import io.vitess.command.PromotionGiftCommand;
import io.vitess.command.PromotionSkuCommand;
import io.vitess.constants.Constants;
import io.vitess.dao.base.*;
import io.vitess.enums.ProductScope;
import io.vitess.enums.PromotionApplyModel;
import io.vitess.enums.PromotionProductType;
import io.vitess.enums.PromotionType;
import io.vitess.model.base.*;
import io.vitess.service.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service("promotionCashManager")
public class PromotionCashManagerImpl extends BaseManagerImpl implements PromotionCashManager {
	private static final long serialVersionUID = 5010724809503175805L;

	@Autowired
	private PromotionActivityDao promotionActivityDao;
	@Autowired
	private PromotionRuleDao promotionRuleDao;
	@Autowired
	private PromotionProductDao promotionProductDao;
	@Autowired
	private PromotionGiftDao promotionGiftDao;
	@Autowired
	private PromotionGiftSkuDao promotionGiftSkuDao;
	@Autowired
	private ProductCategoryTagDao productCategoryTagDao;
   /**
     * 赠品是否到sku标示 0 是不到 1是到SKU
     */
    private static final String NO_SKU_GIFT_TYPE = "0";
    /**
     * 如果到sku 且没有重复的SKU 每次固定赠送1件
     */
    private static final int SKU_GIFT_NUM = 1;

	static Map<Long, List<PromotionActivityCommand2>> promotionMap = new HashMap<Long, List<PromotionActivityCommand2>>();
	
	
	
	/**
	 * @author **
	 * @time 2016年11月25日
	 * @param
	 * @updateBy zhiyong.shi
	 */
	@Override
	public void reloadAllPromotions() {
		Map<Long, List<PromotionActivityCommand2>> promotionMapTemp = new HashMap<Long, List<PromotionActivityCommand2>>();
		//查询促销头信息 为促销起止时间大于设置的初始日期，结束时间大于 当前时间在往前推7天的时间，且状态是开启状态的。
		//涉及表t_ma_promotion_activity
		List<PromotionActivityCommand2> promotionAcList = promotionActivityDao.findAllPromotionActivity();
//		//查出规则表所有的数据t_ma_promotion_rule
		//List<PromotionRule> promotionRuleList = promotionRuleDao.findAllRuleList();
		for (PromotionActivityCommand2 command : promotionAcList) {
			Long promotionId = command.getId();
			//用promotionId 和shopId 查 promotionRuleDao
			List<PromotionRule> ruleList = null;
			Map<Long, List<PromotionProduct>> productMap = new HashMap<Long, List<PromotionProduct>>();
			Map<Long, List<PromotionGift>> giftMap = new HashMap<Long, List<PromotionGift>>();
			ruleList = promotionRuleDao.findRuleList(promotionId, command.getShopId());
			if(ruleList.size()>0){
					for(PromotionRule bean : ruleList){
						//查出促销规则匹配到的主卖品
						productMap.put(bean.getId(), promotionProductDao.findByProductRuleId(bean.getId(),bean.getShopId()));
						//查出促销规则匹配到的赠品 TODO可能需要改
						giftMap.put(bean.getId(), promotionGiftDao.findByProductRuleId(bean.getId(),bean.getShopId()));
					}
	//			for (int i = 0; i < promotionRuleList.size(); i++) {
	//				PromotionRule rule = promotionRuleList.get(i);
	//				if (promotionId.equals(rule.getPromotion().getId())) {
	//					ruleList.add(rule);
	//					//查出促销规则匹配到的主卖品
	//					productMap.put(rule.getId(), promotionProductDao.findByProductRuleId(rule.getId(),command.getShopId()));
	//					//查出促销规则匹配到的赠品 TODO可能需要改
	//					giftMap.put(rule.getId(), promotionGiftDao.findByProductRuleId(rule.getId(),command.getShopId()));
	//					promotionRuleList.remove(i);
	//					i = i - 1;
	//				}
	//			}
					command.setRulesList(ruleList);
					command.setProductMap(productMap);
					command.setGiftMap(giftMap);
					if (null != command.getShopId()) {
						List<PromotionActivityCommand2> proList = promotionMapTemp.get(command.getShopId());
						if (null == proList) {
							proList = new ArrayList<PromotionActivityCommand2>();
						}
						//把匹配到的促销头信息，还有促销赠品，规则信息按照shopId加入缓存
						proList.add(command);
						promotionMapTemp.put(command.getShopId(), proList);
					}
				}
			}
			promotionMap = promotionMapTemp;
		}

	public static Map<Long, List<PromotionActivityCommand2>> getPromotionMap() {
		return promotionMap;
	}

	/**
	 * 满赠类型应用场景
	 * @param promotion
	 * @param skuTotalFee
	 * @param skuInfoComboMap
	 * @param skuInfoNotComboMap
	 * @param proGiftMap
	 * @return
	 */
	@Override
	public Map<Long, PromotionGiftCommand> applyPromotion(PromotionActivityCommand2 promotion, BigDecimal skuTotalFee, Map<Long, PromotionSkuCommand> skuInfoComboMap
			, Map<Long, PromotionSkuCommand> skuInfoNotComboMap, Map<Long, PromotionGiftCommand> proGiftMap) {
		// 数据check
		if (null == promotion || skuTotalFee.equals(BigDecimal.ZERO) || (null == skuInfoComboMap && null == skuInfoNotComboMap)) {
			return null;
		}
		if (null == promotion.getProductMap()) {
			log.info("promotion product is not exist:" + promotion.getId());
			return null;
			//throw new BusinessException(ErrorCode.PROMOTION_PRODUCT_NOT_EXIST_ERROR);
		}
		if (null == promotion.getGiftMap()) {
			log.info("promotion gift is not exist:" + promotion.getId());
			return null;
			//throw new BusinessException(ErrorCode.PROMOTION_GIFT_NOT_EXIST_ERROR);
		}

		if (promotion.getTypeInt() == PromotionType.FULL_GIFT.getValue()) {
			// 满赠类型
			applyProActForMoneyInDiffWays(promotion, skuTotalFee, skuInfoComboMap, skuInfoNotComboMap, proGiftMap);
		} else if (promotion.getTypeInt() == PromotionType.BUY_GIFT.getValue()) {
			// 买赠类型
			applyProActForBuyInDiffWays(promotion, skuTotalFee, skuInfoComboMap, skuInfoNotComboMap, proGiftMap);
		}

		return proGiftMap;
	}

	/**
	 * 满赠类型应用场景
	 * @param promotion
	 * @param skuTotalFee
	 * @param skuInfoComboMap
	 * @param skuInfoNotComboMap
	 * @param proGiftMap
	 * @return
	 */
	private Map<Long, PromotionGiftCommand> applyProActForMoneyInDiffWays(PromotionActivityCommand2 promotion, BigDecimal skuTotalFee,
                                                                          Map<Long, PromotionSkuCommand> skuInfoComboMap, Map<Long, PromotionSkuCommand> skuInfoNotComboMap, Map<Long, PromotionGiftCommand> proGiftMap) {
		// check
		if (null == promotion.getRulesList() || promotion.getRulesList().size() <= 0) {
			log.info("promotion rule is not exist:" + promotion.getId());
			return null;
			//throw new BusinessException(ErrorCode.PROMOTION_RULL_NOT_EXIST_ERROR);
		}
		// 计算商品总金额/总数量fanht
		Map<String,Object> map = calculateProductListTotalFee(promotion, skuTotalFee, skuInfoComboMap, skuInfoNotComboMap);
		// 是否重复
		Boolean isCanRepeate = promotion.getIsCanRepeate() == null ? false : promotion.getIsCanRepeate();
		// 满赠应用
		proGiftMap = applyProActForMoney(promotion, map, proGiftMap, isCanRepeate);
		
		return proGiftMap;
	}

	/**
	 * 商品总金额计算
	 * @param promotion
	 * @param skuTotalFee
	 * @param skuInfoComboMap
	 * @param skuInfoNotComboMap
	 * @return
	 */
	private Map<String,Object> calculateProductListTotalFee(PromotionActivityCommand2 promotion, BigDecimal skuTotalFee,
                                                            Map<Long, PromotionSkuCommand> skuInfoComboMap, Map<Long, PromotionSkuCommand> skuInfoNotComboMap) {

	    Map<String,Object> map = new HashMap<String,Object>();
        int usedNum = 0;
	    
		ProductScope scope = ProductScope.PRODUCT_SCOPE_FULL;
		Integer scopeInt = promotion.getProductScopeInt(); // 主卖品范围
		if (null != scopeInt && scopeInt > 0) {
			scope = ProductScope.valueOf(scopeInt);
		}
		// 是否排除组合商品
		Boolean isExceludeComboSku = promotion.getIsExceludeComboSkuInt() == null ? false : promotion.getIsExceludeComboSkuInt() == 1 ? true : false;
		if (scope == ProductScope.PRODUCT_SCOPE_FULL && isExceludeComboSku == false) {
			// 全场且不排除组合商品
		    map.put("skuTotalFee", skuTotalFee);
		    map.put("usedNum", usedNum);
		    return map;
			//return skuTotalFee;
		}
		
		String includeLabel = promotion.getIncludeLabel(); // 包含指定标签
//		String excludeLabel = promotion.getExcludeLabel(); // 排除指定标签
		Integer productQtyLevel = promotion.getProductQtyLevel();
		
		// 根据规则返回可应用商品行集合
		List<PromotionSkuCommand> promotionSkuList = constructPromotionSkuListForSo(scope, skuInfoComboMap
				, skuInfoNotComboMap, isExceludeComboSku, includeLabel, productQtyLevel);
		BigDecimal skuFee = new BigDecimal(0);
		//fanht 追加非空判断、可用数量
		if(promotionSkuList!=null){
		    for(PromotionSkuCommand ps : promotionSkuList) {
	            if (null != ps) {
	                skuFee = skuFee.add(ps.getLineTotal());
	                //可用数量fanht
	                if(ps.getIsUsed()){
	                    usedNum = usedNum + ps.getProductQty();
	                }
	            }
	        }
		}
		
		map.put("skuTotalFee", skuFee);
        map.put("usedNum", usedNum);
        return map;
	}


	/**
	 * 根据规则返回可应用商品行集合
	 * @param scope
	 * @param skuInfoComboMap
	 * @param skuInfoNotComboMap
	 * @param isExceludeComboSku
	 * @param includeLabel
	 * @param productQtyLevel
	 * @return
	 */
	private List<PromotionSkuCommand> constructPromotionSkuListForSo(ProductScope scope
			, Map<Long, PromotionSkuCommand> skuInfoComboMap
			, Map<Long, PromotionSkuCommand> skuInfoNotComboMap
			, boolean isExceludeComboSku, String includeLabel, Integer productQtyLevel) {
		 List<PromotionSkuCommand> proSkuList = new ArrayList<PromotionSkuCommand>();
		if (isExceludeComboSku == Boolean.TRUE) {
			// 排除组合商品
			if (scope == ProductScope.PRODUCT_SCOPE_INCLUDE && StringUtils.hasText(includeLabel)) {
				// 包含标签商品
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 2, includeLabel, 0);
			} else if (scope == ProductScope.PRODUCT_SCOPE_EXCLUDE && StringUtils.hasText(includeLabel)) {
				// 排除标签商品
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 1, includeLabel, 0);
			} else if (scope == ProductScope.PRODUCT_SCOPE_INCLUDE_LABEL_AND_QTY && StringUtils.hasText(includeLabel)) {
				// 全场且包含指定标签且指定数量
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 4, includeLabel, productQtyLevel);
			} else {
				// 全场
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 3, "", 0);
			}
		} else { 
			// 不排除组合商品
			if (scope == ProductScope.PRODUCT_SCOPE_INCLUDE && StringUtils.hasText(includeLabel)) {
				// 包含标签商品
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoComboMap, 2, includeLabel, 0);
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 2, includeLabel, 0);
			} else if (scope == ProductScope.PRODUCT_SCOPE_EXCLUDE && StringUtils.hasText(includeLabel)) {
				// 排除标签商品
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoComboMap, 1, includeLabel, 0);
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 1, includeLabel, 0);
			} else if (scope == ProductScope.PRODUCT_SCOPE_INCLUDE_LABEL_AND_QTY && StringUtils.hasText(includeLabel)) {
				// 全场且包含指定标签且指定数量
				Map<Long, PromotionSkuCommand> skuInfoMap = unionComboAndNotCombo(skuInfoComboMap, skuInfoNotComboMap);
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoMap, 4, includeLabel, productQtyLevel);
			}  else {
				// 全场
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoComboMap, 3, "", 0);
				proSkuList = excludeSkuAndBackMoney(proSkuList, skuInfoNotComboMap, 3, "", 0);
			}
		}
		
		return proSkuList;
	}

	/**
	 * 买赠类型应用场景
	 * @param promotion
	 * @param skuTotalFee
	 * @param skuInfoComboMap
	 * @param skuInfoNotComboMap
	 * @param proGiftMap
	 * @return
	 */
	private Map<Long, PromotionGiftCommand> applyProActForBuyInDiffWays(PromotionActivityCommand2 promotion, BigDecimal skuTotalFee,
                                                                        Map<Long, PromotionSkuCommand> skuInfoComboMap, Map<Long, PromotionSkuCommand> skuInfoNotComboMap, Map<Long, PromotionGiftCommand> proGiftMap) {
		// check
		if (null == promotion.getRulesList() || promotion.getRulesList().size() <= 0) {
			log.info("promotion rule is not exist:" + promotion.getId());
			return null;
			//throw new BusinessException(ErrorCode.PROMOTION_RULL_NOT_EXIST_ERROR);
		}
		if (null == promotion.getProductMap()) {
			log.info("promotion productMap is not exist:" + promotion.getId());
			return null;
			//throw new BusinessException(ErrorCode.PROMOTION_PRODUCT_NOT_EXIST_ERROR);
		}
		// 是否排除组合商品
		Boolean isExceludeComboSku = promotion.getIsExceludeComboSkuInt() == null ? false : promotion.getIsExceludeComboSkuInt() == 1 ? true : false;
		Map<Long, Integer> skuMap = new HashMap<Long, Integer>();
		// 是否可以重复应用
		Boolean isCanRepeate = promotion.getIsCanRepeate() == null ? false : promotion.getIsCanRepeate();
		
		// 根据是否排除组合商品，获得商品行
		if (isExceludeComboSku == true) {
			// 排除组合商品
			skuMap = reUnionSkuForBuy(skuMap, skuInfoNotComboMap);
		} else {
			// 不排除组合商品
			skuMap = reUnionSkuForBuy(skuMap, skuInfoComboMap); 
			skuMap = reUnionSkuForBuy(skuMap, skuInfoNotComboMap);
		}
		
		//1：按商品指定，2：按标签指定
		if (null != promotion.getPromotionProductTypeInt() && promotion.getPromotionProductTypeInt() == PromotionProductType.APPLY_CATEGORY.getValue()) {
			// 促销应用根据标签
			proGiftMap = applyProActForBuyByCategory(promotion, skuMap, proGiftMap, isCanRepeate);
		} else {
			// 促销应用根据商品
			proGiftMap = applyProActForBuy(promotion, skuMap, proGiftMap, isCanRepeate);
		}
		
		return proGiftMap;
	}

	/**
	 * 满赠类型实际应用
	 * @param promotion
	 * @param map
	 * @param proGiftMap
	 * @param flag
	 * @return
	 */
	private Map<Long, PromotionGiftCommand> applyProActForMoney(PromotionActivityCommand2 promotion, Map<String,Object> map, Map<Long, PromotionGiftCommand> proGiftMap, Boolean flag) {
	    //同时返回金额和数量
	    BigDecimal skuTotalFee = (BigDecimal) map.get("skuTotalFee");
	    Integer usedNum = (Integer) map.get("usedNum");
	    
	    // 满赠应用
		List<PromotionRule> ruleList = promotion.getRulesList();
		//活动规则应用方式条件添加  根据ApplyModel字段判断
		boolean isApplyOnece = false;
		if (null != promotion.getApplyModelInt() && promotion.getApplyModelInt() == PromotionApplyModel.APPLY_ONECE.getValue()) {
			isApplyOnece = true;
		}

		for (int i = 0; i < ruleList.size(); i++) {
			PromotionRule ruleData = ruleList.get(i);
			if (null != ruleData) {
			    
//				// 当赠品已经赠送完毕时fanht 注释
//			    Integer usedGiftTimes = promotionRuleDao.findPromotionRulById(ruleData.getId());//从db里面取得
//				if (usedGiftTimes >= ruleData.getMaxGiftTimes()) {
//					if (isApplyOnece == true) { 
//						// 只应用一次
//						break;
//					} else { 
//						// 如果该规则赠品数量不够，可继续应用下一个规则
//						continue;
//					}
//				}
				
				// 金额大于等于基准线
				if (skuTotalFee.compareTo(ruleData.getAmountLevel()) == 1 || skuTotalFee.compareTo(ruleData.getAmountLevel()) == 0) {
					////////////////////////////////////////////////////
					Integer usedGiftTimes = promotionRuleDao.findPromotionRulById(ruleData.getId(), promotion.getShopId());//从db里面取得
	                if (usedGiftTimes >= ruleData.getMaxGiftTimes()) {
	                    if (isApplyOnece == true) { 
	                        // 只应用一次
	                        break;
	                    } else { 
	                        // 如果该规则赠品数量不够，可继续应用下一个规则
	                        continue;
	                    }
	                }
	                
	                if (flag) {
						// 可多次应用
	                    int times = skuTotalFee.divide(ruleData.getAmountLevel(), 0, BigDecimal.ROUND_FLOOR).intValue();
					    if(promotion.getProductScopeInt()==ProductScope.PRODUCT_SCOPE_INCLUDE_LABEL_AND_QTY.getValue()){
					        //全场且包含指定标签且指定数量fanht
					        int timesNum = usedNum.intValue()/promotion.getProductQtyLevel().intValue();
					        if(timesNum<times){
					            times = timesNum;
					        }
					    }
					    boolean result = salesOrderAddPromotion(promotion.getId(), promotion.getShopId(), ruleData, promotion.getGiftMap().get(ruleData.getId()), proGiftMap, PromotionType.FULL_GIFT, times);
					    if(result){
					    	//促销抢占成功
					    	return proGiftMap;
					    }else{
					    	//促销抢占失败
					    	continue;
					    }
					} else {
						// 应用一次 添加赠品
						boolean result = salesOrderAddPromotion(promotion.getId(), promotion.getShopId(), ruleData, promotion.getGiftMap().get(ruleData.getId()), proGiftMap, PromotionType.FULL_GIFT, 1);
						if(result){
					    	//促销抢占成功
					    	return proGiftMap;
					    }else{
					    	//促销抢占失败
					    	continue;
					    }
					} 
				}
			}
		}
		return proGiftMap;
	}


	/**
	 * 买赠类型实际应用
	 * 重新修改这个算法fanht
	 * @param promotion
	 * @param skuInfoMap
	 * @param proGiftMap
	 * @param flag
	 * @return
	 */
	private Map<Long, PromotionGiftCommand> applyProActForBuy(PromotionActivityCommand2 promotion, Map<Long, Integer> skuInfoMap, Map<Long, PromotionGiftCommand> proGiftMap,
                                                              Boolean flag) {
		// 买赠应用
		List<PromotionRule> ruleList = promotion.getRulesList();
		//活动规则应用方式条件添加  根据ApplyModel字段判断fanht
		boolean isApplyOnece = false;
		if (null != promotion.getApplyModelInt() && promotion.getApplyModelInt() == PromotionApplyModel.APPLY_ONECE.getValue()) {
			isApplyOnece = true;
		}
		
		//一次循环所有规则
		for (int i = 0; i < ruleList.size(); i++) {   
		    //获取规则对应的商品列表fanht
			PromotionRule ruleData = ruleList.get(i);
			if (null != ruleData) {
				List<PromotionProduct> productList = promotion.getProductMap().get(ruleData.getId());
				if (null == productList || productList.size() <= 0) {
					return proGiftMap;
				}
				
				boolean isApplySuccess = true;
				//判断订单是否满足当前规则fanht
				for (PromotionProduct data : productList) {
					Long productId = data.getProduct().getId();
					Integer productQty = data.getQuantityLevel();
					if (null == skuInfoMap.get(productId) || skuInfoMap.get(productId) < productQty) {
						// 不满足条件
						isApplySuccess = false;
						break;
					}
				}
				//本条规则不满足，进入下一条规则fanht
				if(!isApplySuccess){
					continue;
				}
				
				//当赠品已经赠送完毕时 fanht移动
                Integer usedGiftTimes = promotionRuleDao.findPromotionRulById(ruleData.getId(), promotion.getShopId());//从db里面取得
                if (usedGiftTimes >= ruleData.getMaxGiftTimes()) {
                    if (isApplyOnece == true) {
                        break;
                    } else { 
                        // 如果该规则赠品数量不够，可继续应用下一个规则
                        continue;
                    }
                }
				
				// 最多赠送次数
				int giftApplyTimes = 0;
                //计算赠送次数
				int index = 0;
				for (PromotionProduct data : productList) {
					index++;
					if (null == data.getProduct()) {
						isApplySuccess = false;
						break;
					}
					Long productId = data.getProduct().getId();
					Integer productQty = data.getQuantityLevel();
					// 满足条件 计算可赠送次数 获取最少一个商品可赠送次数
					int minTimes = skuInfoMap.get(productId) / productQty;
					if (index <= 1) {
						giftApplyTimes = minTimes;
					}
					if (giftApplyTimes > minTimes) {
						giftApplyTimes = minTimes;
					}
				}
				
				// 应用成功
				if (isApplySuccess) {
					if (flag) {
						// 可重复赠送
						boolean result = salesOrderAddPromotion(promotion.getId(), promotion.getShopId(), ruleData, promotion.getGiftMap().get(ruleData.getId()), proGiftMap, PromotionType.BUY_GIFT, giftApplyTimes);
						if(result){
					    	//促销抢占成功
					    	return proGiftMap;
					    }else{
					    	//促销抢占失败
					    	continue;
					    }
					} else {
						// 添加赠品
						boolean result = salesOrderAddPromotion(promotion.getId(), promotion.getShopId(), ruleData, promotion.getGiftMap().get(ruleData.getId()), proGiftMap, PromotionType.BUY_GIFT, 1);
						if(result){
					    	//促销抢占成功
					    	return proGiftMap;
					    }else{
					    	//促销抢占失败
					    	continue;
					    }
					}
				}
			}
		}
		return proGiftMap;
	}

	/**
	 * 买赠类型实际应用根据标签
	 * @param promotion
	 * @param skuInfoMap
	 * @param proGiftMap
	 * @param flag
	 * @return
	 */
	private Map<Long, PromotionGiftCommand> applyProActForBuyByCategory(PromotionActivityCommand2 promotion, Map<Long, Integer> skuInfoMap, Map<Long, PromotionGiftCommand> proGiftMap,
                                                                        Boolean flag) {
		// 买赠应用
		List<PromotionRule> ruleList = promotion.getRulesList();
		// 活动规则应用方式条件添加  根据ApplyModel字段判断
		boolean isApplyOnece = false;
		if (null != promotion.getApplyModelInt() && promotion.getApplyModelInt() == PromotionApplyModel.APPLY_ONECE.getValue()) {
			isApplyOnece = true;
		}
		// 根据对应商品获取所有的标签集合
		Map<String, Integer> categoryMap = construtCategoryByProduct(skuInfoMap, promotion.getShopId());
		for (int i = 0; i < ruleList.size(); i++) {
			PromotionRule ruleData = new PromotionRule();
			ruleData = ruleList.get(i);
			if (null != ruleData) {
				// 当赠品已经赠送完毕时
			    Integer usedGiftTimes = promotionRuleDao.findPromotionRulById(ruleData.getId(), promotion.getShopId());//从db里面取得
				if (usedGiftTimes >= ruleData.getMaxGiftTimes()) {
					if (isApplyOnece == true) { 
						// 只应用一次
						break;
					} else { 
						// 如果该规则赠品数量不够，可继续应用下一个规则
						continue;
					}
				}
				List<PromotionProduct> productList = promotion.getProductMap().get(ruleData.getId());
				if (null == productList || productList.size() <= 0) {
					return proGiftMap;
				}
				boolean isApplySuccess = true;
				// 最多赠送次数
				int giftApplyTimes = 0;
				int index = 0;
				for (PromotionProduct data : productList) {
					index++;
					if (null == data.getProductCategoryTag()) {
						isApplySuccess = false;
						break;
					}
					String categoryCode = "";
					if (null != data.getProductCategoryTag().getId()) {
						ProductCategoryTag pct = productCategoryTagDao.findById(data.getProductCategoryTag().getId());
						if (null != pct) {
							categoryCode = pct.getCode();
						}
					}
					Integer productQty = data.getQuantityLevel();
					if (null == categoryMap.get(categoryCode) || categoryMap.get(categoryCode) < productQty) {
						// 不满足条件
						isApplySuccess = false;
						break;
					} else {
						// 满足条件 计算可赠送次数 获取最少一个商品可赠送次数
						int minTimes = categoryMap.get(categoryCode) / productQty;
						if (index <= 1) {
							giftApplyTimes = minTimes;
						}
						if (giftApplyTimes > minTimes) {
							giftApplyTimes = minTimes;
						}
					}
				}
				// 应用成功
				if (isApplySuccess) {
					if (flag) {
						// 可重复赠送
						boolean result = salesOrderAddPromotion(promotion.getId(), promotion.getShopId(), ruleData, promotion.getGiftMap().get(ruleData.getId()), proGiftMap, PromotionType.BUY_GIFT, giftApplyTimes);
						if(result){
					    	//促销抢占成功
					    	return proGiftMap;
					    }else{
					    	//促销抢占失败
					    	continue;
					    }
					} else {
						// 添加赠品
						boolean result = salesOrderAddPromotion(promotion.getId(), promotion.getShopId(), ruleData, promotion.getGiftMap().get(ruleData.getId()), proGiftMap, PromotionType.BUY_GIFT, 1);
						if(result){
					    	//促销抢占成功
					    	return proGiftMap;
					    }else{
					    	//促销抢占失败
					    	continue;
					    }
					}
				}
			}
		}
		return proGiftMap;
	}
	
	/**
	 * 根据商品，整合标签
	 * 
	 * @return 标签 <标签编码，商品数量>
	 */
	private Map<String, Integer> construtCategoryByProduct(Map<Long, Integer> skuInfoMap, Long shopId) {
		Map<String, Integer> categoryMap = new HashMap<String, Integer>();
		Set<Long> key = skuInfoMap.keySet();
		for (Iterator<Long> it = key.iterator(); it.hasNext();) {
			Long productId = (Long) it.next();
			Integer proQty = skuInfoMap.get(productId);
			List<String> categoryCodeList = productCategoryTagDao.findCategoryCodeByProduct(productId, shopId, new SingleColumnRowMapper<String>(String.class));
			if (null != categoryCodeList && categoryCodeList.size() > 0) {
				for (String categoryCode : categoryCodeList) {
					if (null == categoryMap.get(categoryCode)) {
						categoryMap.put(categoryCode, proQty);
					} else {
						Integer qty = categoryMap.get(categoryCode);
						categoryMap.put(categoryCode, qty + proQty);
					}
				}
			}
		}
		return categoryMap;
	}

	/**
	 * 根据情况排除商品返回金额
	 * @param proSkuList
	 * @param skuInfoMap
	 * @param flag
	 * @param categoryCode
	 * @param productQtyLevel
	 * @return
	 */
	private List<PromotionSkuCommand> excludeSkuAndBackMoney(List<PromotionSkuCommand> proSkuList,
                                                             Map<Long, PromotionSkuCommand> skuInfoMap, int flag, String categoryCode, Integer productQtyLevel) {
		Set<Long> key = skuInfoMap.keySet();
		Integer qtyTotal = new Integer(0);
		for (Iterator<Long> it = key.iterator(); it.hasNext();) {
			Long productId = (Long) it.next();
			PromotionSkuCommand proSku = skuInfoMap.get(productId);
			if (null != proSku) {
					Long shopId = proSku.getShopId();
					Integer qty = proSku.getProductQty();
					if (flag == 1) {
						// 排除标签
						if (checkCategoryNameIsExist(productId, shopId, categoryCode) == false) {
							// 标签商品增加
							proSkuList.add(proSku);
						}
					} else if (flag == 2) {
						// 包含标签
						if (checkCategoryNameIsExist(productId, shopId, categoryCode) == true) {
							// 标签商品增加
							proSkuList.add(proSku);
						}
					} else if (flag == 3) {
						// 全场
						proSkuList.add(proSku);
					} else if (flag == 4) {
						// 全场且包含指定标签且指定数量
						if (checkCategoryNameIsExist(productId, shopId, categoryCode) == true) {
							// 标签商品增加 fanht 
							//proSkuList.add(proSku);
							qtyTotal += qty;
							proSku.setIsUsed(Boolean.TRUE);
							
						}
						//计算整单金额 fanht
						proSkuList.add(proSku);
					}
				}
		}
		// 全场且包含指定标签且指定数量时 标签商品数量未达到指定数量返回空
		if (flag == 4 && qtyTotal < productQtyLevel) {
			return null;
		} 
		return proSkuList;
	}

	/**
	 * 组合商品跟非组合商品合并
	 * @param skuInfoComboMap
	 * @param skuInfoNotComboMap
	 * @return
	 */
	private Map<Long, PromotionSkuCommand> unionComboAndNotCombo(Map<Long, PromotionSkuCommand> skuInfoComboMap, Map<Long, PromotionSkuCommand> skuInfoNotComboMap) {
		Set<Long> key = skuInfoComboMap.keySet();
		for (Iterator<Long> it = key.iterator(); it.hasNext();) {
			Long productId = (Long) it.next();
			PromotionSkuCommand proComboSku = skuInfoComboMap.get(productId);
			PromotionSkuCommand proNotComboSku = skuInfoNotComboMap.get(productId);
			// 存在相同商品 数量 行总金额叠加
			if (null != proNotComboSku) {
				Integer qty = proNotComboSku.getProductQty();
				BigDecimal lineTotal = proNotComboSku.getLineTotal();
				proComboSku.setLineTotal(proComboSku.getLineTotal().add(lineTotal));
				proComboSku.setProductQty(proComboSku.getProductQty() + qty);
			}
			skuInfoNotComboMap.put(productId, proComboSku);
		}
		return skuInfoNotComboMap;
	}

	/**
	 * 查找是否存在标签
	 * @param productId
	 * @param shopId
	 * @param categoryCode
	 * @return
	 */
	private boolean checkCategoryNameIsExist(Long productId, Long shopId, String categoryCode) {
		// 根据商品查找对应标签
		List<ProductCategoryTag> tagList = productCategoryTagDao.findCategoryTagByProduct(productId, shopId,new BeanPropertyRowMapper<ProductCategoryTag>(ProductCategoryTag.class));
		if (null != tagList && tagList.size() > 0) {
			for (ProductCategoryTag tag : tagList) {
				String categoryName = tag.getCode();
				if (StringUtils.hasText(categoryCode) && categoryCode.equals(categoryName)) {
					return true;
				}
			}
		}
		return false;
	}


	
	/**
	 * 买赠商品组合
	 *
	 * @param skuMap
	 *            String（数量；是否组合商品；金额;标签号；）
	 * 
	 * @param skuInfoMap
	 *            String（数量；是否组合商品；金额;标签号；）
	 * @return 商品id 数量
	 */
	private Map<Long, Integer> reUnionSkuForBuy(Map<Long, Integer> skuMap, Map<Long, PromotionSkuCommand> skuInfoMap) {
		Set<Long> key = skuInfoMap.keySet();
		for (Iterator<Long> it = key.iterator(); it.hasNext();) {
			Long productId = (Long) it.next();
			PromotionSkuCommand proSku = skuInfoMap.get(productId);
			if (null != proSku) {
				Integer qty = proSku.getProductQty();
				// 组合商品中已经存在商品，则商品数量叠加
				if (null != skuMap && skuMap.get(productId) != null) {
					qty = qty + skuMap.get(productId);
				}
				skuMap.put(productId, qty);
			}
		}
		return skuMap;
	}


	private boolean salesOrderAddPromotion(Long promotionId, Long shopId, PromotionRule rule, List<PromotionGift> proGiftList,
										   Map<Long, PromotionGiftCommand> proGiftMap, PromotionType type, int times) {
		
		for(int i = times;i>=1;i--){
			// 赠品使用次数增加
			Integer updateNum  = promotionRuleDao.updateUsedGiftTimesByRuleIdAndTimes(rule.getId(), i, shopId);
			if(updateNum > 0){	
				//OK
				// 添加促销活动日志data
				for (PromotionGift promotionGift : proGiftList) {
					//为0的赠品是到商品的 
					if(NO_SKU_GIFT_TYPE.equals(promotionGift.getBindId())){
						PromotionGiftCommand proGiftData = new PromotionGiftCommand();
						Map<Long, String> proActiveMap = new HashMap<Long, String>();
						if (promotionGift.getProduct() != null) {
							//一单多次活动应用赠品，赠品出现重复行，合并计算，日志分开记录 fanht
							if (null != proGiftMap && proGiftMap.get(promotionGift.getProduct().getId()) != null) {
								proGiftData = proGiftMap.get(promotionGift.getProduct().getId());
								String dataStr = promotionGift.getGiftQty() * i + Constants.SEMICOLON + type.getValue();
								proActiveMap = proGiftData.getProActiveMap();
								if (null != proActiveMap) {
									String proDataStr = proActiveMap.get(promotionId);
									if (null != proDataStr && proDataStr.contains(Constants.SEMICOLON)) {
										String[] args = proDataStr.split(Constants.SEMICOLON);
										Integer giftQtyCount = Integer.parseInt(args[0]) + promotionGift.getGiftQty() * i;
										dataStr = giftQtyCount + Constants.SEMICOLON + type.getValue();
									}
								}
								proActiveMap.put(promotionId, dataStr);
								proGiftData.setProActiveMap(proActiveMap);
								//如果满足规则的有相同商品，取出目前此商品的数量，然后加上本地需要加上的数量放入赠品map
								Integer giftQty = proGiftData.getGiftQty();
								//赠品数量*赠送次数fanht
								proGiftData.setGiftQty(giftQty + promotionGift.getGiftQty() * i);// 设置赠品数量
								proGiftData.setId(promotionGift.getId()); // 设置赠品行ID
								proGiftData.setType(type); // 活动类型
								proGiftData.setBindId(promotionGift.getBindId());
								proGiftMap.put(promotionGift.getProduct().getId(), proGiftData);
								
							}else{
								String dataStr = promotionGift.getGiftQty()*i + Constants.SEMICOLON + type.getValue();
								proActiveMap.put(promotionId, dataStr);
								proGiftData.setProActiveMap(proActiveMap);
								proGiftData.setGiftQty(promotionGift.getGiftQty()*i); // 设置赠品数量
								proGiftData.setId(promotionGift.getId()); // 设置赠品行ID
								proGiftData.setType(type); // 活动类型
								proGiftData.setBindId(promotionGift.getBindId());
								proGiftMap.put(promotionGift.getProduct().getId(), proGiftData);
							}
						}
					}else{
							if (promotionGift.getProduct() != null) {
								//根据商品规则ID和shopId查询到该规则下的已赠送数量小于赠送总数量的具体sku列表
								List<PromotionGiftSku> giftSkuList = promotionGiftSkuDao.findBypromotionGiftId(promotionGift.getId(),promotionGift.getShopId());
								//根据符合规则的次数进行循环
								for(int k=0;k<i;k++){
									//根据商品每次要送的数量来锁定SKU要赠送的数量，每次减1
									for(int j=0;j<promotionGift.getGiftQty();j++){
										for(PromotionGiftSku Giftsku : giftSkuList){
											//进行线程抢占对最大数量大于已赠送数量的赠品sku进行update操作，成功的话，进行添加明细行操作，操作完毕此次list循环break,失败的话进行continue操作继续循环，直到成功。
											Integer updateGiftSkuNum  = promotionGiftSkuDao.updateUsedGiftNum(Giftsku.getId(), SKU_GIFT_NUM, shopId);
											PromotionGiftCommand proGiftData = new PromotionGiftCommand();
											Map<Long, String> proActiveMap = new HashMap<Long, String>();
											if(updateGiftSkuNum>0){
												//查看map里是否有重复的sku，因为每次数量都是1，如果有直接数量+1
												if (null != proGiftMap && proGiftMap.get(Giftsku.getSkuId()) != null) {
													//用skuId当做map里的key 主要用作同个SKU 数量合并
													proGiftData = proGiftMap.get(Giftsku.getSkuId());
													String dataStr = promotionGift.getGiftQty() * i  + Constants.SEMICOLON + type.getValue();
													proActiveMap = proGiftData.getProActiveMap();
													if (null != proActiveMap) {
														String proDataStr = proActiveMap.get(promotionId);
														if (null != proDataStr && proDataStr.contains(Constants.SEMICOLON)) {
															String[] args = proDataStr.split(Constants.SEMICOLON);
															Integer giftQtyCount = Integer.parseInt(args[0]) + promotionGift.getGiftQty() * i ;
															dataStr = giftQtyCount + Constants.SEMICOLON + type.getValue();
														}
													}
													//促销规则ID用作插入促销日志表
													proActiveMap.put(promotionId, dataStr);
													proGiftData.setProActiveMap(proActiveMap);
													//如果满足规则的有相同商品，取出目前此商品的数量，然后加上本地需要加上的数量放入赠品map
													Integer giftQty = proGiftData.getGiftQty();
													//赠品数量*赠送次数fanht
													proGiftData.setGiftQty(giftQty + Giftsku.getGiftQty());// 设置赠品数量
													proGiftData.setId(Giftsku.getId()); // 设置赠品行ID 重复proGiftMap 会覆盖之前的行ID 已确保唯一性 
													proGiftData.setType(type); // 活动类型
													proGiftData.setBindId(promotionGift.getBindId());
													proGiftData.setProductID(promotionGift.getProduct().getId());
													proGiftMap.put(Giftsku.getSkuId(), proGiftData);
													break;
													
												}else{
													//如果没有重复的sku，那么肯定是送1件。
													String dataStr = promotionGift.getGiftQty() * i + Constants.SEMICOLON + type.getValue();
													proActiveMap.put(promotionId, dataStr);
													proGiftData.setProActiveMap(proActiveMap);
													proGiftData.setGiftQty(SKU_GIFT_NUM); // 设置赠品数量
													proGiftData.setId(Giftsku.getId()); // 设置赠品行ID
													proGiftData.setType(type); // 活动类型
													//加入赠品商品中区别是否到赠品sku的ID，进map 用作后续根据此map校验
													proGiftData.setBindId(promotionGift.getBindId());
													proGiftData.setProductID(promotionGift.getProduct().getId());
													proGiftMap.put(Giftsku.getSkuId(), proGiftData);
													break;
												}
											}else{
												continue;
											}
										}
									}
								}
							}
					}
				}
				return true;
			}else{
				continue;
			}
		}
		
		return false;
	}

	
	/**
	 * 定时刷新（1小时刷新一次）
	 * 
	 * @return
	 */
	@Override
	public void refreshPromotionMap() {
		reloadAllPromotions();
	}
	
	@Override
	public List<PromotionActivityCommand2> queryPromotionCash(Long shopId){
		// 缓存中读取促销
    	Map<Long, List<PromotionActivityCommand2>> promotionMap = PromotionCashManagerImpl.getPromotionMap();
    	List<PromotionActivityCommand2> proDataList = promotionMap.get(shopId);
        return proDataList;
	}

	@Override
	public List<PromotionActivityCommand2> findAllPromotionActivityForShopAndTime(Long shopId, Date date) {
		 List<PromotionActivityCommand2> promotionMapTemp = new ArrayList<PromotionActivityCommand2>();
		//查询促销头信息 为促销起止时间大于设置的初始日期，结束时间大于 当前时间在往前推7天的时间，且状态是开启状态的。
		//涉及表t_ma_promotion_activity
		List<PromotionActivityCommand2> promotionAcList = promotionActivityDao.findAllPromotionActivityForShopAndTime(shopId, date);
//		//查出规则表所有的数据t_ma_promotion_rule
		//List<PromotionRule> promotionRuleList = promotionRuleDao.findAllRuleList();
		for (PromotionActivityCommand2 command : promotionAcList) {
			Long promotionId = command.getId();
			//用promotionId 和shopId 查 promotionRuleDao
			List<PromotionRule> ruleList = null;
			Map<Long, List<PromotionProduct>> productMap = new HashMap<Long, List<PromotionProduct>>();
			Map<Long, List<PromotionGift>> giftMap = new HashMap<Long, List<PromotionGift>>();
			ruleList = promotionRuleDao.findRuleList(promotionId, command.getShopId());
			if(ruleList.size()>0){
					for(PromotionRule bean : ruleList){
						//查出促销规则匹配到的主卖品
						productMap.put(bean.getId(), promotionProductDao.findByProductRuleId(bean.getId(),bean.getShopId()));
						//查出促销规则匹配到的赠品 TODO可能需要改
						giftMap.put(bean.getId(), promotionGiftDao.findByProductRuleId(bean.getId(),bean.getShopId()));
					}
	//			for (int i = 0; i < promotionRuleList.size(); i++) {
	//				PromotionRule rule = promotionRuleList.get(i);
	//				if (promotionId.equals(rule.getPromotion().getId())) {
	//					ruleList.add(rule);
	//					//查出促销规则匹配到的主卖品
	//					productMap.put(rule.getId(), promotionProductDao.findByProductRuleId(rule.getId(),command.getShopId()));
	//					//查出促销规则匹配到的赠品 TODO可能需要改
	//					giftMap.put(rule.getId(), promotionGiftDao.findByProductRuleId(rule.getId(),command.getShopId()));
	//					promotionRuleList.remove(i);
	//					i = i - 1;
	//				}
	//			}
					command.setRulesList(ruleList);
					command.setProductMap(productMap);
					command.setGiftMap(giftMap);
					if (null != command.getShopId()) {
						//把匹配到的促销头信息，还有促销赠品，规则信息按照shopId加入缓存
						promotionMapTemp.add(command);
					}
				}
			}
			return promotionMapTemp;
	}
}
