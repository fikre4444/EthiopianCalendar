import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.io.*;
public class EthioCal {
int yearChanging;
int monthChanging;
int todayEthio[] = new int[4];
String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
//String date[][] = {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"15", "16", "17", "18", "19", "20", "21"}, {"22", "23", "24", "25", "26", "27", "28"}, {"29", "30","","","","",""}};
String date[][] = new String[6][7];
String daysFull[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
String months[]={"September", "October", "November", "December", "January", "February", "March", "April", "May", "June", "July", "August", "Pagume"};
JLabel yearCha;
JLabel monthCha;
JTable table;
JFrame frame;
JScrollPane jsp;
AddListener adl;
public static void main(String[] args){
EthioCal ee = new EthioCal(); 
ee.createEthioCal(); }


public void createEthioCal(){ 
setTodayDateEthio();
frame = new JFrame("Ethiopian Calendar");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
JMenuBar mb = new JMenuBar();
JMenu menu = mb.add(new JMenu("Menu"));
JMenuItem displayAll = new JMenuItem("Display All Saved Events");
displayAll.addActionListener(new displayAllList());
JMenuItem addEvent = new JMenuItem("Add an Event");
JMenuItem clr = new JMenuItem("Clear All Events");
clr.addActionListener(new ClearAll());
adl = new AddListener(todayEthio[0]);
addEvent.addActionListener(adl);
menu.add(addEvent);
menu.add(displayAll);
menu.add(clr);
menu.addSeparator();
JMenuItem determine = new JMenuItem("Determine Holiday Date");
determine.addActionListener(new Determine());
menu.add(determine);
menu.add(new JMenuItem("Convert Date"));
menu.addSeparator();
JMenuItem ext = new JMenuItem("Exit");
menu.add(ext);
ext.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee ){
System.exit(0); }});



setStartDayOfMonth();

JPanel panelTop = new JPanel();  // used for the top panel that holds (year) and (month)
panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));  // BoxLayout  is used to add EachPanel
JPanel panelTopLeft = new JPanel();  // This is the panel that holds the year section
JPanel panelTopRight = new JPanel();  // this is the panel that holds the month section
panelTop.setBorder(new LineBorder(Color.black, 3));
panelTop.repaint();
panelTop.add(panelTopLeft);
panelTop.add(panelTopRight);

JLabel yearLabel = new JLabel("Year");
yearLabel.setFont(new Font("sans-serif", Font.BOLD, 15));
yearLabel.setForeground(Color.black);
panelTopLeft.add(yearLabel);

JButton prevYear = new JButton("<<");
panelTopLeft.add(prevYear);
yearCha = new JLabel(yearChanging+"");
panelTopLeft.add(yearCha);
JButton nextYear = new JButton(">>");
panelTopLeft.add(nextYear);
nextYear.addActionListener(new nextYearListener());
prevYear.addActionListener(new prevYearListener());
yearCha.setForeground(Color.white);
panelTopRight.setBackground(Color.gray);
panelTopLeft.setBackground(Color.gray);
JLabel monthLabel = new JLabel("Month");
monthLabel.setFont(new Font("sans-serif", Font.BOLD, 15));
monthLabel.setForeground(Color.black);
panelTopRight.add(monthLabel);
JButton prevMonth = new JButton("<<");
panelTopRight.add(prevMonth);
monthCha = new JLabel(months[monthChanging]+"");
panelTopRight.add(monthCha);
JButton nextMonth = new JButton(">>");
panelTopRight.add(nextMonth);
nextMonth.addActionListener(new nextMonthListener());
prevMonth.addActionListener(new prevMonthListener());
monthCha.setForeground(Color.white);

