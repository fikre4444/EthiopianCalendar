// this class is used for the determine a holiday date menu


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
public class Determine implements ActionListener {
private int year;
private String[] holidays = {"Christmas","Downfall of the Dergue", "Epiphany" ,"Labour Day", "Meskel", "New Year","Patriots Victory Day", "Victory of Adwa" };

private String[] years = new String[100];
private String result="Result";
private String instruction = "Please choose a Holiday and a year from the lists and click Determine";
private JComboBox<String> jcmbYears;
private JComboBox<String> jcmbHolidays;
private JButton deter;
JLabel labelResult;
String chosenHoliday = "Christmas";
String chosenYear = "2000";


public Determine (){
for(int i=0; i<100; i++){
years[i]=(2000+i)+""; }

jcmbYears = new JComboBox<>(years);
jcmbHolidays = new JComboBox<>(holidays);
jcmbYears.addActionListener(new yearListener());
jcmbHolidays.addActionListener(new HolidayListener()); 

}


public void actionPerformed(ActionEvent eve){
JFrame frame = new JFrame("Determine Holiday Date");
JPanel panelInstruction = new JPanel();
panelInstruction.setLayout(new BoxLayout(panelInstruction, BoxLayout.Y_AXIS));
JPanel panelBottom = new JPanel();
panelInstruction.add(Box.createVerticalStrut(10));
JLabel labelInstruction = new JLabel(instruction);
labelInstruction.setFont(new Font("sans-serif", Font.BOLD, 13));
labelInstruction.setForeground(Color.white);
panelInstruction.add(labelInstruction);
panelInstruction.add(Box.createVerticalStrut(10));
panelInstruction.setBorder(new LineBorder(Color.white, 2));
panelInstruction.setBackground(Color.gray);



JPanel pCenter1 = new JPanel();
pCenter1.add(jcmbHolidays);
pCenter1.add(jcmbYears);

JPanel pCenter2 = new JPanel();
deter = new JButton("Determine");
deter.addActionListener(new determineListener());
pCenter2.add(deter);

JPanel pCenter = new JPanel();
pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
pCenter.add(pCenter1);
pCenter.add(pCenter2);
pCenter.setBorder(new LineBorder(Color.white, 2));
pCenter.setBackground(Color.gray);
pCenter1.setBackground(Color.gray);
pCenter2.setBackground(Color.gray);



labelResult = new JLabel(result);
labelResult.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
panelBottom.add(labelResult);
panelBottom.setBorder(new LineBorder(Color.white, 2));


frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.add(panelInstruction, BorderLayout.NORTH);
frame.add(panelBottom, BorderLayout.SOUTH);
frame.add(pCenter, BorderLayout.CENTER);
frame.setSize(525, 200);
frame.setVisible(true);
}

public class yearListener implements ActionListener {
public void actionPerformed(ActionEvent eve){
JComboBox b = (JComboBox)(eve.getSource());
chosenYear = (String)(b.getSelectedItem());
}}

public class HolidayListener implements ActionListener {
public void actionPerformed(ActionEvent eve){
JComboBox b = (JComboBox)(eve.getSource());
chosenHoliday = (String)(b.getSelectedItem()); }}

public class determineListener implements ActionListener{
public void actionPerformed(ActionEvent eve){
result = DetermineAlgorithms.getResult(chosenHoliday, chosenYear); 
labelResult.setText(result); }}





}