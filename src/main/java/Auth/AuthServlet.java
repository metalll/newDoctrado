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
                out.write(gson.toJson(new RealmModelForJSON<Teacher>(teacher,"TEACHER")));
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


    @Override // registration
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String,String> parameterMap = getParameterMapPut(req);

        if(parameterMap==null){



            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("-1");
            out.flush();
            out.close();
            return;
        }







        UserRole role = UserRole.ANNONYMOUS;


        try{
            String tempUserRole = parameterMap.get("role");

            if(tempUserRole.equals("t")){
                role = UserRole.TEACHER;
            }
            if(tempUserRole.equals("s")){
                role = UserRole.STUDENT;
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();
            out.write(-1);
            out.flush();
            out.close();
        }



        if(role.equals(UserRole.STUDENT)){
            String email = null;
            String passwordHex = null;
            String avatar = null;
            String name = null;
            String surname = null;
            String firstname = null;
            String cardNumber = null;
            String cardExploedInfo = null;
            String cardCSV =null ;
            String city = null ;
            String educationInfo = null ;
            String educationInfoDoc = null;
            String bornDate = null;
            String telNumber = null;
            int balance = 100;

            try {
                email = parameterMap.get("email");
                passwordHex = parameterMap.get("password");
                avatar = parameterMap.get("avatar");
                name = parameterMap.get("name");
                surname = parameterMap.get("surname");
                firstname = parameterMap.get("firstName");
                city = parameterMap.get("city");
                educationInfo = parameterMap.get("eduInfo");
                educationInfoDoc = parameterMap.get("eduInfoDoc");
                bornDate = parameterMap.get("bornDate");
                telNumber = parameterMap.get("telNumber");


                if(DBAdmin.getInstance().hasAdmin(email)|| DBStudent.getInstance().hasStudent(email)|| DBTeacher.getInstance().hasTeacher(email)){
                    throw new IllegalFormatCodePointException(1);

                }else{

                    Student tempStudent = new Student();

                    tempStudent.setEmail(email);
                    tempStudent.setPasswordHex(passwordHex);
                    tempStudent.setCardNumber("");
                    tempStudent.setCardCSV("");
                    tempStudent.setCardExploedInfo("");
                    tempStudent.setName(name);
                    tempStudent.setFirstname(firstname);
                    tempStudent.setActivated(false);
                    tempStudent.setCity(city);
                    tempStudent.setBalance(100);
                    tempStudent.setAvatar(avatar);
                    tempStudent.setSurname(surname);
                    tempStudent.setBornDate(bornDate);
                    tempStudent.setEducationInfo(educationInfo);
                    tempStudent.setEducationInfoDoc(educationInfoDoc);
                    tempStudent.setTelNumber(telNumber);
                    DBStudent.getInstance().addStudent(tempStudent);
                    AuthRealm resultRealm = Authorizator.getInstance().Auth(email,passwordHex);
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
            catch (Exception e){

                if(e instanceof IllegalFormatCodePointException){

                    PrintWriter out = resp.getWriter();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    out.write("0");
                    out.flush();
                    out.close();

                }
                else {

                    e.printStackTrace();
                    PrintWriter out = resp.getWriter();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    out.write("-1");
                    out.flush();
                    out.close();
                }



            }





        }


        if(role.equals(UserRole.TEACHER)){

            long id = -1;
            String email = null;
            String passwordHex = null;
            String avatar = null;
            String name = null;
            String surname = null;
            String firstname = null;
            String cardNumber = null;
            String telNumber = null;
            String cardExploedInfo = null;
            String cardCSV = null;
            String expirence = null;
            String bornDate = null;
            String educationInfo = null;
            String educationInfoDoc = null;
            int balance = 100;
            boolean isActivated = false;
            String city = null;

            if(DBAdmin.getInstance().hasAdmin(email)|| DBStudent.getInstance().hasStudent(email)|| DBTeacher.getInstance().hasTeacher(email)){
                throw new IllegalFormatCodePointException(1);

            }else{

                email = parameterMap.get("email");
                passwordHex = parameterMap.get("password");
                avatar = parameterMap.get("password");
                name = parameterMap.get("name");
                surname = parameterMap.get("surname");
                firstname = parameterMap.get("firstName");
                city = parameterMap.get("city");
                educationInfo = parameterMap.get("eduInfo");
                educationInfoDoc = parameterMap.get("eduInfoDoc");
                bornDate = parameterMap.get("bornDate");
                telNumber = parameterMap.get("telNumber");
                expirence = parameterMap.get("expirence");

                Teacher tempTeacher = new Teacher();

                tempTeacher.setEmail(email);
                tempTeacher.setPasswordHex(passwordHex);
                tempTeacher.setCardNumber("");
                tempTeacher.setCardCSV("");
                tempTeacher.setCardExploedInfo("");
                tempTeacher.setName(name);
                tempTeacher.setFirstname(firstname);
                tempTeacher.setActivated(false);
                tempTeacher.setCity(city);
                tempTeacher.setBalance(100);
                tempTeacher.setAvatar(avatar);
                tempTeacher.setSurname(surname);
                tempTeacher.setBornDate(bornDate);
                tempTeacher.setEducationInfo(educationInfo);
                tempTeacher.setEducationInfoDoc(educationInfoDoc);
                tempTeacher.setTelNumber(telNumber);
                tempTeacher.setExpirence(expirence);

                DBTeacher.getInstance().addTeacher(tempTeacher);

                AuthRealm resultRealm = Authorizator.getInstance().Auth(email,passwordHex);
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


    }


    public static Map<String, String> getParameterMapPut(HttpServletRequest request) {

        BufferedReader br = null;
        Map<String, String> dataMap = null;

        try {

            InputStreamReader reader = new InputStreamReader(
                    request.getInputStream());
            br = new BufferedReader(reader);

            String data = br.readLine();

            dataMap = Splitter.on('&')
                    .trimResults()
                    .withKeyValueSeparator(
                            Splitter.on('=')
                                    .limit(2)
                                    .trimResults())
                    .split(data);

            return dataMap;
        } catch (IOException ex) {
            //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
              //      Logger.getLogger(Utils.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }

        return null;
    }




}
