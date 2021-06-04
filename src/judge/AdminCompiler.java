package judge;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class AdminCompiler extends Compiler{
    private String sourceCode;
    private final int fileIdx;
    private String inputPath;
    private String outputPath;
    private String sourcePath;
    private File sourceFile;
    private File inputFile;
    private File outputFile;
    private String inputTxt;
    private String outputTxt;
    AdminCompiler(String sourceCode, int idx) {
        super();//super class constructor 수정해야함!
        this.fileIdx = idx;
        this.sourceCode = sourceCode.replaceFirst("public static void main\\(String\\[\\] args\\)", "public static void solve()");

    }
    @Override
    void run() throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        setPath();
        PrintStream outputPrintStream = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(outputPrintStream);
        System.setIn(new FileInputStream(inputPath));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(System.in, System.out, null, sourceFile.getPath());
        String tmpPath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\judge\\AdminCompiler.java";
        File tmp =new File(tmpPath);
        compiler.run(null, null, null, tmp.getPath());

        AdminCompiler tmpcomp = new AdminCompiler(sourceCode, fileIdx);
        tmpcomp.solve();
    }
    void solve(){
        Answer ans = new Answer();
        //ans.solve();
    }

    @Override
    void setPath(){

        if(fileIdx != 0){
            inputTxt = "input"+fileIdx+".txt";//set input TextFile format like input1.txt input2.txx ...!
            outputTxt = "output"+fileIdx+".txt";//set output TextFile format ...!
        }
        else{
            inputTxt = "input.txt";
            outputTxt = "output.txt";
        }

        sourcePath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\judge\\Answer.java";
        inputPath = rootPath+"\\admin\\input\\"+inputTxt;
        outputPath = rootPath+"\\admin\\output\\"+outputTxt;


        root = new File(rootPath);
        sourceFile = new File(sourcePath);
        inputFile = new File(inputPath);
        outputFile = new File(outputPath);

        sourceFile.getParentFile().mkdirs();
        try{
            Files.write(sourceFile.toPath(), sourceCode.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    void makeInputTxt(String input) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write(input);
        writer.close();
    }
}
