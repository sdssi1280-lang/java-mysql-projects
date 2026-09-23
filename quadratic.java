public class quadratic
{
    public static void main(int a,int b,int c)
    {
        int d=b*b-4*a*c;
        if(d>0)
        {
            System.out.println("roots are real and unequal");
        }
        else if(d==0)
        {
            System.out.println("roots are real and equal");
        }
        else
        {
            System.out.println("roots are imaginary and unequal");

        }
    }
}