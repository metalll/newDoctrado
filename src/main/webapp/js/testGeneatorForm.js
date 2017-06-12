/**
 * Created by NSD on 12.06.17.
 */
function deleteQuestion(id) {
    console.log('del: ' + id);

    var elem = document.getElementById(id);

    var operandChild = elem.parentNode.parentNode.parentNode;
    var operandParent = operandChild.parentNode;

    console.log(operandChild);
    console.log(operandParent);


    operandParent.removeChild(operandChild);


}

function showTestDialog() {
    $('#test-dialog').modal('open');
}

function loadingTest(ltest) {


    for (var i = 0; i < ltest.questions.length; i++) {
        //https://doctrado-sviasy.rhcloud.com/test?course=bd325b1e-5541-4906-bbaa-fe3e3275fb81

        addTest(ltest.questions[i].question, ltest.questions[i].numeric, ltest.questions[i].answers);


    }
}

function loadTest(json) {
    var object = JSON.parse(json);
    var toselect = [];

    for (var i = 0; i < object.length; i++) {


        var inputView = "";
        inputView += "<li id = \"question-list-" + i + "\">";
        inputView += "                <div class=\"collapsible-header center center-align\">Вопрос<\/div>";
        inputView += "                <div class=\"collapsible-body\">";
        inputView += "   <div class=\"row col s12 center center-align\">";
        inputView += "<a href=\"javascript:void(0);\" id = \"del-" + i + "\" onclick=\"deleteQuestion(this.id);\" class=\"btn col s12 center center-align red darken-3\">Удалить вопрос<\/a>";
        inputView += "                        <\/div>";
        inputView += "                    <div class=\"row\">";
        inputView += "                        <div class=\"input-field col s12\">";
        inputView += "                            <input id=\"question-name-" + i + "\" type=\"text\" class=\"validate\" value=\"" + object[i].name + "\">";
        inputView += "                            <label for=\"question-name-" + i + "\">Вопрос<\/label>";
        inputView += "                        <\/div>";
        inputView += "                    <\/div>";
        inputView += "";
        inputView += "                    <table class=\"striped responsive-table centered\" id=\"answer-table-" + i + "\">";
        inputView += "                        <thead>";
        inputView += "                        <tr>";
        inputView += "                            <th data-field=\"answer-name\">Ответ<\/th>";
        inputView += "                            <th data-field=\"answer-is-true\">Правильный ответ<\/th> <th data-field=\"del\">&nbsp;<\/th>";
        inputView += "";
        inputView += "                        <\/tr>";

        for (var j = 0; j < object[i].answers.length; j++) {

            inputView += '<tr><td><div class="left-align  input-field col s12"><input id="answer-name-' + j + '" type="text" value="' + object[i].answers[j].name + '" class="validate"><label for="answer-name-' + j + '">Ответ</label></div></td><td><div class=" switch center center-align"><label>Нет<input';
            inputView += ' id = "isTrue-' + j + '-' + i + '" type="checkbox"><span class="lever"></span>Да</label></div></td><td><a href=\"javascript:void(0);\" id = \"del-a-' + i + j + '\" onclick=\"deleteAnswer(this.id);\" class=\"btn col s12 center center-align red darken-3\"><i class=\"material-icons\">delete</i></a><\/a></td></tr>';
            if (object[i].answers[j].isTrue) {
                toselect.push('isTrue-' + j + '-' + i + '');
            }
        }

        inputView += "                        <\/thead>";
        inputView += "                     ";
        inputView += "               ";
        inputView += "                    <\/table>";
        inputView += "                    <div class=\"col s12 row\" id=\"" + i + "\" href = \"javascript:void(0);\" onclick = \"addAnswer(this.id);\"> <a class=\"btn col s12 center center-align\">+<\/a><\/div>";
        inputView += "                <\/div>";
        inputView += "            <\/li>";


        $("#test-container ul").append(inputView);

        for (var z = 0; z < toselect.length; z++) {
            var input = document.getElementById(toselect[z]);
            input.checked = true;
        }

        Materialize.updateTextFields();


    }

}


