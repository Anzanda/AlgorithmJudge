package judge;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Compiler {
    //set으로 하는 게 더 나을까? 나중에 쓸 거 같아서 일단 public으로 하긴 했는데 ...쩝
    protected final String rootPath = "c:\\judge_"; //use special character like !_@(#!)% to void to effect original file!public File root;
    protected File root;
    protected PrintStream original = System.out;

    Compiler(){

    }

    void run(int i) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, MalformedURLException {
    }
    void setPath(int i) {

    }
}
