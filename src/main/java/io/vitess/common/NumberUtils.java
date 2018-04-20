package io.vitess.common;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class NumberUtils {
	
    /** 除以100 */
    public static BigDecimal calcExtVc(BigDecimal vc) {
        if (vc == null) {
            return BigDecimal.ZERO;
        }
        
        return vc.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN);
    }
    
	/**
	 * 检查字符串是否为数字
	 * @param text
	 * @return
	 */
	public static final boolean isNumber(String text) {
		if (!StringUtils.hasText(text)) {
			return false;
		}
		
		boolean checkResult = true;
		try {
			new BigDecimal(text);
		} catch (NumberFormatException e) {
			checkResult = false;
		}
		
		return checkResult;
	}
	
	/**
	 * 转换字符串为数字
	 * @param text
	 * @return 若转换失败返回BigDecimal.ZERO
	 */
	public static final BigDecimal createBigDecimal(String text) {
		if (text == null || !isNumber(text)) {
			return null;
		}
		return new BigDecimal(text);
	}
	
	/**
	 * 转换字符串为数字
	 * @param text
	 * @return 若转换失败返回BigDecimal.ZERO
	 */
	public static final BigDecimal convertToBigDecimal(String text) {
		if (!isNumber(text)) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(text);
	}
	
	/**
	 * 转换字符串为整数
	 * @param text
	 * @return 如果转换失败，返回null
	 */
	public static final Integer createInteger(String text) {
		if (!isNumber(text)) {
			return null;
		}
		Integer result = null;
		try {
			result = Integer.parseInt(text);
		} catch (NumberFormatException nfe) {
			result = null;
		}
		return result;
	}
	
	public static final BigDecimal divide(Integer divisor, Integer dividend) {
		String divisorStr = divisor == null ? "" : "" + divisor.intValue();
		String dividendStr = dividend == null ? "" : "" + dividend.intValue();
		return divide(divisorStr, dividendStr);
	}
	
	/**
	 * 两数相除
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static final BigDecimal divide(String divisor, String dividend) {
		BigDecimal divisorBigDecimal = convertToBigDecimal(divisor);
		if (divisorBigDecimal.intValue() == BigDecimal.ZERO.intValue()) {
			return BigDecimal.ZERO;
		}
		BigDecimal dividendBigDecimal = convertToBigDecimal(dividend);
		return dividendBigDecimal.divide(divisorBigDecimal, RoundingMode.HALF_UP);
	}
	
	/**
	 * 两数相乘
	 * @param num
	 * @param multiplicand
	 * @return
	 */
	public static final BigDecimal multiply(String num, String multiplicand) {
		if (!isNumber(num) || !isNumber(multiplicand)) {
			return null;
		}
		
		return multiply(convertToBigDecimal(num), convertToBigDecimal(multiplicand));
	}
	
	/**
	 * 两数相乘
	 * @param num
	 * @param multiplicand
	 * @return
	 */
	public static final BigDecimal multiply(BigDecimal num, BigDecimal multiplicand) {
		if (num == null || multiplicand == null) {
			return null;
		}
		return num.multiply(multiplicand);
	}

}