function addTest() {


    var test_container = document.getElementById('test-ul');
    var counter = test_container.childElementCount + 1;

    var inputView = "";
    inputView += "<li id = \"question-list-" + counter + "\">";
    inputView += "                <div class=\"collapsible-header center center-align\">Вопрос<\/div>";
    inputView += "                <div class=\"collapsible-body\">";
    inputView += "   <div class=\"row col s12 center center-align\">";
    inputView += "<a href=\"javascript:void(0);\" id = \"del-" + counter + "\" onclick=\"deleteQuestion(this.id);\" class=\"btn col s12 center center-align red darken-3\">Удалить вопрос<\/a>";
    inputView += "                    <\/div>";
    inputView += "                    <div class=\"row\">";
    inputView += "                        <div class=\"input-field col s12\">";
    inputView += "                            <input id=\"question-name-" + counter + "\" type=\"text\" class=\"validate\" >";
    inputView += "                            <label for=\"question-name-" + counter + "\">Вопрос<\/label>";
    inputView += "                        <\/div>";
    inputView += "                    <\/div>";
    inputView += "";
    inputView += "                    <table class=\"striped responsive-table centered\" id=\"answer-table-" + counter + "\">";
    inputView += "                        <thead>";
    inputView += "                        <tr>";
    inputView += "                            <th data-field=\"answer-name\">Ответ<\/th>";
    inputView += "                            <th data-field=\"answer-is-true\">Правильный ответ<\/th> <th data-field=\"del\">&nbsp;<\/th>";
    inputView += "";
    inputView += "                        <\/tr>";
    inputView += "                        <\/thead>";
    inputView += "                     ";
    inputView += "               ";
    inputView += "                    <\/table>";
    inputView += "                    <div class=\"col s12 row\" id=\"" + counter + "\" href = \"javascript:void(0);\" onclick = \"addAnswer(this.id);\"> <a class=\"btn col s12 center center-align\">+<\/a><\/div>";
    inputView += "                <\/div>";
    inputView += "            <\/li>";

    $("#test-container ul").append(inputView);


    // var ul = document.getElementById('test-ul');


    //console.log( ul.innerHTML);
    //toJSON();

}

function toJSON() {

    //   var ul = document.getElementById('test-ul');


    var retVal = [];


    $("#test-ul li").each(function (i) {
        var Question = {};


        var questionID = $(this).attr("id");
        var questionName = "";


        console.log(questionID);

        var input = $('#' + questionID + " div:last-child div:first-child ~ div div:last-child input:first-child");
        //  var inputA = $('#'+questionID+ " div:last-child div:first-child").next().lastChild.find("input");
        Question.name = input.val();
        console.log(input.val());
        //    console.log(inputA.a)
        var myTableArray = [];

        $('#' + questionID + " div:last-child table tr").each(function () {

            var arrayOfThisRow = {};
            var tableData = $(this).find('td');
            var counter = 1;
            tableData.each(function (each) {


                if (each == 0) {
                    arrayOfThisRow.name = $(this).find('input').val();
                }

                if (each == 1) {
                    arrayOfThisRow.isTrue = $(this).find('input').is(":checked");
                }


//                    if (counter == 1) {
//                        counter += 1;
//
//
//
//
//                    } else {
//                        if(counter==2) {
//                            counter++;
//
//                        }else{   var elem = $(this.childNodes[0].childNodes[0]);
//                            console.log(elem);
//
//                    }
//
//                });
            });
            if (!isEmpty(arrayOfThisRow)) {

                myTableArray.push(arrayOfThisRow);
            }


            // console.log(myTableArray);


        });

        //  console.log(Answers);

        Question.answers = myTableArray;
        retVal.push(Question);


    });


    var JSONSend = JSON.stringify(retVal);

    $.ajax({

        type:"post",
        url:"https://doctrado-sviasy.rhcloud.com/test",
        data:{
            JSON:JSONSend,
            parent:'cd056f82-4a1f-459d-b5fe-109eae8c0d3b'
        },
        success:function (data) {
            alert('eeee');
        }


    });




}

var hasOwnProperty = Object.prototype.hasOwnProperty;

function isEmpty(obj) {

    // null and undefined are "empty"
    if (obj == null) return true;

    // Assume if it has a length property with a non-zero value
    // that that property is correct.
    if (obj.length > 0)    return false;
    if (obj.length === 0)  return true;

    // If it isn't an object at this point
    // it is empty, but it can't be anything *but* empty
    // Is it empty?  Depends on your application.
    if (typeof obj !== "object") return true;

    // Otherwise, does it have any properties of its own?
    // Note that this doesn't handle
    // toString and valueOf enumeration bugs in IE < 9
    for (var key in obj) {
        if (hasOwnProperty.call(obj, key)) return false;
    }

    return true;
}

