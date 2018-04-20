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

package io.vitess.common;

import io.vitess.model.base.DefaultTransTempleteDetail;

public class DefaultTransTempleteDetailCommand extends DefaultTransTempleteDetail {

    /**
     * 
     */
    private static final long serialVersionUID = -2611628111600670734L;
    /**
     * 店铺编码
     */
    private String transName;
    private String expCode;
    private String dttCode;
    private String description;
    private Long transId;
    private String dttName;
    private String dttRemark;
    public String getTransName() {
        return transName;
    }
    public void setTransName(String transName) {
        this.transName = transName;
    }
    public String getExpCode() {
        return expCode;
    }
    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }
    public String getDttCode() {
        return dttCode;
    }
    public void setDttCode(String dttCode) {
        this.dttCode = dttCode;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getTransId() {
        return transId;
    }
    public void setTransId(Long transId) {
        this.transId = transId;
    }
    public String getDttName() {
        return dttName;
    }
    public void setDttName(String dttName) {
        this.dttName = dttName;
    }
    public String getDttRemark() {
        return dttRemark;
    }
    public void setDttRemark(String dttRemark) {
        this.dttRemark = dttRemark;
    }
    

}
