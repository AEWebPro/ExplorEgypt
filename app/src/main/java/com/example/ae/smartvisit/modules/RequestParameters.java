package com.example.ae.smartvisit.modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestParameters {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("group_id")
    @Expose
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
