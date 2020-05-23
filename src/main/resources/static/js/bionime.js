$("#addSiteSubmit").click(function() {

	newSiteName = $("#addSiteName").val();

	if (newSiteName == "") {
		$("#addSiteName").attr("placeholder", "Assign a new site name here");
	} else {
		alert(newSiteName)
		event.preventDefault();
		ajaxPost();

	}
});

function ajaxPost() {

	// PREPARE FORM DATA
	var formData = {
		name : $("#addSiteName").val()
	}

	// DO POST
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : window.location + "/addSite",
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