package com.beadando.xuxejo.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.beadando.xuxejo.model.Employee;
import com.beadando.xuxejo.model.response.EmployeeResponse;
import com.beadando.xuxejo.service.EmployeeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeesViewModel extends ViewModel {
    private MutableLiveData<List<Employee>> employees;

    public LiveData<List<Employee>> getEmployees() {
        if (employees == null){
            employees = new MutableLiveData<>();
            loadEmployees();
        }
        return employees;
    }

    public void loadEmployees(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://dummy.restapiexample.com/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeService service = retrofit.create(EmployeeService.class);

        Call<EmployeeResponse> get = service.listEmployees();
        get.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                System.out.println(response.body());
                employees.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                System.out.println("GET Error in EmployeesViewModel: "+t);
            }
        });

    }
}