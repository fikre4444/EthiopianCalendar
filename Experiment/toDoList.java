import java.util.*;
import java.io.*;


public class toDoList implements Serializable {
TreeSet<toDo> todolist = new TreeSet<>(); 

public static void main(String[] args) throws Exception{

Scanner aa = new Scanner(System.in);
toDoList tdl = new toDoList();
int choice;

while(true){
System.out.println("1. input");
System.out.println("2. displayAlready");
System.out.println("3. save into file");
System.out.println("4. read an object from a file");
choice = aa.nextInt();
if(choice==1){
tdl.input(); }
else if(choice==2)tdl.displayAlready(); 
else if(choice==3){
File file = new File("todo.dat");
FileOutputStream fos = new FileOutputStream(file);
ObjectOutputStream oos = new ObjectOutputStream(fos); 
oos.writeObject(tdl);}
else if(choice==4){
File file = new File("todo.dat");
FileInputStream fis = new FileInputStream(file);
ObjectInputStream ois = new ObjectInputStream(fis);
toDoList tdl2 = (toDoList)(ois.readObject());
tdl2.displayAlready(); }  
if(choice==5)
break;  
}

}


public  void input(){
Scanner bb = new Scanner(System.in); 
do {
toDo a = new toDo(); 
todolist.add(a);
System.out.println("input year");
a.year= bb.nextInt();
System.out.println("input month");
a.month= bb.nextInt();
System.out.println("input Date");
a.date = bb.nextInt();
bb.nextLine();
while(true){
System.out.println("input the event that you want");
a.events.add(bb.nextLine());
System.out.println("input 1 if you want to continue adding to this date otherwise input another number: ");
if(bb.nextInt()==1)
bb.nextLine(); 
else break;      }
System.out.println("input 1 if you want to add another date and events otherwise input another number: ");

}while(bb.nextInt()==1); }

public void displayAlready(){
if(todolist.isEmpty())
System.out.println("you have no event you bitch");
else {
          for(toDo tt : todolist){
	System.out.println(tt.year+" "+tt.month+" "+tt.date+" The events at this date are: ");
	for(String eve : tt.events)
		System.out.println(eve); 
	System.out.println(); } }}


}