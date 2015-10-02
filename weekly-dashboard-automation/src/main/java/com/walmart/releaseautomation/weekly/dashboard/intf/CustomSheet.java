package com.walmart.releaseautomation.weekly.dashboard.intf;

import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public abstract class CustomSheet implements Sheet {
	
	public AutoFilter setAutoFilter(CellRangeAddress range) {
		this.setAutoFilter(range);
		return null;
	}

}
