$(document).ready(function () {

    var res = $(location).attr('href').split("?");
    if(res[1] == "error") {
        console.log("ERROR");
        $("#errorbox").show().html("Passwords do not match");
    }
    if(res[1] == "validUsername") {
        $("#errorbox").show().html("Username already exists");
    }

});