function addAnswer(sender_id) {
    //  alert(sender_id);

    var table = document.getElementById('answer-table-' + sender_id);
    var counter = table.rows.length;
    $('#' + table.id + ' tr:last').after('<tr><td><div class="left-align  input-field col s12"><input id="answer-name-' + counter + '" type="text" class="validate"><label for="answer-name-' + counter + '">Ответ</label></div></td><td><div class=" switch center center-align"><label>Нет<input type="checkbox"><span class="lever"></span>Да</label></div></td><td><a href=\"javascript:void(0);\" id = \"del-a-' + counter + '\" onclick=\"deleteAnswer(this.id);\" class=\"btn col s12 center center-align red darken-3\"><i class=\"material-icons\">delete</i></a><\/a></td></tr>');


}


$(document).ready(function () {
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();

    $.ajax({
        url: 'https://doctrado-sviasy.rhcloud.com/test?parent=cd056f82-4a1f-459d-b5fe-109eae8c0d3b',
        type:'get',
        success:function (data) {
            loadTest(data);
        }
    });



});

function addTheme() {

    $.ajax({
        type: 'post',
        url: 'https://doctrado-sviasy.rhcloud.com/addCourse',
        data: {
            parent: 'cd056f82-4a1f-459d-b5fe-109eae8c0d3b',
            number: '1'
        },
        success: function (data) {
            window.location.reload();
        }
    });


}




function call() {

    //$('#progress_bar').modal('open');


    //  bar.animate(1.0);




    var email = document.getElementById('email1').value;
    var password = document.getElementById('password1').value;
    var errors = false;
    var errEmail = false;
    var errPass = false;
    if (email == "") {
        //Materialize.toast('Введите ваш email',4000,'rounded');
        errEmail = true;
        errors = true;

    }

    if (password == "") {
        //Materialize.toast('Введите ваш пароль',4000,'rounded');
        errPass = true;
        errors = true;
    }
    if (errEmail && errPass) {
        Materialize.toast('<div class="red-text text-darken-3"><b> Введите ваш email <br> и пароль </b></div>', 4000, 'rounded');
        //       bar.hide();
        return;
    }
    if (errEmail) {
        Materialize.toast('<div class="red-text text-darken-3"><b> Введите ваш email </b></div>', 4000, 'rounded');
    }
    if (errPass) {
        Materialize.toast('<div class="red-text text-darken-3"><b>Введите ваш пароль</b></div>', 4000, 'rounded');
    }

    if (errors) {
        //          bar.hide();
        return;
    }
    $.ajax({
        type: 'post',
        url: 'https://doctrado-sviasy.rhcloud.com/login',
        data: {
            email: email,
            password: password
        },
        success: function (data) {
            if (data == -1) {
                Materialize.toast('<div class="red-text text-darken-3"><b>Неверный логин <br> или пароль</b></div>', 4000, 'rounded');
                bar.hide();
            }
            else window.location.href = "https://doctrado-sviasy.rhcloud.com/profile";
        }
    });
}
function call1() {
    //        $('#progress_bar').modal('open');
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var errors = false;
    var errEmail = false;
    var errPass = false;
    if (email == "") {
        //Materialize.toast('Введите ваш email',4000,'rounded');
        errEmail = true;
        errors = true;

    }

    if (password == "") {
        //Materialize.toast('Введите ваш пароль',4000,'rounded');
        errPass = true;
        errors = true;
    }
    if (errEmail && errPass) {
        Materialize.toast('<div class="red-text text-darken-3"><b> Введите ваш email <br> и пароль </b></div>', 4000, 'rounded');
        //          $('#progress_bar').modal('close');
        return;
    }
    if (errEmail) {
        Materialize.toast('<div class="red-text text-darken-3"><b> Введите ваш email </b></div>', 4000, 'rounded');
    }
    if (errPass) {
        Materialize.toast('<div class="red-text text-darken-3"><b>Введите ваш пароль</b></div>', 4000, 'rounded');
    }

    if (errors) {
        //            $('#progress_bar').modal('close');
        return;
    }
    $.ajax({
        type: 'post',
        url: 'https://doctrado-sviasy.rhcloud.com/login',
        data: {
            email: email,
            password: password
        },
        success: function (data) {
            if (data == -1) {
                Materialize.toast('<div class="red-text text-darken-3"><b>Неверный логин <br> или пароль</b></div>', 4000, 'rounded');
                //  $('#progress_bar').modal('close');
            }
            else window.location.href = "https://doctrado-sviasy.rhcloud.com/profile";
        }
    });
}



