package judge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class Judge{
    String solPath;
    String ansPath;
    protected final String rootPath = "c:\\judge_";
    Judge(){}

    boolean judgeTestCase(int i) throws IOException {
        /* First, we have to set path! */
        String solTxt;
        String ansTxt;
        if(i != 0){
            solTxt = "output"+i+".txt";//cause output files are named that output.txt, output1.txt, output2.txt . . .
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

        /* Second, read textFiles in our paths */
        List<String> solLines = Files.readAllLines(solPipe);
        List<String> ansLines = Files.readAllLines(ansPipe);

        boolean flag = false;
        Iterator<String> sIter = solLines.iterator();
        Iterator<String> aIter = ansLines.iterator();
        while(sIter.hasNext() && aIter.hasNext()){
            String sol = sIter.next();
            String ans = aIter.next();
            if(!sol.equals(ans)){//ans and sol is different is failed!
                flag = true;
                break;
            }
        }
        if(sIter.hasNext() || aIter.hasNext()){
            //different of line number means not equal
            flag = true;
        }

        return flag;
    }
}
