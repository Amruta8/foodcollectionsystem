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
				console.log(result);
				/*localStorage.setItem("username",result.name);
				localStorage.setItem("password",result.password);*/
				$.ajax({
					url:"admin/signup.html",
					type: 'GET',
					success: function(result){
						console.log(result);
						localStorage.setItem("username",result.name);
						localStorage.setItem("password",result.password);
						
					}
				});
				
			}
		});
	});
});

function checkLoginInfo(){
	return localStorage.getItem('username')!=null && localStorage.getItem('password')!=null ;
}

