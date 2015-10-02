package com.walmart.releaseautomation.weekly.dashboard.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface DashboardConstants {

	public static final Map<String, String> wbURLMap = Collections
			.synchronizedMap(new HashMap<String, String>());
	public static final String RULES_PROPERTIES = "rules";
	public static final String FILTER = "FILTER";
	public static final String BLANK_CELL_STR = "Blanks";
	public static final String NOT_OF_BLANK_CELL_STR = "!Blanks";
	public static final String EMPTY_STRING = "";
	

	public static enum RULES {
		FILTERANDUPDATE(1), COPYDATA(2), FORMATCOLUMN(3), EVALUATEFORMULA(4), DELETEDATA(
				5), FILTER(6), EXECUTEMACRO(7), FILTERANDEVALUATE(8);

		int ruleNbr = 0;

		RULES(int ruleNbr) {
			this.ruleNbr = ruleNbr;
		}

		public int getRuleNbr() {
			return this.ruleNbr;
		}
	}

	public static enum CELL_TYPE {
		NUMBER(0), CELL_TYPE_STRING(1), NUMBER_FORMULA(2), EMPTY(3), BOOLEAN(
				4), ERROR(5), LABEL(1), DATE_FORMULA(2), STRING_FORMULA(2), BOOLEAN_FORMULA(2), FORMULA_ERROR(2), DATE(0);

		int cellType = -1;

		CELL_TYPE(int cellType) {
			this.cellType = cellType;
		}

		public int getCellType() {
			return this.cellType;
		}
	}

}
