package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ForEach implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private ForEach forEach;
	private String column;
	private String forEachValue;

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
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @return the forEachValue
	 */
	public String getForEachValue() {
		return forEachValue;
	}

	/**
	 * @param forEachValue
	 *            the forEachValue to set
	 */
	public void setForEachValue(String forEachValue) {
		this.forEachValue = forEachValue;
	}

}
