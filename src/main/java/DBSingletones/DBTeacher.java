package DBSingletones;

import DBSingletones.DBInfo.NSDDB;
import Model.Teacher;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 17.05.17.
 */
public class DBTeacher {
    private static volatile DBTeacher instance;
    private Dao<Teacher,Long> dao = null;

    public static DBTeacher getInstance() {
        DBTeacher localInstance = instance;
        if (localInstance == null) {
            synchronized (DBTeacher.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBTeacher();
                }
            }
        }
        return localInstance;
    }

    private DBTeacher(){

        try {
            dao = DaoManager.createDao(NSDDB.getConnectionSourse(),Teacher.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(dao!=null){
            try {
                if (!dao.isTableExists()) {
                    TableUtils.createTable(NSDDB.getConnectionSourse(),Teacher.class);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }


    public List<Teacher> queryAllTeachers(){

        List<Teacher> retVal = null;

        try {
            retVal = dao.queryForAll();
            dao.getConnectionSource().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




        return retVal;

    }

    public Teacher queryTeacher(String login){
        Teacher retVal = null;
        try {
            retVal = dao.queryBuilder().where().eq("email", login).query().get(0);
            dao.getConnectionSource().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }


    public boolean hasTeacher(String login){
        return queryTeacher(login)!=null;
    }

    public void addTeacher(Teacher Teacher){
        try {
            dao.create(Teacher);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTeacher(Teacher teacher){
        try {
            dao.update(teacher);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}



