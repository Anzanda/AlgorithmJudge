package judge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DirManagement {
    protected final String rootPath = "c:\\judge_"; //use special character like !_@(#!)% to void to effect original file!public File root;
    private String adminInputPath = rootPath+"\\admin\\input";
    private String adminOutputPath = rootPath+"\\admin\\output";
    private String userOutputPath = rootPath + "\\user\\output";
    private String sourcePath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge\\src\\source\\";


    DirManagement(){

    }


    void init() throws IOException {
        setDir();
    }
    void setDir(){//admin input/output and user ouput directory setting!!
        //to void file exception!
        File adminInputFile = new File(adminInputPath);
        File adminOutputFile = new File(adminOutputPath);
        File userOutputFile = new File(userOutputPath);
        adminInputFile.mkdirs();
        adminOutputFile.mkdirs();
        userOutputFile.mkdirs();
    }


    void reset() throws IOException {
        //Resetting TestCase means that admin's input/output is no longer needed.
        resetDir(adminInputPath);
        resetDir(adminOutputPath);
        System.out.println("sibal");
//        init();
    }
    void resetDir(String path) {
        deleteFile(path);
    }
    public static void deleteFile(String path) {
        File deleteFolder = new File(path);

        if(deleteFolder.exists()){
            File[] deleteFolderList = deleteFolder.listFiles();

            for (int i = 0; i < deleteFolderList.length; i++) {
                if(deleteFolderList[i].isFile()) {
                    deleteFolderList[i].delete();
                }else {
                    deleteFile(deleteFolderList[i].getPath());//recursive call is used.
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


    int countDir(String path){
        File textFolder = new File(path);
        File[] textFileList = textFolder.listFiles();
        return textFileList.length;
    }
    int getNumOfTestCase(){
        return countDir(adminInputPath);
    }

}
