package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rule")
public class Rule implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private Filter filter;
	private List<Update> update;
	private List<Assignment> assignment;
	private Calculate calculate;
	private CopyData copyData;
	private DeleteData deleteData;
	private EvaluateFormula evaluateFormula;
	private ExecuteMacro executeMacro;
	private FormatColumn formatColumn;
	private RowCount count;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the filter
	 */
	public Filter getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	/**
	 * @return the calculate
	 */
	public Calculate getCalculate() {
		return calculate;
	}

	/**
	 * @param calculate
	 *            the calculate to set
	 */
	public void setCalculate(Calculate calculate) {
		this.calculate = calculate;
	}

	/**
	 * @return the copyData
	 */
	public CopyData getCopyData() {
		return copyData;
	}

	/**
	 * @param copyData
	 *            the copyData to set
	 */
	public void setCopyData(CopyData copyData) {
		this.copyData = copyData;
	}

	/**
	 * @return the deleteData
	 */
	public DeleteData getDeleteData() {
		return deleteData;
	}

	/**
	 * @param deleteData
	 *            the deleteData to set
	 */
	public void setDeleteData(DeleteData deleteData) {
		this.deleteData = deleteData;
	}

	/**
	 * @return the evaluateFormula
	 */
	public EvaluateFormula getEvaluateFormula() {
		return evaluateFormula;
	}

	/**
	 * @param evaluateFormula
	 *            the evaluateFormula to set
	 */
	public void setEvaluateFormula(EvaluateFormula evaluateFormula) {
		this.evaluateFormula = evaluateFormula;
	}

	/**
	 * @return the executeMacro
	 */
	public ExecuteMacro getExecuteMacro() {
		return executeMacro;
	}

	/**
	 * @param executeMacro
	 *            the executeMacro to set
	 */
	public void setExecuteMacro(ExecuteMacro executeMacro) {
		this.executeMacro = executeMacro;
	}

	/**
	 * @return the formatColumn
	 */
	public FormatColumn getFormatColumn() {
		return formatColumn;
	}

	/**
	 * @param formatColumn
	 *            the formatColumn to set
	 */
	public void setFormatColumn(FormatColumn formatColumn) {
		this.formatColumn = formatColumn;
	}

	/**
	 * @return the count
	 */
	public RowCount getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(RowCount count) {
		this.count = count;
	}

	/**
	 * @return the update
	 */
	@XmlElementWrapper(name = "updates")
	public List<Update> getUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            the update to set
	 */
	public void setUpdate(List<Update> update) {
		this.update = update;
	}

	/**
	 * @return the assignment
	 */
	@XmlElementWrapper(name = "assignments")
	public List<Assignment> getAssignment() {
		return assignment;
	}

	/**
	 * @param assignment
	 *            the assignment to set
	 */
	public void setAssignment(List<Assignment> assignment) {
		this.assignment = assignment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rule [type=" + type + ", filter=" + filter + ", update="
				+ update + ", assignment=" + assignment + ", calculate="
				+ calculate + ", copyData=" + copyData + ", deleteData="
				+ deleteData + ", evaluateFormula=" + evaluateFormula
				+ ", executeMacro=" + executeMacro + ", formatColumn="
				+ formatColumn + ", count=" + count + "]";
	}

	
}
