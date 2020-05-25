$("#addSiteSubmit").click(function() {
	var newSiteName = $("#addSiteName").val();
	if (newSiteName == "") {
		$("#addSiteName").attr("placeholder", "Assign a new site name here");
	} else {
		event.preventDefault();
		ajaxPostAddSitr();
	}
});

$("#addStaffSubmit").click(function() {
	
	var newStaffName = $("#addStaffName").val();
	var newStaffID = $("#addStaffID").val();
	var values = $('#my-select').val();
	
	console.log("addStaffSubmit:" + values);
	console.log("addStaffSubmit:" + (newStaffName == "" || newStaffID=="" || values==""));
	
	if (newStaffName == "" || newStaffID=="" || values=="") {
		$("#addStaffResult").html("Request data should not empty");
		
	}else{
		event.preventDefault();
		ajaxPostAddStaff();
	}
	
	//if (newSiteName == "") {
	//	$("#addSiteName").attr("placeholder", "Assign a new site name here");
	//} else {
	//	event.preventDefault();
	//	ajaxPost();
	//}
});


$("#addSiteName").focus(function() {
	$("#addSiteResult").html("");
});

$('#my-select').multiSelect({
	selectableHeader : "<div class='custom-header'>Available sites</div>",
	selectionHeader : "<div class='custom-header'>Assign to site</div>"
})

function deleteSite(id) {
	$.ajax({
		url : '/bionime/deleteSite/' + id,
		type : 'DELETE',
		success : function(result) {
			console.log("deleteSite: " + result);
			location.reload();
		}
	});
}



function ajaxPostAddSitr() {

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
	resetSiteName();
}



function ajaxPostAddStaff() {

	// PREPARE FORM DATA
	var formData = {
			id : $("#addStaffID").val(),
			name : $("#addStaffName").val(),
			serviceSite : String($('#my-select').val())
	}
	
	
	console.log( JSON.stringify(formData));

	// DO POST
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/bionime/addStaff",
		data : JSON.stringify(formData),
		dataType : 'json',
		success : function(result) {
			console.log(result.Result);
			$("#addStaffResult").html(result.Result);

		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});

	// Reset FormData after Posting
	resetStffInput();
}

function resetSiteName() {
	$("#addSiteName").val("");
}

function resetStffInput() {
	$("#addStaffID").val("");
	$("#addStaffName").val("");
	$("#my-select").val("");
}