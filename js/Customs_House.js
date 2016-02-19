 $("#next-button").click(function(event){
	next(); 
});

$("#melli-num, #name, #date, #value, #goods-num").bind("enterKey",function(e){
   next();
});

$("#melli-num, #name, #date, #value, #goods-num").keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});

function next () {
	location.href='goodsPage.html';	
}
