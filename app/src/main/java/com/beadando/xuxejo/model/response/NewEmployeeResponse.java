package com.beadando.xuxejo.model.response;

import com.beadando.xuxejo.model.NewEmployee;

public class NewEmployeeResponse {
    String status;
    NewEmployee data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NewEmployee getData() {
        return data;
    }

    public void setData(NewEmployee data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewEmployeeResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
