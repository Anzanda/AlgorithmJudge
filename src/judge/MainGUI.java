package judge;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainGUI extends JFrame{
	public UserGUI userPanel= null;
	public AdminGUI adminPanel = null;
	public int numberOfTestCase = 0;

	MainGUI(int numberOfTestCase) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
		this.numberOfTestCase = numberOfTestCase;//numberOfTestCase is very important role in my project.
		//It can be criteria that how many we score that user's source code.
		setTitle("Algorithm Judge");

		ImageIcon img =new ImageIcon("C:\\Users\\sunfl\\Desktop\\doge.png");
		setIconImage(img.getImage());//I love doge! not doge coin.

		userPanel = new UserGUI(this);
		adminPanel = new AdminGUI(this);
		getContentPane().add(userPanel);//default GUI is UserGUI.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 674);
		setVisible(true);


	}
	public void change(String panelName){//We can change mode by this function
		//Just only exchange panel in main frame!
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
