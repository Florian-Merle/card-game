$(document).ready(function () {

    $.ajax({
        'url': '/cards',
        success: function (results) {
            $.each(results, function (i, item) {
                console.log(item);
                addCardToList(item.id, '', item.family, '/img/' + item.imgUrl, item.name, item.description, item.hp, item.energy, item.attack, item.defence, item.price)
            })
        }
    });

    $.ajax({
        /*method: 'GET',
        url: '/user',
        success: function (data) {
            $('#userNameId').text(data.firstName + ' ' + data.lastName);
            $('#cash').text(data.cash);
        },
        error: function () {
            window.location.replace(window.location.origin + '/loginForm.html');
        },*/
    });

});

function selectedCard(id) {

    $.ajax({
        'url': '/cards/getById/' + id,
        success: function (result) {
            fillCurrentCard(result.id, '/img/' + result.imgUrl, result.family, '/img/' + result.imgUrl, result.name, result.description,
                result.hp, result.energy, result.attack, result.defence, result.price);
        }
    })
};

function fillCurrentCard(id, imgUrlFamily, familyName, imgUrl, name, description, hp, energy, attack, defence, price) {
    //FILL THE CURRENT CARD
    $('#cardFamilyImgId')[0].src = imgUrlFamily;
    $('#cardFamilyNameId')[0].innerText = familyName;
    $('#cardImgId')[0].src = imgUrl;
    $('#cardNameId')[0].innerText = name;
    $('#cardDescriptionId')[0].innerText = description;
    $('#cardHPId')[0].innerText = hp + " PV";
    $('#cardEnergyId')[0].innerText = energy + " Energie";
    $('#cardAttackId')[0].innerText = attack + " Attaque";
    $('#cardDefenceId')[0].innerText = defence + " Défense";
    $('#cardPriceId')[0].innerText = price + " €";
    $('#toto').attr("onclick", "buyCard(" + id + ")");
};


function addCardToList(id, imgUrlFamily, familyName, imgUrl, name, description, hp, energy, attack, defence, price) {

    content = "\
    <td> \
    <img  class='ui avatar image' src='" + imgUrl + "'> <span>" + name + " </span> \
   </td> \
    <td>" + description + "</td> \
    <td>" + familyName + "</td> \
    <td>" + hp + "</td> \
    <td>" + energy + "</td> \
    <td>" + attack + "</td> \
    <td>" + defence + "</td> \
    <td>" + price + "€</td>\
    <td>\
        <div class='ui vertical animated button' tabindex='0' onclick='selectedCard(" + id + ")'>\
            <div class='hidden content'>Acheter</div>\
    <div class='visible content'>\
        <i class='shop icon'></i>\
    </div>\
    </div>\
    </td>";

    $('#cardListId tr:last').after('<tr id="' + id + '">' + content + '</tr>');


};

function buyCard(id) {

    var data = {};
    data['idCard'] = id;
    data['token'] = localStorage.getItem("token");

    $.ajax({
        type: "POST",
        url: '/buy',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            alert(result);
        }
    });
}
