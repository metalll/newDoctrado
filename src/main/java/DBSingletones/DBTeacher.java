package DBSingletones;

import DBSingletones.DBInfo.NSDDB;
import Model.Admin;
import Model.Teacher;
import Model.Teacher;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String[] str =  NSDDB.getConnection();


            dao = DaoManager.createDao(new JdbcConnectionSource(str[0],str[1],str[2]), Teacher.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(dao!=null){
            try {
                if (!dao.isTableExists()) {
                    TableUtils.createTable(dao.getConnectionSource(),Teacher.class);
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


    public boolean[] hasTeacher(String login,String tel){
        Teacher tTeacher = null;
        Teacher tTeacher2 = null;
        try {

            if(login==null||login.equals("")){

            }else{
            tTeacher = dao.queryBuilder().where().eq("email", login).query().get(0);
            dao.getConnectionSource().close();
            }
        }catch (SQLException e){
            e.printStackTrace();

        }

        try {
            if(tel==null||tel.equals("")){}
            else{
            tTeacher2 = dao.queryBuilder().where().eq("telNumber",tel).query().get(0);
            dao.getConnectionSource().close();
            }
        }catch (SQLException e){
            e.printStackTrace();

        }


        return new boolean[]{tTeacher!=null,tTeacher2!=null};
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



