$("#addSiteSubmit").click(function() {

	newSiteName = $("#addSiteName").val();

	if (newSiteName == "") {
		$("#addSiteName").attr("placeholder", "Assign a new site name here");
	} else {
		event.preventDefault();
		ajaxPost();

	}
});

function deleteSite(id) {

//	$.get("/bionime/deleteSite/" + id, function(result) {
//		console.log(result);
//
//	}).done(function(data) {
//		location.reload();
//	});
	
	
	
	$.ajax({
	    url: '/bionime/deleteSite/' + id,
	    type: 'DELETE',
	    success: function(result) {
	    	console.log("deleteSite: " + result );
	    	location.reload();
	        // Do something with the result
	    }
	});
}

function ajaxPost() {

	// PREPARE FORM DATA
	var formData = {
		name : $("#addSiteName").val()
	}

	// DO POST
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/bionime/addSite",
		data : JSON.stringify(formData),
		dataType : 'json',
		success : function(result) {
			console.log(result.Result);
			$("#addSiteResult").html(result.Result);

		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});

	// Reset FormData after Posting
	resetData();

}

function resetData() {
	$("#addSiteName").val("");
}