package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "filter")
public class Filter implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String fileToFilter;
	private String sheetToFilter;
	private List<NarrowDownTo> narrowDownTo;

	/**
	 * @return the fileToFilter
	 */
	public String getFileToFilter() {
		return fileToFilter;
	}

	/**
	 * @param fileToFilter
	 *            the fileToFilter to set
	 */
	public void setFileToFilter(String fileToFilter) {
		this.fileToFilter = fileToFilter;
	}

	/**
	 * @return the sheetToFilter
	 */
	public String getSheetToFilter() {
		return sheetToFilter;
	}

	/**
	 * @param sheetToFilter
	 *            the sheetToFilter to set
	 */
	public void setSheetToFilter(String sheetToFilter) {
		this.sheetToFilter = sheetToFilter;
	}

	/**
	 * @return the narrowDownTo
	 */
	@XmlElementWrapper(name = "narrowDownTos")
	public List<NarrowDownTo> getNarrowDownTo() {
		return narrowDownTo;
	}

	/**
	 * @param narrowDownTo
	 *            the narrowDownTo to set
	 */
	public void setNarrowDownTo(List<NarrowDownTo> narrowDownTo) {
		this.narrowDownTo = narrowDownTo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Filter [fileToFilter=" + fileToFilter + ", sheetToFilter="
				+ sheetToFilter + ", narrowDownTo=" + narrowDownTo + "]";
	}

}
