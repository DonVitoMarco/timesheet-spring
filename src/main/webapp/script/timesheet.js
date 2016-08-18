$(document).ready(function () {

    $("#data-start").val(getCurrentDay());
    $("#data-end").val(getCurrentDay());

    $("#show-form").submit(function (event) {
        console.log("SHOW FORM");
        event.preventDefault();
        $("#entriesTable > tr").remove();
        showAjax();
    });

    $("#add-form-button").click(function () {
        console.log("ADD FORM BUTTON");
        $("#add-form").show();
        $("#add-form-date").val(getCurrentDay());
    });

    $("#add-form").submit(function (event) {
        console.log("ADD FORM");
        event.preventDefault();
        $("#add-form").hide();
        addAjax();
    });

    $("#edit-form").submit(function (event) {
        console.log("EDIT FORM");
        event.preventDefault();
        $("#edit-form").hide();
        editAjax();
    });

});

function getCurrentDay() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
    return output;
}


function onClickButton(objButton) {
    //console.log("ON CLICK BUTTON FUNC");

    if(objButton.className == 'edit') {
        var c = document.getElementById("entry" + objButton.value).childNodes;
        $("#edit-form").show();
        $("#edit-form-date").val(c[0].innerHTML).prop('disabled', true);
        $("#edit-form-time-start").val(c[1].innerHTML);
        $("#edit-form-time-end").val(c[2].innerHTML);
    }

    if(objButton.className == 'delete') {
        console.log("DELETE: " + objButton.value);
        deleteEntry(objButton.value);
    }

}


function showAjax() {

    var data = {};
    data["dataStart"] = $("#data-start").val();
    data["dataEnd"] = $("#data-end").val();
    //console.log("DATA: ", data);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/query/show",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            //console.log("SUCCESS: ", d);
            drawTable(d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });

    function drawTable(d) {
        for(var i = 0; i < d.length; i++) {
            drawRow(d[i]);
        }
    }

    function drawRow(rowData) {
        //console.log("ROW: ", rowData);
        var row = $("<tr />").attr("id", "entry" + rowData.index);
        $("#entriesTable").append(row);
        row.append($("<td class='date'>" + rowData.date + "</td>"));
        row.append($("<td class='startTime'>" + rowData.startTime + "</td>"));
        row.append($("<td class='endTime'>" + rowData.endTime + "</td>"));
        row.append($("<td class='time'>" + rowData.time + "</td>"));
        row.append($("<td class='approve'>" + rowData.approve + "</td>"));
        row.append($("<td class='username'>" + rowData.username + "</td>"));
        row.append($("<td>" + "<button class='edit' onclick='onClickButton(this)' value=" + rowData.index + ">" + "EDIT" + "</button>" + "</td>"));
        row.append($("<td> <button class='delete' onclick='onClickButton(this)' value=" + rowData.index + ">" + "DELETE" + "</button> </td>"));
    }

}

function addAjax() {

    var add = {};
    add["date"] = $("#add-form-date").val();
    add["timeStart"] = $("#add-form-time-start").val();
    add["timeEnd"] = $("#add-form-time-end").val();
    console.log("SEND", add);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/add",
        data: JSON.stringify(add),
        dataType: 'json',
        timeout: 100000,

        beforeSend: function () {
            console.log("SEND ADD");
        },
        success: function (d) {
            console.log("SUCCESS: ", d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}


function editAjax() {

    var edit = {};
    edit["date"] = $("#edit-form-date").val();
    edit["timeStart"] = $("#edit-form-time-start").val();
    edit["timeEnd"] = $("#edit-form-time-end").val();
    console.log("SEND", edit);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/edit",
        data: JSON.stringify(edit),
        dataType: 'json',
        timeout: 100000,

        beforeSend: function () {
            console.log("SEND EDIT");
        },
        success: function (d) {
            console.log("SUCCESS: ", d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function deleteEntry(index) {

    var c = document.getElementById("entry" + index).childNodes;
    var del = {};
    del["index"] = index;
    del["date"] = c[0].innerHTML;
    console.log(del);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/del",
        data: JSON.stringify(del),
        dataType: 'json',
        timeout: 100000,

        beforeSend: function () {
            console.log("SEND DEL");
        },
        success: function (d) {
            console.log("SUCCESS: ", d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });


}