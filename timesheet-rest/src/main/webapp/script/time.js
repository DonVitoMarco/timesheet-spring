$(document).ready(function () {

    $('#clock').on("click", function() {
        window.location.replace("http://localhost:8080/about")
    });

});

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    var y = today.getFullYear();
    var d = today.getDate();
    var w = getWeekDay(today);
    var o = getMonthDay(today);
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('clock').innerHTML =
        h + ":" + m + ":" + s;
    document.getElementById('clock2').innerHTML =
        w + " " + o + " " + d + " " + y + " " + h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}

function checkTime(i) {
    if (i < 10) {i = "0" + i};
    return i;
}

function getWeekDay(date) {
    var days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat']
    return days[ date.getDay() ];
}

function getMonthDay(date) {
    var days = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    return days[ date.getMonth() ];
}