package judge;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainGUI extends JFrame{
	public UserGUI userPanel= null;
	public AdminGUI adminPanel = null;
	public int numberOfTestCase = 0;

	MainGUI(int numberOfTestCase) throws IOException {
		this.numberOfTestCase = numberOfTestCase;
		setTitle("Algorithm Judge");
		
		userPanel = new UserGUI(this);
		adminPanel = new AdminGUI(this);
		getContentPane().add(userPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 674);
		setVisible(true);


	}
	public void change(String panelName){
		if(panelName.equals("userPanel")){
			getContentPane().removeAll();
			getContentPane().add(adminPanel);
			revalidate();
			repaint();
		}
		else{
			getContentPane().removeAll();
			getContentPane().add(userPanel);
			revalidate();
			repaint();
		}
	}
}
