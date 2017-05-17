package Auth;

import Model.Admin;
import Model.Student;
import Model.Teacher;
import NSDReqCodeUtils.ReqCode;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.acl.LastOwnerException;
import java.util.Map;

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
        AuthRealm authRealm;

        try {
            authRealm = (AuthRealm) req.getAttribute(Authorizator.AUTH_ATTR);
        }catch (Exception e){
            authRealm = new AuthRealm();
        }

        Gson gson = new Gson();

        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);

        switch (authRealm.getAccessRole()){
            case ADMIN:
                Admin admin = (Admin) authRealm.getUser();
                out.write(gson.toJson(admin));
            break;
            case ANNONYMOUS:
                String respG = "ANNONYMOUS";
                out.write(gson.toJson(respG));
            break;
            case STUDENT:
                Student student = (Student) authRealm.getUser();
                out.write(gson.toJson(student));
            break;
            case TEACHER:
                Teacher teacher = (Teacher) authRealm.getUser();
                out.write(gson.toJson(teacher));
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











    }


    @Override // registration
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);

    }


    @Override // this registration is Real?
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }
}
