package com.walmart.releaseautomation.weekly.dashboard.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.walmart.logistics.weekly.dashboard.util.DashboardUtility;
import com.walmart.releaseautomation.weekly.dashboard.constants.DashboardConstants;
import com.walmart.releaseautomation.weekly.dashboard.intf.Rules;
import com.walmart.releaseautomation.weekly.dashboard.model.Filter;
import com.walmart.releaseautomation.weekly.dashboard.model.NarrowDownTo;
import com.walmart.releaseautomation.weekly.dashboard.model.Rule;
import com.walmart.releaseautomation.weekly.dashboard.model.Update;

public class RulesImpl implements Rules {
	private int filterColIndex = -1;
	private int updateColIndex = -1;
	private int rowCountColIndex = -1;
	private boolean newUpdCol;
	private Rule rule;
	private List<Integer> toEvalColIndexes;
	private boolean wbChanged;
	private boolean colMatchesWithFilter;
	private int filtersPassCnt;
	private boolean[] filterResults;
	public static DataFormatter dataFormatter = new DataFormatter();
	private int rowBrkCheckColIndex = -1;

	/**
	 * @return the rowBrkCheckColIndex
	 */
	public int getRowBrkCheckColIndex() {
		return rowBrkCheckColIndex;
	}

	/**
	 * @param rowBrkCheckColIndex
	 *            the rowBrkCheckColIndex to set
	 */
	public void setRowBrkCheckColIndex(int rowBrkCheckColIndex) {
		this.rowBrkCheckColIndex = rowBrkCheckColIndex;
	}

	/**
	 * Filter-DefectsReport.xls|1|Defect Root
	 * Cause|Blanks~Update-DefectsReport|1|RCA Completed|N
	 * 
	 * @throws IOException
	 */
	public void filterAndUpdate(Rule rule) throws IOException {
		setRule(rule);
		Filter filter = rule.getFilter();
		List<Update> updates = rule.getUpdate();
		Date createdDate = null;
		Map<Date, List<Integer>> storeCountMap = new TreeMap<Date, List<Integer>>();
		HSSFWorkbook filterWorkbook = (HSSFWorkbook) DashboardUtility
				.getWorkBook(DashboardConstants.wbURLMap.get(filter
						.getFileToFilter().split(".x")[0]));
		Sheet filterSheet = DashboardUtility.getSheet(
				filter.getSheetToFilter(), filterWorkbook);
		FormulaEvaluator evaluator = filterWorkbook.getCreationHelper()
				.createFormulaEvaluator();
		for (Row row : filterSheet) {
			bindFilterAndRowCntColIndexes(rule, filter, row);
			bindUpdColIndex(rule, row);
			break;
		}

		for (Update update : updates) {
			for (Row row : filterSheet) {
				if (row.getRowNum() == 0) {
					if (filter.getFileToFilter().equalsIgnoreCase(
							"DefectsReport.xls")) {
						setRowBrkCheckColIndex(getRowBrkFilterColIndex(row,
								"Defect ID"));
					} else {
						setRowBrkCheckColIndex(getRowBrkFilterColIndex(row,
								"Created Date"));
					}
				} else {
					if (row.getCell(getRowBrkCheckColIndex()) == null) {
						break;
					}
					setFiltersPassCnt(0);
					for (NarrowDownTo narrowDownTo : filter.getNarrowDownTo()) {
						String valueToFilter = null;
						setColMatchesWithFilter(Boolean.FALSE);
						if (row.getCell(narrowDownTo.getFilterColIndex()) == null) {
							row.createCell(narrowDownTo.getFilterColIndex(),
									Cell.CELL_TYPE_BLANK);
						}
						if (rule.getCount() != null
								&& rule.getCount().isStoreCountInMap()
								&& rule.getCount().getRowCountColIndex() != -1
								&& narrowDownTo.isFilterColADate()) {
							valueToFilter = narrowDownTo.getValuesToFilter()
									.get(0);
							if (createdDate == null) {
								Calendar calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, -Integer
										.parseInt(valueToFilter.split("-")[1]));
								calendar.set(Calendar.HOUR, 0);
								calendar.set(Calendar.MINUTE, 0);
								calendar.set(Calendar.SECOND, 0);
								createdDate = calendar.getTime();
							}
							if (row.getCell(narrowDownTo.getFilterColIndex())
									.getDateCellValue().after(createdDate)) {
								assertFilterMatch();
								getPriority(row, storeCountMap, narrowDownTo);
							}
						} else {
							validateValuesToFilter(row, narrowDownTo, evaluator);
						}
					}
					if (getFiltersPassCnt() > 0
							&& getFiltersPassCnt() == filter.getNarrowDownTo()
									.size()) {
						setCellValue(row, update, evaluator);
					}
				}
			}
		}

