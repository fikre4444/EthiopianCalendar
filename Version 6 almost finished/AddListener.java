import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class AddListener implements ActionListener {
public TreeSet<toDo> todolist = new TreeSet<>();
JFrame frame;
int currentYear;
JTextArea area;
String[] months = {"September", "October", "November", "December", "January", "February", "March", "April", "May", "June", "July", "August", "Pagume"};
String[] date = new String[30];
String[] dateP = {"1", "2", "3", "4", "5"};
String[] dateP2 = {"1", "2", "3", "4", "5", "6"};
JPanel panelTop2;
JComboBox<String> cmbMonth;
JComboBox<String> cmbDate;

public AddListener(int year){
currentYear= year;
for(int i=1; i<31; i++)
date[i-1]=i+""; 
loadList();
}

public void loadList(){
try{
FileInputStream fis = new FileInputStream("Saved.dat");
ObjectInputStream ois = new ObjectInputStream(fis);
int siz =(Integer)ois.readObject();
for(int i=0; i<siz; i++){
Object o = ois.readObject(); 
toDo td = (toDo)o;
todolist.add(td);  }
}
catch(IOException ex){
System.out.println("i didn't load the files");
}
catch(Exception ee){}
}




public void actionPerformed(ActionEvent eve){
frame = new JFrame("Add An Event");
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

area = new JTextArea("Write the Event here", 5, 40);

JPanel panelTop1 = new JPanel();
JLabel labelTop = new JLabel("Choose a date from the list, write an event and then click add");
panelTop1.setBackground(Color.gray);
labelTop.setForeground(Color.white);
panelTop1.add(labelTop);
panelTop2 = new JPanel();
cmbMonth = new JComboBox<>(months);
cmbDate = new JComboBox<>(date);
cmbMonth.addActionListener(new comboMonth());
panelTop2.add(new JLabel(currentYear+":"));
panelTop2.add(cmbMonth);
panelTop2.add(cmbDate);
JPanel panelTop = new JPanel();
panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
panelTop.add(panelTop1);
panelTop.add(panelTop2);
frame.add(panelTop, BorderLayout.NORTH);

JPanel panelBottom = new JPanel();
JButton buttonAdd = new JButton("Add to Events List");
JButton buttonClear = new JButton("Clear");
JButton buttonSave = new JButton("Save Added Events");
//JButton buttonDisplay = new JButton("DisplayEvents");
panelBottom.add(buttonAdd);
panelBottom.add(buttonClear);
panelBottom.add(buttonSave);
//panelBottom.add(buttonDisplay);

panelBottom.setBackground(Color.gray);
frame.add(panelBottom, BorderLayout.SOUTH);

buttonAdd.addActionListener(new eventAdd());
buttonClear.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent eve){
area.setText(""); }});
buttonSave.addActionListener(new Save());
//buttonDisplay.addActionListener(new DisplayList());


frame.add(area, BorderLayout.CENTER);




frame.setSize(500, 300); 
frame.setResizable(false);
frame.setVisible(true); } 


public class comboMonth implements ActionListener{
public void actionPerformed(ActionEvent eve){
changeList();


}}


public void changeList(){
if(cmbMonth.getSelectedItem()=="Pagume" && (cmbDate.getItemCount()!=6 || cmbDate.getItemCount()!=5)){
cmbDate.removeAllItems();
if(currentYear%4==3)
for(int i=0; i<6; i++){
cmbDate.addItem(dateP2[i]);}
else 
for(int i=0; i<5; i++){
cmbDate.addItem(dateP[i]); }  
}
else if(cmbMonth.getSelectedItem()!="Pagume" && (cmbDate.getItemCount()==6 || cmbDate.getItemCount()==5)){
cmbDate.removeAllItems();
for(int i=0; i<30; i++){
cmbDate.addItem(date[i]);}
}}


public class eventAdd implements ActionListener {
public void actionPerformed(ActionEvent eve){
String mm= (String)cmbMonth.getSelectedItem();

int ind=-1;
for(int i=0; i<13; i++){
if(mm.equals(months[i]))
ind=i; }


boolean duplicate = false;
toDo td = new toDo();
td.year = currentYear;
td.monthN = (String)cmbMonth.getSelectedItem();
td.month = 1+ind;
td.date = Integer.parseInt((String)cmbDate.getSelectedItem());
for(toDo tt : todolist){
if(td.compareTo(tt)==0){
td=tt;
duplicate=true; 
break; }}

td.events.add(area.getText());
if(!duplicate)
todolist.add(td); 
area.setText("");
}}


public class Save implements ActionListener  {
public void actionPerformed(ActionEvent eve) {
File file = new File("Saved.dat");
try{
FileOutputStream  fos = new FileOutputStream(file);
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(todolist.size());
for(toDo td: todolist)
oos.writeObject(td); 
oos.close();
System.out.println("finished saving");}
catch(IOException ee){}



}}

/*public class DisplayList implements ActionListener {
public void actionPerformed(ActionEvent eve){
for(toDo td : todolist){
System.out.println("The events to be done on the date: "+td.monthN+" "+td.date+", "+td.year+" are: ");
for(String ss : td.events){
System.out.println(ss); }} 
} }*/




}











