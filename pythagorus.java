import java.util.Scanner;
public class pythagorus
{
    public static void main(String args [])
    {
        Scanner sc=new Scanner(System.in);
        int h=sc.nextInt();
        int p=sc.nextInt();
        int b=sc.nextInt();
        if(h*h==p*p+b*b)
        {
            System.out.println("pythagoreas triplet");
        }
        else
        {
            System.out.println("nota pythyogoras triplet");
        }
    }
}