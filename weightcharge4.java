import java.util.Scanner;
public class weightcharge4
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter weight of parcel");
        int w=sc.nextInt();
        int charge;
        if(w<=100)
        {
            charge=w*2;
        }
        else if(w>=101 && w<=500)
        {
            charge=100*2+(w-100)*3;
        }
        else if(w>=501 && w<=1000)
        {
            charge=100*2+400*3+(w-500)*4;
        }
        else
        {
            charge=100*2+400*3+500*4+(w-1000)*5;
        }
        System.out.println("the charge of parcel is="+charge);
    }
}