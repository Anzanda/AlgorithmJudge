package source;
import java.util.ArrayList;
import java.util.Scanner;

public class Answer {
    public static void solve(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=0; i<n; i++){
            int num = scan.nextInt();
            arr.add(num);
        }
        int max = arr.get(0);
        int min = arr.get(0);
        for(int i=1; i<arr.size(); i++){
            if(max < arr.get(i)){
                max = arr.get(i);
            }
            if(min > arr.get(i)){
                min = arr.get(i);
            }
        }
        System.out.println(max);
        System.out.println(min);
    }
}
