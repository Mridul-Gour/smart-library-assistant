$(document).ready(function(){    
    $("#signupForm").validate({
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