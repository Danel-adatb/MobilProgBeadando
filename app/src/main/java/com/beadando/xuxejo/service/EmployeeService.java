package com.beadando.xuxejo.service;

import com.beadando.xuxejo.model.NewEmployee;
import com.beadando.xuxejo.model.response.EmployeeResponse;
import com.beadando.xuxejo.model.response.NewEmployeeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeService {
    @GET("employees")
    Call<EmployeeResponse> listEmployees();

    @POST("create")
    Call<NewEmployeeResponse> createEmployee(@Body NewEmployee employee);
}
