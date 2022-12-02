package com.beadando.xuxejo.model;

import com.google.gson.annotations.SerializedName;

public class Employee {
    int id;

    @SerializedName("employee_name")
    String name;
    @SerializedName("employee_salary")
    int salary;
    @SerializedName("employee_age")
    int age;

    String profile_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
