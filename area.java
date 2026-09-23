import java.util.Scanner;
public class area
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("1.area of circle");
        System.out.println("2.area of square");
        System.out.println("3.area of rectangle");
        System.out.println("enter your choice");
        int n=sc.nextInt();
        switch(n)
        {
            case 1:
                System.out.println("enter the radius");
                int r=sc.nextInt();
                double a=3.14*(r*r);
                System.out.println("area of circle is ="+a);
                break;
            case 2:
                System.out.println("enter the side");
                int s=sc.nextInt();
                a=s*s;
                System.out.println("area of square is ="+a);
                break;
            case 3:
                System.out.println("enter the length");
                int l=sc.nextInt();
                System.out.println("enter the breadth");
                int b=sc.nextInt();
                a=l*b;
                System.out.println("area of rectangle is ="+a);
                break;
            default:
                System.out.println("incorrect output");
        }
    }
}