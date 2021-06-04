package judge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class practiceCompiler {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PrintStream originalOut= System.out;
        String source ="package judge;\n"+
                "import java.util.ArrayList;\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "class Answer {\n" +
                "Answer(){}\n"+
                "    public static void main(String[] args){\n" +
                "        Scanner scan = new Scanner(System.in);\n" +
                "        int n = scan.nextInt();\n" +
                "        ArrayList<Integer> arr = new ArrayList<Integer>();\n" +
                "        for(int i=0; i<n; i++){\n" +
                "            int num = scan.nextInt();\n" +
                "            arr.add(num);\n" +
                "        }\n" +
                "        int max = arr.get(0);\n" +
                "        int min = arr.get(0);\n" +
                "        for(int i=1; i<arr.size(); i++){\n" +
                "            if(max < arr.get(i)){\n" +
                "                max = arr.get(i);\n" +
                "            }\n" +
                "            if(min > arr.get(i)){\n" +
                "                min = arr.get(i);\n" +
                "            }\n" +
                "        }\n" +
                "        System.out.println(max);\n" +
                "        System.out.println(min+1);\n" +
                "    }\n" +
                "}\n";
        int n;
        Scanner scan = new Scanner(System.in);
        System.out.print("How many testcase you have: ");
        n = scan.nextInt();

        AdminCompiler admin = new AdminCompiler();
        for(int i=0; i<n; i++){
            admin.run(i);
        }


    }
}
