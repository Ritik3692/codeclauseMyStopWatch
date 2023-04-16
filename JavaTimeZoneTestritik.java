
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.TimeZone;


public class JavaTimeZoneTestritik 
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		 TimeZone timeZone = TimeZone.getTimeZone("IST");
		 SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy EEEE hh:mm:ss a");
		 date_format.setTimeZone(timeZone);
		 Date date= new Date();
		 String current_date_time = date_format.format(date);
		 System.out.println("The current Indian time is : "+current_date_time);
		 String S="";
		 System.out.println("Enter the TimeZone you want to convert time into : ");
		 S=sc.next();
		 timeZone=TimeZone.getTimeZone(S);
		 date_format.setTimeZone(timeZone);
		 current_date_time=date_format.format(date);
		 System.out.println("The current time in the "+S+" is : "+current_date_time);
		 
		 
		 
	}
}