$(document).ready(function(){
    $.validator.addMethod("regex", function(value, element, pattern) {
        let regex = new RegExp(pattern);
        return this.optional(element) || regex.test(value);
    }, "Invalid format.");

    $("#signupForm").validate({
        rules:{
            fullname:{
                required:true,
                minlength:5
            },
            email:{
                required:true,
                email:true,
                regex: "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
            },
            role:{
                required:true
            },
            password:{
                required:true,
                minlength:8,
                regex: "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
            },
            cpassword:{
                required:true,
                equalTo:"#password"
            }
        },
        messages:{
            fullname:{
                required:"Enter your Full Name",
                minlength: "Full Name must be atleast 5 characters"
            },
            email:{
                required:"Enter your Email",
                email:"Enter a valid Email"
            },
            role:{
                required:"Select your Role"
            },
            password:{
                required:"Enter your Password",
                minlength: "Password must be atleast 8 characters",
                regex: "Password must contain uppercase, lowercase, number, and special character"
            },
            cpassword:{
                required:"Confirm your Password",
                equalTo: "Password does not match"
            }
        }
    });
});