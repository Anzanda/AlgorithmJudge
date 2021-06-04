package judge;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class Judge{
    String solPath;
    String ansPath;
    protected final String rootPath = "c:\\judge!_@(#!)%("; //use special character like !_@(#!)% to void to effect original file!public File root;
    Judge(){}

    boolean judgeTestCase(int i) throws IOException {
        String solTxt;
        String ansTxt;
        if(i != 0){
            solTxt = "output"+i+".txt";
            ansTxt = "output"+i+".txt";
        }
        else{
            solTxt = "output.txt";
            ansTxt = "output.txt";
        }
        solPath = rootPath+"\\user\\output\\"+solTxt;
        ansPath = rootPath+"\\admin\\output\\"+ansTxt;


        Path solPipe = Paths.get(solPath);
        Path ansPipe = Paths.get(ansPath);
        List<String> solLines = Files.readAllLines(solPipe);
        List<String> ansLines = Files.readAllLines(ansPipe);

        boolean flag = false;
        Iterator<String> sIter = solLines.iterator();
        Iterator<String> aIter = ansLines.iterator();
        while(sIter.hasNext() && aIter.hasNext()){
            String sol = sIter.next();
            String ans = aIter.next();
            //System.out.println("sol: "+sol+" | ans: "+ans);
            if(!sol.equals(ans)){
                flag = true;
                break;
            }
        }
        if(sIter.hasNext() || aIter.hasNext()){
            flag = true;
        }

        return flag;
    }
}
