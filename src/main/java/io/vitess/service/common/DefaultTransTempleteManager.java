package io.vitess.service.common;

import io.vitess.command.TransReginTemplateCommand;
import io.vitess.common.DefaultTransTempleteDetailCommand;
import io.vitess.model.base.DefaultTransTemplete;
import io.vitess.service.BaseManager;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 物流推荐模板
 * @author fanht
 *
 */
public interface DefaultTransTempleteManager extends BaseManager {
	
    String EXCEL_DTT_LIST = "excel/tpl_default_trans_templete.xls";
    
    String EXCEL_RTR_LIST = "excel/tpl_trans_regin_templete.xls";

    DefaultTransTemplete findDefaultTransTemplete(Long tempId);
    
    List<DefaultTransTempleteDetailCommand> findDTTDByTempId(Long tempId);
    
}
