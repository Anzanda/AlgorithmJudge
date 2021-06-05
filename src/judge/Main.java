package judge;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
		DirManagement dir = new DirManagement();
		dir.init();//set Directory C:/judge_!! prevent file exception!!
		MainGUI mainGUI = new MainGUI(dir.getNumOfTestCase());//MainGUI instance is made with dir.getNumOfTestCase()
		//getNumOfTestCase() is for counting testCase that have already made by admin.
	}

}


