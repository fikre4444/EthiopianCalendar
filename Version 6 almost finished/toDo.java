import java.util.ArrayList;
import java.io.*;
public class toDo implements Comparable<toDo>, Serializable {
public int year;
public int month;
public int date;
public String monthN;
public ArrayList<String> events = new ArrayList<>();


public int compareTo(toDo td)throws NullPointerException, ClassCastException {
if(this.year>td.year)
return 1; 
else if(this.year<td.year)
return -1; 
else {
	if(this.month>td.month)
	     return 1; 
	else if(this.month<td.month)
	   return  -1; 
	else {
		if(this.date >td.date)
		     return 1; 
		else if(this.date<td.date)
		       return -1; 
		else return 0;      }      } 

}

 }