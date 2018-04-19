package io.vitess.enums;

public enum SoSourceType {
    /** 1.天猫抓取 */
    TB(1),
    /** 2.客服导入 */
    IMPORT(2);
    
    private int value;
    
    private SoSourceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static SoSourceType valueOf(int value){
        switch (value) {
        case 1:
            return TB;
        case 2:
            return IMPORT;
        default:
            throw new IllegalArgumentException();
        }
    }
}
