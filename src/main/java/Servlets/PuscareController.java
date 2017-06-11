package Servlets;

import Auth.AuthRealm;
import DBSingletones.DBCourse;
import DBSingletones.DBPushcare;
import DBSingletones.DBStudent;
import DBSingletones.DBTeacher;
import Model.Course;
import Model.Pushcare;
import Model.Student;
import Model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.security.Principal;
import java.util.Map;

/**
 * Created by NSD on 08.06.17.
 */


@WebServlet(name = "Pus", urlPatterns = "/API/Pushcare")
public class PuscareController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String,String[]> paramMap = req.getParameterMap();

        try {
            long itemByBuy = Long.parseLong(paramMap.get("itemByBuy")[0]);

            Course course = DBCourse.getInstance().getCourseWithId(itemByBuy);



            long userId = -1;

            AuthRealm reqObject = ( AuthRealm ) req.getSession().getAttribute("auth");

            if(reqObject.getUser() instanceof Student){
                Student tempStudent = (Student) reqObject.getUser();

                userId = tempStudent.getId();

                if(tempStudent.getBalance()>course.getCost()&&userId!=-1){

                    int balance = tempStudent.getBalance();

                    tempStudent.setBalance(balance - course.getCost());
                    DBStudent.getInstance().updateStudent(tempStudent);

                    Pushcare tempPuscare = new Pushcare();
                    tempPuscare.setCourseBeBuy(course.getId());
                    tempPuscare.setUserID(tempStudent.getId());
                    DBPushcare.getInstance().addPushcare(tempPuscare);

                    PrintWriter out = resp.getWriter();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    out.write("1");
                    out.flush();
                    out.close();


                }else{

                    throw new NullPointerException();

                }



            }
            if(reqObject.getUser() instanceof Teacher){
                Teacher tempTeacher = (Teacher) reqObject.getUser();

                userId = tempTeacher.getId();

                if(tempTeacher.getBalance()>course.getCost()&&userId!=-1){

                    int balance = tempTeacher.getBalance();

                    tempTeacher.setBalance(balance - course.getCost());
                    DBTeacher.getInstance().updateTeacher(tempTeacher);

                    Pushcare tempPuscare = new Pushcare();
                    tempPuscare.setCourseBeBuy(course.getId());
                    tempPuscare.setUserID(tempTeacher.getId());
                    DBPushcare.getInstance().addPushcare(tempPuscare);

                    PrintWriter out = resp.getWriter();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    out.write("1");
                    out.flush();
                    out.close();


                }else{

                    throw new NullPointerException();

                }


            }










        }catch (Exception e){


            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.write("-1");
            out.flush();
            out.close();



            return;

        }




    }
}
