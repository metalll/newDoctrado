package DBSingletones;

import DBSingletones.DBInfo.NSDDB;
import Model.Admin;
import Model.Course;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 08.06.17.
 */
public class DBCourse {


    private static volatile DBCourse instance;
    private Dao<Course,Long> dao = null;

    public static DBCourse getInstance() {
        DBCourse localInstance = instance;
        if (localInstance == null) {
            synchronized (DBCourse.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBCourse();
                }
            }
        }
        return localInstance;
    }

    private DBCourse(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String[] str =  NSDDB.getConnection();


            dao = DaoManager.createDao(new JdbcConnectionSource(str[0],str[1],str[2]),Course.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(dao!=null){
            try {
                if (!dao.isTableExists()) {
                    TableUtils.createTable(dao.getConnectionSource(),Course.class);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }


    public void addCourse(Course course){
        try {
            dao.create(course);
            dao.getConnectionSource().close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




    public Course getCourseWithId(long id){
       try {
           Course retVal = dao.queryForId(id);
           dao.getConnectionSource().close();
           return retVal;

       }catch (Exception e){



       }


        return null;
    }


    public List<Course> queryAllCourses(){

        List<Course> retVal = null;

        try {
            retVal = dao.queryForAll();
            dao.getConnectionSource().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;

    }

}