		filterWorkbook = weeklyRunRateSpecialTask(rule, filter, storeCountMap,
				filterWorkbook);
		if (isWbChanged()) {
			writeBack(filter, filterWorkbook);
		}
	}

	/**
	 * @param rule
	 * @param filter
	 * @param storeCountMap
	 * @param filterWorkbook
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private HSSFWorkbook weeklyRunRateSpecialTask(Rule rule, Filter filter,
			Map<Date, List<Integer>> storeCountMap, HSSFWorkbook filterWorkbook)
			throws FileNotFoundException, IOException {
		if (rule.getCount() != null && !storeCountMap.isEmpty()) {
			Sheet countOnSheet = DashboardUtility.getSheet(rule.getCount()
					.getCountOnSheet(), filterWorkbook);
			try {
				countOnSheet.createFreezePane(1, 1);
				writeBack(filter, filterWorkbook);
				filterWorkbook = (HSSFWorkbook) DashboardUtility
						.getWorkBook(DashboardConstants.wbURLMap.get(filter
								.getFileToFilter().split(".x")[0]));
				countOnSheet = DashboardUtility.getSheet(rule.getCount()
						.getCountOnSheet(), filterWorkbook);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("previously didn't have a freeze pane.");
			}
			Row row = null;
			Cell dateCell = null;
			Cell highPriority = null;
			Cell mediumPriority = null;
			Cell lowPriority = null;
			Cell totalPriorityCnt = null;
			int prioritySum = 0;
			int lastRow = countOnSheet.getLastRowNum();
			for (Date createDate : storeCountMap.keySet()) {
				row = countOnSheet.getRow(lastRow);
				if (row == null
						|| (!row.getCell(0).getDateCellValue()
								.equals(createDate) && lastRow == countOnSheet
								.getLastRowNum())) {
					row = countOnSheet.createRow(lastRow);
					dateCell = row.createCell(0);
					dateCell.setCellValue(createDate);
					// binds the Date style
					HSSFCellStyle dateCellStyle = filterWorkbook
							.createCellStyle();
					short df = filterWorkbook.createDataFormat().getFormat(
							"dd-MM-yyyy");
					dateCellStyle.setDataFormat(df);
					dateCell.setCellStyle(dateCellStyle);
				}
				highPriority = row.createCell(1);
				highPriority.setCellValue(storeCountMap.get(createDate).get(0));
				mediumPriority = row.createCell(2);
				mediumPriority.setCellValue(storeCountMap.get(createDate)
						.get(1));
				lowPriority = row.createCell(3);
				lowPriority.setCellValue(storeCountMap.get(createDate).get(2));

				totalPriorityCnt = row.createCell(4);
				prioritySum = (int) (highPriority.getNumericCellValue()
						+ mediumPriority.getNumericCellValue() + lowPriority
						.getNumericCellValue());
				totalPriorityCnt.setCellValue(prioritySum);
				lastRow++;
			}
		}
		return filterWorkbook;
	}

	/**
	 * @param typeConvertedCellValue
	 * @param valueToFilter
	 */
	private void proceedNonBlankCellValidation(String typeConvertedCellValue,
			String valueToFilter) {
		boolean isValMatches = false;
		boolean isValNotMatchesAllExcludeFilterValues = false;
		List<String> notInFilterValues = null;
		if (!valueToFilter.contains("*")) {
			isValMatches = valueToFilter
					.equalsIgnoreCase(typeConvertedCellValue);
			if (valueToFilter.contains("!")) {
				notInFilterValues = new ArrayList<String>();
				for (String value : valueToFilter.split(",")) {
					notInFilterValues.add(value.toLowerCase());
				}
				for (int i = 0; i < notInFilterValues.size(); i++) {
					isValMatches = notInFilterValues.get(i).replace("!", "")
							.equalsIgnoreCase(typeConvertedCellValue);
					/*
					 * Cell Value should match all mentioned excluded values and
					 * it should be any of other excluded value too.
					 */
					if (!isValMatches
							&& !notInFilterValues.contains("!"
									+ typeConvertedCellValue.toLowerCase())) {
						if (i != 0) {
							isValNotMatchesAllExcludeFilterValues = isValNotMatchesAllExcludeFilterValues && true;
						} else {
							isValNotMatchesAllExcludeFilterValues = true;
						}
					} else {
						if (i != 0) {
							isValNotMatchesAllExcludeFilterValues = isValNotMatchesAllExcludeFilterValues && false;
						} else {
							isValNotMatchesAllExcludeFilterValues = false;
						}
					}
				}
				if (isValNotMatchesAllExcludeFilterValues) {
					assertFilterMatch();
				}
			} else if (isValMatches) {
				assertFilterMatch();
			}
		} else {
			boolean isValContains = typeConvertedCellValue.toLowerCase()
					.contains(valueToFilter.replace("*", "").toLowerCase());
			if (isValContains) {
				assertFilterMatch();
			}
		}
	}

	/**
	 * @param valueToFilter
	 * @param filterColCellType
	 */
	private void proceedBlankCellValidation(String valueToFilter,
			int filterColCellType) {
		if (!valueToFilter.contains("!")) {
			assertFilterMatch();
		} else {
			if (filterColCellType != Cell.CELL_TYPE_BLANK) {
				assertFilterMatch();
			}
		}
	}

	/**
	 * @param row
	 * @param typeConvertedCellValue
	 * @param narrowDownTo
	 * @param filterColCellType
	 * @param evaluator
	 * @return
	 */
	private String typeConvertCellValue(Row row, NarrowDownTo narrowDownTo,
			int filterColCellType, FormulaEvaluator evaluator) {
		String tempConvertedCellValue = "";
		if (filterColCellType == Cell.CELL_TYPE_FORMULA) {
			int formulaResultType = Cell.CELL_TYPE_STRING;
			if (evaluator != null) {
				try {
					String formatValue = dataFormatter.formatCellValue(
							row.getCell(narrowDownTo.getFilterColIndex()),
							evaluator);
					if (formatValue.equalsIgnoreCase("#N/A")) {
						throw new RuntimeException();
					}
					Integer.parseInt(formatValue);
					formulaResultType = Cell.CELL_TYPE_NUMERIC;
				} catch (NumberFormatException e) {
					formulaResultType = Cell.CELL_TYPE_STRING;
				} catch (RuntimeException e) {
					formulaResultType = Cell.CELL_TYPE_ERROR;
				}
			}
			tempConvertedCellValue = bindAppropriateCellValue(row,
					narrowDownTo, formulaResultType);
		} else {
			tempConvertedCellValue = bindAppropriateCellValue(row,
					narrowDownTo, filterColCellType);
		}

		return tempConvertedCellValue;
	}

	/**
	 * @param row
	 * @param narrowDownTo
	 * @param tempConvertedCellValue
	 * @param cellType
	 * @return
	 */
	private String bindAppropriateCellValue(Row row, NarrowDownTo narrowDownTo,
			int cellType) {
		String tempConvertedCellValue = "";
		if (cellType == Cell.CELL_TYPE_NUMERIC) {
			Double doubleValue = row.getCell(narrowDownTo.getFilterColIndex())
					.getNumericCellValue();
			tempConvertedCellValue = String.valueOf(doubleValue.intValue());
		} else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
			tempConvertedCellValue = String.valueOf(row.getCell(
					narrowDownTo.getFilterColIndex()).getBooleanCellValue());
		} else if (cellType == Cell.CELL_TYPE_STRING) {
			tempConvertedCellValue = String.valueOf(row.getCell(
					narrowDownTo.getFilterColIndex()).getStringCellValue());
		} else if (cellType == Cell.CELL_TYPE_ERROR) {
			tempConvertedCellValue = "#N/A";
		}
		return tempConvertedCellValue;
	}

	/**
	 * @param rule
	 * @param row
	 */
	private void bindUpdColIndex(Rule rule, Row row) {
		for (Update update : rule.getUpdate()) {
			for (Cell cell : row) {
				String tempCell = update.getColToUpdate().replace("^", "");
				if (updateColIndex == -1) {
					if (cell.getStringCellValue().equalsIgnoreCase(tempCell)) {
						updateColIndex = cell.getColumnIndex();
					} else if (cell.getColumnIndex() + 1 == row
							.getPhysicalNumberOfCells()
							&& update.getColToUpdate().contains("^")) {
						Cell newColumnCell = row.createCell(row
								.getPhysicalNumberOfCells());
						newColumnCell.setCellValue(update.getColToUpdate()
								.replace("^", ""));
						updateColIndex = newColumnCell.getColumnIndex();
						setNewUpdCol(Boolean.TRUE);
					}
				}
			}
			update.setUpdateColIndex(updateColIndex);
			updateColIndex = -1;
		}
	}

	/**
	 * @param rule
	 * @param filter
	 * @param row
	 */
	private void bindFilterAndRowCntColIndexes(Rule rule, Filter filter, Row row) {
		for (NarrowDownTo narrowDownTo : filter.getNarrowDownTo()) {
			for (Cell cell : row) {
				if (filterColIndex == -1
						&& cell.getStringCellValue().equalsIgnoreCase(
								narrowDownTo.getColToFilter())) {
					filterColIndex = cell.getColumnIndex();
				}

				if (rowCountColIndex == -1
						&& rule.getCount() != null
						&& cell.getStringCellValue().equalsIgnoreCase(
								rule.getCount().getCountFromCol())) {
					rowCountColIndex = cell.getColumnIndex();
				}
			}
			narrowDownTo.setFilterColIndex(filterColIndex);
			filterColIndex = -1;
			if (rule.getCount() != null) {
				rule.getCount().setRowCountColIndex(rowCountColIndex);
				rowCountColIndex = -1;
			}

		}
	}

	/**
	 * @param rule
	 * @param filter
	 * @param row
	 */
	private int getRowBrkFilterColIndex(Row row, String header) {
		int colIndex = -1;
		for (Cell cell : row) {
			if (colIndex == -1) {
				if (cell.getStringCellValue().equalsIgnoreCase(header)) {
					colIndex = cell.getColumnIndex();
				}
			} else {
				break;
			}
		}
		return colIndex;
	}

	/**
	 * @param index
	 */
	private void assertFilterMatch() {
		setFiltersPassCnt(getFiltersPassCnt() + 1);
	}

	private void getPriority(Row row, Map<Date, List<Integer>> storeCountMap,
			NarrowDownTo narrowDownTo) {
		String high = "high";
		String medium = "medium";
		String low = "low";
		LinkedList<Integer> priorities = null;
		if (storeCountMap.containsKey(row.getCell(
				narrowDownTo.getFilterColIndex()).getDateCellValue())) {
			priorities = (LinkedList<Integer>) storeCountMap.get(row.getCell(
					narrowDownTo.getFilterColIndex()).getDateCellValue());
			if (row.getCell(rule.getCount().getRowCountColIndex())
					.getStringCellValue().toLowerCase().contains(high)) {
				priorities.set(0, priorities.get(0) + 1);
			} else if (row.getCell(rule.getCount().getRowCountColIndex())
					.getStringCellValue().toLowerCase().contains(medium)) {
				priorities.set(1, priorities.get(1) + 1);
			} else if (row.getCell(rule.getCount().getRowCountColIndex())
					.getStringCellValue().toLowerCase().contains(low)) {
				priorities.set(2, priorities.get(2) + 1);
			}
		} else {
			priorities = new LinkedList<Integer>();
			priorities.add(0);
			priorities.add(0);
			priorities.add(0);
			if (row.getCell(rule.getCount().getRowCountColIndex())
					.getStringCellValue().toLowerCase().contains(high)) {
				priorities.set(0, priorities.get(0) + 1);
			} else if (row.getCell(rule.getCount().getRowCountColIndex())
					.getStringCellValue().toLowerCase().contains(medium)) {
				priorities.set(1, priorities.get(1) + 1);
			} else if (row.getCell(rule.getCount().getRowCountColIndex())
					.getStringCellValue().toLowerCase().contains(low)) {
				priorities.set(2, priorities.get(2) + 1);
			}
		}
		setWbChanged(Boolean.TRUE);
		storeCountMap.put(row.getCell(narrowDownTo.getFilterColIndex())
				.getDateCellValue(), priorities);
	}

	/**
	 * @param filter
	 * @param filterWorkbook
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void writeBack(Filter filter, HSSFWorkbook filterWorkbook)
			throws FileNotFoundException, IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(
				DashboardConstants.wbURLMap.get(filter.getFileToFilter().split(
						".x")[0]));
		filterWorkbook.write(fileOutputStream);
		fileOutputStream.close();
	}

	/**
	 * @param row
	 * @param update
	 * @param evaluator
	 */
	private void setCellValue(Row row, Update update, FormulaEvaluator evaluator) {
		setWbChanged(Boolean.TRUE);
		if (isNewUpdCol()) {
			row.createCell(update.getUpdateColIndex());
		}
		if (row.getCell(update.getUpdateColIndex()) == null) {
			row.createCell(update.getUpdateColIndex());
		}
		if (update.getValueToUpdate().equals("*")
				&& rule.getFilter().getNarrowDownTo().size() == 1) {
			/*
			 * We have here for loop, but der will be one narrowDownTo for
			 * valuetoUpdate having '*'
			 */
			for (NarrowDownTo narrowDownTo : rule.getFilter().getNarrowDownTo()) {
				row.getCell(update.getUpdateColIndex()).setCellType(
						Cell.CELL_TYPE_STRING);
				row.getCell(update.getUpdateColIndex()).setCellValue(
						row.getCell(narrowDownTo.getFilterColIndex())
								.getStringCellValue());

				try {
					dataFormatter.formatCellValue(row.getCell(update
							.getUpdateColIndex()));
				} catch (java.lang.IllegalArgumentException e) {
					System.out
							.println("Error while formatting Cell value - Can be Ignored");
				}

			}
		} else {
			row.getCell(update.getUpdateColIndex()).setCellType(
					Cell.CELL_TYPE_STRING);
			row.getCell(update.getUpdateColIndex()).setCellValue(
					update.getValueToUpdate());
			dataFormatter.formatCellValue(row.getCell(update
					.getUpdateColIndex()));
		}
	}

	public void copyData(Rule rule) throws IOException, BiffException {
		Workbook workbook = null;
		CellStyle cellStyle = null;
		CreationHelper creationHelper = null;
		Calendar calendar = null;
		SimpleDateFormat simpleDateFormat = null;
		List<Integer> copyColumnsIndexes = new LinkedList<Integer>();
		HSSFWorkbook copyFrmWB = (HSSFWorkbook) DashboardUtility
				.getWorkBook(DashboardConstants.wbURLMap.get(rule.getCopyData()
						.getCopyFromFile().split(".x")[0]));
		Sheet sheet = DashboardUtility.getSheet(rule.getCopyData()
				.getCopyFromSheet(), copyFrmWB);

		for (Row row : sheet) {
			for (Cell cell : row) {
				for (String copyCol : rule.getCopyData().getCopyFromColumns()) {
					if (cell.getStringCellValue().equalsIgnoreCase(copyCol)) {
						copyColumnsIndexes.add(cell.getColumnIndex());
						continue;
					} else if (copyCol.equals("*")) {
						copyColumnsIndexes.add(cell.getColumnIndex());
						continue;
					}
				}
			}
			break;
		}
		workbook = Workbook.getWorkbook(new File(DashboardConstants.wbURLMap
				.get(rule.getCopyData().getCopyFromFile().split(".x")[0])));
		jxl.Sheet jxlSheet = workbook.getSheet(sheet.getSheetName());
		List<jxl.Cell[]> copiedCells = new LinkedList<jxl.Cell[]>();
		for (Integer i : copyColumnsIndexes) {
			copiedCells.add((jxl.Cell[]) jxlSheet.getColumn(i));
		}
		List<jxl.Cell[]> rowWiseCells = new ArrayList<jxl.Cell[]>();
		jxl.Cell[] cellArray = new jxl.Cell[copiedCells.size()];
		int colIndex = 0;
		for (int i = 0; i < copiedCells.get(0).length; i++) {
			while (colIndex < cellArray.length) {
				cellArray[colIndex] = copiedCells.get(colIndex)[i];
				colIndex++;
			}
			colIndex = 0;
			rowWiseCells.add(cellArray);
			cellArray = new jxl.Cell[copiedCells.size()];
		}

		HSSFWorkbook pasteToWB = (HSSFWorkbook) DashboardUtility
				.getWorkBook(DashboardConstants.wbURLMap.get(rule.getCopyData()
						.getPasteToFile().split(".x")[0]));
		Sheet pasteToSheet = DashboardUtility.getSheet(rule.getCopyData()
				.getPasteToSheet().replace("^", ""), pasteToWB);
		if (pasteToSheet == null
				&& rule.getCopyData().getPasteToSheet().contains("^")) {
			pasteToSheet = pasteToWB.createSheet(rule.getCopyData()
					.getPasteToSheet().replace("^", ""));
		}
		colIndex = 0;
		if (pasteToSheet.getPhysicalNumberOfRows() != 0) {
			for (Row row : pasteToSheet) {
				while (colIndex < rowWiseCells.get(0).length) {
					Cell cell = row.getCell(colIndex);
					jxl.Cell toBePasteCell = rowWiseCells.get(row.getRowNum())[colIndex];
					cell.setCellType(DashboardConstants.CELL_TYPE.valueOf(
							toBePasteCell.getType().toString().toUpperCase())
							.getCellType());
					cell.setCellValue(toBePasteCell.getContents());
					colIndex++;
				}
				colIndex = 0;
			}
		} else {
			colIndex = 0;
			cellStyle = pasteToWB.createCellStyle();
			creationHelper = pasteToWB.getCreationHelper();
			for (int i = 0; i < rowWiseCells.size(); i++) {
				Row row = pasteToSheet.createRow(i);
				while (colIndex < rowWiseCells.get(0).length) {
					row.createCell(
							rowWiseCells.get(i)[colIndex].getColumn(),
							DashboardConstants.CELL_TYPE.valueOf(
									rowWiseCells.get(i)[colIndex].getType()
											.toString().toUpperCase())
									.getCellType());
					if (rowWiseCells.get(i)[colIndex].getType().toString()
							.toUpperCase().equalsIgnoreCase("DATE")) {
						try {
							cellStyle
									.setDataFormat(creationHelper
											.createDataFormat().getFormat(
													"dd/mm/yyyy"));
							simpleDateFormat = new SimpleDateFormat("dd/mm/yy");
							Date date = simpleDateFormat.parse(rowWiseCells
									.get(i)[colIndex].getContents());
							calendar = Calendar.getInstance();
							calendar.setTime(date);
							calendar.set(Calendar.HOUR, 0);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							row.getCell(
									rowWiseCells.get(i)[colIndex].getColumn())
									.setCellValue(calendar.getTime());
							row.getCell(
									rowWiseCells.get(i)[colIndex].getColumn())
									.setCellStyle(cellStyle);
							System.out.println(row
									.getCell(
											rowWiseCells.get(i)[colIndex]
													.getColumn())
									.getCellStyle().getDataFormat()
									+ ":"
									+ row.getCell(
											rowWiseCells.get(i)[colIndex]
													.getColumn())
											.getCellStyle()
											.getDataFormatString());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						row.getCell(rowWiseCells.get(i)[colIndex].getColumn())
								.setCellValue(
										rowWiseCells.get(i)[colIndex]
												.getContents());
					}
					colIndex++;
				}
				colIndex = 0;
			}
		}

		FileOutputStream fileOutputStream = new FileOutputStream(new File(
				DashboardConstants.wbURLMap.get(rule.getCopyData()
						.getPasteToFile().split(".x")[0])));
		pasteToWB.write(fileOutputStream);
		fileOutputStream.close();
	}

	public void formatColumn(Rule rule) {
		// TODO Auto-generated method stub

	}

	public void executeMacro(Rule rule) throws IOException,
			InterruptedException {
		executeProcess(rule);
	}

	public void evaluateFormula(Rule rule) throws IOException {
		HSSFWorkbook evalFrmWB = (HSSFWorkbook) DashboardUtility
				.getWorkBook(DashboardConstants.wbURLMap.get(rule
						.getEvaluateFormula().getFileToFilter().split(".x")[0]));
		Sheet sheet = DashboardUtility.getSheet(rule.getEvaluateFormula()
				.getFromSheet(), evalFrmWB);
		FormulaEvaluator evaluator = null;

		HSSFCellStyle dateCellStyle = evalFrmWB.createCellStyle();
		short df = evalFrmWB.createDataFormat().getFormat("dd-MM-yyyy");
		dateCellStyle.setDataFormat(df);

		HSSFCellStyle numberCellStyle = evalFrmWB.createCellStyle();
		DataFormat dataFormat = evalFrmWB.createDataFormat();
		numberCellStyle.setDataFormat(dataFormat.getFormat("0"));

		for (Row row : sheet) {
			if (row.getRowNum() == 0) {
				setRowBrkCheckColIndex(getRowBrkFilterColIndex(row,
						"Created Date"));
			}
			if (row.getCell(getRowBrkCheckColIndex()) == null) {
				break;
			}
			if (getToEvalColIndexes().isEmpty()) {
				bindEvalColIndexes(rule, row);
			} else {
				evaluator = evalFrmWB.getCreationHelper()
						.createFormulaEvaluator();
				evaluateCells(rule, evaluator, row, dateCellStyle,
						numberCellStyle);
			}
		}
		setToEvalColIndexes(null);
		FileOutputStream outputStream = new FileOutputStream(
				DashboardConstants.wbURLMap.get(rule.getEvaluateFormula()
						.getFileToFilter().split(".x")[0]));
		evalFrmWB.write(outputStream);
		outputStream.close();
	}

	public void deleteData(Rule rule) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the newUpdCol
	 */
	public boolean isNewUpdCol() {
		return newUpdCol;
	}

	/**
	 * @param newUpdCol
	 *            the newUpdCol to set
	 */
	public void setNewUpdCol(boolean newUpdCol) {
		this.newUpdCol = newUpdCol;
	}

	/**
	 * @return the rule
	 */
	public Rule getRule() {
		return rule;
	}

	/**
	 * @param rule
	 *            the rule to set
	 */
	public void setRule(Rule rule) {
		this.rule = rule;
	}

	private void executeProcess(Rule rule) throws IOException {
		StringBuilder cmdBuilder = new StringBuilder();
		String fileWithRscDir = "rsc/vbs/"
				+ rule.getExecuteMacro().getScriptName();
		String fullScriptPath = this.getClass().getClassLoader()
				.getResource(fileWithRscDir).getFile();
		fullScriptPath = URLDecoder.decode(fullScriptPath, "UTF-8");
		if (fullScriptPath.charAt(0) == '/') {
			fullScriptPath = fullScriptPath.substring(1,
					fullScriptPath.length());
		}
		cmdBuilder.append("wscript ").append("\"").append(fullScriptPath)
				.append("\"");
		if (rule.getExecuteMacro().getMacroName() != null
				&& !rule.getExecuteMacro().getMacroName().isEmpty()) {
			cmdBuilder.append(" ")
					.append(rule.getExecuteMacro().getMacroName());

		}
		if (rule.getExecuteMacro().getParams() != null
				&& !rule.getExecuteMacro().getParams().isEmpty()) {
			for (String param : rule.getExecuteMacro().getParams()) {
				cmdBuilder.append(" ").append("\"").append(param).append("\"");
			}
		}
		Process p = null;
		int processOutputCode = 0;
		try {
			p = Runtime.getRuntime().exec(cmdBuilder.toString());
			processOutputCode = p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the toEvalColIndexes
	 */
	public List<Integer> getToEvalColIndexes() {
		if (toEvalColIndexes == null) {
			toEvalColIndexes = new ArrayList<Integer>();
		}
		return toEvalColIndexes;
	}

	/**
	 * @param toEvalColIndexes
	 *            the toEvalColIndexes to set
	 */
	public void setToEvalColIndexes(List<Integer> toEvalColIndexes) {
		this.toEvalColIndexes = toEvalColIndexes;
	}

	public static void main(String[] args) throws IOException {/*
																 * //
																 * "Mini_Dashboard"
																 * ,
																 * "Daily_Release_Dashboard.xlsm"
																 * String
																 * fullScriptPath
																 * =
																 * URLDecoder.decode
																 * (
																 * "C:/makeACopyOfMiniDashboardForThisWeek.vbs"
																 * , "UTF-8");
																 * if
																 * (fullScriptPath
																 * .charAt(0) ==
																 * '/') {
																 * fullScriptPath
																 * =
																 * fullScriptPath
																 * .substring(1,
																 * fullScriptPath
																 * .length()); }
																 * Process p =
																 * null; int
																 * processOutputCode
																 * = 0; try { p
																 * = Runtime.
																 * getRuntime
																 * ().exec(
																 * "wscript " +
																 * "\"" +
																 * fullScriptPath
																 * + "\"" + " "
																 * +
																 * "Mini_Dashboard"
																 * + " " +
																 * "Daily_Release_Dashboard.xlsm"
																 * );
																 * processOutputCode
																 * =
																 * p.waitFor();
																 * } catch (
																 * InterruptedException
																 * e) {
																 * e.printStackTrace
																 * (); }
																 */
	}

	/**
	 * @return the wbChanged
	 */
	public boolean isWbChanged() {
		return wbChanged;
	}

	/**
	 * @param wbChanged
	 *            the wbChanged to set
	 */
	public void setWbChanged(boolean wbChanged) {
		this.wbChanged = wbChanged;
	}

	/**
	 * @return the filterResults
	 */
	public boolean[] getFilterResults() {
		return filterResults;
	}

	/**
	 * @param filterResults
	 *            the filterResults to set
	 */
	public void setFilterResults(boolean[] filterResults) {
		this.filterResults = filterResults;
	}

	public void filterAndEvaluate(Rule rule) throws IOException {
		setRule(rule);
		Filter filter = rule.getFilter();
		HSSFWorkbook filterWorkbook = (HSSFWorkbook) DashboardUtility
				.getWorkBook(DashboardConstants.wbURLMap.get(filter
						.getFileToFilter().split(".x")[0]));
		Sheet filterSheet = DashboardUtility.getSheet(
				filter.getSheetToFilter(), filterWorkbook);
		FormulaEvaluator evaluator = filterWorkbook.getCreationHelper()
				.createFormulaEvaluator();
		HSSFCellStyle dateCellStyle = filterWorkbook.createCellStyle();
		short df = filterWorkbook.createDataFormat().getFormat("dd-MM-yyyy");
		dateCellStyle.setDataFormat(df);

		HSSFCellStyle numberCellStyle = filterWorkbook.createCellStyle();
		DataFormat dataFormat = filterWorkbook.createDataFormat();
		numberCellStyle.setDataFormat(dataFormat.getFormat("0"));

		for (Row row : filterSheet) {
			bindFilterAndRowCntColIndexes(rule, filter, row);
			bindEvalColIndexes(rule, row);
			break;
		}
		for (Row row : filterSheet) {
			if (row.getRowNum() == 0) {
				setRowBrkCheckColIndex(getRowBrkFilterColIndex(row,
						"Created Date"));
			}
			if (row.getRowNum() > 0) {
				if (row.getCell(getRowBrkCheckColIndex()) == null) {
					break;
				}
				setFiltersPassCnt(0);
				for (NarrowDownTo narrowDownTo : filter.getNarrowDownTo()) {
					setColMatchesWithFilter(Boolean.FALSE);
					if (row.getCell(narrowDownTo.getFilterColIndex()) != null) {
						validateValuesToFilter(row, narrowDownTo, evaluator);
					} else {
						doCellValidation(narrowDownTo,
								DashboardConstants.EMPTY_STRING,
								Cell.CELL_TYPE_BLANK);
					}
				}
				if (getFiltersPassCnt() == filter.getNarrowDownTo().size()) {
					// Evaluate Here
					evaluateCells(rule, evaluator, row, dateCellStyle,
							numberCellStyle);
				}
			}
		}
		setToEvalColIndexes(null);
		FileOutputStream outputStream = new FileOutputStream(
				DashboardConstants.wbURLMap.get(rule.getEvaluateFormula()
						.getFileToFilter().split(".x")[0]));
		filterWorkbook.write(outputStream);
		outputStream.close();
	}

	/**
	 * @param rule
	 * @param evaluator
	 * @param row
	 * @param dateCellStyle
	 * @param numberCellStyle
	 * @throws IOException
	 */
	private void evaluateCells(Rule rule, FormulaEvaluator evaluator, Row row,
			HSSFCellStyle dateCellStyle, HSSFCellStyle numberCellStyle)
			throws IOException {
		HSSFWorkbook refWb = null;
		FormulaEvaluator refWbFormulaEvaluator = null;
		Map<String, FormulaEvaluator> evaluators = new HashMap<String, FormulaEvaluator>();
		String[] workbookNames = new String[rule.getEvaluateFormula().getCell().length];
		HSSFFormulaEvaluator[] hssfFormulaEvaluators = new HSSFFormulaEvaluator[rule
				.getEvaluateFormula().getCell().length];
		for (int eachEvalColIndex = 0; eachEvalColIndex < getToEvalColIndexes()
				.size(); eachEvalColIndex++) {
			Cell cell = row
					.getCell(getToEvalColIndexes().get(eachEvalColIndex));
			if (cell == null) {
				cell = row.createCell(getToEvalColIndexes().get(
						eachEvalColIndex));
			}
			if (rule.getEvaluateFormula().getCell()[eachEvalColIndex]
					.getCellType() != null) {
				if (rule.getEvaluateFormula().getCell()[eachEvalColIndex]
						.getCellType().equalsIgnoreCase("Date")) {
					cell.setCellValue(new Date());
					cell.setCellStyle(dateCellStyle);
				} else if (rule.getEvaluateFormula().getCell()[eachEvalColIndex]
						.getCellType().equalsIgnoreCase("Number")) {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(0d);
					cell.setCellStyle(numberCellStyle);
				}

			}
			if (rule.getEvaluateFormula().getCell()[eachEvalColIndex]
					.getAddRefLinks() != null
					&& !rule.getEvaluateFormula().getCell()[eachEvalColIndex]
							.getAddRefLinks().isEmpty()) {
				// Set up the workbook environment for evaluation
				for (String refLink : rule.getEvaluateFormula().getCell()[eachEvalColIndex]
						.getAddRefLinks()) {
					workbookNames[eachEvalColIndex] = refLink;
					refWb = (HSSFWorkbook) DashboardUtility
							.getWorkBook(DashboardConstants.wbURLMap
									.get(refLink.split(".x")[0]));
					refWbFormulaEvaluator = refWb.getCreationHelper()
							.createFormulaEvaluator();
					hssfFormulaEvaluators[eachEvalColIndex] = (HSSFFormulaEvaluator) refWbFormulaEvaluator;
					evaluators.put(refLink, refWbFormulaEvaluator);
				}
				HSSFFormulaEvaluator.setupEnvironment(workbookNames,
						hssfFormulaEvaluators);
				evaluator.setupReferencedWorkbooks(evaluators);
			}
			cell.setCellFormula(rule.getEvaluateFormula().getCell()[eachEvalColIndex]
					.getFormula());
			evaluator.evaluate(cell);
			dataFormatter.formatCellValue(cell);
		}
	}

	/**
	 * @param row
	 * @param typeConvertedCellValue
	 * @param narrowDownTo
	 * @param evaluator
	 */
	private void validateValuesToFilter(Row row, NarrowDownTo narrowDownTo,
			FormulaEvaluator evaluator) {
		String typeConvertedCellValue = null;
		int filterColCellType = row.getCell(narrowDownTo.getFilterColIndex())
				.getCellType();
		typeConvertedCellValue = typeConvertCellValue(row, narrowDownTo,
				filterColCellType, evaluator);
		doCellValidation(narrowDownTo, typeConvertedCellValue,
				filterColCellType);
	}

	/**
	 * Method for cell validation
	 * 
	 * @param narrowDownTo
	 * @param typeConvertedCellValue
	 * @param filterColCellType
	 */
	private void doCellValidation(NarrowDownTo narrowDownTo,
			String typeConvertedCellValue, int filterColCellType) {
		for (String filterValue : narrowDownTo.getValuesToFilter()) {
			if (!isColMatchesWithFilter()) {
				if (filterValue.contains(DashboardConstants.BLANK_CELL_STR)
						&& !filterValue.contains(",")) {
					proceedBlankCellValidation(filterValue, filterColCellType);
				} else {
					proceedNonBlankCellValidation(typeConvertedCellValue,
							filterValue);
				}
			}
		}
	}

	/**
	 * @param rule
	 * @param row
	 */
	private void bindEvalColIndexes(Rule rule, Row row) {
		for (int eval = 0; eval < rule.getEvaluateFormula().getCell().length; eval++) {
			Cell cell = null;
			for (Iterator<Cell> iterator = row.iterator(); iterator.hasNext();) {
				cell = iterator.next();
				if (cell.getStringCellValue().equalsIgnoreCase(
						rule.getEvaluateFormula().getCell()[eval].getHeader())) {
					getToEvalColIndexes().add(cell.getColumnIndex());
				}
			}
		}
	}

	/**
	 * @return the colMatchesWithFilter
	 */
	public boolean isColMatchesWithFilter() {
		return colMatchesWithFilter;
	}

	/**
	 * @param colMatchesWithFilter
	 *            the colMatchesWithFilter to set
	 */
	public void setColMatchesWithFilter(boolean colMatchesWithFilter) {
		this.colMatchesWithFilter = colMatchesWithFilter;
	}

	/**
	 * @return the filtersPassCnt
	 */
	public int getFiltersPassCnt() {
		return filtersPassCnt;
	}

	/**
	 * @param filtersPassCnt
	 *            the filtersPassCnt to set
	 */
	public void setFiltersPassCnt(int filtersPassCnt) {
		this.filtersPassCnt = filtersPassCnt;
	}

}
