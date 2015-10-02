package com.walmart.releaseautomation.weekly.dashboard.intf;

import java.io.IOException;

import jxl.read.biff.BiffException;

import com.walmart.releaseautomation.weekly.dashboard.model.Rule;

public interface Rules {

	void filterAndUpdate(Rule rule) throws IOException;

	void copyData(Rule rule) throws IOException, BiffException;

	void formatColumn(Rule rule);

	void evaluateFormula(Rule rule) throws IOException;

	void deleteData(Rule rule);

	void executeMacro(Rule rule) throws IOException, InterruptedException;

	void filterAndEvaluate(Rule rule) throws IOException;
}
