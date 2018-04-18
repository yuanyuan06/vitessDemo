/**
 * 、 * Copyright (c) 2010 Jumbomart All Rights Reserved.
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

package io.vitess.enums;

public enum SlipType {
	PURCHASE_ORDER(1), // 采购单
	SALES_ORDER(2), // 销售单
	RETURN_REQUEST(3), // 退换货申请
	RA_EXCHANGE_OUT(6), // 换货出库
	REFUND_REQUEST(5), // 退款申请
	OUT_RETURN_REQUEST(4),
	CLAIM(7); // 索赔

	private int value;

	private SlipType(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}
	
	public static String getName(SlipType slipType) {
		switch (slipType.getValue()) {
		case 1:
			return "采购单";
		case 2:
			return "销售单";
		case 3:
			return "退换货申请";
		case 4:
			return "外部退换货申请";
		case 5:
			return "退款申请";
		case 6:
		    return "换货出库";
        case 7:
            return "索赔单";
		default:
			throw new IllegalArgumentException();
		}
	}

	public static SlipType valueOf(int value){
		switch (value) {
		case 1:
			return PURCHASE_ORDER;
		case 2:
			return SALES_ORDER;
		case 3:
			return RETURN_REQUEST;
		case 4:
			return OUT_RETURN_REQUEST;
		case 5:
			return REFUND_REQUEST;
		case 6:
		    return RA_EXCHANGE_OUT;
        case 7:
            return CLAIM;
		default:
			throw new IllegalArgumentException();
		}
	}
}
