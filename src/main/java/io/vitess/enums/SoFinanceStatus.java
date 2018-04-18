package io.vitess.enums;


public enum SoFinanceStatus {
    // 未付款
    UNPAID(1),
    // 支付定金
    PARTPAYMENT(2),
    // 全额付款
    FULLPAYMENT(3);

    private int value;

    private SoFinanceStatus(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static String getName(int value) {
    	if (value == 1) {
    		return "未付款";
    	} else if (value == 2) {
    		return "支付定金";
    	} else if (value == 3) {
    		return "全额付款";
    	}
    	return "";
    }

    public static SoFinanceStatus valueOf(int value) {
        switch (value) {
            case 1:
                return UNPAID;
            case 2:
                return PARTPAYMENT;
            case 3:
                return FULLPAYMENT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
