package com.beadando.xuxejo.model.response;

import com.beadando.xuxejo.model.Employee;

import java.util.List;

public class EmployeeResponse {
    List<Employee> data;
    String status;

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
