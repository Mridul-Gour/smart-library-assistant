$(document).ready(function(){    
    $("#loginForm").validate({
        rules:{
            email:{
                required:true,
                email:true
            },
            pass:{
                required:true,
                minlength:8
            }
        },
        messages:{
            email:{
                required:"Email is Required",
                email:"Enter a valid Email adress"
            },
            pass:{
                required:"Password is Required",
                minlength:"Password must be at least 8 characters long"
            }
        }
    });
});

// forgot password
$('#forgotForm').validate({
    submitHandler: function(form) {
        alert("If your email is registered, you will receive reset instructions.");
        form.reset(); // optional: reset the form
    }
});