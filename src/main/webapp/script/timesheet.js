//-------------------------------------- READY --------------------------------------
$(document).ready(function () {
    $("#data-start").val(getCurrentDay());
    $("#data-end").val(getCurrentDay());
    $("#add-form-date").val(getCurrentDay());


    $("#show-form").submit(function (event) {
        event.preventDefault();
        $("#entriesTable > tr").remove();
        showAjax();
    });

    $("#add-form").submit(function (event) {
        event.preventDefault();
        addAjax();
    });

    $("#edit-form").submit(function (event) {
        event.preventDefault();
        $("#edit-form").hide();
        editAjax();
    });
});

function onClickButton(objButton) {
    if(objButton.className == 'edit') {
        var c = document.getElementById("entry" + objButton.value).childNodes;
        var p = $("#entry" + objButton.value + " td:last-child").position();
        $('#edit-form').css({position: 'absolute', top: p.top + 15, left: p.left - 50}).show();
        $("#edit-form-date").val(c[0].innerHTML).prop('disabled', true);
        $("#edit-form-time-start").val(c[1].innerHTML);
        $("#edit-form-time-end").val(c[2].innerHTML);
    }
    if(objButton.className == 'delete') {
        deleteEntryAjax(objButton.value);
    }
}

//-------------------------------------- AJAX --------------------------------------
function showAjax() {
    var data = {};
    data["dataStart"] = $("#data-start").val();
    data["dataEnd"] = $("#data-end").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/query/show",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            drawTable(d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });

    function drawTable(d) {
        d.sort(function(a, b) {
            a = new Date(a.date);
            b = new Date(b.date);
            return a>b ? -1 : a<b ? 1 : 0;
        });

        for(var i = 0; i < d.length; i++) {
            drawRow(d[i]);
        }
        checkApprove();
    }

    function drawRow(rowData) {
        var row = $("<tr />").attr("id", "entry" + rowData.index);
        $("#entriesTable").append(row);
        row.append($("<td class='date'>" + rowData.date + "</td>"));
        row.append($("<td class='startTime'>" + rowData.startTime + "</td>"));
        row.append($("<td class='endTime'>" + rowData.endTime + "</td>"));
        row.append($("<td class='time'>" + rowData.time + "</td>"));
        row.append($("<td class='approve'>" + rowData.approve + "</td>"));
        row.append($("<td class='username'>" + rowData.username + "</td>"));
        row.append($("<td>" + "<button class='edit' onclick='onClickButton(this)' value=" +
            rowData.index + ">" + "<i class='fa fa-pencil' aria-hidden='true'></i> " + "</button>" +
            "<button class='delete' onclick='onClickButton(this)' value=" + rowData.index + ">" +
            "<i class='fa fa-trash-o' aria-hidden='true'></i> " + "</button> </td>"));
    }
}

function addAjax() {
    var add = {};
    add["date"] = $("#add-form-date").val();
    add["timeStart"] = $("#add-form-time-start").val();
    add["timeEnd"] = $("#add-form-time-end").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/add",
        data: JSON.stringify(add),
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById("btn-search").click();
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

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/edit",
        data: JSON.stringify(edit),
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById("btn-search").click();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function deleteEntryAjax(index) {
    var del = {};
    del["index"] = index;
    del["date"] = document.getElementById("entry" + index).childNodes[0].innerHTML;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/del",
        data: JSON.stringify(del),
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById("btn-search").click();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

//-------------------------------------- FUNC --------------------------------------
function getCurrentDay() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
    return output;
}

function hideEditForm() {
    $("#edit-form").hide();
}

function checkApprove() {
    var elem = document.getElementsByClassName('approve');
    for(var i = 0; i < elem.length; i++) {
        if(elem[i].innerHTML == 'false') {
            elem[i].classList.add('error');
        }
    }
}