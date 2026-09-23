import java.util.Scanner;
public class weightcharge
{
    public static void main(String args [])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the parcel weight");
        double w=sc.nextDouble();
        double charge=0;
        if(w<=15)
        {
            charge=w*35;
        }
        else if(w<=30)
        {
            charge=15*35+(w-15)*25;
        }
        else if(w<=40)
        {
            charge=15*35+15*25+(w-30)*20;
        }
        else
        {
            charge=15*35+15*25+10*20+(w-40)*15;
        }
        System.out.println("total charge="+charge);
    }
}