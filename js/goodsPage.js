 $("#next-button").click(function(event){
	next(); 
});

$("#goods-name, #goods-company, #weight, #num, #price, #goods-num").bind("enterKey",function(e){
   next();
});

$("#goods-name, #goods-company, #weight, #num, #price, #goods-num").keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});

function next () {
	location.href='TrackingCodePage.html';	
}
