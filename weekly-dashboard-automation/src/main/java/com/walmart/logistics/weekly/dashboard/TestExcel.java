package com.walmart.logistics.weekly.dashboard;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jxl.read.biff.BiffException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.walmart.releaseautomation.weekly.dashboard.constants.DashboardConstants;
import com.walmart.releaseautomation.weekly.dashboard.impl.RulesImpl;
import com.walmart.releaseautomation.weekly.dashboard.intf.Rules;
import com.walmart.releaseautomation.weekly.dashboard.model.ListContainer;
import com.walmart.releaseautomation.weekly.dashboard.model.Rule;

public class TestExcel {

	private String rule;

	private Rules rules;

	public static void main(String[] args) throws IOException, JAXBException,
			InvalidFormatException, BiffException, InterruptedException {

		mockMapValues();
		new TestExcel().readFromExcel();

		/*
		 * JAXBContext context = JAXBContext.newInstance(Rule.class); Marshaller
		 * marshaller = context.createMarshaller(); Rule rule = new Rule();
		 * Filter filter = new Filter(); filter.setFileToFilter("fadsfa");
		 * filter.setSheetToFilter("fadsf"); NarrowDownTo downTo = new
		 * NarrowDownTo(); downTo.setColToFilter("fads");
		 * downTo.setFilterColADate(false); List<String> strings = new
		 * ArrayList<String>(); strings.add("fdasdfd");
		 * downTo.setValuesToFilter(strings); List<NarrowDownTo> downTos = new
		 * ArrayList<NarrowDownTo>(); downTos.add(downTo); downTos.add(downTo);
		 * filter.setNarrowDownTo(downTos); rule.setFilter(filter);
		 * marshaller.marshal(rule, System.out);
		 */

	}

	private static void mockMapValues() {
		DashboardConstants.wbURLMap.put("Mini_Dashboard",
				"C:/Users/264314/Documents/" + "Mini_Dashboard.xls");
		DashboardConstants.wbURLMap.put("DefectsReport",
				"C:/Users/264314/Documents/DefectsReport.xls");
		DashboardConstants.wbURLMap
				.put("Daily_Release_Dashboard",
						"C:/Users/264314/Documents/Daily_Release_Dashboard.xlsm");
		DashboardConstants.wbURLMap.put("PMO Only",
				"C:/Users/264314/Documents/PMO Only.xls");
	}

	public void readFromExcel() throws IOException, JAXBException,
			InvalidFormatException, BiffException, InterruptedException {
		int ruleIndex = 1;
		// HSSFWorkbook, File
		Class[] classes = new Class[] { ListContainer.class, Rule.class };
		JAXBContext context = JAXBContext.newInstance(classes);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ListContainer<Rule> listContainer = (ListContainer<Rule>) unmarshaller
				.unmarshal(this.getClass().getClassLoader()
						.getResourceAsStream("rsc/rules/rules.xml"));
		rules = new RulesImpl();
		
		for (Rule rule : listContainer.getItems()) {
			switch (DashboardConstants.RULES.valueOf(rule.getType()
					.toUpperCase())) {
			case FILTERANDUPDATE:
				rules.filterAndUpdate(rule);
				break;
			case COPYDATA:
				rules.copyData(rule);
				break;
			case DELETEDATA:
				rules.deleteData(rule);
				break;
			case EVALUATEFORMULA:
				rules.evaluateFormula(rule);
				break;
			case FORMATCOLUMN:
				rules.formatColumn(rule);
				break;
			case FILTER:
				break;
			case EXECUTEMACRO:
				rules.executeMacro(rule);
				break;
			case FILTERANDEVALUATE:
				rules.filterAndEvaluate(rule);
			default:
				break;
			}
			System.out.println("*****************COMPLETED**************" );
			System.out.println(rule.toString());
			ruleIndex = ruleIndex + 1;
			//System.out.println("Sleep Starts");
			Thread.sleep(2000);
			//System.out.println("Sleep Ends");
		}
	}

