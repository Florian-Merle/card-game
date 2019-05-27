$('#form_registration').on('submit', function (e) {
    e.preventDefault();

    $.ajax({
        method: 'POST',
        url: '/register',
        contentType: 'application/json',
        data: JSON.stringify(transformArray($(this).serializeArray())),
        success: function () {
            window.location.replace(window.location.origin + '/loginForm.html');
        },
        error: function () {
            alert('Erreur lors de l\'inscription');
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