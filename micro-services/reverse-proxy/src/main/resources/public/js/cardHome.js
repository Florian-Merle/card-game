$(document ).ready(function(){

    $.ajax({
        /*method: 'GET',
        url: '/user',
        success: function (data) {
            $('#userNameId').text(data.firstName + ' ' + data.lastName);
            $('#cash').text(data.cash);
        },
        error: function () {
            window.location.replace(window.location.origin + '/loginForm.html');
        },*/ // TODO : Voir WS pour récupérer infos
    });


    $("#playButtonId").click(function(){
        window.location.replace(window.location.origin + '/cardPlay.html');
        //TO DO
    });    
    $("#buyButtonId").click(function(){
        window.location.replace(window.location.origin + '/cardBuy.html');
        //TO DO
    });    
    $("#sellButtonId").click(function(){
        window.location.replace(window.location.origin + '/cardSell.html');
        //TO DO
    });

    $("#searchButtonId").click(function(){
        window.location.replace(window.location.origin + '/searchCard.html');
        //TO DO
    });
});

