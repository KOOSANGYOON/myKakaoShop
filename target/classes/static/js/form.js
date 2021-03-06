$(".join-btn").on("click", submitJoin);
$(".signIn-btn").on("click", submitSignIn);
$(".logoutBtn").on("click", logout);

function logout(e) {
    e.preventDefault();
    console.log("hihi");

    var url = $(e.target).closest(".logoutForm").attr("action");
    console.log(url);

    $.ajax({
        type: 'post',
        url: url
    }).done(function joinSuccess() {
        console.log("success!");
        location.href = "/";
    }).fail(function joinFail(data) {
        console.log("data :" + data);
        console.log("status :" + status);
        alert("fail to log out.");
    })
}

function submitJoin(e) {
    e.preventDefault();
    var url = $(e.target).closest(".join-form").attr("action");

    var data = {
        userId : $("#form-userid").val(),
        passwd : $("#form-password").val(),
        name : $("#form-username").val(),
        email : $("#form-email").val()
    };

    console.log("queryString : " + JSON.stringify(data));

    $.ajax({
        type: 'post',
        url: url,
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(data),
        dataType: 'json'
    }).done(function joinSuccess(data) {
        console.log("success!", data);
        location.href = "/";
    }).fail(function joinFail(data) {
        console.log("fail to join..", data);
        alert("fail to join... check validation.");
    })
}

function submitSignIn(e) {
    e.preventDefault();
    var url = $(e.target).closest(".login-form").attr("action");
    console.log(url);

    var data = {
        userId : $("#form-userid").val(),
        passwd : $("#form-password").val()
    };

    console.log("queryString : " + JSON.stringify(data));

    $.ajax({
        type: 'post',
        url: url,
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(data),
        dataType: 'json'
    }).done(function joinSuccess(data) {
        console.log("success!", data);
        location.href = "/";
    }).fail(function joinFail(data) {
        console.log("fail to join..", data);
        alert("fail to login..");
    })
}
