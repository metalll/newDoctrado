package Auth;

import DBSingletones.DBAdmin;
import DBSingletones.DBStudent;
import DBSingletones.DBTeacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by NSD on 30.05.17.
 */

@WebServlet(name = "validator",urlPatterns = "/API/Validator")
public class AuthValidator extends HttpServlet {


    @Override // this registration is Real?
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String,String[]> paramMap = req.getParameterMap();

        String email = null;
        String telNumber = null;


        try {
            email = paramMap.get("email")[0];
            telNumber = null;
        }catch (Exception e){

        }

        try {
            telNumber = paramMap.get("telNumber")[0];
        }catch (Exception e){
            telNumber = null;
        }



        boolean[] stRetVal = DBStudent.getInstance().hasStudent(email,telNumber);
        boolean[] tRetVal = DBTeacher.getInstance().hasTeacher(email,telNumber);
        boolean   aRetVal = DBAdmin.getInstance().hasAdmin(email);

        boolean checkLogin = stRetVal[0]||tRetVal[0]||aRetVal;
        boolean checkTel = stRetVal[1]||tRetVal[1];

        if(checkTel&&checkLogin){


            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("3"); // err 2 param
            out.flush();
            out.close();
            return;


        }
        if(checkTel){

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("2");
            out.flush();
            out.close();
            return;


        }
        if(checkLogin){


            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("1");
            out.flush();
            out.close();
            return;


        }






        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);
        out.write("0");
        out.flush();
        out.close();





    }
}
