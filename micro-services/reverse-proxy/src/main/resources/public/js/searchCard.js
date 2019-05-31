$(document ).ready(function(){

    $.ajax({
       /* method: 'GET',
        url: '/user',
        success: function (data) {
            $('#userNameId').text(data.firstName + ' ' + data.lastName);
            $('#cash').text(data.cash);
        },
        error: function () {
            window.location.replace(window.location.origin + '/loginForm.html');
        },*/
    });

      $("#searchButtonId").click(searchByName);

      $("#form").submit(searchByName);

      function searchByName (e){

          e.preventDefault();
          $.ajax({
              'url':'/cards/getByName/'+$('input[name=search]').val(),
              success: function(result){
                  fillCurrentCard('/img/'+result.imgUrl,result.family,'/img/'+result.imgUrl,result.name,result.description,
                      result.hp,result.energy,result.attack,result.defence);
              }
          })

      };
    
});

function fillCurrentCard(imgUrlFamily,familyName,imgUrl,name,description,hp,energy,attack,defence){
    //FILL THE CURRENT CARD
    $('#cardFamilyImgId')[0].src=imgUrlFamily;
    $('#cardFamilyNameId')[0].innerText=familyName;
    $('#cardImgId')[0].src=imgUrl;
    $('#cardNameId')[0].innerText=name;
    $('#cardDescriptionId')[0].innerText=description;
    $('#cardHPId')[0].innerText=hp+" PV";
    $('#cardEnergyId')[0].innerText=energy+" Energie";
    $('#cardAttackId')[0].innerText=attack+" Attaque";
    $('#cardDefenceId')[0].innerText=defence+" Défense";
};



