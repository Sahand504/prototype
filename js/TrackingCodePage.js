 $("#sub-button").click(function(event){
	sub(); 
});

function sub () {
	if ($("#goods-name1").val() == "1") {
		$("#rej").css("display","none");
		$("#acc").css("display","block");
	}

	else {
		$("#acc").css("display","none");
		$("#rej").css("display","block");
	}
}
