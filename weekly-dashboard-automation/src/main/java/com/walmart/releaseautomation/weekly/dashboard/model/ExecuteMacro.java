package com.walmart.releaseautomation.weekly.dashboard.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExecuteMacro {
	private String onFile;
	private String onSheet;
	private String macroName;
	private String scriptName;
	private List<String> params;

	/**
	 * @return the onFile
	 */
	public String getOnFile() {
		return onFile;
	}

	/**
	 * @param onFile
	 *            the onFile to set
	 */
	public void setOnFile(String onFile) {
		this.onFile = onFile;
	}

	/**
	 * @return the onSheet
	 */
	public String getOnSheet() {
		return onSheet;
	}

	/**
	 * @param onSheet
	 *            the onSheet to set
	 */
	public void setOnSheet(String onSheet) {
		this.onSheet = onSheet;
	}

	/**
	 * @return the macroName
	 */
	public String getMacroName() {
		return macroName;
	}

	/**
	 * @param macroName
	 *            the macroName to set
	 */
	public void setMacroName(String macroName) {
		this.macroName = macroName;
	}

	/**
	 * @return the scriptName
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * @param scriptName the scriptName to set
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * @return the params
	 */
	public List<String> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<String> params) {
		this.params = params;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExecuteMacro [onFile=" + onFile + ", onSheet=" + onSheet
				+ ", macroName=" + macroName + ", scriptName=" + scriptName
				+ ", params=" + params + "]";
	}

}
