package com.walmart.logistics.weekly.dashboard;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.walmart.releaseautomation.weekly.dashboard.constants.CustomFilterOperator;
import com.walmart.releaseautomation.weekly.dashboard.intf.CustomFilter;

public class DataFilterImpl implements CustomFilter {
	private Sheet sheet;
	private int toBeFilteredColumnIndex;
	private String[] toBeFilteredValues;
	private int filterOperator;

	public CustomFilter applyFilter(Sheet sheet, int columnIndex, int operator,
			String... values) {
		setSheet(sheet);
		setToBeFilteredColumnIndex(columnIndex);
		setToBeFilteredValues(values);
		setFilterOperator(operator);
		return this;
	}

	public CustomFilter applyFilter(Sheet sheet, int columnIndex,
			String... values) {
		setSheet(sheet);
		setToBeFilteredColumnIndex(columnIndex);
		setToBeFilteredValues(values);
		if (values.length > 1) {
			setFilterOperator(CustomFilterOperator.CONTAINS);
		} else if (values.length == 1 && values[0].isEmpty()) {
			setFilterOperator(CustomFilterOperator.CONTAINS_EMPTY);
		}

		return this;
	}

	/**
	 * @return the sheet
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet
	 *            the sheet to set
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the toBeFilteredColumnIndex
	 */
	public int getToBeFilteredColumnIndex() {
		return toBeFilteredColumnIndex;
	}

	/**
	 * @param toBeFilteredColumnIndex
	 *            the toBeFilteredColumnIndex to set
	 */
	public void setToBeFilteredColumnIndex(int toBeFilteredColumnIndex) {
		this.toBeFilteredColumnIndex = toBeFilteredColumnIndex;
	}

	/**
	 * @return the toBeFilteredValues
	 */
	public String[] getToBeFilteredValues() {
		return toBeFilteredValues;
	}

	/**
	 * @param toBeFilteredValues
	 *            the toBeFilteredValues to set
	 */
	public void setToBeFilteredValues(String[] toBeFilteredValues) {
		this.toBeFilteredValues = toBeFilteredValues;
	}

	/**
	 * @return the filterOperator
	 */
	public int getFilterOperator() {
		return filterOperator;
	}

	/**
	 * @param filterOperator
	 *            the filterOperator to set
	 */
	public void setFilterOperator(int filterOperator) {
		this.filterOperator = filterOperator;
	}

	public CustomFilter updateColumn(int columnIndex, String toBeUpdatedValue) {
		List<String> values = null;
		for (Iterator<Row> iterator = getSheet().iterator(); iterator.hasNext();) {
			Row row = iterator.next();
			if (row.getRowNum() == 0) {
				continue;
			}
			switch (getFilterOperator()) {
			case CustomFilterOperator.CONTAINS:
				values = Arrays.asList(getToBeFilteredValues());
				if (values.contains(row.getCell(getToBeFilteredColumnIndex()))) {
					row.getCell(columnIndex).setCellValue(toBeUpdatedValue);
				}
				break;

			case CustomFilterOperator.GREATERTHANOREQUALTO:
				if (row.getCell(getToBeFilteredColumnIndex())
						.getNumericCellValue() > Integer
						.parseInt(getToBeFilteredValues()[0])) {
					row.getCell(columnIndex).setCellValue(toBeUpdatedValue);
				}
				break;

			case CustomFilterOperator.CONTAINS_EMPTY:
				if (row.getCell(getToBeFilteredColumnIndex()).getCellType() == Cell.CELL_TYPE_BLANK) {
					row.getCell(columnIndex).setCellValue(toBeUpdatedValue);
				}
				break;

			default:
				break;
			}
		}
		return this;
	}
}
