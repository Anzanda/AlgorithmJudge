import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class UserCompiler extends Compiler{
    private String sourceCode;
    private final int fileIdx;
    private String inputPath;
    private String outputPath;
    private String sourcePath;
    private File sourceFile;
    private File inputFile;
    private File outputFile;
    UserCompiler(String sourceCode, int idx){
        super();
        this.fileIdx = idx;
        this.sourceCode = sourceCode.replaceFirst("public static void main\\(String\\[\\] args\\)", "public static void solve()");
    }
    @Override
    void run() throws FileNotFoundException {
        setPath();
        PrintStream outputPrintStream = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(outputPrintStream);
        System.setIn(new FileInputStream(inputPath));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(System.in, System.out, null, sourceFile.getPath());

        Solution sol = new Solution();
        sol.solve();

    }
    @Override
    void setPath(){
        String inputTxt;
        String outputTxt;

        if(fileIdx != 0){
            inputTxt = "input"+fileIdx+".txt";//set input TextFile format like input1.txt input2.txx ...!
            outputTxt = "output"+fileIdx+".txt";//set output TextFile format ...!
        }
        else{
            inputTxt = "input.txt";
            outputTxt = "output.txt";
        }

        sourcePath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\Solution.java";
        inputPath = rootPath+"\\admin\\input\\"+inputTxt;
        outputPath = rootPath+"\\user\\output\\"+outputTxt;


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

}
