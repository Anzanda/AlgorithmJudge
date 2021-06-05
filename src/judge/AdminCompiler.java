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

public class AdminCompiler extends Compiler{
    private String sourceCode;
    private int fileIdx;
    private String inputPath;
    private String outputPath;
    private final String sourcePath= "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\source";
    private File inputFile;
    private File outputFile;
    private String inputTxt;
    private String outputTxt;
    private DirManagement dir = new DirManagement();
    AdminCompiler() {
        super();

    }
    @Override
    void run(int i) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, MalformedURLException {
        setPath(i);
        PrintStream outputPrintStream = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(outputPrintStream);
        System.setIn(new FileInputStream(inputPath));

        File classRepo = new File("C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src");
        URL[] classLoaderUrls = new URL[]{classRepo.toURI().toURL()};

        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
        Class<?> ansClass = urlClassLoader.loadClass("source.Answer");
        Object ansObj = ansClass.newInstance();
        Method method = ansClass.getMethod("solve");
        method.invoke(ansObj);

        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Override
    void setPath(int i){
        fileIdx = i;
        if(fileIdx != 0){
            inputTxt = "input"+fileIdx+".txt";//set input TextFile format like input1.txt input2.txx ...!
            outputTxt = "output"+fileIdx+".txt";//set output TextFile format ...!
        }
        else{
            inputTxt = "input.txt";
            outputTxt = "output.txt";
        }

        inputPath = rootPath+"\\admin\\input\\"+inputTxt;// AdminCompiler made inputFile in admin/input!
        outputPath = rootPath+"\\admin\\output\\"+outputTxt;//AdminCompiler made outputFile in admin/output!


        inputFile = new File(inputPath);
        outputFile = new File(outputPath);
    }
    void makeInputTxt(String input,int i) throws IOException {
        setPath(i);//set path
        /* write in be designated text file process! */
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write(input);
        writer.close();
    }
    String getSourceCode() throws IOException {
        sourceCode = "";
        Path path = Paths.get(sourcePath + "\\Answer.java");//Answer.java is Admin's code!
        /* read that .java like textFile */
        //it will be okay cause .java and .txt is the same text basically.
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            sourceCode += line + "\n";
        }
        return sourceCode;
    }
    void reset() throws IOException {
        dir.reset();//TestCase's input is reset by DirManagement but we abstract for readibility!
    }
}
