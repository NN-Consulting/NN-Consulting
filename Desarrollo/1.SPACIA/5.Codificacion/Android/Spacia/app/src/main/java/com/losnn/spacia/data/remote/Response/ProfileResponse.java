package com.losnn.spacia.data.remote.response;

import com.losnn.spacia.data.entities.UserEntity;

public class ProfileResponse {
    private UserEntity data;


    public UserEntity getData() {
        return data;
    }

    public void setData(UserEntity data) {
        this.data = data;
    }
}