package com.walmart.releaseautomation.weekly.dashboard.intf;

import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Sheet;

public interface CustomFilter extends AutoFilter {

	/**
	 * * Apply a custom filter * *
	 * <p>
	 * * A custom AutoFilter specifies an operator and a value. * There can be
	 * at most two customFilters specified, and in that case the parent element
	 * * specifies whether the two conditions are joined by 'and' or 'or'. For
	 * any cells whose * values do not meet the specified criteria, the
	 * corresponding rows shall be hidden from * view when the filter is
	 * applied. *
	 * </p>
	 * * *
	 * <p>
	 * * Example: * <blockquote>
	 * 
	 * <pre>
	 * *  AutoFilter filter = sheet.setAutoFilter(CellRangeAddress.valueOf("A1:F200"));      *  filter.applyFilter(0, FilterOperator.GreaterThanOrEqual", "0.2");      *  filter.applyFilter(1, FilterOperator.LessThanOrEqual"", "0.5");      *
	 * </pre>
	 * 
	 * </blockquote> *
	 * </p>
	 * * * @param columnIndex 0-based column index * @param operator the
	 * operator to apply * @param criteria top or bottom value used in the
	 * filter criteria. * * TODO YK: think how to combine AutoFilter with with
	 * DataValidationConstraint, they are really close relatives *
	 */
	CustomFilter applyFilter(Sheet sheet, int columnIndex, int operator, String... criteria);

	/**
	 * * Apply a filter against a list of values * *
	 * <p>
	 * * Example: * <blockquote>
	 * 
	 * <pre>
	 * *  AutoFilter filter = sheet.setAutoFilter(CellRangeAddress.valueOf("A1:F200"));      *  filter.applyFilter(0, "apache", "poi", "java", "api");      *
	 * </pre>
	 * 
	 * </blockquote> *
	 * </p>
	 * * * @param columnIndex 0-based column index * @param values the filter
	 * values * 
	 * @return *
	 */
	CustomFilter applyFilter(Sheet sheet, int columnIndex, String... values);

	CustomFilter updateColumn(int columnIndex, String toBeUpdatedValue);
}
