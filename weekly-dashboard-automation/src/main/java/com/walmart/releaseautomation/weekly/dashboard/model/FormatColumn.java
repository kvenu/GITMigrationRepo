package com.walmart.releaseautomation.weekly.dashboard.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormatColumn {
	private String fileToFilter;
	private String sheet;
	private String[] onColumns;
	private String cellTypeformat;

	/**
	 * @return the cellTypeformat
	 */
	public String getCellTypeformat() {
		return cellTypeformat;
	}

	/**
	 * @param cellTypeformat
	 *            the cellTypeformat to set
	 */
	public void setCellTypeformat(String cellTypeformat) {
		this.cellTypeformat = cellTypeformat;
	}

	/**
	 * @return the sheet
	 */
	public String getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the onColumns
	 */
	public String[] getOnColumns() {
		return onColumns;
	}

	/**
	 * @param onColumns the onColumns to set
	 */
	public void setOnColumns(String[] onColumns) {
		this.onColumns = onColumns;
	}

	/**
	 * @return the fileToFilter
	 */
	public String getFileToFilter() {
		return fileToFilter;
	}

	/**
	 * @param fileToFilter the fileToFilter to set
	 */
	public void setFileToFilter(String fileToFilter) {
		this.fileToFilter = fileToFilter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormatColumn [fileToFilter=" + fileToFilter + ", sheet="
				+ sheet + ", onColumns=" + Arrays.toString(onColumns)
				+ ", cellTypeformat=" + cellTypeformat + "]";
	}

}
