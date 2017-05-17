package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.05.17.
 */

@DatabaseTable
public class Student extends User {

    public Student(){}

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String email;

    @DatabaseField
    private  String passwordHex;

    @DatabaseField
    private String avatar;

    @DatabaseField
    private String name;

    @DatabaseField
    private String surname;

    @DatabaseField
    private String firstname;

    @DatabaseField
    private String cardNumber;

    @DatabaseField
    private String cardExploedInfo;

    @DatabaseField
    private String cardCSV;

    @DatabaseField
    private String city;

    @DatabaseField
    private String educationInfo;

    @DatabaseField
    private String educationInfoDoc;

    @DatabaseField
    private String bornDate;

    @DatabaseField
    private boolean isActivated;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExploedInfo() {
        return cardExploedInfo;
    }

    public void setCardExploedInfo(String cardExploedInfo) {
        this.cardExploedInfo = cardExploedInfo;
    }

    public String getCardCSV() {
        return cardCSV;
    }

    public void setCardCSV(String cardCSV) {
        this.cardCSV = cardCSV;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducationInfo() {
        return educationInfo;
    }

    public void setEducationInfo(String educationInfo) {
        this.educationInfo = educationInfo;
    }

    public String getEducationInfoDoc() {
        return educationInfoDoc;
    }

    public void setEducationInfoDoc(String educationInfoDoc) {
        this.educationInfoDoc = educationInfoDoc;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
