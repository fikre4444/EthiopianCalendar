import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
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
menu.add(new JMenuItem("Save Events"));
menu.add(new JMenuItem("Determine Holiday Date"));
menu.add(new JMenuItem("Convert Date"));
menu.addSeparator();
menu.add(new JMenuItem("Exit"));

setStartDayOfMonth();

JPanel panelTop = new JPanel();  // used for the top panel that holds (year) and (month)
panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));  // BoxLayout  is used to add EachPanel
JPanel panelTopLeft = new JPanel();  // This is the panel that holds the year section
JPanel panelTopRight = new JPanel();  // this is the panel that holds the month section
panelTop.setBorder(new LineBorder(Color.black, 3));
panelTop.repaint();
panelTop.add(panelTopLeft);
panelTop.add(panelTopRight);

panelTopLeft.add(new JLabel("Year"));
JButton prevYear = new JButton("<<");
panelTopLeft.add(prevYear);
yearCha = new JLabel(yearChanging+"");
panelTopLeft.add(yearCha);
JButton nextYear = new JButton(">>");
panelTopLeft.add(nextYear);

panelTopRight.add(new JLabel("Month"));
JButton prevMonth = new JButton("<<");
panelTopRight.add(prevMonth);
monthCha = new JLabel(months[monthChanging]+"");
panelTopRight.add(monthCha);
JButton nextMonth = new JButton(">>");
panelTopRight.add(nextMonth);
nextMonth.addActionListener(new nextMonthListener());



frame.add(panelTop, BorderLayout.NORTH);
JPanel panelBottom = new JPanel();////////////////////
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


}
