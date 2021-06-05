package judge;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class AdminGUI extends JPanel {

	private JPanel contentPane;
	private JTextArea codeField;
	private JTextArea inputField;
	private MainGUI mainGUI;
	private int numberOfTestCase;
	private AdminCompiler admin = new AdminCompiler();
	private JLabel numoftestLabel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AdminGUI(MainGUI mainGUI) throws IOException {
		this.mainGUI = mainGUI;
		setBounds(100, 100, 873, 674);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel titleLabel = new JLabel("Algorithm Judge");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 39));
		titleLabel.setBounds(10, 10, 301, 53);
		add(titleLabel);
		
		JLabel subtitleLabel = new JLabel("-Admin mode");
		subtitleLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		subtitleLabel.setBounds(298, 30, 130, 24);
		add(subtitleLabel);
		
		JScrollPane codePane = new JScrollPane();
		codePane.setBounds(20, 73, 524, 552);
		add(codePane);
		
		codeField = new JTextArea();
		codeField.setBackground(Color.WHITE);
		codePane.setViewportView(codeField);
		codeField.append(admin.getSourceCode());
		codeField.setColumns(10);
		
		JButton UserModeBtn = new JButton("Usermode");
		UserModeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGUI.change("adminPanel");
			}
		});
		UserModeBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		UserModeBtn.setBounds(617, 24, 114, 40);
		add(UserModeBtn);

		
		JScrollPane inputPane = new JScrollPane();
		inputPane.setBounds(556, 73, 301, 330);
		add(inputPane);

		inputField = new JTextArea();
		inputPane.setViewportView(inputField);
		inputField.setColumns(10);
		
		JButton inputSubmitBtn = new JButton("Input Submit");
		inputSubmitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeTestCase();
			}
		});
		inputSubmitBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		inputSubmitBtn.setBounds(743, 24, 114, 40);
		add(inputSubmitBtn);
		
		numoftestLabel = new JLabel("TestCase: "+mainGUI.numberOfTestCase);
		numoftestLabel.setBackground(Color.WHITE);
		numoftestLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		numoftestLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numoftestLabel.setBounds(691, 426, 166, 40);
		add(numoftestLabel);
		
//		JTextPane txtpnPleaseInputUpdate = new JTextPane();
//		txtpnPleaseInputUpdate.setForeground(Color.BLACK);
//		txtpnPleaseInputUpdate.setBackground(Color.LIGHT_GRAY);
//		txtpnPleaseInputUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		txtpnPleaseInputUpdate.setBounds(556, 501, 301, 110);
//		txtpnPleaseInputUpdate.setText("                       Notice!!!\nPlease input update after admin code have submitted. n means that n-th output(n).txt will be made.");
//		add(txtpnPleaseInputUpdate);

		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				admin.reset();
				mainGUI.numberOfTestCase = 0;
				numoftestLabel.setText("TestCase: "+mainGUI.numberOfTestCase);

			}
		});
		resetBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		resetBtn.setBounds(491, 24, 114, 40);
		add(resetBtn);
	}
	void makeTestCase(){
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				admin.makeInputTxt(inputField.getText(), mainGUI.numberOfTestCase);
				admin.run(mainGUI.numberOfTestCase);
				mainGUI.numberOfTestCase++;

				numoftestLabel.setText("TestCase: "+mainGUI.numberOfTestCase);
				inputField.setText("");
				return true;
			}
		};
		worker.execute();
	}
}
