package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NarrowDownTo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7856249150057248605L;
	private String colToFilter;
	private List<String> valuesToFilter;
	private boolean filterColADate;
	private int filterColIndex = -1;
	private int rowCountColIndex = -1;

	/**
	 * @return the filterColIndex
	 */
	public int getFilterColIndex() {
		return filterColIndex;
	}

	/**
	 * @param filterColIndex the filterColIndex to set
	 */
	public void setFilterColIndex(int filterColIndex) {
		this.filterColIndex = filterColIndex;
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

	/**
	 * @return the colToFilter
	 */
	public String getColToFilter() {
		return colToFilter;
	}

	/**
	 * @param colToFilter
	 *            the colToFilter to set
	 */
	public void setColToFilter(String colToFilter) {
		this.colToFilter = colToFilter;
	}

	/**
	 * @return the valuesToFilter
	 */
	@XmlElementWrapper(name = "values")
	public List<String> getValuesToFilter() {
		return valuesToFilter;
	}

	/**
	 * @param valuesToFilter
	 *            the valuesToFilter to set
	 */
	public void setValuesToFilter(List<String> valuesToFilter) {
		this.valuesToFilter = valuesToFilter;
	}

	/**
	 * @return the filterColADate
	 */
	public boolean isFilterColADate() {
		return filterColADate;
	}

	/**
	 * @param filterColADate
	 *            the filterColADate to set
	 */
	public void setFilterColADate(boolean filterColADate) {
		this.filterColADate = filterColADate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NarrowDownTo [colToFilter=" + colToFilter + ", valuesToFilter="
				+ valuesToFilter + ", filterColADate=" + filterColADate
				+ ", filterColIndex=" + filterColIndex + ", rowCountColIndex="
				+ rowCountColIndex + "]";
	}

}
