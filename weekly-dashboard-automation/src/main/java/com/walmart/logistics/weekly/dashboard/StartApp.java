package com.walmart.logistics.weekly.dashboard;

import javax.swing.SwingUtilities;

import com.walmart.releaseautomation.weekly.dashboard.ui.MetricsDashBoard;

public class StartApp {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				MetricsDashBoard form = new MetricsDashBoard();
				form.setVisible(true);
			}
		});
	}
}
