import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DirManagement {
    protected final String rootPath = "c:\\judge!_@(#!)%("; //use special character like !_@(#!)% to void to effect original file!public File root;
    DirManagement(){

    }

    void init() throws IOException {
        setDir();
        setClass();
    }
    void setDir(){
        File adminInputFile = new File(rootPath+"\\admin\\input");
        File adminOutputFile = new File(rootPath+"\\admin\\output");
        File userOutputFile = new File(rootPath + "\\user\\output");
        adminInputFile.mkdirs();
        adminOutputFile.mkdirs();
        userOutputFile.mkdirs();
    }
    void setClass() throws IOException {
        String solPath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\Solution.java";
        String ansPath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\Answer.java";

        String solCode ="class Solution {\n" +
                "    public static void solve(){\n" +
                "        \n" +
                "    }\n" +
                "}";
        String ansCode = "class Answer {\n" +
                "    public static void solve(){\n" +
                "\n" +
                "    }\n" +
                "}\n";
        File solFile = new File(solPath);
        File ansFile = new File(ansPath);
        Files.write(solFile.toPath(), solCode.getBytes(StandardCharsets.UTF_8));
        Files.write(ansFile.toPath(), ansCode.getBytes(StandardCharsets.UTF_8));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, solFile.getPath());
        compiler.run(null, null, null, ansFile.getPath());
    }
    void resetCode() throws IOException {
        setClass();
    }

    void resetDir(){
        deleteFile(rootPath);
    }
    public static void deleteFile(String path) {
        File deleteFolder = new File(path);

        if(deleteFolder.exists()){
            File[] deleteFolderList = deleteFolder.listFiles();

            for (int i = 0; i < deleteFolderList.length; i++) {
                if(deleteFolderList[i].isFile()) {
                    deleteFolderList[i].delete();
                }else {
                    deleteFile(deleteFolderList[i].getPath());
                }
                deleteFolderList[i].delete();
            }
            deleteFolder.delete();
        }
    }
}
