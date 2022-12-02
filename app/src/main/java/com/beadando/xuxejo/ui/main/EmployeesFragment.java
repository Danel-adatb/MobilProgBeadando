package com.beadando.xuxejo.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beadando.xuxejo.R;

public class EmployeesFragment extends Fragment {

    private EmployeesViewModel mViewModel;

    public static EmployeesFragment newInstance() {
        return new EmployeesFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EmployeesViewModel.class);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
        recyclerView.setLayoutManager(layoutManager);
        
        mViewModel.getEmployees().observe(getViewLifecycleOwner(), employees -> {
            EmployeesAdapter adapter = new EmployeesAdapter(employees);
            recyclerView.setAdapter(adapter);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }

}