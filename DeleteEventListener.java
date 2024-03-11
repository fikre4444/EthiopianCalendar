import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class DeleteEventListener implements ActionListener {

TreeSet<toDo> todoli;
ArrayList<String[]> allDates;
JLabel result;
JTextArea area;
String montt[]={"September", "October", "November", "December", "January", "February", "March", "April", "May", "June", "July", "August", "Pagume"};
JComboBox<String> Month;
JComboBox<String> datess;
public DeleteEventListener(TreeSet<toDo> tdl){
todoli=tdl;  
}

public void actionPerformed(ActionEvent eve){
if(todoli.size()!=0){
LinkedHashSet<Integer> monthsTo = new LinkedHashSet<>();
for(toDo td : todoli){
if(!monthsTo.contains(td.month))
monthsTo.add(td.month); }
int mont[] = new int[monthsTo.size()];  //holds the list months that have an event in them (in number form)
int i=0;
for(int s : monthsTo){
mont[i]=s;
i++; }

allDates = new ArrayList<>();
for(int j=0; j<=13; j++){
allDates.add(new String[31]); 
}

for(i=0; i<mont.length; i++)
System.out.println(mont[i]);
for(toDo td : todoli){
allDates.get(td.month)[td.date]=td.date+""; }
for(String[] s: allDates){
for(int k =0; k<s.length; k++)
     if(s[k]!=null)
	System.out.print(s[k]+" ");
System.out.println(); }





Month = new JComboBox<>();
for(int j=0; j<mont.length; j++)
Month.addItem(montt[mont[j]-1]);

String[] d = allDates.get(mont[0]);
datess = new JComboBox<>();
for(int j=0; j<d.length; j++){
if(d[j]!=null)
datess.addItem(d[j]); }

Month.addActionListener(new MonthListener());




JFrame frame = new JFrame("Delete Events of One day");
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setSize(400, 150);
JPanel panelTitle = new JPanel();
panelTitle.setBackground(Color.gray);
JLabel Title = new JLabel("Select a Day with an event then click delete");
Title.setForeground(Color.white);
Title.setFont(new Font("sans-serif", Font.BOLD, 13));
panelTitle.add(Title);
JPanel panelTop = new JPanel();
panelTop.setBackground(Color.gray);
panelTop.setLayout(new BorderLayout());
panelTop.add(panelTitle, BorderLayout.NORTH);
frame.add(panelTop, BorderLayout.NORTH);
JPanel pinter = new JPanel();  //pinter means panel interactive
pinter.setBackground(Color.gray);
pinter.setLayout(new BorderLayout());
panelTop.add(pinter, BorderLayout.SOUTH);
JPanel pintert = new JPanel(); //pintert means pinter top
pintert.add(Month);
pintert.add(datess);
pinter.add(pintert, BorderLayout.NORTH);
JPanel pinterb = new JPanel(); //pinterb means pinter bottom
JButton DeleteDate = new JButton("Delete DateEvent");
DeleteDate.addActionListener(new DeleteDateListener());
pinterb.add(DeleteDate);
pinter.add(pinterb, BorderLayout.SOUTH);
result = new JLabel("");
result.setFont(new Font("sans-serif", Font.BOLD, 13));
frame.add(result);
frame.setVisible(true);
}
else {
JOptionPane.showMessageDialog(null, "The Events List is Empty");   }



}

public class MonthListener implements ActionListener {
public void actionPerformed(ActionEvent eve){
String s = (String)Month.getSelectedItem();
numberOfDates(s);



}}

public class DeleteDateListener implements ActionListener {
public void actionPerformed(ActionEvent eve){
int dat = Integer.parseInt((String)(datess.getSelectedItem()));
String mo = (String)(Month.getSelectedItem());
toDo td = null;
for(toDo tt : todoli){
if(dat==(tt.date) && mo.equals(tt.monthN))
td = tt; }
int i=0;
for(toDo tt : todoli){
if(td.monthN.equals(tt.monthN))
i++; }
i--;

allDates.get(td.month)[td.date]=null;
numberOfDates(td.monthN);
if(datess.getItemCount()==0 && Month.getItemCount()!=0){
Month.removeItem(Month.getSelectedItem());
String momo = (String)Month.getItemAt(0);
numberOfDates(momo); }

/*
else if(datess.getItemCount()==1 && Month.getItemCount()==1){
Month.removeItem(Month.getSelectedItem());
datess.removeItem((String)datess.getSelectedItem()); 
JOptionPane.showMessageDialog(null, "The list has been emptied!"); 
System.exit(0);}*/

todoli.remove(td);




}}

public void numberOfDates(String s){
int ind =-1;
for(int i=0; i<13; i++){
if(s.equals(montt[i])){
ind=i;
break; }}
ind++;
String ss[] = allDates.get(ind);
datess.removeAllItems();
for(int i=0; i<ss.length; i++){
if(ss[i]!=null){
datess.addItem(ss[i]); }} 


 }






}