
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.text.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         // initialize ArrayList
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         // start with empty records
         records.clear();
         
         // create FileResource for filename
         FileResource fr = new FileResource(filename);
         // add log entries from the file
         // iterate over lines for each file
         for (String line : fr.lines()) {
             // process each line in turn
             // read in those log entries
             // want to use WebLogParser.parseEntry, so create an object
             WebLogParser wlp = new WebLogParser();
             //add LogEntry to records arrayList
             records.add(wlp.parseEntry(line));
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             // process each item in turn 
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             // process each item in turn 
             int status = le.getStatusCode();
             if (status > num) {
                 System.out.println(le);
             }
         } 
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         // “MMM DD” where MMM is the first three characters of the month 
         // name with the first letter capitalized and the others in lowercase, 
         // and DD is the day in two digits (examples are “Dec 05” and “Apr 22”).
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             // process each item in turn 
             String ipAddr = le.getIpAddress();
             Date d = le.getAccessTime();
             
             // Convert to format we want to compare
             String pattern = "MMM dd";
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
             String date = simpleDateFormat.format(d);
             //System.out.println(d.toString());
             //System.out.println(date);
             //System.out.println(someday);
             
             if (!uniqueIPs.contains(ipAddr) && someday.equals(date)) {
                 uniqueIPs.add(ipAddr);
             }
         } 
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            // process each item in turn 
            String ipAddr = le.getIpAddress();
            int status = le.getStatusCode();
            if (status >= low && status <= high && !uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
            }
        } 
        return uniqueIPs.size();
     }
     
     
     public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            // process each item in turn 
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip)+1);
            }
        } 
        return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
         int maxNumVisits = 0;
         
         for (Integer count : counts.values()) {
             // process each value in turn 
             if (count > maxNumVisits) {
                 maxNumVisits = count;
             }
         }
         return maxNumVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> iPs = new ArrayList<String>();
         int maxNumVisits = mostNumberVisitsByIP(counts);
         
         for (String ip : counts.keySet()) {
             // process each key in turn 
             if(counts.get(ip) == maxNumVisits && !iPs.contains(ip)){
                 iPs.add(ip);
             }
         } 
         return iPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         //. maps days from web logs to an ArrayList of IP addresses that occurred on that day
         //(including repeated IP addresses).
         // A day is in the format “MMM DD”
         // where MMM is the first three characters of the month name
         // with the first letter capital and the others in lowercase,
         // and DD is the day in two digits
         // (examples are “Dec 05” and “Apr 22”).
         HashMap<String, ArrayList<String>> mapDateToIPs = new HashMap<String, ArrayList<String>>();

         for (LogEntry le : records) {
             // process each item in turn 
             String ipAddr = le.getIpAddress();
             Date d = le.getAccessTime();
             ArrayList<String> iPs = new ArrayList<String>();
        
             // Convert to format we want to compare
             String pattern = "MMM dd";
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
             String date = simpleDateFormat.format(d);
             //System.out.println(d.toString());
             //System.out.println(date);
             
             if (!mapDateToIPs.containsKey(date)) {
                 // key didn't exist, add ip address to list, then map date to list
                 iPs.add(ipAddr);
                 mapDateToIPs.put(date, iPs);
             }
             else {
                 // key did exist, get IPs, append new one, and put it in
                 iPs = mapDateToIPs.get(date);
                 iPs.add(ipAddr);
                 mapDateToIPs.put(date, iPs);
             }
         }
         return mapDateToIPs;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapDateToIPs){
         /* returns the day that has the most IP address visits.
          * If there is a tie, then return any such day.*/
         
         int maxNumVisits = 0;
         String busiestDay = "";
         for (String date : mapDateToIPs.keySet()) {
             // process each key in turn              
             if (mapDateToIPs.get(date).size() > maxNumVisits) {
                 maxNumVisits = mapDateToIPs.get(date).size();
                 busiestDay = date;
             }
         } 
         return busiestDay;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> mapDateToIPs, String date){
         // Get all the repeated IP addresses on the given date
         ArrayList<String> repeatedIPs = mapDateToIPs.get(date);

         // start a tally in a hashmap of the counts for each IP
         HashMap<String, Integer> counts = new HashMap<String, Integer>();

         // count the number of times each IP occurs in the repeated IP list
         for (String ip : repeatedIPs) {
             // process each key in turn 
             if (!counts.containsKey(ip)) { //if counts not in HashMap yet add it.
                 counts.put(ip,1);
             }
             else { //if counts is in HashMap, increment it
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         
         ArrayList<String> popularIPs = iPsMostVisits(counts);
         
         return popularIPs;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
