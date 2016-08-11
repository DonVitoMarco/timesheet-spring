jQuery(document).ready(function ($) {

    $("#show-form").submit(function (event) {
        event.preventDefault();
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
            display(d);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });

    function display(d) {

    }
}
