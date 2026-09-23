import java.util.Scanner;
public class madrid 
{
    public static void main(String args [])
    {
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter a positive 2-digit number");
            int n=sc.nextInt();
            int d1=n/10;
            int d2=n%10;
            if(Math.abs(d1-d2)==2)
            {
                System.out.println("difference is 2");
            }
            else
            {
                System.out.println("difference is not 2");
            }
    }
}