frame.add(panelTop, BorderLayout.NORTH);
JPanel panelBottom = new JPanel();//is used for the bottom two elements
panelBottom.setLayout(new BorderLayout());//
JButton eve = new JButton("List of Events this Month");
JLabel tod = new JLabel("");
tod.setText("Today is: "+daysFull[todayEthio[3]-1]+", "+months[todayEthio[1]]+" "+todayEthio[2]+",  "+todayEthio[0]);
panelBottom.add(eve, BorderLayout.EAST);
panelBottom.add(tod, BorderLayout.WEST);
frame.add(panelBottom, BorderLayout.SOUTH);
frame.setSize(415, 365);
frame.setJMenuBar(mb);
frame.setVisible(true);
 }

public class MyTableModel extends DefaultTableModel {
public MyTableModel(Object[][] tableData, Object[] colNames){
super(tableData, colNames); }
public boolean isCellEditable(int row, int col){
return false; }}


public void setTodayDateEthio(){
int yearEthiopian=0;
int monthEthiopian=0;
int dateEthiopian=0;
GregorianCalendar today = new GregorianCalendar();
int yearGregorian = today.get(Calendar.YEAR);
int monthGregorian = today.get(Calendar.MONTH);
int dateGregorian = today.get(Calendar.DAY_OF_MONTH);
int dayOfWeekGregorian = today.get(Calendar.DAY_OF_WEEK);
todayEthio[3]=dayOfWeekGregorian;
if(monthGregorian>=Calendar.JANUARY && monthGregorian<=Calendar.AUGUST)
yearEthiopian = yearGregorian-8;
else yearEthiopian = yearGregorian - 7;
if(monthGregorian==Calendar.JANUARY){
        if(yearEthiopian%4==0){
	if(dateGregorian<10){
		monthEthiopian = Calendar.DECEMBER;
		dateEthiopian = (dateGregorian + 31) - 10;  }
	else {
		monthEthiopian = Calendar.JANUARY;
		dateEthiopian = dateGregorian - 9; } }// closes the yearEthiopian%4 part
       else {
	if(dateGregorian<9){
		monthEthiopian = Calendar.DECEMBER;
		dateEthiopian = (dateGregorian + 31) - 9; }
	else {
		monthEthiopian = Calendar.JANUARY;
		dateEthiopian = dateGregorian - 8; }  } } // closes January case
else if(monthGregorian == Calendar.FEBRUARY){
              if(yearEthiopian%4==0){
	    if(dateGregorian<10){
		monthEthiopian = Calendar.JANUARY;
		dateEthiopian = (dateGregorian + 31) -9; }
	    else {
		monthEthiopian = Calendar.FEBRUARY;
		dateEthiopian = dateGregorian - 8; }  }// closes remainder part
             else  {
	        if(dateGregorian<8){
		monthEthiopian = Calendar.JANUARY;
		dateEthiopian = (dateGregorian + 31) -8; }
	     else {
		monthEthiopian = Calendar.FEBRUARY; 
		dateEthiopian = (dateGregorian - 7);  } } }// closes february case
else if(monthGregorian == Calendar.MARCH){
             if(dateGregorian<10){
		monthEthiopian = Calendar.FEBRUARY;
		dateEthiopian = (dateGregorian + 29) -8;  }
            else {
		monthEthiopian = Calendar.MARCH;
		dateEthiopian = dateGregorian - 9; }  }// closes the march case (shorter)
else if(monthGregorian == Calendar.APRIL){
             if(dateGregorian < 9 ){
		monthEthiopian = Calendar.MARCH;
		dateEthiopian = (dateGregorian + 30)-8; }
             else {
		monthEthiopian = Calendar.APRIL; 
		dateEthiopian = dateGregorian - 8; } }// closes the april case
else if(monthGregorian == Calendar.MAY){
          if(dateGregorian < 9){
		monthEthiopian = Calendar.APRIL;
		dateEthiopian = (dateGregorian + 30) - 8; } 
          else {
		monthEthiopian = Calendar.MAY;
		dateEthiopian = (dateGregorian - 8 );  } }// closes may case
else if(monthGregorian == Calendar.JUNE){
          if(dateGregorian < 8 ){
		monthEthiopian = Calendar.MAY; 
		dateEthiopian = (dateGregorian + 31) -8; }
            else {
		monthEthiopian = Calendar.JUNE;
		dateEthiopian = dateGregorian - 7;  }  }  // closes june case
else if(monthGregorian == Calendar.JULY){
           if(dateGregorian < 8 ){
		monthEthiopian = Calendar.JUNE;
		dateEthiopian = (dateGregorian + 30) - 7; }
           else {
		monthEthiopian = Calendar.JULY;
		dateEthiopian = dateGregorian - 7; } }// closes july case
else if(monthGregorian == Calendar.AUGUST){
           if(dateGregorian < 7 ){
		monthEthiopian = Calendar.JULY; 
		dateEthiopian = (dateGregorian + 31) - 7; }
           else {
		monthEthiopian = Calendar.AUGUST; 
		dateEthiopian = (dateGregorian - 6);  } }// closes augu case.
else if(monthGregorian == Calendar.SEPTEMBER){
           if(yearEthiopian%4==0){
	if(yearGregorian%4==0){
	     if(dateGregorian < 6 ){
		monthEthiopian = Calendar.AUGUST;
		dateEthiopian = (dateGregorian + 31) - 6;  }
	    else {
	              if(dateGregorian < 12 ){
		         monthEthiopian = 12;
		         dateEthiopian = dateGregorian - 5; }
	              else {
		         monthEthiopian = Calendar.SEPTEMBER;
		         dateEthiopian = dateGregorian - 10; } } }// closes the case if second %4
            	 else {
	           if(dateGregorian < 6 ){
		        monthEthiopian = Calendar.AUGUST;
		        dateEthiopian = (dateGregorian + 31) - 7; }
	           else {
		        if(dateGregorian < 12){
			monthEthiopian = 12;
			dateEthiopian = dateGregorian - 6; }
		     else {
			monthEthiopian = Calendar.SEPTEMBER;
			dateEthiopian = dateGregorian - 10; }  } } }//closes first %4
           else {
	       if(yearGregorian%4==0){
		if(dateGregorian < 5){
			monthEthiopian = Calendar.AUGUST;
			dateEthiopian = (dateGregorian + 31) - 5; }
		else {
			if(dateGregorian < 11){
				monthEthiopian = 12;
				dateEthiopian = dateGregorian - 4; }
			else {
				monthEthiopian = Calendar.SEPTEMBER;
				dateEthiopian = dateGregorian - 10; } }}
	      else {
		 if(dateGregorian< 5){
			monthEthiopian = Calendar.AUGUST; 
			dateEthiopian = (dateGregorian + 31) - 6; }
		 else {
			if(dateGregorian< 11){
				monthEthiopian = 12;
				dateEthiopian = dateGregorian - 5; }
			else {
				monthEthiopian = Calendar.SEPTEMBER;
				dateEthiopian = dateGregorian - 10; } } } }}// closes sep
else if(monthGregorian == Calendar.OCTOBER){
            if(yearEthiopian%4==0){
            	  if(dateGregorian< 12){
		 monthEthiopian = Calendar.SEPTEMBER;
		 dateEthiopian = (dateGregorian + 30) - 11;  }
	  else {
		monthEthiopian = Calendar.OCTOBER;
		dateEthiopian = dateGregorian -11; } }
          else {
	      if(dateGregorian<11){
		monthEthiopian = Calendar.SEPTEMBER;
		dateEthiopian = (dateGregorian + 30) - 10; }
	      else {
		monthEthiopian = Calendar.OCTOBER;
		dateEthiopian = dateGregorian - 10; } } }//closes october case
else if(monthGregorian == Calendar.NOVEMBER){
            if(yearEthiopian%4==0){
	if(dateGregorian<11){
		monthEthiopian = Calendar.OCTOBER;
		dateEthiopian = (dateGregorian + 31) - 11; }
	else {
		monthEthiopian = Calendar.NOVEMBER;
		dateEthiopian = dateGregorian - 10; }}// closes %4
           else {
	      if(dateGregorian < 10){
		monthEthiopian = Calendar.OCTOBER;
		dateEthiopian = (dateGregorian + 31) - 10; }
	     else {
		monthEthiopian = Calendar.NOVEMBER; 
		dateEthiopian = dateGregorian - 9; } } }// closes november case
else if(monthGregorian == Calendar.DECEMBER){
           if(yearEthiopian%4==0){
	if(dateGregorian < 11){
		monthEthiopian = Calendar.NOVEMBER;
		dateEthiopian = (dateGregorian + 30) - 10;}
	else {
		monthEthiopian = Calendar.DECEMBER;
		dateEthiopian = dateGregorian - 10; }}
          else {
	   if(dateGregorian<10){
		monthEthiopian = Calendar.NOVEMBER;
		dateEthiopian = (dateGregorian + 30) - 9; }
	   else {
		monthEthiopian = Calendar.DECEMBER; 
		dateEthiopian = dateGregorian - 9; } } }// closes Decemeber	



todayEthio[0]=yearEthiopian;
yearChanging = yearEthiopian;
if(monthEthiopian!=12)  // if the month is not pagume
todayEthio[1]=(monthEthiopian+4)%12;
else todayEthio[1]=12;
monthChanging = todayEthio[1];
todayEthio[2]=dateEthiopian;
}


