/**
 * Created by NSD on 25.05.17.
 */




function getCurentUser(callback) {

    $.get("/API/Auth",{user:"user"},function (data) {

        if(data=="ANNONYMOUS"){

            //callback(null);

         return;
        }

        var user = JSON.parse(data);
        console.log(data);

        if(callback!=null){

            callback(user);

        }

    });


}

function check_user_role() {







}

