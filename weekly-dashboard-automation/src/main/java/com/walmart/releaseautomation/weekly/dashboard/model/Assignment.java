package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Assignment implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String colToAssign;

	/**
	 * @return the colToAssign
	 */
	public String getColToAssign() {
		return colToAssign;
	}

	/**
	 * @param colToAssign
	 *            the colToAssign to set
	 */
	public void setColToAssign(String colToAssign) {
		this.colToAssign = colToAssign;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Assignment [colToAssign=" + colToAssign + "]";
	}
 
}