public void setStartDayOfMonth(){
int dayOfWeek = todayEthio[3]-1;
int date = todayEthio[2];
int startDay = (dayOfWeek - (date-1))%7;
if(startDay<0)
startDay = startDay+7;
else startDay = (dayOfWeek - (date-1))%7;
setTable(startDay);
}

public void setTable(int startDay){
int counter = 0;
int numDays;
if(monthChanging==12)
if(yearChanging%4==3)
numDays=6;
else numDays=5;
else numDays=30;
for(int i=0; i<42; i++){
if(i<startDay || counter>=numDays)
date[i/7][i%7] = ""; 
else {
	counter++;
	date[i/7][i%7] = counter+""; }}

if(table!=null)
frame.remove(jsp);
table = new JTable(date, days);
table = new JTable(new MyTableModel(this.date, days));
table.setFont(new Font("sans-serif", Font.BOLD, 20));
table.setRowHeight(35);
table.setGridColor(Color.black);
table.setColumnSelectionAllowed(true);
jsp = new JScrollPane(table);
frame.add(jsp, BorderLayout.CENTER);

}

public class nextMonthListener implements ActionListener {
public void actionPerformed(ActionEvent ee){

monthChanging=(monthChanging+1)%13;
monthCha.setText(months[monthChanging]);
int lastDayOfMonth;
if(monthChanging==0){ // if meskerem or (after pagume)
if(yearChanging%4==3){ // check if leap year
lastDayOfMonth = 6;}
else {lastDayOfMonth=5;}  // if not leap year
yearChanging++;
yearCha.setText(yearChanging+"");}
else {lastDayOfMonth = 30;} // if not meskerem

int startDay=0;
for(int i=0; i<date.length; i++){
for(int j=0; j<date[i].length; j++){
if((lastDayOfMonth+"").equals(date[i][j])){
startDay = (j+1)%7;   
break; }
}}

setTable(startDay);
   }}

