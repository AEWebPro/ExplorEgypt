package com.example.ae.smartvisit.modules;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableRequest {
    @SerializedName("Operation")
    @Expose
    private String Operation;
    @SerializedName("table")
    @Expose
    private String table;
    @SerializedName("Parameters")
    @Expose
    private RequestParameters Parameters;

    public TableRequest(String operation, String table, RequestParameters Parameters) {
        this.Operation = operation;
        this.table = table;
        this.Parameters = Parameters;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        this.Operation = operation;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public RequestParameters getParameters() {
        return Parameters;
    }

    public void setParameters(RequestParameters parameters) {
        this.Parameters = parameters;
    }

}

