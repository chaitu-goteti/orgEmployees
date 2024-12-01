package com.swissre.casestudy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class to store level wise employee information.
 */
public class ManagerReportees {

    private TreeMap<Integer, List<Integer>> managersMap;

    private TreeMap<Integer, Double> subordinatesAvgSal;

    private List<List<Integer>> levelsList = new ArrayList<>(1000);

    public ManagerReportees() {
        this.managersMap = new TreeMap<>();
        this.subordinatesAvgSal = new TreeMap<>();
    }

    public TreeMap<Integer, List<Integer>> getManagersMap() {
        return managersMap;
    }

    public void setManagersMap(TreeMap<Integer, List<Integer>> managersMap) {
        this.managersMap = managersMap;
    }

    public TreeMap<Integer, Double> getSubordinatesAvgSal() {
        return subordinatesAvgSal;
    }

    public void setSubordinatesAvgSal(TreeMap<Integer, Double> subordinatesAvgSal) {
        this.subordinatesAvgSal = subordinatesAvgSal;
    }

    public List<Integer> getEmployeesAtLevel(int level) {
        return levelsList.get(level);
    }
}
