/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */

package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 系统常量表，用于记录常量列表或者系统常量设置，举例如下： 对于常量列表 布尔值， 可以如下设置： 常量集编码 常量集名称 常量键 常量值 bool 布尔值 true 真 bool 布尔值
 * false 假
 * 
 * 对于系统常量，如每页数据记录数，可以如下设置 常量集编码 常量集名称 常量键 常量值 system 系统常量 ITEM_PER_PAGE 50 其中
 * 系统常量[system]是系统预定义的常量集编码和名称
 * 
 * @author benjamin
 * 
 */

@Data
//@TableName("t_sys_choose_option")
public class ChooseOption extends SuperEntity{

    private static final long serialVersionUID = -8939064652943346455L;

    private String categoryCode;

    private String categoryName;

    private String packageName;

    private int sortNo;

    private String optionKey;

    private String optionValue;

    private Boolean isAvailable = true;

    private Boolean categoryAvailable;

    private String optionDescription;

}
