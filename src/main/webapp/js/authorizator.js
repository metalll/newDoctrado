/**
 * Created by NSD on 25.05.17.
 */




function getCurentUser() {

    $.get("/API/Auth",{user:"user"},function (data) {

        var user = JSON.parse(data);
        console.log(data);
    });


}

function check_user_role() {







}

