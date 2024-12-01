package com.swissre.casestudy.report;

import com.swissre.casestudy.models.Employee;
import com.swissre.casestudy.utils.FileUtils;

import java.util.*;

import static java.util.stream.Collectors.*;

public class OrgReport {

    private static double minSal;
    private static double maxSal;
    private static int minLevels;
    static Map<Integer, Double> managersEarningLess = new TreeMap<>();
    static Map<Integer, Double> managersEarningMore = new TreeMap<>();
    static Map<Integer, List<Integer>> employeesWithLongReportingLine = new TreeMap<>();

    public OrgReport(double minSal, double maxSal, int minLevels) {
        OrgReport.minSal = FileUtils.minSalPercentage;
        OrgReport.maxSal = FileUtils.maxSalPercentage;
        OrgReport.minLevels = FileUtils.minLevels;
    }

    public static void getManagersWithLowHighSalThreshold(Map<Integer, Employee> employees, TreeMap<Integer, Double> avgSal){

        avgSal.keySet().stream().filter(m -> m != -1).forEach(m -> {
            double minSalLimit = avgSal.get(m) + (minSal/100) * avgSal.get(m);
            double maxSalLimit = avgSal.get(m) + (maxSal/100) * avgSal.get(m);
            if(employees.get(m).getSalary() <= minSalLimit){
                managersEarningLess.put(m, minSalLimit - employees.get(m).getSalary());
            }
            if(employees.get(m).getSalary() >= maxSalLimit){
                managersEarningMore.put(m, employees.get(m).getSalary() - maxSalLimit);
            }
        });
    }

    public static void getEmployeesWithLargeReportingLine(Map<Integer, Employee> employees){
        employeesWithLongReportingLine = employees.values().stream().collect(groupingBy(Employee::getLevel, TreeMap::new,
                mapping(Employee::getId, toList()))).subMap(minLevels, employees.size());
    }


    public static void printReport(Map<Integer, Employee> employees, TreeMap<Integer, Double> avgSal){
        getManagersWithLowHighSalThreshold(employees, avgSal);
        getEmployeesWithLargeReportingLine(employees);
        System.out.printf("Manager Ids getting lower than %3.2f percentage :%n",minSal);
        System.out.println(managersEarningLess);
        System.out.printf("Manager Ids getting higher than %3.2f percentage :%n",maxSal);
        System.out.println(managersEarningMore);
        System.out.printf("Employee Ids having more than %d Level :%n",minLevels);
        System.out.println(employeesWithLongReportingLine);
    }
}
