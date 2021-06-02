import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class helloworld {
    public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
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
        source = source.replaceAll("public static void main\\(String\\[\\] args\\)", "public void solve()");
        source = source.replaceFirst("public static void main()", "public void solve()");//main exception처리!
        String rootPath = "C:\\Users\\sunfl\\IdeaProjects\\AlgorithmJudge";
        File root = new File(rootPath);
        File sourceFile = new File(root, "src/Solution.java");
        File inputFile = new File(root, "src/intput.txt");
        File outputFile = new File(root, "src/output.txt");

        sourceFile.getParentFile().mkdirs();
        try {
            Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
        }   catch (IOException e){
            e.printStackTrace();
        }
        PrintStream outputPrintStream = new PrintStream(new FileOutputStream(outputFile));
        PrintStream originalOut = System.out;
        System.setOut(outputPrintStream);
        System.setIn(new FileInputStream(rootPath+"\\src\\input.txt"));



        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(System.in, System.out, null, sourceFile.getPath());

//        Solution t = new Solution();
//        t.solve();//solve랑 따로해야댐! 굳이? 할 필요 없을 지도?


//        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
//        Class<?> cls = Class.forName("test.Test", true, classLoader); // class method쓰는 방법이에요
//        Object instance = cls.newInstance();
//        System.out.println(instance); // Should print "test.Test@hashcode".
//        String s = "hello,world";
//        byte[] b = s.getBytes(StandardCharsets.UTF_8);
//        out.write(b);
    }
}
