$(document).ready(function() {
    $("#navico").click(function () {
        var width = $('#sidebar').css('width');
        if(width === '165px') {
            $("#sidebar").animate({
                width: '65px'
            });
            $("#navico").removeClass('fa-arrow-left').addClass('fa-bars');
            $("#login-menu").hide();
            $("#main-menu").hide();
            $("#logo").hide();
        } else {
            $("#sidebar").animate({
                width: '165px'
            });
            $("#login-menu").show();
            $("#main-menu").show();
            $("#navico").removeClass('fa-bars').addClass('fa-arrow-left');
            $("#logo").show();
        }
    });
});