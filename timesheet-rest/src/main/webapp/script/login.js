$(document).ready(function () {

    var res = $(location).attr('href').split("?");
    if(res[1] == "error") {
        console.log("ERROR");
        $("#errorbox").show();
    }

});