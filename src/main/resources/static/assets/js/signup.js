
function showResponse(msg) {

    if (msg == -1) {
        $('#alert-area').html(" <div class=\"col-xs-12\"> <div class=\"alert alert-danger alert-dismissable\"> <p> <i class=\"icon fa fa-ban\"></i> <span th:text=\"#{error.login_failed}\">Invalid Email.</span> </p> </div>");
    }
    if (msg == -2) {
        $('#alert-area').html(" <div class=\"col-xs-12\"> <div class=\"alert alert-danger alert-dismissable\"> <p> <i class=\"icon fa fa-ban\"></i> <span th:text=\"#{error.login_failed}\">Passwords are not matching.</span> </p> </div>");
    }
    if (msg == -3) {
        $('#alert-area').html(" <div class=\"col-xs-12\"> <div class=\"alert alert-danger alert-dismissable\"> <p> <i class=\"icon fa fa-ban\"></i> <span th:text=\"#{error.login_failed}\">Please try again later.</span> </p> </div>");
    }
    if (msg == -4) {
        $('#alert-area').html(" <div class=\"col-xs-12\"> <div class=\"alert alert-danger alert-dismissable\"> <p> <i class=\"icon fa fa-ban\"></i> <span th:text=\"#{error.login_failed}\">E-mail provided already registered.</span> </p> </div>");
    }
    if (msg == -5) {
        $('#alert-area').html(" <div class=\"col-xs-12\"> <div class=\"alert alert-danger alert-dismissable\"> <p> <i class=\"icon fa fa-ban\"></i> <span th:text=\"#{error.login_failed}\">Password must have at least 3 characters</span> </p> </div>");
    }

    if (msg == 0) {
        $('#alert-area').html(" <div class=\"col-xs-12\"> <div class=\"alert alert-info alert-success\"> <p> <i class=\"icon fa fa-thumbs-up\"></i> <span th:text=\"#{error.login_failed}\">Please wait confirmation.</span> </p> </div>");
    }

    //if (msg == 0) {
    //    result = "<div class=\"bggreen\"><p><span class=\"icon-ok-sign\"><\/span><i class=\"close icon-remove-circle\"></i><span>Success!<\/span>You have successfully registered. Please check your email for further information<\/p><\/div>";
    //    $("#fullform").hide();
    //} else {
    //    result = msg;
    //}

}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

$(document).ready(function () {

    $('#signup-form').ajaxForm({
        async: true,
        dataType: 'json',
        type: 'post',
        beforeSubmit:  function () {
            email = ($('#email').val() || '').trim();
            pass = ($('#password').val() || '').trim();
            pass2 = ($('#re-password').val() || '').trim();

            if (!validateEmail(email)) {
                showResponse(-1);
                return false;

            }
            console.log(pass+' '+pass2);

            if(pass != pass2){
                showResponse(-2);
                return false;
            }
            if(pass.length < 3){
                showResponse(-5);
                return false;

            }

            return true;
        },
        success: function (res) {
            console.log("success");
            console.log(res.data.code);

            if(res.data.code == 402)
                showResponse(-4);
            else
                showResponse(0);

        }
    });


    // var email, pass, pass2;
    //
    // var options = {
    //     target: "#alert-area",
    //
    //     beforeSend: function () {
    //         email = ($('#email').val() || '').trim();
    //         pass = ($('#password').val() || '').trim();
    //         pass2 = ($('#re-password').val() || '').trim();
    //
    //         if (!validateEmail(email)) {
    //             showResponse(-1);
    //             return false;
    //
    //         }
    //         console.log(pass+' '+pass2);
    //
    //         if(pass != pass2){
    //             showResponse(-2);
    //             return false;
    //         }
    //         if(pass.length < 3){
    //             showResponse(-5);
    //             return false;
    //
    //         }
    //
    //         return true;
    //     },
    //
    //     success: function (res) {
    //         console.log("success");
    //
    //     },
    //
    //     url: `${SERVER_URL}/register/user`,
    //     resetForm: 0,
    //     clearForm: 0,
    //     async: true,
    //     data: {
    //         email:email,
    //         password: pass,
    //     }
    // };
    // $("#signup-form").ajaxForm(options);




    // $("#signup-form").ajaxForm({
    //
    //     beforeSend: function()
    //     {
    //
    //         email = ($('#email').val() || '').trim();
    //         pass = ($('#password').val() || '').trim();
    //         var pass2 = ($('#re-password').val() || '').trim();
    //
    //         if (!validateEmail(email)) {
    //             showResponse(-1);
    //             return false;
    //
    //         }
    //         console.log(pass+' '+pass2);
    //
    //         if(pass != pass2){
    //             showResponse(-2);
    //             return false;
    //         }
    //         if(pass.length < 3){
    //             showResponse(-5);
    //             return false;
    //
    //         }
    //
    //         return true;
    //     },
    //     url: `${SERVER_URL}/register/user`,
    //     resetForm: 0,
    //     clearForm: 0,
    //     type : 'POST',
    //     data: {
    //         email:email,
    //         password: pass
    //     },
    //
    //     success: function()
    //     {
    //
    //     },
    //     complete: function(xhr)
    //     {
    //     },
    //     error: function(xhr)
    //     {
    //     }
    // });

});


