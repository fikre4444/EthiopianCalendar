//this class is used to determine the actual holidays in the list



public class DetermineAlgorithms {
private String[] holidays = {"Christmas","Downfall of the Dergue", "Epiphany", "Labour Day", "Meskel", "New Year","Patriots Victory Day", "Victory of Adwa" };

public static String getResult(String holiday, String yea){
String result="";
int year = Integer.parseInt(yea);
switch(holiday){
case "Christmas":
result = determineChristmas(year);
break;
case "Downfall of the Dergue":
result = determineDownfall(year);
break;
case "Epiphany":
result = determineEpiphany(year);
break;
case "Labour Day":
result = determineLabourDay(year);
break;
case "Meskel":
result = determineMeskel(year);
break; 
case "New Year":
result = determineNewYear(year);
break; 
case "Patriots Victory Day":
result = determinePatriots(year);
break; 
case "Victory of Adwa":
result = determineVictoryAdwa(year);
break; }

return result; }


private static String determineChristmas(int year){
String result="";
if(year%4==0)
result = "The Christmas Holiday falls on the date December 28, in "+year;
else result = "The Christmass Holiday falls on the date December 29, in "+year;
return result; }

private static String determineDownfall(int year){
return "The National Holiday Downfall of the Dergue falls on the date May 20, in "+year;}

private static String determineEpiphany(int year){
return "The Holiday Epiphany falls on the date, January 11, in "+year;}
private static String determineLabourDay(int year){
return "The National Holiday Labour day falls on the date, April 23, in "+year;}

private static String determineMeskel(int year){
return "The Holiday Meskel falls on the date, September 17, in "+year;}

private static String determineNewYear(int year){
return "The Holiday New Year falls on the date, September 1, in "+year;}

private static String determinePatriots(int year){
return "The National Holiday Patriots day falls on the date, April 27, in "+year;}

private static String determineVictoryAdwa(int year){
return "The National Holiday Victory of Adwa falls on the date February 23, in "+year;}












}
