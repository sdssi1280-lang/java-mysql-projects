import java.util.Scanner;
public class discount
{
    public static void main(String args [])
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the amount of purchase");
        int a=sc.nextInt();
        if(a<=5100)
        {
            int d=a*5/100;
            int ap=a-d;
            System.out.println("discount="+d);
            System.out.println("amount payable="+ap);
            System.out.println("the gift is a wallet");
        
        }
        else if(a>=5101 && a<=8100)
        {
           int d=a*11/100;
            int ap=a-d;
            System.out.println("discount="+d);
            System.out.println("amount payable="+ap);
            System.out.println("the gift is a wrist watch");
         
        }
        else if(a<=8101 && a>=1600)
        {
           int d=a*15/100;
            int ap=a-d;
            System.out.println("discount="+d);
            System.out.println("amount payable="+ap);
            System.out.println("the gift is a wall clock");
          
        }
        else
        {
            int d=a*21/100;
            int ap=a-d;
            System.out.println("discount="+d);
            System.out.println("amount payable="+ap);
            System.out.println("the gift is a travel kit");
          
        }
    }
}