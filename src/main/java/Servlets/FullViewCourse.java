package Servlets;

import Auth.AuthRealm;
import DBSingletones.DBCourse;
import DBSingletones.DBPushcare;
import Model.Admin;
import Model.Course;
import Model.Student;
import Model.Teacher;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by NSD on 08.06.17.
 */


@WebServlet(name = "fullViewCourse",urlPatterns = "/API/ViewCourse")
public class FullViewCourse extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Map<String,String[]> paramMap = req.getParameterMap();

        long courseId;

        try {



            Object reqO = req.getSession().getAttribute("auth");
            courseId = Long.valueOf(paramMap.get("course")[0]);
            AuthRealm realm = (AuthRealm) reqO;


            Course tCourse = DBCourse.getInstance().getCourseWithId(courseId);

            if(reqO!=null){

                long userId = -1;



                if(realm.getUser() instanceof Admin){

                    PrintWriter out = resp.getWriter();
                    Gson gson = new Gson();
                    out.write(gson.toJson(DBCourse.getInstance().getCourseWithId(courseId)));
                    out.flush();
                    out.close();

                    return;



                }

                if(realm.getUser() instanceof Student){


                    if(DBPushcare.getInstance().checkPushcare(((Student)realm.getUser()).getId(),courseId)){


                        PrintWriter out = resp.getWriter();
                        Gson gson = new Gson();
                        out.write(gson.toJson(DBCourse.getInstance().getCourseWithId(courseId)));
                        out.flush();
                        out.close();


                    }




                }

                if(realm.getUser() instanceof Teacher){






                    if(DBPushcare.getInstance().checkPushcare(((Teacher)realm.getUser()).getId(),courseId)||((Teacher)realm.getUser()).getId()==tCourse.getAuthorId()){


                        PrintWriter out = resp.getWriter();
                        Gson gson = new Gson();
                        out.write(gson.toJson(tCourse));
                        out.flush();
                        out.close();


                    }

                }


                //if()














            }



            tCourse.setTest("");
            tCourse.setCourse("");

            PrintWriter out = resp.getWriter();
            Gson gson = new Gson();
            out.write(gson.toJson(tCourse));
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
