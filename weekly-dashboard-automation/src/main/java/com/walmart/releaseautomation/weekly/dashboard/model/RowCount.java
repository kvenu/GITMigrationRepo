/**
 * 
 */
package com.walmart.releaseautomation.weekly.dashboard.model;

/**
 * @author l.naga rajesh
 * 
 */
public class RowCount {
	private String countFromCol;
	private String countForValues;
	private boolean storeCountInMap;
	private String countOnSheet;
	private int rowCountColIndex = -1;

	/**
	 * @return the countFromCol
	 */
	public String getCountFromCol() {
		return countFromCol;
	}

	/**
	 * @param countFromCol
	 *            the countFromCol to set
	 */
	public void setCountFromCol(String countFromCol) {
		this.countFromCol = countFromCol;
	}

	/**
	 * @return the countForValues
	 */
	public String getCountForValues() {
		return countForValues;
	}

	/**
	 * @param countForValues
	 *            the countForValues to set
	 */
	public void setCountForValues(String countForValues) {
		this.countForValues = countForValues;
	}

	/**
	 * @return the storeCountInMap
	 */
	public boolean isStoreCountInMap() {
		return storeCountInMap;
	}

	/**
	 * @param storeCountInMap the storeCountInMap to set
	 */
	public void setStoreCountInMap(boolean storeCountInMap) {
		this.storeCountInMap = storeCountInMap;
	}

	/**
	 * @return the countOnSheet
	 */
	public String getCountOnSheet() {
		return countOnSheet;
	}

	/**
	 * @param countOnSheet the countOnSheet to set
	 */
	public void setCountOnSheet(String countOnSheet) {
		this.countOnSheet = countOnSheet;
	}

	/**
	 * @return the rowCountColIndex
	 */
	public int getRowCountColIndex() {
		return rowCountColIndex;
	}

	/**
	 * @param rowCountColIndex the rowCountColIndex to set
	 */
	public void setRowCountColIndex(int rowCountColIndex) {
		this.rowCountColIndex = rowCountColIndex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RowCount [countFromCol=" + countFromCol + ", countForValues="
				+ countForValues + ", storeCountInMap=" + storeCountInMap
				+ ", countOnSheet=" + countOnSheet + ", rowCountColIndex="
				+ rowCountColIndex + "]";
	}

}
