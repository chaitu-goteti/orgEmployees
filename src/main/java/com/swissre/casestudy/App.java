package com.swissre.casestudy;

import com.swissre.casestudy.exceptions.CaseStudyException;
import com.swissre.casestudy.models.Employee;
import com.swissre.casestudy.models.ManagerReportees;
import com.swissre.casestudy.report.OrgReport;
import com.swissre.casestudy.utils.FileUtils;
import com.swissre.casestudy.utils.ReportingUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        FileUtils fileUtils = new FileUtils();
        Map<Integer, Employee> employeeMap = null;
        try {
            employeeMap = fileUtils.getEmployees();
        } catch (IOException e) {
            System.out.println("IOException while fetching employees from "+e.getMessage());
        } catch (CaseStudyException e) {
            System.out.println(e.getMessage());
        }

        if(employeeMap == null || employeeMap.isEmpty()){
            System.out.println("No employee found exiting..");
            return;
        }
        // get manager and the subordinates list, subordinates avg sal
        ManagerReportees managers = ReportingUtil.formEmployeeHierarchy(employeeMap);

        OrgReport orgReport = new OrgReport(FileUtils.minSalPercentage, FileUtils.maxSalPercentage, FileUtils.minLevels);
        orgReport.printReport(employeeMap, managers.getSubordinatesAvgSal());

        System.out.println(employeeMap);
        System.out.println("CEO Manager Id: "+ employeeMap.values().stream().filter( e -> e.getManagerId() == -1).findFirst().get());
    }
}
