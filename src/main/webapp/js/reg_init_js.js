/**
 * Created by NSD on 05.06.17.
 */



var telephone = "";
var email = "";
var role = "";
var hasPhoto = false;

var password ;
var name ;
var surname;
var firstname ;
var city ;
var eduInfo ;
var eduInfoDoc ;
var bornDate ;
var avatar ;
var expirence ;


function selectStudent() {

    role = "student";

    document.getElementById('login_div').innerHTML=page3Student;

    $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 160,

        labelMonthNext: 'Следущий месяц',
        labelMonthPrev: 'Прошлый месяц',

        labelMonthSelect: 'Выберите месяц',
        labelYearSelect: 'Выберите год',

        monthsFull: ['Январь', 'Ферваль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
        monthsShort: ['Янв', 'Фев', 'Март', 'Апр', 'Mай', 'Июн', 'Июл', 'Авг', 'Сент', 'Окт', 'Ноябр', 'Декбр'],
        weekdaysFull: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Субота'],
        weekdaysShort: ['Вс', 'Пн', 'Вт', 'Ср', 'Чтв', 'Птн', 'Сб'],

        weekdaysLetter: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],

        today: 'Сегодня',
        clear: 'Очистить',
        close: 'X',

        format: 'dd.mm.yyyy'

    });

    document.getElementById('additional_div').style.minHeight = "120%";

    $("#avatar-input").change(function () {
        hasPhoto = false;
        var file, img;
        var _URL = window.URL || window.webkitURL;
        if ((file = this.files[0])) {
            if (this.files[0].size > 5242880) {
                document.getElementById("avatar-input").value = "";

                Materialize.toast('Размер картинки не должен превышать 5Мб', 3000, 'rounded red');
                return;
            }
            img = new Image();
            img.onload = function () {
                hasPhoto = true;
                $("#avatar-img").css('width', '100%').css('height', '100%');
                document.getElementById('avatar-img').src = img.src;
            };
            img.onerror = function () {

                Materialize.toast('Выбранный файл не является картинкой!', 3000, 'rounded red');
            };
            img.src = _URL.createObjectURL(file);
        }
    });

}