public class prevMonthListener implements ActionListener{
public void actionPerformed(ActionEvent ev){
if(monthChanging==0){ 
yearChanging--;
yearCha.setText(yearChanging+""); }  //if it was september and button was pushed then decrement the  year

monthChanging--; //decrement the month
if(monthChanging<0)  // if month is negative then add 12 to change into pagume
monthChanging=monthChanging+13;
monthCha.setText(months[monthChanging]); 
int startDay=0;
for(int i=0; i<date[0].length; i++){
if("1".equals(date[0][i]))
if(monthChanging==12)      // if it's pagume
if(yearChanging%4==3)         // if it's a leap year
startDay = i - 6;
else startDay = i - 5; 	 // if it's not a leap year
else startDay = i - 2;   	// if it's any other month  
if("1".equals(date[0][i]))
break;  }
if(startDay<0)
startDay = startDay + 7; 
setTable(startDay);  
}}


public class nextYearListener implements ActionListener {
public void actionPerformed(ActionEvent ev){
nextYearSetTable(); }}

public class prevYearListener implements ActionListener {
public void actionPerformed(ActionEvent ev){
prevYearSetTable(); }}

public void nextYearSetTable(){
for(int k=0; k<13; k++){ // set The Table 13 times so that we go 13 months  into the future including pagume.
monthChanging=(monthChanging+1)%13;
int lastDayOfMonth;
if(monthChanging==0){ // if meskerem or (after pagume)
if(yearChanging%4==3){ // check if leap year
lastDayOfMonth = 6;}
else {lastDayOfMonth=5;}  // if not leap year
yearChanging++;
yearCha.setText(yearChanging+"");}
else {lastDayOfMonth = 30;} // if not meskerem

int startDay=0;
for(int i=0; i<date.length; i++){
for(int j=0; j<date[i].length; j++){
if((lastDayOfMonth+"").equals(date[i][j])){
startDay = (j+1)%7;   
break; }
}}

setTable(startDay); }}


