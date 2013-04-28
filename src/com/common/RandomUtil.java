package com.common;

import java.util.LinkedHashSet;
import java.util.Set;

public class RandomUtil {

	private Set<Integer> resultSet = new LinkedHashSet<Integer>();

	/**
	 * totalQuantity个数中随机quantity个数，且每个数不相等(范围0——totalQuantity),
	 * 如果totalQuantity<=quantity则totalQuantity<=quantity后再计算
	 */
	public RandomUtil(int totalQuantity, int quantity) {
		if (quantity > totalQuantity) {
			quantity = totalQuantity;
		}
		while (resultSet.size() < quantity) {
			resultSet.add((int) (Math.random() * totalQuantity));
		}
	}

	// public static void main(String[] args) {
	// RandomUtil r = new RandomUtil(3,3);
	// Set<Integer> mySet = r.resultSet;
	// for(Integer i:mySet)
	// {
	// System.out.print(i+"   ");
	// }
	// }

	public Set<Integer> getResultSet() {
		return resultSet;
	}

	public void setResultSet(Set<Integer> resultSet) {
		this.resultSet = resultSet;
	}

}
