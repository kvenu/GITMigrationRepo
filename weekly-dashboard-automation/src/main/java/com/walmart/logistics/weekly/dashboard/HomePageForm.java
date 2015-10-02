package com.walmart.logistics.weekly.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HomePageForm extends JFrame {
	private Container container;
	private JPanel jPanel;
	/* 45: 42 */private final Font font1 = new Font("Calibri", 0, 20);
	/* 46: 43 */private final Font font2 = new Font("Calibri", 1, 20);
	/* 48: */private JTextArea lblError;
	/* 49: */private JLabel lblTrailerId;
	/* 50: */private JLabel lblStatus;
	/* 51: */private JLabel lblTitle;
	/* 52: */private JTextField txtTrailerId;
	/* 53: */private JComboBox cboStatus;
	/* 54: */private JPanel mainPanel;
	/* 55: */private JPanel midPanel;
	/* 56: */private JPanel bottomPanel;
	/* 57: */private JPanel contentPanel;
	/* 58: */private JButton gatein;
	/* 59: */private JButton gateout;
	/* 60: */private JButton storeDetail;
	/* 61: */private JButton close;
	/* 64: 53 */private final String[] statuses = { "Inbound/Outbound",
			"Empty",
			/* 65: 54 */"Relay" };
	/* 66: 55 */private Exception ex = null;
	/* 67: 56 */private String operation = null;
	/* 68: 56 */private String url = null;

	public HomePageForm() {
		setUpForm();
	}

	private void setUpForm() {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		int w = (int) rect.getWidth();
		int h = (int) rect.getHeight();
		setDefaultCloseOperation(3);
		setBounds(w / 2 - 250, h / 2 - 180, 500, 360);
		setResizable(true);
		this.lblError = new JTextArea();
		/* 85: 72 */this.lblError.setBackground(getBackground());
		/* 86: 73 */this.lblError.setEditable(false);
		/* 87: 74 */this.lblError.setBorder(null);
		/* 88: 75 */this.lblError.setLineWrap(true);
		/* 89: 76 */this.lblError.setWrapStyleWord(true);
		/* 90: 77 */this.lblError.setBorder(BorderFactory.createTitledBorder("fadsfa"));
		/* 91: 78 */this.lblError.setFont(new Font("Calibri", 1, 15));
		/* 92: */
		/* 93: */
		/* 94: 81 */this.lblError.setForeground(Color.RED);
		/* 95: 82 */this.lblTrailerId = new JLabel("Trailer ID");
		/* 96: 83 */this.lblTrailerId.setFont(this.font2);
		/* 97: 84 */this.lblTrailerId.setForeground(Color.BLUE);
		/* 98: 85 */this.lblStatus = new JLabel("Status");
		/* 99: 86 */this.lblStatus.setFont(this.font2);
		/* 100: 87 */this.lblStatus.setForeground(Color.BLUE);
		/* 101: 88 */this.lblTitle = new JLabel("SMARTGATE DRIVER KIOSK");
		/* 102: 89 */this.lblTitle.setFont(this.font2);
		/* 103: 90 */this.lblTitle.setForeground(Color.DARK_GRAY);
		/* 104: */
		/* 105: 92 */this.txtTrailerId = new JTextField();
		/* 106: 93 */this.txtTrailerId.setFont(this.font1);
		/* 107: 94 */this.cboStatus = new JComboBox(this.statuses);
		/* 108: 95 */this.cboStatus.setFont(this.font1);
		/* 109: */
		/* 110: 97 */this.gatein = new JButton("Gate In");
		/* 111: 98 */this.gatein.setFont(this.font1);
		/* 112: 99 */this.gateout = new JButton("Gate Out");
		/* 113:100 */this.gateout.setFont(this.font1);
		/* 114:101 */this.storeDetail = new JButton("Store Dtls");
		/* 115:102 */this.storeDetail.setFont(this.font1);
		/* 116:103 */this.close = new JButton("Close");
		/* 117:104 */this.close.setFont(this.font1);
		/* 118: */
		/* 119:106 */this.contentPanel = new JPanel(new FlowLayout())
		/* 120: */{
			/* 121: */private static final long serialVersionUID = 8366123903812711601L;

			/* 122: */
			/* 123: */public Insets getInsets()
			/* 124: */{
				/* 125:111 */Insets insets = new Insets(20, 20, 20, 20);
				/* 126:112 */return insets;
				/* 127: */}
			/* 128:115 */
		};
		/* 129:116 */this.container = getContentPane();
		/* 130:117 */this.container.setLayout(new BorderLayout());
		/* 131:118 */this.container.add(this.contentPanel, "Center");
		/* 132: */
		/* 133:120 */this.midPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		/* 134:121 */this.midPanel.add(this.lblTrailerId);
		/* 135:122 */this.midPanel.add(this.txtTrailerId);
		/* 136:123 */this.midPanel.add(this.lblStatus);
		/* 137:124 */this.midPanel.add(this.cboStatus);
		/* 138: */
		/* 139:126 */this.bottomPanel = new JPanel(new FlowLayout(2));
		/* 140:127 */this.bottomPanel.setPreferredSize(new Dimension(360, 80));
		/* 141:128 */this.bottomPanel.add(this.gatein);
		/* 142:129 */this.bottomPanel.add(this.gateout);
		/* 143:130 */this.bottomPanel.add(this.storeDetail);
		/* 144:131 */this.bottomPanel.add(this.close);
		/* 145: */
		/* 146:133 */this.mainPanel = new JPanel(new BorderLayout())
		/* 147: */{
			/* 148: */private static final long serialVersionUID = 8366123903812711601L;

			/* 149: */
			/* 150: */public Insets getInsets()
			/* 151: */{
				/* 152:138 */Insets insets = new Insets(0, 20, 20, 20);
				/* 153:139 */return insets;
				/* 154: */}
			/* 155:141 */
		};
		/* 156:142 */this.mainPanel.setPreferredSize(new Dimension(370, 235));
		/* 157:143 */this.mainPanel.setBorder(BorderFactory
				.createTitledBorder(""));
		/* 158:144 */this.mainPanel.add(this.lblError, "North");
		/* 159:145 */this.mainPanel.add(this.midPanel, "Center");
		/* 160:146 */this.mainPanel.add(this.bottomPanel, "South");
		/* 161:147 */this.contentPanel.add(this.lblTitle);
		/* 162:148 */this.contentPanel.add(this.mainPanel);
	}
}
