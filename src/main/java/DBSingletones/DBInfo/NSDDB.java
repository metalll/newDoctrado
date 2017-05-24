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




    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

        DB_LOGIN = dbUri.getUserInfo().split(":")[0];
        DB_PASS = dbUri.getUserInfo().split(":")[1];
        DBURL = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(DBURL, DB_LOGIN, DB_PASS);
    }

   public static ConnectionSource getConnectionSourse(){
       if(connectionSource==null||connectionSource.isOpen()){
           try {
               if(connectionSource!=null){
               if(connectionSource.isOpen()){
                   connectionSource.close();

               }
               }


               try {
                   getConnection();
               } catch (URISyntaxException e) {
                   System.out.print("error conn to db");
               }
               connectionSource = new JdbcConnectionSource(DBURL,DB_LOGIN,DB_PASS);
           } catch (SQLException e) {
               try {
                   if(connectionSource!=null){
                       connectionSource.close();
                       connectionSource.releaseConnection(connectionSource.getReadWriteConnection());
                       connectionSource = null;
                       connectionSource = new JdbcConnectionSource(DBURL,DB_LOGIN,DB_PASS);
                   }
               }catch (Exception e1){
                   e1.printStackTrace();
               }
           }
       }


       return connectionSource;


   }

}
