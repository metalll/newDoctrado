package Model;

/**
 * Created by NSD on 24.05.17.
 */
public class RealmModelForJSON<T> {

    T user;
    String role;


    public RealmModelForJSON(T user,String role){
        this.user = user;
        this.role = role;

    }




}
