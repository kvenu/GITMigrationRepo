package com.walmart.releaseautomation.weekly.dashboard.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CopyData {
	private String copyFromFile;
	private String copyFromSheet;
	private String[] copyFromColumns;
	private String pasteToFile;
	private String pasteToSheet;
	private int pasteFromColumnIndex;

	/**
	 * @return the copyFromFile
	 */
	public String getCopyFromFile() {
		return copyFromFile;
	}

	/**
	 * @param copyFromFile
	 *            the copyFromFile to set
	 */
	public void setCopyFromFile(String copyFromFile) {
		this.copyFromFile = copyFromFile;
	}

	/**
	 * @return the pasteToFile
	 */
	public String getPasteToFile() {
		return pasteToFile;
	}

	/**
	 * @param pasteToFile
	 *            the pasteToFile to set
	 */
	public void setPasteToFile(String pasteToFile) {
		this.pasteToFile = pasteToFile;
	}

	/**
	 * @return the pasteFromColumnIndex
	 */
	public int getPasteFromColumnIndex() {
		return pasteFromColumnIndex;
	}

	/**
	 * @param pasteFromColumnIndex
	 *            the pasteFromColumnIndex to set
	 */
	public void setPasteFromColumnIndex(int pasteFromColumnIndex) {
		this.pasteFromColumnIndex = pasteFromColumnIndex;
	}

	/**
	 * @return the copyFromSheet
	 */
	public String getCopyFromSheet() {
		return copyFromSheet;
	}

	/**
	 * @param copyFromSheet
	 *            the copyFromSheet to set
	 */
	public void setCopyFromSheet(String copyFromSheet) {
		this.copyFromSheet = copyFromSheet;
	}

	/**
	 * @return the copyFromColumns
	 */
	public String[] getCopyFromColumns() {
		return copyFromColumns;
	}

	/**
	 * @param copyFromColumns
	 *            the copyFromColumns to set
	 */
	public void setCopyFromColumns(String[] copyFromColumns) {
		this.copyFromColumns = copyFromColumns;
	}

	/**
	 * @return the pasteToSheet
	 */
	public String getPasteToSheet() {
		return pasteToSheet;
	}

	/**
	 * @param pasteToSheet
	 *            the pasteToSheet to set
	 */
	public void setPasteToSheet(String pasteToSheet) {
		this.pasteToSheet = pasteToSheet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CopyData [copyFromFile=" + copyFromFile + ", copyFromSheet="
				+ copyFromSheet + ", copyFromColumns="
				+ Arrays.toString(copyFromColumns) + ", pasteToFile="
				+ pasteToFile + ", pasteToSheet=" + pasteToSheet
				+ ", pasteFromColumnIndex=" + pasteFromColumnIndex + "]";
	}

}
