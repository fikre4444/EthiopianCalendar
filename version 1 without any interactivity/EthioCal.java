import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
public class EthioCal {
String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
String date[][] = {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"15", "16", "17", "18", "19", "20", "21"}, {"22", "23", "24", "25", "26", "27", "28"}, {"29", "30","","","","",""}};
public static void main(String[] args){
EthioCal ee = new EthioCal(); 
ee.createEthioCal(); }
public void createEthioCal(){ 
JFrame frame = new JFrame("Ethiopian Calendar");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
JMenuBar mb = new JMenuBar();
JMenu menu = mb.add(new JMenu("Menu"));
menu.add(new JMenuItem("Save Events"));
menu.add(new JMenuItem("Determine Holiday Date"));
menu.add(new JMenuItem("Convert Date"));
menu.addSeparator();
menu.add(new JMenuItem("Exit"));

JPanel panelTop = new JPanel();  // used for the top panel that holds (year) and (month)
panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));  // BoxLayout  is used to add EachPanel
JPanel panelTopLeft = new JPanel();  // This is the panel that holds the year section
JPanel panelTopRight = new JPanel();  // this is the panel that holds the month section
panelTop.setBorder(new LineBorder(Color.black, 3));
panelTop.repaint();
panelTop.add(panelTopLeft);
panelTop.add(panelTopRight);

panelTopLeft.add(new JLabel("Year"));
panelTopLeft.add(new JButton("<<"));
panelTopLeft.add(new JLabel("2012"));
panelTopLeft.add(new JButton(">>"));

panelTopRight.add(new JLabel("Month"));
panelTopRight.add(new JButton("<<"));
panelTopRight.add(new JLabel("January"));
panelTopRight.add(new JButton(">>"));

frame.add(panelTop, BorderLayout.NORTH);
JTable table = new JTable(new MyTableModel(date, days));
table.setFont(new Font("sans-serif", Font.BOLD, 20));
table.setRowHeight(35);
table.setGridColor(Color.black);
table.setColumnSelectionAllowed(true);
JScrollPane jsp = new JScrollPane(table);
frame.add(jsp, BorderLayout.CENTER);
JPanel panelBottom = new JPanel();
panelBottom.setLayout(new BorderLayout());
JButton eve = new JButton("List of Events this Month");
JLabel tod = new JLabel("");
tod.setFont(new Font("sans-serif", Font.BOLD, 15));
tod.setText("Today is: January 2012");
panelBottom.add(eve, BorderLayout.EAST);
panelBottom.add(tod, BorderLayout.WEST);
frame.add(panelBottom, BorderLayout.SOUTH);
frame.setSize(415, 340);
frame.setJMenuBar(mb);
frame.setVisible(true);
 }
public class MyTableModel extends DefaultTableModel {
public MyTableModel(Object[][] tableData, Object[] colNames){
super(tableData, colNames); }
public boolean isCellEditable(int row, int col){
return false; }
}

}
