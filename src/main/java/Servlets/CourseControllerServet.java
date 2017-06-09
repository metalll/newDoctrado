package Servlets;

import Auth.AuthRealm;
import Auth.UserRole;
import DBSingletones.DBCourse;
import Model.Course;
import Model.Teacher;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NSD on 08.06.17.
 */

@WebServlet(name="courseControllerServlet", urlPatterns = "/API/Course")
public class CourseControllerServet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");



        Map<String,String[]> paramMap = req.getParameterMap();


        String query = null;
        String author = null;
        String page = null;
        try{
            query = paramMap.get("q")[0];
        }catch (Exception e){
            query = null;
        }
        try{
            author = paramMap.get("a")[0];
        }catch (Exception e){
            query = null;
        }
        try{
            page = paramMap.get("page")[0];
        }catch (Exception e){
            page = null;
        }


        if(author!=null&&query!=null){

            PrintWriter out = resp.getWriter();
            out.write("[]");
            out.flush();
            out.close();

            return;


        }

        int intAuthor = 0;

        if(author!=null){
            try {

               intAuthor = Integer.valueOf(author);


            }catch (Exception e){
                PrintWriter out = resp.getWriter();
                out.write("[]");
                out.flush();
                out.close();
            }


        }

        List<Course> courseList = DBCourse.getInstance().queryAllCourses();
        List<Course> resultList = new ArrayList<Course>();


        if(courseList==null){
            PrintWriter out = resp.getWriter();
            out.write("[]");
            out.flush();
            out.close();


        }

        for(Course tCourse: courseList){


            if(author==null&&query == null){
                tCourse.setCourse("");
                resultList.add(tCourse);
                continue;
            }



            if(author!=null){
                if(intAuthor==tCourse.getAuthorId()){

                    tCourse.setCourse("");

                    resultList.add(tCourse);
                    continue;
                }
            }

            if(query!=null){

                if(tCourse.getHeaderText().toLowerCase().contains(query.toLowerCase())){

                    tCourse.setCourse("");

                    resultList.add(tCourse);
                }

            }

        }

        Gson gson = new Gson();



        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);
        out.write(gson.toJson(resultList));
        out.flush();
        out.close();



        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");




       try{

           AuthRealm realm = (AuthRealm) req.getSession().getAttribute("auth");
           if(realm.getAccessRole().equals(UserRole.TEACHER)){



               Map<String,String[]> paramMap = req.getParameterMap();

               String headerText = paramMap.get("headerText")[0];
               String course = paramMap.get("course")[0];
               int cost = Integer.valueOf(paramMap.get("cost")[0]);
               String avatar = paramMap.get("avatar")[0];
               long authorId = ((Teacher) realm.getUser()).getId();
               boolean hasTest = false;
               String test = "";


               Course tempCourse = new Course();
               tempCourse.setCourse(course);
               tempCourse.setHeaderText(headerText);
               tempCourse.setAuthorId(authorId);
               tempCourse.setCost(cost);
               tempCourse.setAvatar(avatar);
               tempCourse.setHasTest(false);
               tempCourse.setTest("");


               DBCourse.getInstance().addCourse(tempCourse);




               PrintWriter out = resp.getWriter();
               out.write("0");
               out.flush();
               out.close();


           }else {
                throw new NullPointerException();
           }

       }catch (Exception e){

           PrintWriter out = resp.getWriter();
           out.write("-1");
           out.flush();
           out.close();
       }






    }
}
