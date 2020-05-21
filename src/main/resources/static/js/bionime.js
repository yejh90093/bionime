$("#addSiteSubmit").click(function(){

	newSiteName = $("#addSiteName").val();
	
    if(newSiteName == "") { 
    	$("#addSiteName").attr("placeholder", "Assign a new site name here");
     }
    else{
    	alert(newSiteName)

    }
});