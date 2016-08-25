$(document).ready(function () {

    $("#entries-form").submit(function (event) {
        console.log("ENTRIES FORM");
        event.preventDefault();
        $("#entriesTable > tr").remove();

        hideEntriesForm();
        showEntriesAjax();
    });

});


//----------------------- CLICK BUTTONS -----------------------
function users() {
    console.log("CLICK USERS");
    hideManageTable();
    $("#usersTable > tr").remove();
    $("#usersTable").show();
    $("#search-box").show();
    hideEntriesForm();
    showUsersAjax();
}

function entries() {
    hideManageTable();
    $("#entriesTable > tr").remove();
    $("#search-box").show();
    $("#entriesTable").show();
    $("#entries-form-date-start").val(getCurrentDay());
    $("#entries-form-date-end").val(getCurrentDay());

    var p = $("#entries-button").position();
    $('#entries-form').css({position: 'absolute', top: p.top + 50, left: p.left + 20}).show();
}

function departments() {
    console.log("DEPARTMENTS");
    hideManageTable();
    $("#departmentsList > li").remove();
    $("#departmentsTable").show();
    showDepartmentsAjax();
}

function changeRole(i) {
    console.log("CLICK CHANGE ROLE: ", i);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/changeRole",
        data: {'index': i},
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById('users-button').click();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function hideEntriesForm() {
    console.log("HIDE ENTRIES FORM");
    $('#entries-form').hide();
}

//----------------------- AJAX -----------------------
function showUsersAjax() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/query/users",
        data: {"data":"users"},
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS SHOW USERS AJAX: ", d);
            drawTable(d);
        },
        error: function (e) {
            console.log("ERROR SHOW USERS AJAX: ", e);
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
        var row = $("<tr />").attr("id", "user" + rowData.index).addClass("filter");
        $("#usersTable").append(row);
        row.append($("<td class='username'>" + rowData.username + "</td>"));
        row.append($("<td class='enable'>" + rowData.enable + "</td>"));
        row.append($("<td class='roles'>" + rowData.roles + " <i style='cursor: pointer;' " +
            "onclick='changeRole(" + rowData.index + ")' class='fa fa-refresh' aria-hidden='true'></i>" + "</td>"));
        row.append($("<td class='department'>" + rowData.department + "</td>"));
    }
}

function showDepartmentsAjax() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/query/departments",
        data: {'data': 'departments'},
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            drawList(d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });

    function drawList(d) {
        d.sort();
        for(var i = 0; i < d.length; i++) {
            $("#departmentsList").append("<li>" + d[i] + " " +
                "<button type='button' style='margin-top: 0; margin-left: 10px;' onclick='changeDepartment(this)' value='delete' id='" + d[i] + "-" + i + "'>" +
                "<i class='fa fa-trash-o' aria-hidden='true'></i></button>" + "</li>");
        }
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
            $("#entriesTable > tr").remove();
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
    }

    function drawRow(rowData) {
        var row = $("<tr />").attr("id", "entry" + rowData.index).addClass("filter");
        $("#entriesTable").append(row);
        row.append($("<td class='date'>" + rowData.date + "</td>"));
        row.append($("<td class='startTime'>" + rowData.startTime + "</td>"));
        row.append($("<td class='endTime'>" + rowData.endTime + "</td>"));
        row.append($("<td class='time'>" + rowData.time + "</td>"));
        row.append($("<td class='approve'>" + rowData.approve + "</td>"));
        row.append($("<td class='username'>" + rowData.username + "</td>"));
        row.append($("<td>" + "<button onclick=notapprove(" + rowData.index + ") value=" + rowData.index + ">" + "<i class='fa fa-times' aria-hidden='true'></i> " + "</button> " +
            "<button onclick=approve(" + rowData.index + ") value=" + rowData.index + ">" + "<i class='fa fa-check' aria-hidden='true'></i>" + "</button>" + "</td>"));
    }
}

function changeEnable(i) {
    console.log("CHANGE ENABLE:", i);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/changeEnable",
        data: {'index': i},
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById('users-button').click();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

//----------------------- FUNC -----------------------
function hideManageTable() {
    $("#search-box").hide();
    $("#usersTable").hide();
    $("#entriesTable").hide();
    $("#departmentsTable").hide();
}

function checkEnable() {
    var elem = document.getElementsByClassName('enable');
    for(var i = 0; i < elem.length; i++) {
        var index = elem[i].closest("tr").getAttribute('id').replace('user','');
        if(elem[i].innerHTML == 'false') {
            elem[i].innerHTML = "no " + "<i style='cursor: pointer;' onclick='changeEnable(" + index + ")' class='fa fa-check check' aria-hidden='true'></i>";
        } else {
            elem[i].innerHTML = "yes " + "<i style='cursor: pointer;' onclick='changeEnable(" + index + ")' class='fa fa-times error' aria-hidden='true'></i></button>";
        }
    }
}




function getCurrentDay() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
    return output;
}

function search() {
    reset();
    console.log($("#search-text").val());
    var text = $("#search-text").val();
    var elements = document.getElementsByClassName("filter");
    console.log(elements);
    for(var i = 0; i < elements.length; i++) {
        var hasContent = false;
        console.log(elements[i].children);
        console.log(elements[i].children.length);
        for(var j = 0; j < elements[i].children.length; j++) {
            console.log(elements[i].children[j].textContent);
            console.log();
            if("index", elements[i].children[j].textContent.indexOf(text) > -1) {
                console.log(elements[i].children[j].textContent, " = ", text);
                hasContent = true;
            }
        }
        if(!hasContent) {
            console.log("TRUE", elements[i]);
            elements[i].style.display = "none";
        }
    }
    var text = $("#search-text").val("");
}

function reset() {
    var elements = document.getElementsByClassName("filter");
    for(var i = 0; i < elements.length; i++) {
        console.log(elements.length);
        elements[i].style.display = "";
    }
}

function notapprove(i) {

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/notapprove",
        data: {'index': i},
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById('entries-form-submit').click();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });

}

function approve(i) {

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/approve",
        data: {'index': i},
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            $("#entries-form-submit").click();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });

}

function approveAll() {
    var elements = $('.filter:visible');
    console.log(elements.length);
    for(var i = 0; i < elements.length; i++) {
        console.log(elements[i].getAttribute('id').replace('entry',''));
        approve(elements[i].getAttribute('id').replace('entry',''));
    }
    console.log("END APPROVE ALL");
}

function changeDepartment(objButton) {
    console.log("ADD DEPARTMENT");

    var data = {};
    if(objButton.value == 'add') {
        data["name"] = $("#add-department").val();
        data["action"] = objButton.value;
    }
    if(objButton.value == 'delete') {
        data["name"] = objButton.getAttribute("id").split("-")[0];
        data["action"] = objButton.value;
    }
    console.log("DEPARTMENT", data);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/command/changeDepartment",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 100000,

        success: function (d) {
            console.log("SUCCESS: ", d);
            document.getElementById("departments-button").click();
            $("#add-department").val("");
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });

}