	/**
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * @param rule
	 *            the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/*
	 * int index = 0; Cell cell = null; FormulaEvaluator evaluator =
	 * wb.getCreationHelper() .createFormulaEvaluator(); for (Row row : sheet) {
	 * if (index == 0) { Iterator<Cell> cellIterator = row.cellIterator(); while
	 * (cellIterator.hasNext()) { if ((cell =
	 * cellIterator.next()).getStringCellValue() .equals("D")) index =
	 * cell.getColumnIndex(); } } else { cell = row.createCell(index);
	 * cell.setCellType(Cell.CELL_TYPE_FORMULA); int num = row.getRowNum() + 1;
	 * cell.setCellFormula("VLOOKUP(A" + num + ",B:C,1,FALSE)");
	 * evaluator.setDebugEvaluationOutputForNextEval(true);
	 * evaluator.evaluateInCell(cell); } }
	 * 
	 * List<Rule> ruleList = new ArrayList<Rule>(); Rule rule = new Rule();
	 * rule.setType("CopyData"); CopyData copyData = new CopyData();
	 * copyData.setCopyFromFile("PMO_Only.xls");
	 * copyData.setCopyFromSheet("Sheet1"); copyData.setCopyFromColumns(new
	 * String[] { "*" }); copyData.setPasteToFile("Daily_Release_Dashboard");
	 * copyData.setPasteToSheet("Base"); copyData.setPasteFromColumnIndex(0);
	 * rule.setCopyData(copyData); ruleList.add(rule);
	 * 
	 * FormatColumn formatColumn = new FormatColumn();
	 * formatColumn.setFromFile("Daily_Release_Dashboard");
	 * formatColumn.setSheet("Base"); formatColumn.setOnColumns(new String[] {
	 * "Created Date", "Estimated Fix Date" });
	 * formatColumn.setCellTypeformat("DATE"); rule = new Rule();
	 * rule.setType("FormatColumn"); rule.setFormatColumn(formatColumn);
	 * ruleList.add(rule); EvaluateFormula evaluateFormula = new
	 * EvaluateFormula();
	 * evaluateFormula.setFromFile("Daily_Release_Dashboard");
	 * evaluateFormula.setFromSheet("Daily Data");
	 * evaluateFormula.setOnColumns(new String[] {"RCA","RCA Age"}); rule = new
	 * Rule(); rule.setType("EvaluateFormula");
	 * rule.setEvaluateFormula(evaluateFormula); ruleList.add(rule);
	 * 
	 * DeleteData data = new DeleteData();
	 * data.setFromFile("Daily_Release_Dashboard");
	 * data.setFromSheet("Daily Data"); data.setColumns(new String[] {"AD:AI"});
	 * rule = new Rule(); rule.setType("DeleteData"); rule.setDeleteData(data);
	 * ruleList.add(rule);
	 * 
	 * ExecuteMacro executeMacro = new ExecuteMacro();
	 * executeMacro.setOnFile("Daily_Release_Dashboard");
	 * executeMacro.setScriptName("triggerReleasemacro.vbs"); rule = new Rule();
	 * rule.setType("ExecuteMacro"); rule.setExecuteMacro(executeMacro);
	 * ruleList.add(rule); listContainer = new ListContainer<Rule>(ruleList);
	 * 
	 * try { Class[] classes = new Class[] { listContainer.getClass() };
	 * JAXBContext context = JAXBContext.newInstance(classes); Marshaller
	 * marshaller = context.createMarshaller();
	 * marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); File
	 * ruleFile = new File(
	 * "C:\\Users\\l.naga rajesh\\springwork\\weekly-dashboard\\src\\main\\resources\\rules\\"
	 * + "part_2.xml"); marshaller.marshal(listContainer, ruleFile); } catch
	 * (JAXBException e) { e.printStackTrace(); }
	 */

}
