
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("number of unique IPs: "+la.countUniqueIPs());

        la.readFile("weblog2_log");
        System.out.println("Quiz: "+la.countUniqueIPs());
    }
    
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int num = 201;
        System.out.println("all higher than "+num+": ");
        la.printAllHigherThanNum(num);
        
        la.readFile("weblog1_log");
        num = 400;
        System.out.println("all higher than "+num+": ");
        la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");

        ArrayList<String> uniqueIPs = new ArrayList<String>();
        uniqueIPs = la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Should return 2: "+uniqueIPs.size());
        uniqueIPs = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Should return 3: "+uniqueIPs.size());
        
        la.readFile("weblog1_log");
        uniqueIPs = la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("Mar 24: "+uniqueIPs.size());
        
        la.readFile("weblog2_log");
        uniqueIPs = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Quiz: "+uniqueIPs.size());
        
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");

        int uniqueIPs = la.countUniqueIPsInRange(200,299);
        System.out.println("Should return 4: "+uniqueIPs);
         
        uniqueIPs = la.countUniqueIPsInRange(300,399);
        System.out.println("Should return 2: "+uniqueIPs);

        la.readFile("weblog1_log");
        uniqueIPs = la.countUniqueIPsInRange(300,399);
        System.out.println("weblog1_log: "+uniqueIPs);
        
        la.readFile("weblog2_log");
        uniqueIPs = la.countUniqueIPsInRange(200,299);
        System.out.println("Quiz: "+uniqueIPs);
    }
     
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println("counts: "+counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int maxNumVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("should return 3: "+maxNumVisits);

        la.readFile("weblog1_log");
        counts = la.countVisitsPerIP();
        maxNumVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("PracQuiz: "+maxNumVisits);
    
        la.readFile("weblog2_log");
        counts = la.countVisitsPerIP();
        maxNumVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Quiz: "+maxNumVisits);
    
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> iPs = la.iPsMostVisits(counts);
        System.out.println("should return 61.15.121.171 and 84.133.195.161: "+iPs);

        la.readFile("weblog1_log");
        counts = la.countVisitsPerIP();
        iPs = la.iPsMostVisits(counts);
        System.out.println("PracQuiz: "+iPs);
        
        la.readFile("weblog2_log");
        counts = la.countVisitsPerIP();
        iPs = la.iPsMostVisits(counts);
        System.out.println("Quiz: "+iPs);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> mapDateToIPs = la.iPsForDays();
        System.out.println("should return Sep 14 maps to one IP address, \n"+
                            "Sep 21 maps to four IP addresses, \n"+
                            "and Sep 30 maps to five IP addresses: \n"+
                            mapDateToIPs);
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> mapDateToIPs = la.iPsForDays();
        String busiestDay = la.dayWithMostIPVisits(mapDateToIPs);
        System.out.println("should return the day most visited as Sep 30: "+
                            busiestDay);
                            
        la.readFile("weblog1_log");
        mapDateToIPs = la.iPsForDays();
        busiestDay = la.dayWithMostIPVisits(mapDateToIPs);
        System.out.println("PracQuiz: "+
                            busiestDay);
                            
        la.readFile("weblog2_log");
        mapDateToIPs = la.iPsForDays();
        busiestDay = la.dayWithMostIPVisits(mapDateToIPs);
        System.out.println("Quiz: "+
                            busiestDay);
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> mapDateToIPs = la.iPsForDays();
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(mapDateToIPs,"Sep 30");
        System.out.println("should return 61.15.121.171 and 177.4.40.87: "+
                            ips);

        la.readFile("weblog1_log");
        mapDateToIPs = la.iPsForDays();
        ips = la.iPsWithMostVisitsOnDay(mapDateToIPs,"Mar 17");
        System.out.println("PracQuiz: "+
                            ips);

        la.readFile("weblog2_log");
        mapDateToIPs = la.iPsForDays();
        ips = la.iPsWithMostVisitsOnDay(mapDateToIPs,"Sep 29");
        System.out.println("Quiz: "+
                            ips);
    }
}