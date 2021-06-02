import java.io.FileNotFoundException;

public class practiceCompiler {
    public static void main(String[] args) throws FileNotFoundException {
        String source = "import java.util.ArrayList;\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "class Solution {\n" +
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
                "        System.out.println(min);\n" +
                "    }\n" +
                "}\n";
        Compiler c = new Compiler(source, 0);
        c.run();
    }
}
