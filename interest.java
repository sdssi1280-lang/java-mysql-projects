import java.util.Scanner;
public class interest
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("1.Simple interest");
        System.out.println("2.Compound interest");
        System.out.println("Enter your choice");
        int ch=sc.nextInt();
        System.out.println("Principal");
        double p=sc.nextDouble();
        System.out.println("Rate");
        double r=sc.nextDouble();
        System.out.println("Time");
        double t=sc.nextDouble();
        switch(ch)
        {
            case'1':
            double si=(p*r*t)/100;
            double a=p+si;
            System.out.println("Simple Interest="+si);
            System.out.println("Final Amount="+a);
            break;
            case'2':
            double ci=p*Math.pow((1+r/100),t)-1;
            a=p+ci;
            System.out.println("Compound Interest="+ci);
            System.out.println("Final Amount="+a);
            break;
            default:
                System.out.println("incorrect input");
        }
    }
}