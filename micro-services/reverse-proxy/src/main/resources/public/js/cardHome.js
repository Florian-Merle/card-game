$(document ).ready(function(){

    $.ajax({
        method: 'POST',
        url: '/getUserById',
        contentType: 'application/json',
        data: JSON.stringify({
            token: localStorage.getItem("token"),
            id: localStorage.getItem("token"),
        }),
        success: function (data) {
            $('#userNameId').text(data.firstName + ' ' + data.lastName);
            $('#cash').text(data.cash);
        },
        error: function () {
            window.location.replace(window.location.origin + '/loginForm.html');
        },
    });

    $('#logout').click(function ()  {
        localStorage.removeItem("token");
        window.location.replace(window.location.origin + '/loginForm.html');
    });

    $("#playButtonId").click(function(){
        window.location.replace(window.location.origin + '/cardPlay.html');
    });
    $("#buyButtonId").click(function(){
        window.location.replace(window.location.origin + '/cardBuy.html');
    });
    $("#sellButtonId").click(function(){
        window.location.replace(window.location.origin + '/cardSell.html');
    });

    $("#searchButtonId").click(function(){
        window.location.replace(window.location.origin + '/searchCard.html');
    });
});

