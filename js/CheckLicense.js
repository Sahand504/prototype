 $("#show-button").click(function(event){
	next(); 
});

$("#goods-name, #goods-company, #weight, #num, #price, #date, #origin-country").bind("enterKey",function(e){
   next();
});

$("#goods-name, #goods-company, #weight, #num, #price, #date, #origin-country").keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});

function next () {
	location.href='ShowPage.html';	
}
