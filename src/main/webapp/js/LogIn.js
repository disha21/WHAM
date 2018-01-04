$(document).ready(function () {
	$("#error").hide();
	
	$("#loginForm").submit(function(event) {
		if ($('#loginForm').valid()) {
			var neu_email_id = document.getElementById("neu_emailid");
			var password = document.getElementById("password");
			console.log(string1);
			if ((neu_email_id.value == "student@husky.neu.edu" || neu_email_id.value == "faculty@neu.edu") && password.value == "secret") {
			    var string = neu_email_id.value;
			    var string1 = string.split("@");
			    localStorage.setItem("name", string1[0]);
			    return;
			} else {
				event.preventDefault();
				//document.getElementById("password").value ="";
				//document.getElementById("neu_emailid").value ="";
				$("#error").show();
			}
		}
	}); 

	$("#loginForm").validate({
		onfocusout : function(element) {
			$("#error").hide();
			$(element).valid();
		},
		rules : {
			husky_emailid : {
				required : true,
				email : true
			},
			password : {
				required : true,
			},
		},
		messages : {
			password : "Please enter a password",
			neu_emailid : "Please enter a valid NEU email address"
		}
	});
});
