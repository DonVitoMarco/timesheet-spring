$(document).ready(function () {

    $("#entries-form").submit(function (event) {
        console.log("ENTRIES FORM");
        event.preventDefault();
        $("#entriesTable > tr").remove();
        showEntriesAjax();
    });

});

function users() {
    console.log("USERS");
    $("#usersTable > tr").remove();
    $("#usersTable").show();
    $("#entriesTable").hide();
    $("#departmentsList").hide();
    hideEntriesForm();
    showUsersAjax();
}

function entries() {
    console.log("ENTRIES");
    $("#entriesTable > tr").remove();
    $("#usersTable").hide();
    $("#entriesTable").show();
    $("#departmentsList").hide();

    var p = $("#entries-button").position();
    $('#entries-form').css({position: 'absolute', top: p.top + 50, left: p.left + 20}).show();
}

function departments() {
    console.log("DEPARTMENTS");
    $("#usersTable").hide();
    $("#entriesTable").hide();
    $("#departmentsList").show();

}

function hideEntriesForm() {
    console.log("HIDE");
    $('#entries-form').hide();
}

function showUsersAjax() {
    var data = {};
    data["data"] = "users";

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/query/users",
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
            a = a.username;
            b = b.username;
            return a<b ? -1 : a>b ? 1 : 0;
        });

        for(var i = 0; i < d.length; i++) {
            drawRow(d[i]);
        }
        checkEnable();
    }

    function drawRow(rowData) {
        var row = $("<tr />").attr("id", "user" + rowData.index);
        $("#usersTable").append(row);
        row.append($("<td>" + rowData.username + "</td>"));
        row.append($("<td class='enable'>" + rowData.enable + "</td>"));
        row.append($("<td>" + rowData.roles + " <i style='cursor: pointer;' onclick='changeRole()' class='fa fa-refresh' aria-hidden='true'></i>" + "</td>"));
        row.append($("<td>" + rowData.department + "</td>"));
    }
}

function showEntriesAjax() {

    var data = {};
    data["dataStart"] = $("#entries-form-date-start").val();
    data["dataEnd"] = $("#entries-form-date-end").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/query/entries",
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
        //checkEnable();
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
        row.append($("<td>" + "<button class='delete' onclick='onClickButton(this)' value=" + rowData.index + ">" +
            "<i class='fa fa-trash-o' aria-hidden='true'></i> " + "</button> </td>"));
    }
}

function checkEnable() {
    var elem = document.getElementsByClassName('enable');
    for(var i = 0; i < elem.length; i++) {
        if(elem[i].innerHTML == 'false') {
            elem[i].innerHTML = "no " + "<i style='cursor: pointer;' onclick='changeEnable()' class='fa fa-check check' aria-hidden='true'></i>";
        } else {
            elem[i].innerHTML = "yes " + "<i style='cursor: pointer;' onclick='changeEnable()' class='fa fa-times error' aria-hidden='true'></i></button>";
        }
    }
}

function changeEnable() {
    console.log("CHANGE ENABLE");
}

function changeRole() {
    console.log("CHANGE ROLE");
}