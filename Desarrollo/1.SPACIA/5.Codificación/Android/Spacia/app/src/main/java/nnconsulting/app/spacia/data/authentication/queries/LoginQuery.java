package nnconsulting.app.spacia.data.authentication.queries;

import java.io.Serializable;

/**
 * Coded by luiz kawai on 27/02/18.
 */

public class LoginQuery implements Serializable {

    private String password;
    private String email;

    public LoginQuery(String password, String email) {
        this.password = password;
        this.email = email;
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
}