public void prevYearSetTable(){
for(int k=0; k<13; k++){ // set the table 13 times so that we go back 13 months including pagume
if(monthChanging==0){ 
yearChanging--;
yearCha.setText(yearChanging+""); }  //if it was september and button was pushed then decrement the  year

monthChanging--; //decrement the month
if(monthChanging<0)  // if month is negative then add 12 to change into pagume
monthChanging=monthChanging+13;
int startDay=0;
for(int i=0; i<date[0].length; i++){
if("1".equals(date[0][i]))
if(monthChanging==12)      // if it's pagume
if(yearChanging%4==3)         // if it's a leap year
startDay = i - 6;
else startDay = i - 5; 	 // if it's not a leap year
else startDay = i - 2;   	// if it's any other month  
if("1".equals(date[0][i]))
break;  }
if(startDay<0)
startDay = startDay + 7; 
setTable(startDay);   }}


public class ClearAll implements ActionListener {
public void actionPerformed(ActionEvent eve){
adl.todolist.clear();
File file = new File("Saved.dat");
file.delete();

 }}

public class displayAllList implements ActionListener {
public void actionPerformed(ActionEvent eve){
JFrame fr = new JFrame("List Of Events");
fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
fr.setSize(400, 500);
JPanel panelC = new JPanel();
fr.add(panelC, BorderLayout.CENTER);
panelC.setBackground(Color.gray);
JLabel lab = new JLabel("List of Events:");
lab.setFont(new Font("sans-serif", Font.BOLD, 25));
lab.setForeground(Color.white);
panelC.setLayout(new BorderLayout());
panelC.add(lab, BorderLayout.NORTH);
JPanel panelCenter = new JPanel();
panelCenter.setBackground(Color.gray);
panelC.add(panelCenter, BorderLayout.CENTER);
panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
TreeSet<toDo> aa = adl.todolist;
for(toDo td : aa){
JLabel l = new JLabel("");
l.setFont(new Font("sans-serif", Font.BOLD, 14));
l.setText("The Events to be done on "+td.month+", "+td.date+", "+td.year+" are: ");
l.setForeground(Color.white);
panelCenter.add(l);
   for(String ss : td.events){
  JLabel ll = new JLabel("	   "+ss);
    ll.setForeground(Color.white);
   panelCenter.add(ll); }
 }
fr.setVisible(true); }}










}
