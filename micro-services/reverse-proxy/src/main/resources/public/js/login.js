$('#login_registration').on('submit', function (e) {
    e.preventDefault();

    $.ajax({
        method: 'POST',
        url: '/getAuthToken',
        contentType: 'application/json',
        data: JSON.stringify(transformArray($(this).serializeArray())),
        success: function (data) {
            localStorage.setItem("token", data.token);
            window.location.replace(window.location.origin + '/cardHome.html');
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