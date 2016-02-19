 $("#add-button").click(function(event){
	add(); 
});

$("#username, #password").bind("enterKey",function(e){
   add();
});

$("#username, #password").keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});

function add () {
	location.href='LoginPage.html';	
}