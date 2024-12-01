package com.swissre.casestudy.utils;

import com.swissre.casestudy.models.Employee;
import com.swissre.casestudy.models.ManagerReportees;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

public class ReportingUtil {

    /**
     *
     * @param employees
     * @return
     */
    public static ManagerReportees formEmployeeHierarchy(Map<Integer, Employee> employees) {
        ManagerReportees reportees = new ManagerReportees();

        TreeMap<Integer, List<Integer>> managersAndSubordinates =
                employees.values().stream().collect(groupingBy(Employee::getManagerId, TreeMap::new, mapping(Employee::getId,toList())));

        TreeMap<Integer, Double> managerSubordinateAvgSal = employees.values().stream()
                .collect(groupingBy(Employee::getManagerId, TreeMap::new, averagingDouble(Employee::getSalary)));

        reportees.setManagersMap(managersAndSubordinates);
        reportees.setSubordinatesAvgSal(managerSubordinateAvgSal);
        return reportees;
    }

}
