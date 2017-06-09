package DBSingletones;

import DBSingletones.DBInfo.NSDDB;
import Model.Pushcare;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

/**
 * Created by NSD on 08.06.17.
 */
public class DBPushcare {

    private static volatile DBPushcare instance;
    private Dao<Pushcare,Long> dao = null;

    public static DBPushcare getInstance() {
        DBPushcare localInstance = instance;
        if (localInstance == null) {
            synchronized (DBPushcare.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBPushcare();
                }
            }
        }
        return localInstance;
    }

    private DBPushcare(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String[] str =  NSDDB.getConnection();


            dao = DaoManager.createDao(new JdbcConnectionSource(str[0],str[1],str[2]),Pushcare.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(dao!=null){
            try {
                if (!dao.isTableExists()) {
                    TableUtils.createTable(dao.getConnectionSource(),Pushcare.class);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }


    public void addPushcare(Pushcare pushcare){



    }



    public boolean checkPushcare(long userId,long itemId){

        try {
            List <Pushcare> pushcares = dao.queryForAll();


            if(pushcares != null){

                for (Pushcare tpuscare : pushcares){

                    if(tpuscare.getUserID()==userId&&tpuscare.getCourseBeBuy()==itemId){
                        return true;
                    }



                }




            }




        }catch (Exception e){

            return false;
        }




        return false;
    }



}
