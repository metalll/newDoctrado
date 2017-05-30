package DBSingletones;

import DBSingletones.DBInfo.NSDDB;
import Model.Student;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 17.05.17.
 */
public class DBStudent {


    private static volatile DBStudent instance;
    private Dao<Student,Long> dao = null;

    public static DBStudent getInstance() {
        DBStudent localInstance = instance;
        if (localInstance == null) {
            synchronized (DBStudent.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBStudent();
                }
            }
        }
        return localInstance;
    }

    private DBStudent(){

        try {



            String[] str =  NSDDB.getConnection();

            dao = DaoManager.createDao(new JdbcConnectionSource(str[0],str[1],str[2]),Student.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(dao!=null){
            try {
                if (!dao.isTableExists()) {
                    TableUtils.createTable(dao.getConnectionSource(),Student.class);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }


    public List<Student> queryAllStudents(){

        List<Student> retVal = null;

        try {
            retVal = dao.queryForAll();
            dao.getConnectionSource().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




        return retVal;

    }

    public Student queryStudent(String login){
        Student retVal = null;
        try {
            retVal = dao.queryBuilder().where().eq("email", login).query().get(0);
            dao.getConnectionSource().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }


    public boolean hasStudent(String login){
        return queryStudent(login)!=null;
    }


    public boolean[] hasStudent(String login,String tel){
        Student tStudent = null;
        Student tStudent2 = null;
        try {

            if(login==null||login.equals("")){

            }else{
            tStudent = dao.queryBuilder().where().eq("email", login).query().get(0);
            dao.getConnectionSource().close();
            }
        }catch (SQLException e){
            e.printStackTrace();

        }

        try {
            if(tel==null||tel.equals("")){}
            else{
            tStudent2 = dao.queryBuilder().where().eq("telNumber",tel).query().get(0);
            dao.getConnectionSource().close();
            }
        }catch (SQLException e){
            e.printStackTrace();

        }


        return new boolean[]{tStudent!=null,tStudent2!=null};
    }

    public void addStudent(Student student){
        try {
            dao.create(student);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateStudent(Student student){
        try {
            dao.update(student);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
