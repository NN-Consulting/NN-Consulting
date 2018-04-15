package nnconsulting.app.spacia.data.authentication.queries;

import java.io.Serializable;

/**
 * Coded by luiz kawai on 27/02/18.
 */

public class RegisterQuery implements Serializable {

    private String first_name;
    private String last_name;
    private String password;
    private String birthday;
    private String email;
    private String phone_number;
    private String facebook_id;

    public RegisterQuery(String first_name, String last_name, String password, String email, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;

        this.email = email;
        this.phone_number = phone_number;
    }

    public RegisterQuery(String first_name, String last_name,String birthday,String password, String email, String phone_number, String facebook_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.birthday=birthday;
        this.phone_number = phone_number;
        this.facebook_id = facebook_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
