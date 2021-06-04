package judge;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UserCompiler extends Compiler{
    private String sourceCode;
    private int fileIdx;
    private String inputPath;
    private String outputPath;
    private final String sourcePath = rootPath + "\\Solution.java";
    private File sourceFile;
    private File outputFile;

    UserCompiler(String sourceCode, int fileIdx) throws FileNotFoundException {
        super();
    }

    @Override
    void run(int i) throws FileNotFoundException, MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        setPath(i);
        PrintStream outputPrintStream = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(outputPrintStream);
        System.setIn(new FileInputStream(inputPath));

        File classRepo = new File("C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src");
        URL[] classLoaderUrls = new URL[]{classRepo.toURI().toURL()};
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        Class<?> solClass = urlClassLoader.loadClass("source.Solution");
        Object solObj = solClass.newInstance();
        Method method = solClass.getMethod("solve");
        method.invoke(solObj);
    }
    @Override
    void setPath(int i){
        String inputTxt;
        String outputTxt;
        fileIdx = i;
        if(fileIdx != 0){
            inputTxt = "input"+fileIdx+".txt";//set input TextFile format like input1.txt input2.txx ...!
            outputTxt = "output"+fileIdx+".txt";//set output TextFile format ...!
        }
        else{
            inputTxt = "input.txt";
            outputTxt = "output.txt";
        }

        inputPath = rootPath+"\\admin\\input\\"+inputTxt;
        outputPath = rootPath+"\\user\\output\\"+outputTxt;


        root = new File(rootPath);
        sourceFile = new File(sourcePath);
        outputFile = new File(outputPath);
    }

    String getSourceCode() throws IOException {
        sourceCode = "";
        Path path = Paths.get(sourcePath);
        List<String> lines = Files.readAllLines(path);
        for(String line: lines){
            sourceCode += line+"%n";
        }
        return sourceCode;
    }
}
