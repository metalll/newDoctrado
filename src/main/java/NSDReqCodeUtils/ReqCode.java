package NSDReqCodeUtils;

import com.google.gson.Gson;

/**
 * Created by NSD on 17.05.17.
 */
public class ReqCode {

    public static String getCodeWith(int code){

        String reqCode = String.valueOf(code);
        Gson gson = new Gson();


        return gson.toJson(reqCode);




    }

}
