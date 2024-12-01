package com.swissre.casestudy.models;

public class Employee {

    private int id;
    private final String firstName;
    private final String lastName;
    private final double salary;
    // manager can be changed for an employee
    private int managerId;
    // organization level
    private int level;

    public Employee(int id, String firstName, String lastName, double salary, int managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
        this.level = 0;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary + ", managerId=" + managerId + "]";
    }
}
