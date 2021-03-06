package Auth;

import DBSingletones.DBAdmin;
import DBSingletones.DBStudent;
import DBSingletones.DBTeacher;
import Model.*;
import NSDReqCodeUtils.ReqCode;
import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.acl.LastOwnerException;
import java.util.IllegalFormatCodePointException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by NSD on 17.05.17.
 */

@WebServlet(name = "Auth",urlPatterns = "/API/Auth")
public class AuthServlet extends HttpServlet{

    private static final String U_NAME_PARAM = "login";
    private static final String U_PASS_PARAM = "pass";


    @Override // delete creditals
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isSuccesfull = false;

        try {
            req.getSession().removeAttribute(Authorizator.AUTH_ATTR);
            isSuccesfull = true;

            req.changeSessionId();

        }catch (Exception e){
            e.printStackTrace();
        }

        if(isSuccesfull){
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();
            out.print(ReqCode.getCodeWith(-1));
            out.flush();
            out.close();
        }
        else {
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();
            out.print(ReqCode.getCodeWith(0));
            out.flush();
            out.close();
        }





    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        AuthRealm authRealm = null;

        try {
            authRealm = (AuthRealm) req.getSession().getAttribute(Authorizator.AUTH_ATTR);
        }catch (Exception e){
           // authRealm = new AuthRealm();
        }

        if(authRealm == null){
            authRealm = new AuthRealm();
            authRealm.setAccessRole(UserRole.ANNONYMOUS);
        }



        Gson gson = new Gson();

        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);

        switch (authRealm.getAccessRole()){
            case ADMIN:
                Admin admin = (Admin) authRealm.getUser();
                out.write(gson.toJson(new RealmModelForJSON<Admin>(admin,"ADMIN")));
            break;
            case ANNONYMOUS:
                String respG = "ANNONYMOUS";
                out.write(respG);
            break;
            case STUDENT:
                Student student = (Student) authRealm.getUser();
                out.write(gson.toJson(new RealmModelForJSON<Student>(student,"STUDENT")));
            break;
            case TEACHER:
                Teacher teacher = (Teacher) authRealm.getUser();
                out.write( gson.toJson(new RealmModelForJSON<Teacher>(teacher,"TEACHER")));
            break;


        }
            out.flush();
            out.close();



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String,String[]> parameterMap = req.getParameterMap();


        String login = "";
        String pass = "";

        try {
            login = parameterMap.get(U_NAME_PARAM)[0];
            pass = parameterMap.get(U_PASS_PARAM)[0];
        }catch (Exception e){
            e.printStackTrace();
        }

        Object result = null;
        UserRole userRole = UserRole.ANNONYMOUS;

        //TODO get exist user is db







        AuthRealm resultRealm = Authorizator.getInstance().Auth(login,pass);

        if(resultRealm.getAccessRole()==UserRole.ANNONYMOUS){
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("-1");
            out.flush();
            out.close();
        }else{

            req.getSession().setAttribute("auth",resultRealm);
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            switch (resultRealm.getAccessRole()){
                case ADMIN:
                    out.write("1"); break;
                case STUDENT:
                    out.write("2"); break;
                case TEACHER:
                    out.write("3"); break;
            }

            out.flush();
            out.close();


        }








    }








}
