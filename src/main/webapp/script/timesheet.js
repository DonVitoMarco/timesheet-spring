jQuery(document).ready(function ($) {

    $("#show-form").submit(function (event) {
        event.preventDefault();
        $("#entriesTable > tr").remove();
        showAjax();
    });

});

function showAjax() {

    var data = {};
    data["dataStart"] = $("#data-start").val();
    data["dataEnd"] = $("#data-end").val();
    console.log("DATA: ", data);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/show",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 100000,

        beforeSend: function () {
            console.log("SEND")
        },
        success: function (d) {
            console.log("SUCCESS: ", d);
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
        console.log("ROW: ", rowData);
        var row = $("<tr />");
        $("#entriesTable").append(row);
        row.append($("<td>" + rowData.index + "</td>"));
        row.append($("<td>" + rowData.date + "</td>"));
        row.append($("<td>" + rowData.startTime + "</td>"));
        row.append($("<td>" + rowData.endTime + "</td>"));
        row.append($("<td>" + rowData.time + "</td>"));
        row.append($("<td>" + rowData.approve + "</td>"));
        row.append($("<td>" + rowData.department + "</td>"));
        row.append($("<td>" + rowData.username + "</td>"));
    }
}
