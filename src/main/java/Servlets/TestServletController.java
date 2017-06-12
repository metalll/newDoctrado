package Servlets;

import Auth.AuthRealm;
import DBSingletones.DBCourse;
import DBSingletones.DBTeacher;
import Model.Course;
import Model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by NSD on 12.06.17.
 */

@WebServlet(name = "testController", urlPatterns = "/API/Test")
public class TestServletController extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            Map<String, String[]> paramMap = req.getParameterMap();


            long courseId = Long.valueOf(paramMap.get("id")[0]);

            String respVal = DBCourse.getInstance().getCourseWithId(courseId).getTest();


            PrintWriter out = resp.getWriter();
            out.write(respVal);
            out.flush();
            out.close();



        }catch (Exception e){

            PrintWriter out = resp.getWriter();
            out.write("-1");
            out.flush();
            out.close();
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String,String[]> paramMap = req.getParameterMap();

        try {





            String test = paramMap.get("test")[0];
            long courseId = Long.valueOf(paramMap.get("id")[0]);

            Course tempCourse = DBCourse.getInstance().getCourseWithId(courseId);

            if(tempCourse==null){ throw new NullPointerException(); }

            AuthRealm realm = (AuthRealm) req.getSession().getAttribute("auth");

            long teacherId = ((Teacher)realm.getUser()).getId();
            if(tempCourse.getAuthorId()==teacherId){


                tempCourse.setTest(test);
                tempCourse.setHasTest(true);

                DBCourse.getInstance().updateCourse(tempCourse);
                PrintWriter out = resp.getWriter();
                out.write("0");
                out.flush();
                out.close();
                return;

            }

            PrintWriter out = resp.getWriter();
            out.write("-1");
            out.flush();
            out.close();


        }catch (Exception e){

            PrintWriter out = resp.getWriter();
            out.write("-1");
            out.flush();
            out.close();



        }

    }
}
