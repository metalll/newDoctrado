package Auth;

import DBSingletones.DBAdmin;
import DBSingletones.DBStudent;
import DBSingletones.DBTeacher;
import Model.Student;
import Model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

/**
 * Created by NSD on 05.06.17.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = "API/Reg")
public class RegisterServlet extends HttpServlet {

    @Override // registration
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        Map<String,String[]> parameterMap = req.getParameterMap();

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
            String tempUserRole = parameterMap.get("role")[0];

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
                email = parameterMap.get("email")[0];
                passwordHex = parameterMap.get("password")[0];
                avatar = parameterMap.get("avatar")[0];
                name = parameterMap.get("name")[0];
                surname = parameterMap.get("surname")[0];
                firstname = parameterMap.get("firstName")[0];
                city = parameterMap.get("city")[0];
                educationInfo = parameterMap.get("eduInfo")[0];
                educationInfoDoc = parameterMap.get("eduInfoDoc")[0];
                bornDate = parameterMap.get("bornDate")[0];
                telNumber = parameterMap.get("telNumber")[0];


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
            String email = parameterMap.get("email")[0];
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

                email = parameterMap.get("email")[0];
                passwordHex = parameterMap.get("password")[0];
                avatar = parameterMap.get("avatar")[0];
                name = parameterMap.get("name")[0];
                surname = parameterMap.get("surname")[0];
                firstname = parameterMap.get("firstName")[0];
                city = parameterMap.get("city")[0];
                educationInfo = parameterMap.get("eduInfo")[0];
                educationInfoDoc = parameterMap.get("eduInfoDoc")[0];
                bornDate = parameterMap.get("bornDate")[0];
                telNumber = parameterMap.get("telNumber")[0];
                expirence = parameterMap.get("expirence")[0];

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

}
