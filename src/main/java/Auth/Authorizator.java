package Auth;

/**
 * Created by NSD on 17.05.17.
 */
public class Authorizator {

    public static final String AUTH_ATTR = "auth";

    private static volatile Authorizator instance;

    public static Authorizator getInstance() {
        Authorizator localInstance = instance;
        if (localInstance == null) {
            synchronized (Authorizator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Authorizator();
                }
            }
        }
        return localInstance;
    }


    public AuthRealm Auth(String login,String hexOfPass){



        return new AuthRealm();
    }


    public AuthRealm Reg(Object user,String login,String hexOfPass){



        return new AuthRealm();
    }




}
