package judge;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DirManagement {
    protected final String rootPath = "c:\\judge_"; //use special character like !_@(#!)% to void to effect original file!public File root;
    private String adminInputPath = rootPath+"\\admin\\input";
    private String adminOutputPath = rootPath+"\\admin\\output";
    private String sourcePath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\source\\";
    DirManagement(){

    }

    void init() throws IOException {
        setDir();
        getNumOfTestCase();
    }
    void setDir(){
        File adminInputFile = new File(rootPath+"\\admin\\input");
        File adminOutputFile = new File(rootPath+"\\admin\\output");
        File userOutputFile = new File(rootPath + "\\user\\output");
        adminInputFile.mkdirs();
        adminOutputFile.mkdirs();
        userOutputFile.mkdirs();
    }
    void reset(){
        resetDir(rootPath+"\\admin\\input");
        resetDir(rootPath+"\\admin\\output");
    }
    void resetDir(String path) {
        deleteFile(path);
    }
    int getNumOfTestCase(){
        return countDir(adminInputPath);
    }
    int countDir(String path){
        File textFolder = new File(path);
        File[] textFileList = textFolder.listFiles();
        return textFileList.length;
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
    String getSourceText(String what) throws IOException {
        String retString = "";
        Path filePath = Paths.get(sourcePath+what);
        List<String> lines = Files.readAllLines(filePath);
        for(String line: lines){
            retString += line+"\n";
        }
        return retString;
    }

}
