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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {



            String[] str =  NSDDB.getConnection();

            for(int i=0;i<3;i++){
                System.out.println("str ["+i+"] : =" + str[i]);

            }

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
            List<Student> tempRetVal = queryAllStudents();

            if(tempRetVal.size()>0){

                for(Student tempStudent: tempRetVal){

                    if(tempStudent.getEmail().equals(login)) return tempStudent;


                }

            }
           // dao.getConnectionSource().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }


    public boolean hasStudent(String login){
        return queryStudent(login)!=null;
    }


    public boolean[] hasStudent(String login,String tel){
        boolean hasLogin = false;
        boolean hasTelephone = false;

        for (Student tempStudnet: queryAllStudents()
             ) {

            if(tempStudnet.getEmail().equals(login)) hasLogin = true;
            if(tempStudnet.getTelNumber().equals(tel)) hasTelephone = true;
            if(hasLogin&&hasTelephone) return new boolean[]{true,true};
        }



        return new boolean[]{hasLogin,hasTelephone};
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
