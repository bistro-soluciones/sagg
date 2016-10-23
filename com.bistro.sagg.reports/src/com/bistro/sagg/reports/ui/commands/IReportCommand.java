package com.bistro.sagg.reports.ui.commands;

import org.eclipse.ui.PartInitException;

public interface IReportCommand {
	
	String getEvent();

	void execute() throws PartInitException;
	
}
