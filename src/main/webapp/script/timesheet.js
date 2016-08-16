$(document).ready(function () {

    $("#show-form").submit(function (event) {
        console.log("SHOW FORM");
        event.preventDefault();
        $("#entriesTable > tr").remove();
        showAjax();
    });

    $("#add-form-button").click(function () {
        console.log("ADD FORM BUTTON");
        $("#add-form").show();
    });

    $("#add-form").submit(function (event) {
        console.log("ADD FORM");
        event.preventDefault();
        $("#add-form").hide();
        addAjax();
    })

});



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

        beforeSend: function () {
            //console.log("SEND")
        },
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
        var row = $("<tr />").addClass("entry-" + rowData.index);
        $("#entriesTable").append(row);
        row.append($("<td>" + rowData.index + "</td>"));
        row.append($("<td>" + rowData.date + "</td>"));
        row.append($("<td>" + rowData.startTime + "</td>"));
        row.append($("<td>" + rowData.endTime + "</td>"));
        row.append($("<td>" + rowData.time + "</td>"));
        row.append($("<td>" + rowData.approve + "</td>"));
        row.append($("<td>" + rowData.username + "</td>"));
        row.append($("<td>" + "<button class=\"edit\">" + "EDIT" + "</button>" + "</td>"));
        row.append($("<td> <button class=\"delete\">" + "DELETE" + "</button> </td>"));
    }

}

function addAjax() {

    var add = {};
    add["date"] = $("#add-form-date").val();
    add["timeStart"] = $("#add-form-time-start").val();
    add["timeEnd"] = $("#add-form-time-end").val();
    console.log("SEND", add)

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
