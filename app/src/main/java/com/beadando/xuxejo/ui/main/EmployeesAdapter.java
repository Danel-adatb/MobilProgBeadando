package com.beadando.xuxejo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beadando.xuxejo.R;
import com.beadando.xuxejo.model.Employee;

import org.w3c.dom.Text;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder> {
    private List<Employee> data;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout =
                (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_employees_single_items, parent, false);
        EmployeeViewHolder holder = new EmployeeViewHolder(linearLayout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.id.setText(String.valueOf(data.get(position).getId()));
        holder.name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView id;

        public EmployeeViewHolder(ViewGroup v) {
            super(v);
            name = (TextView) v.getChildAt(1);
            id = (TextView) v.getChildAt(0);
        }
    }

    public EmployeesAdapter(List<Employee> data) {
        this.data = data;
    }
}
