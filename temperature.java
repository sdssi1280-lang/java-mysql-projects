import java.util.Scanner;
public class temperature
{
    public static void main(String args [])
    {
         Scanner sc=new Scanner(System.in);
         System.out.println("c.farenheit to celsius");
         System.out.println("f.celsius to farenheit");
         System.out.println("enter your choice");
         char ch=sc.next().charAt(0);
         switch(ch)
         {
             case 'c':
                 System.out.println("enter the temperature in farenheit");
                 double f=sc.nextDouble();
                 
                 double c=(f-32)*5/9;
                 System.out.println("the temperature in celsius is="+c);
                 break;
                 case 'f':
                     System.out.println("enter the temperature in celsius");
                     c=sc.nextDouble();
                     f=(c*9/5)+32;
                     System.out.println("the temperature in farenheit is="+f);
                     break;
                     default:
                         System.out.println("INCORRECT OUTPUT");
                     
            
         }
    }
}