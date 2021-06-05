package judge;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UserCompiler extends Compiler{
    private String sourceCode;
    private int fileIdx;
    private String inputPath;
    private String outputPath;
    private final String sourcePath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\source";
    //sourceFolder path is constant! as we can define final!
    private File outputFile;
    private String inputTxt;
    private String outputTxt;

    UserCompiler() throws FileNotFoundException {
        super();
    }

    @Override
    void run(int i) throws FileNotFoundException, MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        setPath(i);//Path setting is done
        PrintStream outputPrintStream = new PrintStream(new FileOutputStream(outputFile));//To print output in outputFile(output(i).txt in user)
        System.setOut(outputPrintStream);
        System.setIn(new FileInputStream(inputPath));//To input testCase in inputFile(input(i).txt in admin)

        //Dynamic class loading. I used that because design package judge and source to distinct strictly.
        //URLClassLoader is used in my project!
        File classRepo = new File("C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src");
        URL[] classLoaderUrls = new URL[]{classRepo.toURI().toURL()};
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        //URLClass identify only public class. so we must set class name with public declaration.
        Class<?> ansClass = urlClassLoader.loadClass("source.Solution");
        Object ansObj = ansClass.newInstance();
        Method method = ansClass.getMethod("solve");//Because I fixed method name that call to make output as "solve".
        //So user or admin must set main class as void solve()
        method.invoke(ansObj);//solve() is called

        System.setIn(originalInput);//System.in is connected to my inputPath!
        System.setOut(originalOutput);//System.out is connected to my outputPath!

    }
    @Override
    void setPath(int i){
        fileIdx = i;
        if(fileIdx != 0){
            inputTxt = "input"+fileIdx+".txt";//set input TextFile format like input1.txt input2.txx ...!
            outputTxt = "output"+fileIdx+".txt";//set output TextFile format ...!
        }//Because file name have not to be same others to distinct.
        //So name textfiles as "input.txt" , "input1.txt", "input2.txt", "input3.txt" . . . like that!
        else{
            inputTxt = "input.txt";
            outputTxt = "output.txt";
        }

        inputPath = rootPath+"\\admin\\input\\"+inputTxt;//[IMPORTANT]input is driven by admin folder in UserCompiler!!!
        outputPath = rootPath+"\\user\\output\\"+outputTxt;//[IMPORTANT]output is driven by user folder in UserCompiler!!!

        outputFile = new File(outputPath);
    }

    String getSourceCode() throws IOException {
        sourceCode = "";
        Path path = Paths.get(sourcePath+"\\Solution.java");//Solution.java is User's code!
        List<String> lines = Files.readAllLines(path);
        for(String line: lines){
            sourceCode += line+"\n";
        }

        return sourceCode;
    }
}
