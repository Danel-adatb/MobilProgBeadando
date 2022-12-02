package com.beadando.xuxejo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.beadando.xuxejo.model.Employee;
import com.beadando.xuxejo.model.NewEmployee;
import com.beadando.xuxejo.model.response.EmployeeResponse;
import com.beadando.xuxejo.model.response.NewEmployeeResponse;
import com.beadando.xuxejo.service.EmployeeService;
import com.beadando.xuxejo.ui.main.EmployeesFragment;
import com.beadando.xuxejo.ui.main.EmployeesViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Employees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EmployeesFragment.newInstance())
                    .commitNow();
        }

        //Calling API
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://dummy.restapiexample.com/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        EmployeeService service = retrofit.create(EmployeeService.class);

        //GET
        Call<EmployeeResponse> get = service.listEmployees();
        get.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                System.out.println(response.body().getData());
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                System.out.println("GET Error Employees.class: "+t);
            }
        });

        //POST
        NewEmployee e = new NewEmployee();
        e.setName("Daniel Szabo");
        e.setAge(22);
        e.setSalary(260000);

        Call<NewEmployeeResponse> post = service.createEmployee(e);
        post.enqueue(new Callback<NewEmployeeResponse>() {
            @Override
            public void onResponse(Call<NewEmployeeResponse> call, Response<NewEmployeeResponse> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<NewEmployeeResponse> call, Throwable t) {
                System.out.println("POST Error Employees.class: "+t);
            }
        });
    }
}