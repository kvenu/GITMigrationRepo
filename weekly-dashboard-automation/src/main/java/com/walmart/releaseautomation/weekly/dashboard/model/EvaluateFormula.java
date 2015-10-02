package com.walmart.releaseautomation.weekly.dashboard.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EvaluateFormula {
	private String fileToFilter;
	private String fromSheet;
	private Cell[] cell;

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
	 * @return the cell
	 */
	public Cell[] getCell() {
		return cell;
	}

	/**
	 * @param cell
	 *            the cell to set
	 */
	public void setCell(Cell[] cell) {
		this.cell = cell;
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
		return "EvaluateFormula [fileToFilter=" + fileToFilter + ", fromSheet="
				+ fromSheet + ", cell=" + Arrays.toString(cell) + "]";
	}

}
