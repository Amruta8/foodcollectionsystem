/*Author : Shrikant Ghuge*/
$(document).ready(function(){
	
	/*This checks the valid user is accessing the page*/
	if(checkLoginInfo()){
		$.ajax({
			url : "../fcs/user/checkLogin",
			type : "GET",
			data: {
				name : localStorage.getItem('username'),
				password : localStorage.getItem('password')
			},
			success : function(response){
				if(response=="false"){
					window.location.replace('./error.html');
				}
			}
		});
	}else{
		window.location.replace('./error.html');
	}
	
	
	/*Signup the user : START*/
	$("#submitForm").click(function(){
		if(validateFields()!=undefined){
			$.ajax({
				url : "../fcs/user/signUp",
				data : {
					name : $("#uFName").val() ,
					email : $("#uEmailId").val(),
					mobile : $("#uContactNo").val(),
					password : $("#password1").val()
				},
				type : "GET",
				success : function(response){
					console.log(response);
					alert(response);
				}
			});
		}
	});
	/*Signup the user : END*/
	
	/*List the registered user : START*/
	$("#listRegUser").click(function(){
		$.ajax({
			url : "../fcs/user/getRegistereduser",
			dataType : "application/json",
			type : "GET",
			success : function(response){
				console.log(response);
				alert(response);
			}
		});
	});
	/*List the registered user : END*/
	
});

function validateFields(){
		if($("#uFName").val()==""){
			alert("Enter First Name");
		}else
		if($("#uLName").val()==""){
			alert("Enter Last Name");
		}else
		if($("#uEmailId").val()==""){
			alert("Enter Email Id");
		}else
		if($("#uContactNo").val()==""){
			alert("Enter Contact Number");
		}else
		if($("#password1").val()==""){
			alert("Enter Password");
		}else
		if($("#password2").val()==""){
			alert("Re-Enter password");
		}else
		if($("#password1").val()!=$("#password2").val()){
			alert("Both passwords doesnot match");
		}else{
			return true;
		}
}

function checkLoginInfo(){
	return localStorage.getItem('username')!=null && localStorage.getItem('password')!=null ;
}




