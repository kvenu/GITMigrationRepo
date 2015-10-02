package com.walmart.releaseautomation.weekly.dashboard.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeleteData {
	private String fromFile;
	private String fromSheet;
	private String[] columns;

	/**
	 * @return the fromFile
	 */
	public String getFromFile() {
		return fromFile;
	}

	/**
	 * @param fromFile
	 *            the fromFile to set
	 */
	public void setFromFile(String fromFile) {
		this.fromFile = fromFile;
	}

	/**
	 * @return the fromSheet
	 */
	public String getFromSheet() {
		return fromSheet;
	}

	/**
	 * @param fromSheet
	 *            the fromSheet to set
	 */
	public void setFromSheet(String fromSheet) {
		this.fromSheet = fromSheet;
	}

	/**
	 * @return the columns
	 */
	public String[] getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(String[] columns) {
		this.columns = columns;
	}

}
