package judge;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class Compiler {
    protected final String rootPath = "c:\\judge_";
    //System.in and System.out are changed arbitrary with Admin.run() and User.run().
    //So I preserve original in and out!
    protected final InputStream originalInput = System.in;
    protected final PrintStream originalOutput = System.out;
    Compiler(){

    }

    void run(int i) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, MalformedURLException {
    }
    void setPath(int i) {

    }
}
