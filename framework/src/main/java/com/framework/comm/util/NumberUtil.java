package com.framework.comm.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Pattern;

 
/**
 * 
 * 接口或类的说明: 处理数字的工具
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
public class NumberUtil {
	/**
	 * 根据长度获取随机数据
	 * 
	 * @param len
	 *            长度
	 * @return
	 */
	public static String randomNum(Integer len) {
		int num = 0;
		try {
			num = (int) (Math.random() * 9 * Math.pow(10, (len - 1))) + (int) (Math.pow(10, (len - 1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(num);
	}

	/**
	 * 转换成价格的格式 ( 如: 99.00 )
	 * 
	 * @return
	 */
	public static String convert2price(BigDecimal bigDecimal) {
		if (bigDecimal == null)
			return null;
		NumberFormat formater = NumberFormat.getInstance();
		formater.setMaximumFractionDigits(2);
		formater.setMinimumFractionDigits(2);
		return formater.format(bigDecimal);
	}

	/**
	 * 判断一个字符串是否是数字
	 * 
	 * @param numStr
	 * @return
	 */
	public static boolean isNum(String numStr) {
		if (numStr == null)
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(numStr).matches();
	}

	/**
	 * 判断两个给定的数字区间是否有交集，有则返回true
	 * 
	 * @param one
	 * @param theOther
	 * @return
	 */
	public static boolean hasIntersection(Interval one, Interval theOther) {
		if (oneInTheOther(one, theOther))
			return true;
		if (oneInTheOther(theOther, one))
			return true;
		return false;
	}

	/**
	 * 判断一个区间（one）是否有一点（左或右
	 * 
	 * @param one
	 * @param theOther
	 * @return
	 */
	private static boolean oneInTheOther(Interval one, Interval theOther) {
		if (one.contains(theOther.left)) { // 一个区间的左点落在另一个区间中
			if (one.isLeftClosed() && !theOther.isLeftClosed() && one.left.equals(theOther.left))
				return false;
			if (one.isRightClosed() && !theOther.isLeftClosed() && one.right.equals(theOther.left))
				return false;
			return true;
		}
		if (one.contains(theOther.right)) { // 一个区间的右点落在另一个区间中
			if (one.isLeftClosed() && !theOther.isRightClosed() && one.left.equals(theOther.right))
				return false;
			if (one.isRightClosed() && !theOther.isRightClosed() && one.right.equals(theOther.right))
				return false;
			return true;
		}
		return false;
	}

	/**
	 *
	 * 接口或类的说明: 数字区间
	 *
	 * <br>
	 * ========================== <br>
	 * 公司：南京壹号家信息科技有限公司 <br>
	 * 开发：yisheng.lu@yihaojiaju.com <br>
	 * 版本：1.0 <br>
	 * 创建时间：2012-11-7 上午9:45:04 <br>
	 * ==========================
	 *
	 */
	public static class Interval {
		/**
		 * 左区间
		 */
		private final BigDecimal left;
		/**
		 * 标识左区间是否闭区间
		 */
		private final boolean leftClosed;
		/**
		 * 右区间
		 */
		private final BigDecimal right;
		/**
		 * 标识右区间是否闭区间
		 */
		private final boolean rightClosed;

		public Interval(BigDecimal left, boolean leftClosed, BigDecimal right, boolean rightClosed) {
			this.left = left;
			this.leftClosed = leftClosed;
			this.right = right;
			this.rightClosed = rightClosed;
		}

		/**
		 * 判断给定的数字是否落在区间中
		 * 
		 * @param number
		 * @return
		 */
		public boolean contains(BigDecimal number) {
			boolean rightToLeft; // 标识给定数字是否在左区间右边
			if (leftClosed)
				rightToLeft = (number.compareTo(left) >= 0);
			else
				rightToLeft = (number.compareTo(left) > 0);

			boolean leftToRight; // 标识给定数字是否在右区间左边
			if (rightClosed)
				leftToRight = (number.compareTo(right) <= 0);
			else
				leftToRight = (number.compareTo(right) < 0);

			return rightToLeft && leftToRight;
		}

		public BigDecimal getLeft() {
			return left;
		}

		public boolean isLeftClosed() {
			return leftClosed;
		}

		public BigDecimal getRight() {
			return right;
		}

		public boolean isRightClosed() {
			return rightClosed;
		}

	}

	private NumberUtil() {

	}

	public static void main(String[] args) {
		System.out.println(convert2price(new BigDecimal("0")));

		Interval intervalA = new Interval(new BigDecimal(1), true, new BigDecimal(5), true); // [1,5]
		BigDecimal number = new BigDecimal(5);
		System.out.println(number + " is " + (intervalA.contains(number) ? "" : "not") + " in [1,5]");

		Interval intervalB = new Interval(new BigDecimal(0), true, new BigDecimal(10), true); // (5,10]

		System.out.println(NumberUtil.hasIntersection(intervalA, intervalB));
	}
}
