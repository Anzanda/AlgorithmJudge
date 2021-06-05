package judge;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AdminGUI extends JPanel {
	private JTextArea codeField;
	private JTextArea inputField;
	private MainGUI mainGUI;
	private AdminCompiler admin = new AdminCompiler();
	private DirManagement dir = new DirManagement();
	private JLabel numoftestLabel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AdminGUI(MainGUI mainGUI) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
		this.mainGUI = mainGUI;
		setBounds(100, 100, 873, 674);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		for(int i=0; i<mainGUI.numberOfTestCase; i++){
			admin.run(i);
		}//To prevent error with situation that testcase have already made!
		//So we make each output.txt with input.txt that already been.

		/* title */
		JLabel titleLabel = new JLabel("Algorithm Judge");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 39));
		titleLabel.setBounds(10, 10, 301, 53);
		add(titleLabel);
		
		JLabel subtitleLabel = new JLabel("-Admin mode");
		subtitleLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		subtitleLabel.setBounds(298, 30, 130, 24);
		add(subtitleLabel);

		/* Admin's code TextArea */
		JScrollPane codePane = new JScrollPane();
		codePane.setBounds(20, 73, 524, 552);
		add(codePane);

		codeField = new JTextArea();
		codeField.setBackground(Color.WHITE);
		codePane.setViewportView(codeField);
		codeField.append(admin.getSourceCode());//get sourcecode in AdminCompiler as we can see in UserGUI!
		codeField.setColumns(10);

		/* Input field */
		JScrollPane inputPane = new JScrollPane();
		inputPane.setBounds(556, 73, 301, 330);
		add(inputPane);

		inputField = new JTextArea();
		inputPane.setViewportView(inputField);
		inputField.setColumns(10);

		/* To notify how many testcase we have */
		numoftestLabel = new JLabel("TestCase: "+mainGUI.numberOfTestCase);
		numoftestLabel.setBackground(Color.WHITE);
		numoftestLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		numoftestLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numoftestLabel.setBounds(691, 426, 166, 40);
		add(numoftestLabel);


		JButton UserModeBtn = new JButton("Usermode");
		UserModeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGUI.change("adminPanel");//change main frame admin to user!
			}
		});
		UserModeBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		UserModeBtn.setBounds(617, 24, 114, 40);
		add(UserModeBtn);

		
		JButton inputSubmitBtn = new JButton("Input Submit");
		inputSubmitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeTestCase();//input submit button event excution admin code with inputFiles.
			}
		});
		inputSubmitBtn.setFont(new Font("Dialog", Font.ITALIC, 12));
		inputSubmitBtn.setBounds(743, 24, 114, 40);
		add(inputSubmitBtn);


		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					admin.reset();//file delete!
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
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
				admin.makeInputTxt(inputField.getText(), mainGUI.numberOfTestCase);//input file is made in admin/input
				admin.run(mainGUI.numberOfTestCase);//output file is made in admin/output
				mainGUI.numberOfTestCase++;//testCase must be plus one!

				numoftestLabel.setText("TestCase: "+mainGUI.numberOfTestCase);
				inputField.setText(""); // input field reset
				return true;
			}
		};
		worker.execute();
	}
}