function selectTeacher() {
    role = "teacher";


    document.getElementById('login_div').innerHTML=page3Teacher;


    $('.datepicker').pickadate({
        selectMonths: true,//Creates a dropdown to control month
        selectYears: 160,//Creates a dropdown of 15 years to control year
//The title label to use for the month nav buttons
        labelMonthNext: 'Следущий месяц',
        labelMonthPrev: 'Прошлый месяц',
//The title label to use for the dropdown selectors
        labelMonthSelect: 'Выберите месяц',
        labelYearSelect: 'Выберите год',
//Months and weekdays
        monthsFull: ['Январь', 'Ферваль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
        monthsShort: ['Янв', 'Фев', 'Март', 'Апр', 'Mай', 'Июн', 'Июл', 'Авг', 'Сент', 'Окт', 'Ноябр', 'Декбр'],
        weekdaysFull: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Субота'],
        weekdaysShort: ['Вс', 'Пн', 'Вт', 'Ср', 'Чтв', 'Птн', 'Сб'],
//Materialize modified
        weekdaysLetter: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
//Today and clear
        today: 'Сегодня',
        clear: 'Очистить',
        close: 'X',
//The format to show on the `input` element
        format: 'dd.mm.yyyy'

    });

    document.getElementById('additional_div').style.minHeight = "120%";

    $("#avatar-input").change(function () {
        hasPhoto = false;
        var file, img;
        var _URL = window.URL || window.webkitURL;
        if ((file = this.files[0])) {
            if (this.files[0].size > 5242880) {
                document.getElementById("avatar-input").value = "";

                Materialize.toast('Размер картинки не должен превышать 5Мб', 3000, 'rounded red');
                return;
            }
            img = new Image();
            img.onload = function () {
                hasPhoto = true;
                $("#avatar-img").css('width', '100%').css('height', '100%');
                document.getElementById('avatar-img').src = img.src;
            };
            img.onerror = function () {

                Materialize.toast('Выбранный файл не является картинкой!', 3000, 'rounded red');
            };
            img.src = _URL.createObjectURL(file);
        }
    });


}




var preloader = "";
var page1 = "";
var page2 = "";
var page3Teacher = "";
var page3Student = "";

$(document).ready(function () {

    $.get("https://datadoctrado-sviasy.rhcloud.com",{},function () {

        console.log("set data storage online");

    });


    page3Teacher += "     <div class=\"row\">";
    page3Teacher += "            <div class=\"col s12\" style=\"padding: 10px;\">";
    page3Teacher += "                <a href=\"index.html\">";
    page3Teacher += "                    <img src=\"favicon.ico\" style=\"height: 28px; vertical-align: middle; margin-top: -7px;\"><span class=\"grey-text text-darken-1 lighten\" style=\"font-size: 1.3rem; margin-left: 10px; font-weight: 700; font-family: Lato;\"><b class=\"orange-text\">Doct<\/b>rado<\/span>";
    page3Teacher += "                <\/a>";
    page3Teacher += "            <\/div>";
    page3Teacher += "        <\/div>";
    page3Teacher += "        <div class=\"row\">";
    page3Teacher += "            <div class=\"col s12 edge\">";
    page3Teacher += "                <h4 class=\"lato\">Регистрация преподователя<\/h4>";
    page3Teacher += "            <\/div>";
    page3Teacher += "        <\/div>";
    page3Teacher += "        <div class=\"row\" style=\"max-width: 600px;\">";
    page3Teacher += "            <h6 class=\"center lato center-align\"><span style=\"font-size: 16px\">Ваш аватар<\/span><\/h6>";
    page3Teacher += "            <div class=\"row col s12 edge\">";
    page3Teacher += "                <button style=\"position: relative;";
    page3Teacher += "                       background: #ffffff;";
    page3Teacher += "                       background-color: #ffffff;";
    page3Teacher += "                       width: 100%;";
    page3Teacher += "                       max-width: 220px;";
    page3Teacher += "                       height: 220px;";
    page3Teacher += "                       display: block;";
    page3Teacher += "                       border: none;";
    page3Teacher += "                       margin: 0 auto;\"";
    page3Teacher += "                        type=\"button\"";
    page3Teacher += "                        onclick=\"uploadPhoto();\">";
    page3Teacher += "";
    page3Teacher += "";
    page3Teacher += "                    <img class=\"responsive-img circle\" src=\"img\/default_avatar.jpg\" id=\"avatar-img\">";
    page3Teacher += "";
    page3Teacher += "                <\/button>";
    page3Teacher += "                <input accept=\"image\/*\" style=\"display: none;\" id=\"avatar-input\" type=\"file\">";
    page3Teacher += "";
    page3Teacher += "";
    page3Teacher += "                <div class=\"row\">";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"name\" type=\"text\" class=\"validate\">";
    page3Teacher += "                        <label for=\"name\">Ваше имя<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"surname\" type=\"text\" class=\"validate\">";
    page3Teacher += "                        <label for=\"surname\">Ваша фамилия<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                <\/div>";
    page3Teacher += "                <div class=\"row\">";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"first_name\" type=\"text\" class=\"validate\">";
    page3Teacher += "                        <label for=\"first_name\">Ваше отчество<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"city\" type=\"text\" class=\"validate\">";
    page3Teacher += "                        <label for=\"city\">Город проживания<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                <\/div>";
    page3Teacher += "                <div class=\"row\">";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"edu_info\" type=\"text\" class=\"validate\">";
    page3Teacher += "                        <label for=\"edu_info\">Ваше образование<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                    <div class=\"file-field input-field col s12 m6\">";
    page3Teacher += "                        <div class=\"btn orange\">";
    page3Teacher += "                            <span>Файл<\/span>";
    page3Teacher += "                            <input id=\"edu_info_doc\" type=\"file\">";
    page3Teacher += "                        <\/div>";
    page3Teacher += "                        <div class=\"file-path-wrapper\">";
    page3Teacher += "                            <input  class=\"file-path validate\" type=\"text\" placeholder=\"Подтв. документы\">";
    page3Teacher += "                        <\/div>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                <\/div>";
    page3Teacher += "                <div class=\"row\">";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"born_date\" placeholder=\"Дата рождения\" type=\"date\" class=\"datepicker\">";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"expirence\" type=\"text\" class=\"validate\">";
    page3Teacher += "                        <label for=\"expirence\">Ваш опыт преподавания<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                <\/div>";
    page3Teacher += "";
    page3Teacher += "                <div class=\"row\">";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"password\" type=\"password\" class=\"validate\">";
    page3Teacher += "                        <label for=\"password\">Ваш пароль для входа<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                    <div class=\"input-field col s12 m6\">";
    page3Teacher += "                        <input id=\"password_2\" type=\"password\" class=\"validate\">";
    page3Teacher += "                        <label for=\"password_2\">Подтвердите пароль<\/label>";
    page3Teacher += "                    <\/div>";
    page3Teacher += "                <\/div>";
    page3Teacher += "                <a class=\"col s12 center center-align btn green\" href=\"javascript:void(0)\" onclick=\"regTeacher();\">Зарегестрироваться<\/a>";
    page3Teacher += "            <\/div>";
    page3Teacher += "        <\/div>";




    page3Student += "   <div class=\"row\">";
    page3Student += "            <div class=\"col s12\" style=\"padding: 10px;\">";
    page3Student += "                <a href=\"index.html\">";
    page3Student += "                    <img src=\"favicon.ico\" style=\"height: 28px; vertical-align: middle; margin-top: -7px;\"><span class=\"grey-text text-darken-1 lighten\" style=\"font-size: 1.3rem; margin-left: 10px; font-weight: 700; font-family: Lato;\"><b class=\"orange-text\">Doct<\/b>rado<\/span>";
    page3Student += "                <\/a>";
    page3Student += "            <\/div>";
    page3Student += "        <\/div>";
    page3Student += "        <div class=\"row\">";
    page3Student += "            <div class=\"col s12 edge\">";
    page3Student += "                <h4 class=\"lato\">Регистрация студента<\/h4>";
    page3Student += "            <\/div>";
    page3Student += "        <\/div>";
    page3Student += "        <div class=\"row\" style=\"max-width: 600px;\">";
    page3Student += "            <h6 class=\"center lato center-align\"><span style=\"font-size: 16px\">Ваш аватар<\/span><\/h6>";
    page3Student += "            <div class=\"row col s12 edge\">";
    page3Student += "                <button style=\"position: relative;";
    page3Student += "                       background: #ffffff;";
    page3Student += "                       background-color: #ffffff;";
    page3Student += "                       width: 100%;";
    page3Student += "                       max-width: 220px;";
    page3Student += "                       height: 220px;";
    page3Student += "                       display: block;";
    page3Student += "                       border: none;";
    page3Student += "                       margin: 0 auto;\"";
    page3Student += "                        type=\"button\"";
    page3Student += "                        onclick=\"uploadPhoto();\">";
    page3Student += "";
    page3Student += "";
    page3Student += "                    <img class=\"responsive-img circle\" src=\"img\/default_avatar.jpg\" id=\"avatar-img\">";
    page3Student += "";
    page3Student += "                <\/button>";
    page3Student += "                <input accept=\"image\/*\" style=\"display: none;\" id=\"avatar-input\" type=\"file\">";
    page3Student += "";
    page3Student += "";
    page3Student += "                <div class=\"row\">";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"name\" type=\"text\" class=\"validate\">";
    page3Student += "                        <label for=\"name\">Ваше имя<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"surname\" type=\"text\" class=\"validate\">";
    page3Student += "                        <label for=\"surname\">Ваша фамилия<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                <\/div>";
    page3Student += "                <div class=\"row\">";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"first_name\" type=\"text\" class=\"validate\">";
    page3Student += "                        <label for=\"first_name\">Ваше отчество<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"city\" type=\"text\" class=\"validate\">";
    page3Student += "                        <label for=\"city\">Город проживания<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                <\/div>";
    page3Student += "                <div class=\"row\">";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"edu_info\" type=\"text\" class=\"validate\">";
    page3Student += "                        <label for=\"edu_info\">Ваше образование<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                    <div class=\"file-field input-field col s12 m6\">";
    page3Student += "                        <div class=\"btn orange\">";
    page3Student += "                            <span>Файл<\/span>";
    page3Student += "                            <input id=\"edu_info_doc\" type=\"file\">";
    page3Student += "                        <\/div>";
    page3Student += "                        <div class=\"file-path-wrapper\">";
    page3Student += "                            <input  class=\"file-path validate\" type=\"text\" placeholder=\"Подтв. документы\">";
    page3Student += "                        <\/div>";
    page3Student += "                    <\/div>";
    page3Student += "                <\/div>";
    page3Student += "                <div class=\"row\">";
    page3Student += "                    <div class=\"input-field col s12 \">";
    page3Student += "                        <input id=\"born_date\" placeholder=\"Дата рождения\" type=\"date\" class=\"datepicker\">";
    page3Student += "                    <\/div>";
    page3Student += "                <\/div>";
    page3Student += "";
    page3Student += "                <div class=\"row\">";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"password\" type=\"password\" class=\"validate\">";
    page3Student += "                        <label for=\"password\">Ваш пароль для входа<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                    <div class=\"input-field col s12 m6\">";
    page3Student += "                        <input id=\"password_2\" type=\"password\" class=\"validate\">";
    page3Student += "                        <label for=\"password_2\">Подтвердите пароль<\/label>";
    page3Student += "                    <\/div>";
    page3Student += "                <\/div>";
    page3Student += "                <a class=\"col s12 center center-align btn green\" href=\"javascript:void(0)\" onclick=\"regStudent();\">Зарегестрироваться<\/a>";
    page3Student += "            <\/div>";
    page3Student += "        <\/div>";



    page2 += "";
    page2 += "        <div class=\"row\">";
    page2 += "            <div class=\"col s12\" style=\"padding: 10px;\">";
    page2 += "                <a href=\"index.html\">";
    page2 += "                    <img src=\"favicon.ico\" style=\"height: 28px; vertical-align: middle; margin-top: -7px;\"><span class=\"grey-text text-darken-1 lighten\" style=\"font-size: 1.3rem; margin-left: 10px; font-weight: 700; font-family: Lato;\"><b class=\"orange-text\">Doct<\/b>rado<\/span>";
    page2 += "                <\/a>";
    page2 += "            <\/div>";
    page2 += "        <\/div>";
    page2 += "        <div class=\"row\">";
    page2 += "            <div class=\"col s12 edge\">";
    page2 += "                <h4 class=\"lato\">Выберите тип аккаунта<\/h4>";
    page2 += "            <\/div>";
    page2 += "        <\/div>";
    page2 += "        <div class=\"row\" style=\"max-width: 600px;\">";
    page2 += "            <div class=\"row col s12 edge\">";
    page2 += "";
    page2 += "";
    page2 += "                    <div class=\"col s12 m6\">";
    page2 += "                        <div class=\"card\">";
    page2 += "                            <div class=\"card-image center hoverable center-align hand_cursor\" href=\"javascript:void(0);\" onclick=\"selectTeacher();\">";
    page2 += "                                <img src=\"img\/img_avatar3.png\">";
    page2 += "                                <span class=\"card-title backgr-col\">Преподаватель<\/span>";
    page2 += "                            <\/div>";
    page2 += "";
    page2 += "                        <\/div>";
    page2 += "                    <\/div>";
    page2 += "                <div class=\"col s12 m6\">";
    page2 += "                    <div class=\"card center center-align hoverable hand_cursor\" href=\"javascript:void(0);\" onclick=\"selectStudent();\">";
    page2 += "                        <div class=\"card-image\">";
    page2 += "                            <img src=\"img\/student.png\">";
    page2 += "                            <span class=\"card-title backgr-col\">Студент<\/span>";
    page2 += "                        <\/div>";
    page2 += "";
    page2 += "                    <\/div>";
    page2 += "                <\/div>";
    page2 += "";
    page2 += "            <\/div>";
    page2 += "        <\/div>";
    page2 += "";



    page1 += "   <div class=\"row\">";
    page1 += "            <div class=\"col s12\" style=\"padding: 10px;\">";
    page1 += "                <a href=\"index.html\">";
    page1 += "                    <img src=\"favicon.ico\" style=\"height: 28px; vertical-align: middle; margin-top: -7px;\"><span class=\"grey-text text-darken-1 lighten\" style=\"font-size: 1.3rem; margin-left: 10px; font-weight: 700; font-family: Lato;\"><b class=\"orange-text\">Doct<\/b>rado<\/span>";
    page1 += "                <\/a>";
    page1 += "            <\/div>";
    page1 += "        <\/div>";
    page1 += "        <div class=\"row\">";
    page1 += "            <div class=\"col s12 edge\">";
    page1 += "                <h4 class=\"lato\">Создание аккаунта<\/h4>";
    page1 += "            <\/div>";
    page1 += "        <\/div>";
    page1 += "        <div class=\"row\" style=\"max-width: 600px;\">";
    page1 += "            <div class=\"row col s12 edge\">";
    page1 += "                <form class=\"col s12 \">";
    page1 += "                    <div class=\"row\">`";
    page1 += "                        <div class=\"input-field col s12\">";
    page1 += "                            <input id=\"username\" type=\"email\" class=\"validate\">";
    page1 += "                            <label id=\"username-l\" for=\"username\" data-error=\"неверный формат email\" data-success=\"\">Email<\/label>";
    page1 += "                        <\/div>";
    page1 += "                    <\/div>";
    page1 += "                    <div class=\"row\">";
    page1 += "                        <div class=\"input-field col s12\">";
    page1 += "                            <input id=\"telephone\"  pattern=\"0[0-9]{2,2}–[0-9]{3,3}–[0-9]{2,2}–[0-9]{2,2}\" type=\"tel\" class=\"validate\" placeholder=\"063-333-33-33\">";
    page1 += "                            <label id = \"telephone-l\" for=\"telephone\" data-error=\"неверный формат телефона\" data-success=\"\">Номер телефона<\/label>";
    page1 += "                        <\/div>";
    page1 += "                    <\/div>";
    page1 += "                <\/form>";
    page1 += "            <\/div>";
    page1 += "        <\/div>";
    page1 += "        <a href=\"javascript:void(0)\" class=\"btn col s12 center center-aling green\" onclick=\"traceParam();\">Далее<\/a>";




    preloader += "  <div class=\"col s12 m7 white\" style=\"min-height:100%; height: 100%;\">";
    preloader += "        <div class=\"row\">";
    preloader += "            <div class=\"col s12\" style=\"padding: 10px;\">";
    preloader += "                <a href=\"index.html\">";
    preloader += "                    <img src=\"favicon.ico\" style=\"height: 28px; vertical-align: middle; margin-top: -7px;\"><span class=\"grey-text text-darken-1 lighten\" style=\"font-size: 1.3rem; margin-left: 10px; font-weight: 700; font-family: Lato;\"><b class=\"orange-text\">Doct<\/b>rado<\/span>";
    preloader += "                <\/a>";
    preloader += "            <\/div>";
    preloader += "        <\/div>";
    preloader += "        <div class=\"row col s12\" style=\"max-width: 600px; min-height: 80%\">";
    preloader += "            <div class=\"preloader-wrapper center center-align big active\" style=\"margin-left: 60% ; margin-top: 50% \">";
    preloader += "                <div class=\"spinner-layer spinner-green-only\">";
    preloader += "                    <div class=\"circle-clipper left\">";
    preloader += "                        <div class=\"circle\"><\/div>";
    preloader += "                    <\/div><div class=\"gap-patch\">";
    preloader += "                    <div class=\"circle\"><\/div>";
    preloader += "                <\/div><div class=\"circle-clipper right\">";
    preloader += "                    <div class=\"circle\"><\/div>";
    preloader += "                <\/div>";
    preloader += "                <\/div>";
    preloader += "            <\/div>";
    preloader += "        <\/div>";


});


$('#username').on('input',function () {
    if($('#username').hasClass('invalid')&& $('#username-l').attr('data-error')=='email уже занят'){
        $('#username-l').removeAttr('data-error');
        document.getElementById('username-l').setAttribute('data-error',"неверный формат email");
        $('#username').removeClass('invalid')
    }
});


var prevLength = 0;
$('#telephone').on('input',function () {
    if($('#telephone').hasClass('invalid')&& $('#telephone-l').attr('data-error')=='номер телефона уже занят'){
        $('#telephone-l').removeAttr('data-error');
        document.getElementById('telephone-l').setAttribute('data-error',"неверный формат телефона");
        $('#telephone').removeClass('invalid')
    }

    var telep = $(this).val();
try{
    if(telep[telep.length-1].match(/^[–\+]?\d+/) === null&&telep[telep.length-1]!='–'||telep.length>13){
        var len = telep.length;
        len-=1;
        $(this).val(telep.substring(0,len));
        return;
    }



    switch ($(this).val().length){




        case 10:
        case 7:
        case 3:






            if(prevLength<$(this).val().length){
                var tVal = $(this).val();
                tVal += "–";
                $(this).val(tVal);
            }
            break;

    }



    prevLength = $(this).val().length;

}catch(e){

}

});



function regStudent() {


    if($('#edu_info_doc').prop('files')[0]==null){

        Materialize.toast('Добавьте подтверждающие документы', 3000, 'rounded red');
        return;
    }





    if($('#password').val()!=$('#password_2').val()){

        Materialize.toast('Пароли должны совпадать!', 3000, 'rounded red');
        return;
    }


    if($('#password').val() == ''||$('#password-2').val() == ''||$('#name').val() == ''||
        $('#surname').val() == ''||$('#first_name').val() == ''||$('#city').val() == ''||
        $('#edu_info').val() == ''||$('#born_date').val() == ''){

        Materialize.toast('Заполните все поля', 3000, 'rounded red');
        return;
    }




    //validate



    role = "s";

    var loadedDoc = false;
    var loadedAvatar = false;





    var tP = document.getElementById('password').value;
    password = tP.hashCode();
    name = $('#name').val();
    surname = $('#surname').val();
    firstname = $('#first_name').val();
    city = $('#city').val();
    eduInfo = $('#edu_info').val();
    eduInfoDoc = "";
    bornDate = $('#born_date').val();
    avatar = "";

    var doc = $('#edu_info_doc').prop('files')[0];
    //load Doc
    var docForm = new FormData();
    docForm.append('type', 'conf_doc');
    docForm.append('doc', doc);
    var form = new FormData();
    var image = $('#avatar-input').prop('files')[0];
    document.getElementById('login_div').innerHTML = preloader;
    window.scrollTo(0, 0);


    $.ajax({
        type: 'post',
        url: '/API/File',
        processData: false,
        contentType: false,
        cache: false,
        data: docForm,
        success: function (data) {
            loadedDoc = true;
            eduInfoDoc = data;

            if(loadedDoc&&loadedAvatar){

                endRegStudent();

            }


        }



    });



    if(image==null){
        avatar =  'img/default_avatar.jpg';
        loadedAvatar = true;

        if(loadedDoc&&loadedAvatar){

            endRegStudent();

        }
    }else{
        form.append('image', image);

        $.ajax({

            type: 'post',
            url: "/API/File",
            processData: false,
            contentType: false,
            cache: false,
            data: form,
            success: function (data) {
                loadedAvatar = true;

                avatar = data;


                if(loadedDoc&&loadedAvatar){

                    endRegStudent();

                }

            }




        });
    }




    //loadAvatar






}

function endRegStudent() {

    $.ajax({

        url: 'API/Reg',
        type: "POST",
        data:{
            "role":role,
            "email":email,
            "password":password,
            "avatar":avatar,
            "name":name,
            "surname":surname,
            "firstName":firstname,
            "city":city,
            "eduInfo":eduInfo,
            "eduInfoDoc":eduInfoDoc,
            "bornDate":bornDate,
            "telNumber":telephone
        },
        success:function (result) {

            if(result=="-1"){
                Materialize.toast('Имя пользователя занято, обновите страницу', 3000, 'rounded red');
                return;
            }

            if(result=="0"){

                Materialize.toast('Ошибка регистрации, попробуйте обновить страницу', 3000, 'rounded red');
                return;
            } // show modal "try again"

            if(result=="1"){
                Materialize.toast('Добро пожаловать', 3000, 'rounded green');
                //admin
            }
            if(result=="2"){
                Materialize.toast('Добро пожаловать', 3000, 'rounded green');
                //student
            }
            if(result=="3"){
                Materialize.toast('Добро пожаловать', 3000, 'rounded green');
                //teacher
            }




            window.location.href = "/personal_area.html"

        }
    });









}


function regTeacher() {




    if($('#edu_info_doc').prop('files')[0]==null){

        Materialize.toast('Добавьте подтверждающие документы', 3000, 'rounded red');
        return;
    }





    if($('#password').val()!=$('#password_2').val()){

        Materialize.toast('Пароли должны совпадать!', 3000, 'rounded red');
        return;
    }


    if($('#password').val() == ''||$('#password-2').val() == ''||$('#name').val() == ''||
        $('#surname').val() == ''||$('#first_name').val() == ''||$('#city').val() == ''||
        $('#edu_info').val() == ''||$('#born_date').val() == ''||$('#expirence').val() == ''){

        Materialize.toast('Заполните все поля', 3000, 'rounded red');
        return;
    }




    //validate



    role = "t";

    var loadedDoc = false;
    var loadedAvatar = false;





    var tP = document.getElementById('password').value;
    password = tP.hashCode();
    name = $('#name').val();
    surname = $('#surname').val();
    firstname = $('#first_name').val();
    city = $('#city').val();
    eduInfo = $('#edu_info').val();
    eduInfoDoc = "";
    bornDate = $('#born_date').val();
    avatar = "";
    expirence = $('#expirence').val();

    var doc = $('#edu_info_doc').prop('files')[0];
    //load Doc
    var docForm = new FormData();
    docForm.append('type', 'conf_doc');
    docForm.append('doc', doc);
    var form = new FormData();
    var image = $('#avatar-input').prop('files')[0];
    document.getElementById('login_div').innerHTML = preloader;
    window.scrollTo(0, 0);


    $.ajax({
        type: 'post',
        url: '/API/File',
        processData: false,
        contentType: false,
        cache: false,
        data: docForm,
        success: function (data) {
            loadedDoc = true;
            eduInfoDoc = data;

            if(loadedDoc&&loadedAvatar){

                endRegTeacher();

            }


        }



    });



    if(image==null){
        avatar =  'img/default_avatar.jpg';
        loadedAvatar = true;

        if(loadedDoc&&loadedAvatar){

            endRegTeacher();

        }
    }else{
        form.append('image', image);

        $.ajax({

            type: 'post',
            url: "/API/File",
            processData: false,
            contentType: false,
            cache: false,
            data: form,
            success: function (data) {
                loadedAvatar = true;

                avatar = data;


                if(loadedDoc&&loadedAvatar){

                    endRegTeacher();

                }

            }




        });
    }




    //loadAvatar





}

function  endRegTeacher() {

    $.ajax({

        url: 'API/Reg',
        type: "POST",
        data:{
            "role":role,
            "email":email,
            "password":password,
            "avatar":avatar,
            "name":name,
            "surname":surname,
            "firstName":firstname,
            "city":city,
            "eduInfo":eduInfo,
            "eduInfoDoc":eduInfoDoc,
            "bornDate":bornDate,
            "telNumber":telephone,
            "expirence":expirence
        },
        success:function (result) {

            if(result=="-1"){
                Materialize.toast('Имя пользователя занято, обновите страницу', 3000, 'rounded red');
                return;
            }

            if(result=="0"){

                Materialize.toast('Ошибка регистрации, попробуйте обновить страницу', 3000, 'rounded red');
                return;
            } // show modal "try again"

            if(result=="1"){
                Materialize.toast('Добро пожаловать', 3000, 'rounded green');
                //admin
            }
            if(result=="2"){
                Materialize.toast('Добро пожаловать', 3000, 'rounded green');
                //student
            }
            if(result=="3"){
                Materialize.toast('Добро пожаловать', 3000, 'rounded green');
                //teacher
            }




            window.location.href = "/personal_area.html";

        }
    });

    }








function traceParam() {
    console.log('startTracingParam');

    var loginField = document.getElementById('username');
    var telField = document.getElementById('telephone');
    if($('#username').hasClass('invalid')||$('#telephone').hasClass('invalid')||loginField.value==""||telField.value==""){
        Materialize.toast('не все поля заполнены правильно', 3000, 'rounded red');
        return;

    }




    document.getElementById('login_div').innerHTML = preloader;

    var lfV = loginField.value;
    var tfV = telField.value;

    $.ajax({

        url: "/API/Validator",
        type: "POST",
        data:{"email":loginField.value,
            "telNumber":telField.value},
        success:function (data) {
            console.log("responce data :" +data);
            if(data=='0'||data==0){


                telephone = tfV;
                email = lfV;

                console.log("data equeals 0");
                document.getElementById('login_div').innerHTML = page2;



                //do next;

            }else{

                document.getElementById('login_div').innerHTML = page1;

                document.getElementById('username').value = lfV;
                document.getElementById('telephone').value = tfV;


                $('#username-l').addClass('active');
                $('#telephone-l').addClass('active');
                Materialize.updateTextFields();


                switch (data){
                    case '1':
                        $('#username-l').removeAttr('data-error');
                        document.getElementById('username-l').setAttribute('data-error',"email уже занят");
                        Materialize.toast('email уже занят', 3000, 'rounded red');
                        $('#username').addClass('invalid');
                        break;
                    case '2':
                        $('#telephone-l').removeAttr('data-error');
                        document.getElementById('telephone-l').setAttribute('data-error',"номер телефона уже занят");
                        Materialize.toast('номер телефона уже занят', 3000, 'rounded red');
                        $("#telephone").addClass('invalid');
                        break;
                    case '3':
                        $('#telephone-l').removeAttr('data-error');
                        document.getElementById('telephone-l').setAttribute('data-error',"номер телефона уже занят");
                        Materialize.toast('номер телефона уже занят', 3000, 'rounded red');
                        $("#telephone").addClass('invalid');

                        $('#username-l').removeAttr('data-error');
                        Materialize.toast('email уже занят', 3000, 'rounded red');
                        document.getElementById('username-l').setAttribute('data-error',"email уже занят");
                        $('#username').addClass('invalid');
                        break;
                }




            }




        }


    });





};



function uploadPhoto() {
    $("#avatar-input").click();
}
function upload() {
    var UUIDImg = getUUID();
    var form = new FormData();
    var image = $('#avatar-input').prop('files')[0];
    form.append('key',UUIDImg);
    form.append('image', image);
    $.ajax({
        type: 'post',
        url: "/API/File",
        processData: false,
        contentType: false,
        cache: false,
        data: form,
        success: function (data) {

            //todo doto))

        }
    });
}


