package com.walmart.releaseautomation.weekly.dashboard.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import jxl.read.biff.BiffException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.walmart.logistics.weekly.dashboard.TestExcel;
import com.walmart.releaseautomation.weekly.dashboard.constants.DashboardConstants;

public class UI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 651, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenuItem mntmFile = new JMenuItem("File");
		menuBar.add(mntmFile);

		JMenuItem mntmHelp = new JMenuItem("Help");
		menuBar.add(mntmHelp);
		frame.getContentPane().setLayout(null);

		JLabel lblSelectDefectReports = new JLabel(
				"Select Defect Reports Sheet");
		lblSelectDefectReports.setBounds(45, 53, 168, 14);
		frame.getContentPane().add(lblSelectDefectReports);

		textField = new JTextField();
		textField.setBounds(233, 50, 246, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defectReportsSheetChooseBtnActionPerformed(e);
			}
		});
		btnNewButton.setBounds(511, 50, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Select Dashboard Template");
		lblNewLabel.setBounds(45, 129, 168, 14);
		frame.getContentPane().add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(233, 126, 246, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("Choose");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboardTemplateSheetChooseBtnActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(511, 126, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Select PMO Only Sheet");
		lblNewLabel_1.setBounds(45, 211, 168, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBounds(233, 208, 246, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_2 = new JButton("Choose");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pomoSheetChooseBtnActionPerformed(e);
			}
		});
		btnNewButton_2.setBounds(511, 208, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Run");

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestExcel testExcel = new TestExcel();
				try {
					testExcel.readFromExcel();
				} catch (InvalidFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(181, 267, 158, 23);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Clear Selection");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				DashboardConstants.wbURLMap.clear();
			}
		});
		btnNewButton_4.setBounds(360, 267, 158, 23);
		frame.getContentPane().add(btnNewButton_4);
	}

	private void defectReportsSheetChooseBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Excel Workbooks", "xls", "xlsb", "xlsx", "xlsm");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
			textField.setText(chooser.getSelectedFile().getAbsolutePath());
			DashboardConstants.wbURLMap.put("DefectsReport", chooser
					.getSelectedFile().getAbsolutePath());
		}
	}

	private void dashboardTemplateSheetChooseBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Excel Workbooks", "xls", "xlsb", "xlsx", "xlsm");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
			textField_1.setText(chooser.getSelectedFile().getAbsolutePath());
			DashboardConstants.wbURLMap.put("Daily_Release_Dashboard", chooser
					.getSelectedFile().getAbsolutePath());
		}
	}

	private void pomoSheetChooseBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Excel Workbooks", "xls", "xlsb", "xlsx", "xlsm");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
			textField_2.setText(chooser.getSelectedFile().getAbsolutePath());
			DashboardConstants.wbURLMap.put("PMO Only", chooser
					.getSelectedFile().getAbsolutePath());
			DashboardConstants.wbURLMap.put("Mini_Dashboard",
					chooser.getSelectedFile().getParent() + "\\Mini_Dashboard.xls");
		}
	}
}