function callReg1(accept) {
    var nameR = document.getElementById('nameR1').value;
    var last_nameR = document.getElementById('last_nameR1').value;
    var surnameR = document.getElementById('surnameR1').value;
    var emailR = document.getElementById('emailR1').value;
    var passwordR = document.getElementById('passwordR1').value;
    var telephone = document.getElementById('telephone1').value;
    var born = document.getElementById('born1').value;
    var test5 = document.getElementById('test51').value;

    var error = false;

    var nameREr = false;
    var last_nameREr = false;
    var surnameREr = false;
    var emailREr = false;
    var passwordREr = false;
    var telephoneEr = false;
    var bornEr = false;
    var testEr = false;

    if (nameR == "") {
        error = true;
        nameREr = true;
    }
    if (last_nameR == "") {
        error = true;
        last_nameREr = true;
    }
    if (surnameR == "") {
        error = true;
        surnameREr = true;
    }
    if (emailR == "") {
        error = true;
        emailREr = true;
    }
    if (passwordR == "") {
        error = true;
        passwordREr = true;
    }
    if (telephone == "") {
        error = true;
        telephoneEr = true;
    }
    if (born == "") {
        error = true;
        bornEr = true;
    }
    if (test5 == "") {
        error = true;
        testEr = true;
    }

    if (error) {
        if (nameREr || last_nameREr || surnameREr || emailREr || passwordREr || telephoneEr || bornEr || testEr) {
            Materialize.toast('<div class="red-text text-darken-3"><b>Заполните все поля</b></div>', 4000, 'rounded');

            return;
        }
    }
    $.ajax({
        type: 'post',
        url: 'https://doctrado-sviasy.rhcloud.com/validate_user',
        data: {
            email: emailR,
            password: passwordR,
            name: nameR,
            last_name: last_nameR,
            surname: surnameR,
            telephone: telephone,
            born: born,
            type: accept
        },
        success: function (data) {
            if (data == 0) {
                $('#modal3').modal('close');
                $('#modal1').modal('close');
                $('#modal2').modal('close');


                if (accept == 's') $('#modal4').modal('open');

                if (accept == 't') $('#modal5').modal('open');
            }

        }
    });


}




$( window ).load(function(){


});


function callReg(accept) {
    var nameR = document.getElementById('nameR').value;
    var last_nameR = document.getElementById('last_nameR').value;
    var surnameR = document.getElementById('surnameR').value;
    var emailR = document.getElementById('emailR').value;
    var passwordR = document.getElementById('passwordR').value;
    var telephone = document.getElementById('telephone').value;
    var born = document.getElementById('born').value;
    var test5 = document.getElementById('test5').value;


    var error = false;

    var nameREr = false;
    var last_nameREr = false;
    var surnameREr = false;
    var emailREr = false;
    var passwordREr = false;
    var telephoneEr = false;
    var bornEr = false;
    var testEr = false;

    if (nameR == "") {
        error = true;
        nameREr = true;
    }
    if (last_nameR == "") {
        error = true;
        last_nameREr = true;
    }
    if (surnameR == "") {
        error = true;
        surnameREr = true;
    }
    if (emailR == "") {
        error = true;
        emailREr = true;
    }
    if (passwordR == "") {
        error = true;
        passwordREr = true;
    }
    if (telephone == "") {
        error = true;
        telephoneEr = true;
    }
    if (born == "") {
        error = true;
        bornEr = true;
    }
    if (test5 == "") {
        error = true;
        testEr = true;
    }

    if (error) {
        if (nameREr || last_nameREr || surnameREr || emailREr || passwordREr || telephoneEr || bornEr || testEr) {
            Materialize.toast('<div class="red-text text-darken-3"><b>Заполните все поля</b></div>', 4000, 'rounded');
            return;
        }
    }
    $.ajax({
        type: 'post',
        url: 'https://doctrado-sviasy.rhcloud.com/validate_user',
        data: {
            email: emailR,
            password: passwordR,
            name: nameR,
            last_name: last_nameR,
            surname: surnameR,
            telephone: telephone,
            born: born,
            type: accept
        },
        success: function (data) {
            if (data == 0) {
                $('#modal3').modal('close');
                $('#modal1').modal('close');
                $('#modal2').modal('close');

                if (accept == 's') $('#modal4').modal('open');

                if (accept == 't') $('#modal5').modal('open');


                //  $('#progress_bar').modal('close');
            }

        }
    });
}





function closeTest() {

    $('#test-dialog').modal('close');
    Materialize.toast("Изменения успешно сохранены")

}


function wait(ms) {
    var start = new Date().getTime();
    var end = start;
    while (end < start + ms) {
        end = new Date().getTime();
    }
}