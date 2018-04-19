package io.vitess.constants;

/**
 * 0, 付款经销
 * 1, 代销
 * 2, 结算经销
 * 3, 结算经销+代销
 * 4, 付款经销+结算经销
 * @author Administrator
 */
public class SalesModelToTOMS {
	public static String SKU_SALES_MODEL = "skuSalesModel";
	public static String SHOP_SALES_MODEL = "shopSalesModel";
	public static Integer SaleModelMapping(String type, String pacKey){
		if(SKU_SALES_MODEL.equals(type) ){
			if(PacsConstants.PAYMENT_FOR_SKU.equals(pacKey)){
				return 0;
			}else if(PacsConstants.CONSIGNMENT_FOR_SKU.equals(pacKey)){
				return 1;
			}else if(PacsConstants.SETTLEMENT_FOR_SKU.equals(pacKey)){
				return 2;
			}else if(PacsConstants.SETTLEMENT_OR_CONSIGNMENT_FOR_SKU.equals(pacKey)){
				return 3;
			}else{
				throw new IllegalArgumentException();
			}
		}else if(SHOP_SALES_MODEL.equals(type)){
			if(PacsConstants.PAYMENT.equals(pacKey)){
				return 0;
			}else if(PacsConstants.CONSIGNMENT.equals(pacKey)){
				return 1;
			}else if(PacsConstants.SETTLEMENT.equals(pacKey)){
				return 2;
			}else if(PacsConstants.PAYMENT_OR_SETTLEMENT.equals(pacKey)){
				return 4;
			}else{
				throw new IllegalArgumentException();
			}
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	public static String mappingSalesModel(int modelNum){
		switch(modelNum){
		case 0:
			return "0";
		case 1:
			return "1";
		case 2:
			return "2";
		case 3:
			return "1,2";
		case 4:
			return "0,2";
		default:
			throw new IllegalArgumentException();
		}
	}
}