package com.losnn.spacia.data.entities;

public class UserEntity {
    private int user_id;
    private String first_name;
    private String last_name;
    private String image;
    private String email;
    private String birthday;
    private String phone_number;

    public UserEntity(String first_name,String last_name,String phone_number,String email){
        this.first_name=first_name;
        this.last_name=last_name;
        this.phone_number=phone_number;
        this.email=email;
    }




    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
