$("#addSiteSubmit").click(function() {

	newSiteName = $("#addSiteName").val();

	if (newSiteName == "") {
		$("#addSiteName").attr("placeholder", "Assign a new site name here");
	} else {
		event.preventDefault();
		ajaxPost();

	}
});

$("#addStaffSubmit").click(function() {

	var newStaffName = $("#addStaffName").val();
	var newStaffID = $("#addStaffID").val();
	var values = $('#my-select').val();

	console.log(newStaffName);
	console.log(newStaffID);
	console.log(values);
	//if (newSiteName == "") {
	//	$("#addSiteName").attr("placeholder", "Assign a new site name here");
	//} else {
	//	event.preventDefault();
//		ajaxPost();

	//}
});

var values = $('#select-meal-type').val();


$("#addSiteName").focus(function() {

	console.log("focus");
	$("#addSiteResult").html("");
});

$('#my-select').multiSelect({
	selectableHeader : "<div class='custom-header'>Available sites</div>",
	selectionHeader : "<div class='custom-header'>Assign to site</div>"
})






/*
 * 
 * $( "#result" ).load( "ajax/test.html", function() {
  alert( "Load was performed." );
});
 * */


function deleteSite(id) {

	// $.get("/bionime/deleteSite/" + id, function(result) {
	// console.log(result);
	//
	// }).done(function(data) {
	// location.reload();
	// });

	$.ajax({
		url : '/bionime/deleteSite/' + id,
		type : 'DELETE',
		success : function(result) {
			console.log("deleteSite: " + result);
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