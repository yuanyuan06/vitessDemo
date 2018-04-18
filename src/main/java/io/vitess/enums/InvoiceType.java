package io.vitess.enums;

/**
 * 发票类型
 * @author 李光辉
 * @date 2014年7月4日 下午4:06:37
 * @since
 */
public enum InvoiceType {
    /** 1,普通商业零售发票 */
    ORDINARY_COMMERCIAL(1, "普通商业零售发票"),
    /** 2,增值税专用发票 */
    VAT(2, "增值税专用发票");
    
    private int value;
    private String name;

    private InvoiceType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static InvoiceType valueOf(int value) {
        switch (value) {
            case 1:
                return ORDINARY_COMMERCIAL;
            case 2:
                return VAT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
