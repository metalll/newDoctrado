/**
 * Created by NSD on 12.06.17.
 */

var currCourseTestID ;


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

       // //  console.log(Answers);

        Question.answers = myTableArray;
        retVal.push(Question);


    });


    var JSONSend = JSON.stringify(retVal);

    var endcodedJSON = Base64.encode(JSONSend);

    $.ajax({

        type:"post",
        url:"/API/Test",
        data:{
            test:endcodedJSON,
            id:currCourseTestID,
        },
        success:function (data) {
            Materialize.toast('Изменения успешно сохранены', 3000, 'rounded green');

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


function initTest(id) {
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    // $('.modal').modal();

    currCourseTestID = id;

    $.ajax({
        url: '/API/Test',
        data: {
            id: id
        },
        type: 'get',

        success: function (data) {
            var decodedData = Base64.decode(data)

            loadTest(decodedData);
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