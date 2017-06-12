package Auth;

import Model.Annonymous;

/**
 * Created by NSD on 17.05.17.
 */
public class AuthRealm {

    private Object user;
    private UserRole accessRole;

    public AuthRealm(Object user, UserRole accessRole) {
        this.user = user;
        this.accessRole = accessRole;
    }

    public AuthRealm(){
        this.user = new Annonymous();
        this.accessRole = UserRole.ANNONYMOUS;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public UserRole getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(UserRole accessRole) {
        this.accessRole = accessRole;
    }





}
