package judge;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		DirManagement dir = new DirManagement();
		dir.init();
		MainGUI mainGUI = new MainGUI(dir.getNumOfTestCase());
	}
}
