package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "update")
public class Update implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String colToUpdate;
	private String valueToUpdate;
	private String fileToUpdate;
	private String sheetToUpdate;
	private boolean updateRowCntFromMap;
	private int updateColIndex = -1;

	/**
	 * @return the colToUpdate
	 */
	public String getColToUpdate() {
		return colToUpdate;
	}

	/**
	 * @param colToUpdate
	 *            the colToUpdate to set
	 */
	public void setColToUpdate(String colToUpdate) {
		this.colToUpdate = colToUpdate;
	}

	/**
	 * @return the valueToUpdate
	 */
	public String getValueToUpdate() {
		return valueToUpdate;
	}

	/**
	 * @param valueToUpdate
	 *            the valueToUpdate to set
	 */
	public void setValueToUpdate(String valueToUpdate) {
		this.valueToUpdate = valueToUpdate;
	}

	/**
	 * @return the fileToUpdate
	 */
	public String getFileToUpdate() {
		return fileToUpdate;
	}

	/**
	 * @param fileToUpdate
	 *            the fileToUpdate to set
	 */
	public void setFileToUpdate(String fileToUpdate) {
		this.fileToUpdate = fileToUpdate;
	}

	/**
	 * @return the sheetToUpdate
	 */
	public String getSheetToUpdate() {
		return sheetToUpdate;
	}

	/**
	 * @param sheetToUpdate
	 *            the sheetToUpdate to set
	 */
	public void setSheetToUpdate(String sheetToUpdate) {
		this.sheetToUpdate = sheetToUpdate;
	}

	/**
	 * @return the updateRowCntFromMap
	 */
	public boolean isUpdateRowCntFromMap() {
		return updateRowCntFromMap;
	}

	/**
	 * @param updateRowCntFromMap
	 *            the updateRowCntFromMap to set
	 */
	public void setUpdateRowCntFromMap(boolean updateRowCntFromMap) {
		this.updateRowCntFromMap = updateRowCntFromMap;
	}

	/**
	 * @return the updateColIndex
	 */
	public int getUpdateColIndex() {
		return updateColIndex;
	}

	/**
	 * @param updateColIndex the updateColIndex to set
	 */
	public void setUpdateColIndex(int updateColIndex) {
		this.updateColIndex = updateColIndex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Update [colToUpdate=" + colToUpdate + ", valueToUpdate="
				+ valueToUpdate + ", fileToUpdate=" + fileToUpdate
				+ ", sheetToUpdate=" + sheetToUpdate + ", updateRowCntFromMap="
				+ updateRowCntFromMap + ", updateColIndex=" + updateColIndex
				+ "]";
	}

}
