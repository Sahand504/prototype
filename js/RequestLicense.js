 $("#req-button").click(function(event){
	req(); 
});

function req () {
	$("#acc").css("display","block");
	setTimeout(function() {
		window.location.href = "ShowTrackingCode.html";
	}, 2000);
}
