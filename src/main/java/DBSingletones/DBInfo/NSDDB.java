package DBSingletones.DBInfo;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by NSD on 17.05.17.
 */
public class NSDDB {
private static  String DBURL="";
private static String DB_LOGIN="";
private static String DB_PASS="";


private static ConnectionSource connectionSource = null;




    public static String[] getConnection() throws URISyntaxException, SQLException {

       try{
        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

        DB_LOGIN = dbUri.getUserInfo().split(":")[0];
        DB_PASS = dbUri.getUserInfo().split(":")[1];
        DBURL = "jdbc:mysql://" + dbUri.getHost() + ':' + "3306" + dbUri.getPath()+"?reconnect=true";


        return new String[]{DBURL,DB_LOGIN,DB_PASS};
    }catch(Exception e) {

           DB_LOGIN = "bf8592ce2655df";
           DB_PASS = "50429447";
           DBURL = "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306/heroku_4bec834d769add6?reconnect=true";

           return new String[]{DBURL,DB_LOGIN,DB_PASS};
       }
    }


}
