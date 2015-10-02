package com.walmart.releaseautomation.weekly.dashboard.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cell {
	private String header;
	private String formula;
	private String cellType;
	private List<String> addRefLinks;

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * @param formula
	 *            the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * @return the cellType
	 */
	public String getCellType() {
		return cellType;
	}

	/**
	 * @param cellType
	 *            the cellType to set
	 */
	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

	/**
	 * @return the addRefLinks
	 */
	public List<String> getAddRefLinks() {
		return addRefLinks;
	}

	/**
	 * @param addRefLinks the addRefLinks to set
	 */
	public void setAddRefLinks(List<String> addRefLinks) {
		this.addRefLinks = addRefLinks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell [header=" + header + ", formula=" + formula
				+ ", cellType=" + cellType + ", addRefLinks=" + addRefLinks
				+ "]";
	}
}
