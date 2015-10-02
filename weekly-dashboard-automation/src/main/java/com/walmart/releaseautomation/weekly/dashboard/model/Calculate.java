package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Calculate implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private ForEach forEach;
	private boolean calcRowsCount;

	/**
	 * @return the forEach
	 */
	public ForEach getForEach() {
		return forEach;
	}

	/**
	 * @param forEach
	 *            the forEach to set
	 */
	public void setForEach(ForEach forEach) {
		this.forEach = forEach;
	}

	/**
	 * @return the calcRowsCount
	 */
	public boolean isCalcRowsCount() {
		return calcRowsCount;
	}

	/**
	 * @param calcRowsCount
	 *            the calcRowsCount to set
	 */
	public void setCalcRowsCount(boolean calcRowsCount) {
		this.calcRowsCount = calcRowsCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Calculate [forEach=" + forEach + ", calcRowsCount="
				+ calcRowsCount + "]";
	}

}
