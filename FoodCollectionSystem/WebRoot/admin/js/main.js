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
			type : "GET",
			data : {
				
			},
			success : function(response){
				console.log(response);
				$("#pageHeading").html("Collector List");
				var htmlToRender = '<div class="sign-u "><div class="sign-up1" style="width: 7%; "><h4 maxLength="10">Sr No</h4></div><div class="sign-up1 " style="width: 30%; padding-left: 5%;"><h4>Name</h4></div><div class="sign-up1 " style="width: 20%; padding-left: 5%;"><h4>Contact No</h4></div><div class="sign-up1 " style="width: 25%; padding-left: 5%;"><h4>Email Id</h4></div><div class="sign-up1 " style="width: 16%; padding-left: 5%;"><h4>Status</h4><div class="clearfix"> </div></div>';
				for(var i=0;i<response.user.length;i++){
					htmlToRender= htmlToRender +'<div class="sign-u "><div class="sign-up1" style="width: 7%;"><h4>'+(i+1)+'</h4></div><div class="sign-up1 shriUserListBorder" style="width: 30%; padding-left: 5%;"><h4>'+response.user[i].name+'</h4></div><div class="sign-up1 shriUserListBorder" style="width: 20%; padding-left: 5%;"><h4>'+response.user[i].mobile+'</h4></div><div class="sign-up1 shriUserListBorder" style="width: 25%; padding-left: 5%;"><h4>'+response.user[i].email+'</h4></div><div class="sign-up1 shriUserListBorder" style="width: 16%; padding-left: 5%;"><h4>Yet to update</h4></div><div class="clearfix"> </div></div>';
				}				
				var htmlToRender1 = '<table id="t01" class="names"><tr><th>Sr No</th><th>Name</th><th>Contact No</th><th>Email Id</th><th>Status</th><th>Edit Button</th><th>Delete Button</th></tr>';
				for(var i=0;i<response.user.length;i++){
					htmlToRender1= htmlToRender1 +'<tr><td>'+(i+1)+'</td><td>'+response.user[i].name+'</td><td>'+response.user[i].mobile+'</td><td>'+response.user[i].email+'</td><td>Yet to update</td><td>Edit</td><td>Delete</td></tr>';
				}
				htmlToRender1= htmlToRender1 + '</table>';
				
				$("#mainTemplateBody").html(htmlToRender1);
				$("#mainTemplateBody").css("width","100%");
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




