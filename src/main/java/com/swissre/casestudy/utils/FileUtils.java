package com.swissre.casestudy.utils;

import com.swissre.casestudy.exceptions.CaseStudyException;
import com.swissre.casestudy.models.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FileUtils {

    int ceoManagerId = -1;
    public static double minSalPercentage = 0.0;
    public static double maxSalPercentage = 0.0;
    public static int minLevels = 0;
    /**
     * read employees.csv file and construct list of Employees 
     * @return List of employees
     * @throws IOException
     */
    public Map<Integer, Employee> getEmployees() throws IOException, CaseStudyException {

        // Read Properties of resource file path, maxSalary, minSalary percentages
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream appProperties = classLoader.getResourceAsStream("application.properties");
        if(appProperties == null) {
            throw new CaseStudyException("Application properties file not found");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(appProperties, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String srcFile = "";

        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if(line.startsWith("app.sourceFile")){
                srcFile = line.substring("app.sourceFile:".length());
            } else if(line.startsWith("minSalPercentage")){
                minSalPercentage = Double.parseDouble(line.substring("minSalPercentage:".length()));
            } else if(line.startsWith("maxSalPercentage")){
                maxSalPercentage = Double.parseDouble(line.substring("maxSalPercentage:".length()));
            } else if(line.startsWith("minLevels")){
                minLevels = Integer.parseInt(line.substring("minLevels:".length()));
            }
        }


        // Read employees.csv and form employees map
        InputStream inputStream = classLoader.getResourceAsStream(srcFile);

        if (inputStream == null) {
            throw new CaseStudyException("Unable to fine/read employees.csv");
        }

        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        Map<Integer, Employee> employees = new TreeMap<>();

        for (String line; (line = reader.readLine()) != null; ) {
            // skip header columns
            if (line.startsWith("Id"))
                continue;
            String[] employeeProps = line.split(",");
            if (employeeProps.length < 4) {
                throw new CaseStudyException("Invalid record received " + line);
            } else {
                Employee employee = getEmployees(employeeProps);
                if(employee.getManagerId() == -1){
                    employee.setLevel(0);
                } else {
                    employee.setLevel(employees.get(employee.getManagerId()).getLevel()+1);
                }
                employees.put(employee.getId(),employee);
            }
        }
        inputStream.close();
        reader.close();
        return employees;
    }

    private Employee getEmployees(String[] employeeProps) {
        Employee employee = null;
        // for CEO set managerId = -1
        if (employeeProps.length == 4) {
            employee = new Employee(Integer.parseInt(employeeProps[0]), employeeProps[1],
                    employeeProps[2], Double.parseDouble(employeeProps[3]), ceoManagerId);
        } else {
            employee = new Employee(Integer.parseInt(employeeProps[0]), employeeProps[1],
                    employeeProps[2], Double.parseDouble(employeeProps[3]), Integer.parseInt(employeeProps[4]));
        }
        return employee;
    }

}
