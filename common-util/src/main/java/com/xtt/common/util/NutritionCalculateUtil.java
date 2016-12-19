/**   营养相关指数计算类
 * @Title: NutritionCalculate.java 
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年10月25日 下午3:09:14 
 *
 */
package com.xtt.common.util;

import java.math.BigDecimal;

public class NutritionCalculateUtil {
	/** -------------身高测量方式------------ */
	/** 身高测量方式(直接测量) */
	public static final String STATURE_MEASURE_WAY_DIRECT = "1";
	/** 身高测量方式(间接测量) */
	public static final String STATURE_MEASURE_WAY_INDIRECT_ALLPARTS = "2";
	/** 身高测量方式(膝高测量方式) */
	public static final String STATURE_MEASURE_WAY_KNEE = "3";
	/** -------------人体体表面积------------ */
	/** 体表面积计算公式(DuBois) */
	public static final String BSA_FORMULA_DUBOIS = "1";
	/** 体表面积计算公式(赵松山公式) */
	public static final String BSA_FORMULA_ZSS = "2";
	/** -------------标准体重计算------------ */
	/** 标准体重计算公式(标准) */
	public static final String STANDARD_WEIGHT_FORMULA_STANDARD = "1";
	/** 标准体重计算公式(改良版) */
	public static final String STANDARD_WEIGHT_FORMULA_IMPROVED = "2";

	/** 是否是男性 */
	private static boolean isMan(String sex) {
		return "M".equals(sex) ? true : false;
	}

