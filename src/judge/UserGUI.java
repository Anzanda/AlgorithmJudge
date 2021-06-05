package judge;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class UserGUI extends JPanel {

	private JPanel contentPane;
	private JTextArea codeField;
	private MainGUI mainGUI;
	private int numberOfTestCase;
	private PrintStream originalOut = System.out;
	private UserCompiler user = new UserCompiler();
	private DirManagement dir = new DirManagement();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public UserGUI(MainGUI mainGUI) throws IOException {
		this.mainGUI = mainGUI; 
		setBounds(100, 100, 873, 674);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel titleLabel = new JLabel("Algorithm Judge");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 39));
		titleLabel.setBounds(10, 10, 301, 53);
		add(titleLabel);
		
		JScrollPane descriptionPane = new JScrollPane();
		descriptionPane.setBounds(10, 73, 286, 298);
		add(descriptionPane);
		
		JPanel descriptionTitlePanel = new JPanel();
		descriptionTitlePanel.setBackground(Color.LIGHT_GRAY);
		descriptionPane.setColumnHeaderView(descriptionTitlePanel);
		
		JLabel descriptionTitleLabel = new JLabel("Description");
		descriptionTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		descriptionTitleLabel.setBackground(Color.BLUE);
		descriptionTitlePanel.add(descriptionTitleLabel);
		
		JTextPane descriptionField = new JTextPane();
		descriptionField.setText(dir.getSourceText("description.txt"));
		descriptionPane.setViewportView(descriptionField);
		
		JScrollPane inputPane = new JScrollPane();
		inputPane.setBounds(10, 395, 286, 98);
		add(inputPane);
		
		JPanel inputTitlePanel = new JPanel();
		inputTitlePanel.setBackground(Color.LIGHT_GRAY);
		inputPane.setColumnHeaderView(inputTitlePanel);
		
		JLabel inputTitleLabel = new JLabel("Input");
		inputTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		inputTitlePanel.add(inputTitleLabel);
		
		JTextArea inputField = new JTextArea();
		inputPane.setViewportView(inputField);
		inputField.setText(dir.getSourceText("input.txt"));
		
		JScrollPane outputPane = new JScrollPane();
		outputPane.setBounds(10, 515, 286, 98);
		add(outputPane);
		
		JPanel outputTitlePanel = new JPanel();
		outputTitlePanel.setBackground(Color.LIGHT_GRAY);
		outputPane.setColumnHeaderView(outputTitlePanel);
		
		JLabel outputTitleLabel = new JLabel("Output");
		outputTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		outputTitlePanel.add(outputTitleLabel);
		
		JTextArea outputField = new JTextArea();
		outputPane.setViewportView(outputField);
		outputField.setText(dir.getSourceText("output.txt"));
		
		JScrollPane codePane = new JScrollPane();
		codePane.setBounds(321, 73, 524, 552);
		add(codePane);
		
		codeField = new JTextArea();
		codeField.setBackground(Color.WHITE);
		codePane.setViewportView(codeField);
		codeField.setColumns(100);
		codeField.append(user.getSourceCode());
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startJudge();
			}
		});
		submitBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		submitBtn.setBounds(743, 24, 114, 40);
		add(submitBtn);
		
		JButton adminModeBtn = new JButton("Admin mode");
		adminModeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGUI.change("userPanel");
			}
		});
		adminModeBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		adminModeBtn.setBounds(617, 24, 114, 40);
		add(adminModeBtn);


		
		JLabel subtitleLabel = new JLabel("-User mode");
		subtitleLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		subtitleLabel.setBounds(298, 30, 114, 24);
		add(subtitleLabel);
	}

	void startJudge(){
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				for(int i=0; i<mainGUI.numberOfTestCase; i++){
					user.run(i);
				}
				Judge judge = new Judge();
				for(int i=0; i<mainGUI.numberOfTestCase; i++) {
					if (judge.judgeTestCase(i)) {
						System.out.printf("Test Case %d is Failed!%n", i + 1);
					} else {
						System.out.printf("Test Case %d is accepted!%n", i + 1);
					}
				}
				return true;
			}
		};
		worker.execute();
	}
}
