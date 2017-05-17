package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.05.17.
 */




@DatabaseTable
public class Admin extends User {


    public Admin(){}

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String email;
    @DatabaseField
    private String passwordHex;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHex() {
        return passwordHex;
    }

    public void setPasswordHex(String passwordHex) {
        this.passwordHex = passwordHex;
    }
}
