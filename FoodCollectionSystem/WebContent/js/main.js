$(document).ready(function(){
	$("#loginButton").click(function(){
		$.ajax({
			url:"fcs/user/login",
			type: 'GET',
			data : {
				name : $("#username").val(),
				password : $("#password").val()
			},
			dataType : 'json',
			success: function(result){
				alert(result);
			}
		});
	});
});