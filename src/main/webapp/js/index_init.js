/**
 * Created by NSD on 24.05.17.
 */
$(document).ready(function () {



    renderCourse();

    $('.carousel.carousel-slider').carousel({fullWidth: true});
    // $('.button-collapse').sideNav();

    // setInterval(function() {
    //     $('.carousel').carousel('next');
    // }, 5000);











    //getCurentUser();

});

function renderCourse() {

    $.ajax({

        type: 'get',
        url: "/API/Course",
        data: {

        },success:function (data) {

            var courses = JSON.parse(data);

            var card="";

            var container = document.getElementById('course-container');


            card += "<div class=\" row col s12 m12 l12\">";


            for(var i = 0 ; i<courses.length;i++){

var elC = i+1;


                var j =  courses.length-i-1;


            card += "  <div class=\"col s12 m4 l4\">";
            card += "        <div style=\"height: 350px;\"  class=\"card hoverable\">";
            card += "            <div class=\"card-image\">";
            card += "                <img class=\"center center-align\" style=\"height:200px;overflow:hidden; top: 0; bottom:0; left: 0; right:0; margin: auto;\" src=\""+courses[i].avatar+"\">";
            card += "                <span class=\"card-title flow-text\"><\/span><\/div><div class=\"card-content\"><p class=\"center-align\">"+courses[i].headerText+"<\/p><\/div>";
            card += "            <div class=\"card-action  center-align\"><p style=\"margin-top: 10px;\" class=\"col s6 m6 l6\"><a href=\"https:\/\/doctrado-sviasy.rhcloud.com\/getCourse?id=cd056f82-4a1f-459d-b5fe-109eae8c0d3b\" class=\"\">Цена "+courses[i].cost+" грн<\/a><\/p>";
            card += "               <a style=\" \" href=\"https:\/\/doctrado-sviasy.rhcloud.com\/getCourse?id=cd056f82-4a1f-459d-b5fe-109eae8c0d3b\" class=\"col s6 m6 l6 btn green\">Подробнее<\/a>";
            card += "            <\/div>";
            card += "        <\/div>";
            card += "        <\/div>";


                if(elC%3==0&&j<=3){
                    if(j==3){  }
                    if(j==2){
                        card += "<div class=\"col l2\"><\/div>";
                    }
                    if(j==1){
                        card += "<div class=\"col l4\"><\/div>";
                    }
                }
            }

            card += "<\/div>"

            container.innerHTML = card;
        }




    });



}
