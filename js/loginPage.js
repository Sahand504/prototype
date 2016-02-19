 $("#login-button").click(function(event){
	login(); 
});

$("#username, #password").bind("enterKey",function(e){
   login();
});

$("#username, #password").keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});

function login () {
	if ($("#username").val() == "admin" && $("#password").val() == "admin") {
		location.href='adminPage.html';
	}

	else if ($("#username").val() == "1" && $("#password").val() == "1") {
		location.href='Chamber_of_Commerce.html';
	}

	else if ($("#username").val() == "2" && $("#password").val() == "2") {
		location.href='Customs_House.html';
	}
	else {
		alert("Username or Password is incorrect! : (");
	}	
}