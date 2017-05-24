package DBSingletones;

import DBSingletones.DBAdmin;
import DBSingletones.DBInfo.NSDDB;
import Model.Admin;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 17.05.17.
 */
public class DBAdmin {

    private static volatile DBAdmin instance;
    private Dao<Admin,Long> dao = null;

    public static DBAdmin getInstance() {
        DBAdmin localInstance = instance;
        if (localInstance == null) {
            synchronized (DBAdmin.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBAdmin();
                }
            }
        }
        return localInstance;
    }

    private DBAdmin(){

        try {
            dao = DaoManager.createDao(NSDDB.getConnectionSourse(),Admin.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(dao!=null){
            try {
                if (!dao.isTableExists()) {
                    TableUtils.createTable(NSDDB.getConnectionSourse(),Admin.class);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }


    public List<Admin> queryAllAdmins(){

        List<Admin> retVal = null;

        try {
            retVal = dao.queryForAll();
            dao.getConnectionSource().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




        return retVal;

    }

    public Admin queryAdmin(String login){
        Admin retVal = null;
        try {
            retVal = dao.queryBuilder().where().eq("email", login).query().get(0);
            dao.getConnectionSource().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }


    public boolean hasAdmin(String login){
        return queryAdmin(login)!=null;
    }

    public void addAdmin(Admin admin){
        try {
            dao.create(admin);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateAdmin(Admin admin){
        try {
            dao.update(admin);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