	/**
	 * 根据身高测量法获取身高的值
	 * 
	 * @Title: CalculateStature
	 * @param formulaType
	 *            计算器类型（选择使用某种计算器）
	 * @param measureWay
	 *            测量方式
	 * @param measureValue
	 *            测量结果
	 * @param age
	 *            年龄
	 * @param sex
	 *            性别
	 * @return
	 *
	 */
	public static BigDecimal CalculateStature(String measureWay, BigDecimal[] measureValues, int age, String sex) {
		BigDecimal value = BigDecimal.ZERO;
		if (measureValues == null || measureValues.length == 0)
			return value;
		if (STATURE_MEASURE_WAY_KNEE.equals(measureWay)) {
			// 男性身高（cm）=62.59-[0.01×年龄（岁）]÷[2.09×膝高（cm）]
			// 女性身高（cm）=69.28-[0.02×年龄（岁）]÷[1.50×膝高（cm）]
			if (isMan(sex)) {
				value = new BigDecimal(62.59 - (0.01 * age) / (2.09 * measureValues[0].doubleValue()));
			} else {
				value = new BigDecimal(69.28 - (0.02 * age) / (1.50 * measureValues[0].doubleValue()));
			}
		} /*else if (STATURE_MEASURE_WAY_INDIRECT_ALLPARTS.equals(measureWay)) {
			// 测定腿、足跟、骨盆、脊柱和头颅的长度，各部分长度之和
			for (int i = 0; i < measureValues.length; i++) {
				value = value.add(measureValues[i]);
			}
			}*/ else {
			value = measureValues[0];
		}
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);// 默认保留两位小数
		return value;
	}

	/**
	 * 计算身体质量指数
	 * 
	 * @Title: CalculateBMI 公式类别
	 * @param weight
	 *            体重(kg)
	 * @param stature
	 *            身高(cm)
	 * @return
	 *
	 */
	public static BigDecimal CalculateBMI(BigDecimal weight, BigDecimal stature) {
		BigDecimal value = BigDecimal.ZERO;
		if (weight == null || stature == null || BigDecimal.ZERO.equals(stature)) {
			return value;
		}
		// 体重kg/(身高cm/100)2
		// 默认保留两位小数
		value = weight.divide(stature.divide(new BigDecimal(100)).pow(2), 2, BigDecimal.ROUND_HALF_UP);
		return value;
	}

	/**
	 * 计算人体体表面积（m2）
	 * 
	 * @Title: CalculateBSA
	 * @param formulaType
	 *            公式类型
	 * @param weight
	 *            体重(kg)
	 * @param stature
	 *            身高(cm)
	 * @param sex性别
	 * @return
	 *
	 */
	public static BigDecimal CalculateBSA(String formulaType, BigDecimal weight, BigDecimal stature, String sex) {
		BigDecimal value = BigDecimal.ZERO;
		if (weight == null || stature == null || BigDecimal.ZERO.equals(stature)) {
			return value;
		}
		if (BSA_FORMULA_ZSS.equals(formulaType)) {
			// 小于30kg的使用小儿体表面积计算公式
			// 男性成年=0.00607x身高（cm）+0.0127x体重（kg）-0.0698
			// 女性成年=0.00586x身高（cm）+0.0126x体重（kg）-0.0461
			// 小儿=0.0061x身高（cm）+0.0128x体重（kg）-0.1529
			if (new BigDecimal(30).compareTo(weight) > 1) {
				value = new BigDecimal(0.0061 * stature.doubleValue() + 0.0128 * weight.doubleValue() - 0.1529);
			} else {
				if (isMan(sex)) {
					value = new BigDecimal(0.00607 * stature.doubleValue() + 0.0127 * weight.doubleValue() - 0.0698);
				} else {
					value = new BigDecimal(0.00586 * stature.doubleValue() + 0.0126 * weight.doubleValue() - 0.0461);
				}
			}
		} else if (BSA_FORMULA_DUBOIS.equals(formulaType)) {
			// BSA=0.007184×体重^0.425(kg)×身高^0.725(cm)
			value = new BigDecimal(0.007184 * Math.pow(weight.floatValue(), 0.425) * Math.pow(stature.floatValue(), 0.725));
		}
		// 默认保留两位小数
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return value;
	}

	/**
	 * 上臂肌围
	 * 
	 * @Title: CalculateMAMC
	 * @param mac
	 *            上臂围(cm)
	 * @param tsf
	 *            三头肌皮褶厚度(cm)
	 * @return MAMC(cm)
	 *
	 */
	public static BigDecimal CalculateMAMC(BigDecimal mac, BigDecimal tsf) {
		BigDecimal value = BigDecimal.ZERO;
		if (mac == null || tsf == null) {
			return value;
		}
		// MAMC(cm)=上臂围mac(cm)-3.14×TSF(cm)
		value = mac.subtract(new BigDecimal(3.14).multiply(tsf));
		// 默认保留两位小数
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return value;
	}

	/**
	 * 腰臀比
	 * 
	 * @Title: CalculateWHR
	 * @param waist
	 *            腰围(cm）
	 * @param hip
	 *            臀围（cm）
	 * @return 返回百分比，如：（60.68）；
	 *
	 */
	public static BigDecimal CalculateWHR(BigDecimal waist, BigDecimal hip) {
		BigDecimal value = BigDecimal.ZERO;
		if (waist == null || hip == null || BigDecimal.ZERO.equals(hip)) {
			return value;
		}
		// WHR=waist/hip
		// 默认保留两位小数
		value = waist.divide(hip, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return value;
	}

	/**
	 * 计算标准体重
	 * 
	 * @Title: CalculateWHR
	 * @param formulaType
	 * @param stature
	 *            身高(cm)
	 * @return
	 *
	 */
	public static BigDecimal CalculateStandardWeight(String formulaType, BigDecimal stature) {
		BigDecimal value = BigDecimal.ZERO;
		if (stature == null) {
			return value;
		}
		// Broca公式：标准体重（kg）=身高（cm）-100（国外）
		// Broca改良公式：标准体重（kg）=身高（cm）-105（我国）
		if (STANDARD_WEIGHT_FORMULA_STANDARD.equals(formulaType)) {
			value = stature.subtract(new BigDecimal(100));
		} else if (STANDARD_WEIGHT_FORMULA_IMPROVED.equals(formulaType)) {
			value = stature.subtract(new BigDecimal(105));
		}
		return value;
	}

	/**
	 * 体重比计算
	 * 
	 * @Title: CalculateWeightRatio
	 * @param actual
	 *            实际体重(kg)
	 * @param standard
	 *            根据身高计算的标准体重(kg)
	 * @return 返回百分比，如：（60.68）；
	 */
	public static BigDecimal CalculateWeightRatio(BigDecimal actual, BigDecimal standard) {
		if (actual == null || standard == null || BigDecimal.ZERO.equals(standard)) {
			return null;
		}
		// (实际体重-标准体重）÷同身高标准体重
		BigDecimal value = new BigDecimal((actual.floatValue() - standard.floatValue()) / standard.floatValue() * 100.0);
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return value;
	}

	/**
	 * 体重丢失率
	 * 
	 * @Title: CalculateWeightChange
	 * @param original
	 *            原来的体重(kg)
	 * @param now
	 *            当前的体重(kg)
	 * @return 返回百分比，如：（60.68）；
	 */
	public static BigDecimal CalculateWeightChange(BigDecimal original, BigDecimal now) {
		if (original == null || now == null || BigDecimal.ZERO.equals(original)) {
			return null;
		}
		// 体重丢失率（%）=（原体重-现体重）/原体重×100%
		BigDecimal value = new BigDecimal((original.floatValue() - now.floatValue()) / original.floatValue() * 100.0);
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return value;
	}
}
