$('#login_registration').on('submit', function (e) {
    e.preventDefault();

    $.ajax({
        method: 'POST',
        url: '/getAuthToken',
        contentType: 'application/json',
        data: JSON.stringify(transformArray($(this).serializeArray())),
        success: function (data) {
            window.token = data.token;
            localStorage.setItem("token", data.token);
            window.location.replace(window.location.origin + '/cardHome.html');
            alert(localStorage.getItem("token"))
        },
        error: function () {
            alert('Erreur lors de la connexion');
        },
    });
});


function transformArray(data) {
    var formattedData = {};

    data.forEach(function (element) {
        formattedData[element.name] = element.value;
    });

    return formattedData;
}