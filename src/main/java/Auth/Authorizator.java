package Auth;

import DBSingletones.DBAdmin;
import DBSingletones.DBStudent;
import DBSingletones.DBTeacher;
import Model.Admin;
import Model.Student;
import Model.Teacher;

/**
 * Created by NSD on 17.05.17.
 */
public class Authorizator {

    static final String AUTH_ATTR = "auth";

    private static volatile Authorizator instance;

    public static Authorizator getInstance() {
        Authorizator localInstance = instance;
        if (localInstance == null) {
            synchronized (Authorizator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Authorizator();
                }
            }
        }
        return localInstance;
    }


    public AuthRealm Auth(String login,String hexOfPass){

        Object retVal;

        retVal = DBStudent.getInstance().queryStudent(login);

        if(retVal==null){
            retVal = DBTeacher.getInstance().queryTeacher(login);
        }
        if(retVal==null){
            retVal = DBAdmin.getInstance().queryAdmin(login);
        }


        if(retVal!=null){

            if(retVal instanceof Student){
                Student tS = (Student) retVal;

                if(tS.getPasswordHex().equals(hexOfPass)){
                    //clear creditals
                    tS.setPasswordHex("");
                    tS.setCardCSV("");
                    tS.setCardNumber("");
                    tS.setCardExploedInfo("");
                    return new AuthRealm(tS,UserRole.STUDENT);
                }

            }

            if(retVal instanceof Teacher){
                Teacher tT = (Teacher) retVal;

                if(tT.getPasswordHex().equals(hexOfPass)){
                    //clear creditals

                    tT.setPasswordHex("");
                    tT.setCardCSV("");
                    tT.setCardNumber("");
                    tT.setCardExploedInfo("");
                    return new AuthRealm(tT,UserRole.TEACHER);
                }

            }

            if(retVal instanceof Admin){
                Admin tA = (Admin) retVal;
                if(tA.getPasswordHex().equals(hexOfPass)){
                    tA.setPasswordHex("");
                    //clear creditals

                    return new AuthRealm(tA,UserRole.ADMIN);
                }




            }



        }





        return new AuthRealm(); //ret Annonimous
    }


    public AuthRealm Reg(Object user,String login,String hexOfPass){


        if(DBAdmin.getInstance().hasAdmin(login)||DBStudent.getInstance().hasStudent(login)|DBTeacher.getInstance().hasTeacher(login)){

            return new AuthRealm();

        }


        if(user instanceof Student){


            Student regStudent = (Student) user;
            if(regStudent.getPasswordHex()==null|regStudent.getPasswordHex().equals("")){
                regStudent.setPasswordHex(hexOfPass);
            }

            DBStudent.getInstance().addStudent(regStudent);

            regStudent.setCardNumber("");
            regStudent.setCardExploedInfo("");
            regStudent.setCardCSV("");
            regStudent.setPasswordHex("");

            return new AuthRealm(regStudent,UserRole.STUDENT);

        }

        if(user instanceof Teacher){


            Teacher regTeacher = (Teacher) user;
            if(regTeacher.getPasswordHex()==null|regTeacher.getPasswordHex().equals("")){
                regTeacher.setPasswordHex(hexOfPass);
            }

            DBTeacher.getInstance().addTeacher(regTeacher);
            regTeacher.setCardNumber("");
            regTeacher.setCardExploedInfo("");
            regTeacher.setCardCSV("");
            regTeacher.setPasswordHex("");
            return new AuthRealm(regTeacher,UserRole.TEACHER);
        }




        return new AuthRealm();
    }




}
