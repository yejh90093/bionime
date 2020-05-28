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
	
});


$("#modifyStaffSubmit").click(function() {
	
	var newStaffName = $("#viewStaffName").val();
	var values = $('#my-select').val();
	
		event.preventDefault();
		ajaxPostModifyStaff();  
});



$("#addSiteName").focus(function() {
	$("#addSiteResult").html("");
});




$( document ).ready(function() {
    console.log( "ready!" );
    $('#my-select').multiSelect({
    	selectableHeader : "<div class='custom-header'>Available sites</div>",
    	selectionHeader : "<div class='custom-header'>Assign to site</div>"
    })
    
    $('#modifySelect').multiSelect({
    	selectableHeader : "<div class='custom-header'>Available sites</div>",
    	selectionHeader : "<div class='custom-header'>Assign to site</div>"
    })
});


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
			name : $("#addStaffName").val()
	}
	
	var serviceSite = [];	
	
	$('#my-select').val().forEach(function(item){		
		serviceSite.push({ 
	        "name" : item,
	        "date" : Date.now()
	    });
	});
	
	 
	formData["serviceSite"] = JSON.stringify(serviceSite);
	console.log( JSON.stringify(serviceSite));

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
	resetStaffInput();
}

function ajaxPostModifyStaff() {

	// PREPARE FORM DATA
	var formData = {
			name : $("#viewStaffName").text()
	}
	
	var serviceSite = [];	
	
	$('#modifySelect').val().forEach(function(item){		
		serviceSite.push({ 
	        "name" : item,
	        "date" : Date.now()
	    });
	});
	
	 
	formData["serviceSite"] = JSON.stringify(serviceSite);
	console.log( JSON.stringify(serviceSite));

	console.log( JSON.stringify(formData));

	// DO POST
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/bionime/modifyStaff",
		data : JSON.stringify(formData),
		dataType : 'json',
		success : function(result) {
			console.log(result.Result);
			$("#modifyStaffResult").html(result.Result);

		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}

function resetSiteName() {
	$("#addSiteName").val("");
}

function resetStaffInput() {
	$("#addStaffID").val("");
	$("#addStaffName").val("");
	$("#my-select").multiSelect('deselect_all');
}