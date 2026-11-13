import java.util.Scanner;
public class task1 {
    public static void main(String[] args) {
System.out.println("Vvedite  kolichestvo el ");
Scanner scan= new Scanner(System.in);
int k = scan.nextInt();
long[] numbers = new long[k];
System.out.println("Daj chisla ");
        for (int i = 0; i < k; i++) {
            numbers[i] = scan.nextLong();
        }
for (int i=0; i < k; i++){
    long n = numbers[i];
    long tmp = n;
    int len =0;
    while (tmp >0){
        len++;
        tmp=tmp/10;
    }
    if (len%2==0){
        int [] count=new int[10];
        tmp = n;
        while (tmp>0){
            int digit = (int) (tmp%10);
            count[digit]++;
            tmp= tmp/10;

        }
        System.out.println("number" + n + ":");
        for(int d=0; d<10; d++){
            System.out.println("digit " + d + " : " + count[d]);
        }
    }
}

    }
}