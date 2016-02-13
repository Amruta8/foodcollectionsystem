function isEmail(email) {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}
function saveData(){
		if(selectedRecord==undefined || !$("#"+selectedRecord).is(':checked')){
			alert('Select a Tour');
			return;
		}
		if($('#name'+selectedRecord).val()==''){
			alert('Please Enter name');
			return;
		}
		if($('#numberOfTourist'+selectedRecord).val()==''){
			alert('Number of tourist must be atleast one');
			return;
		}
		if($('#contactNumber'+selectedRecord).val()=='' || $('#contactNumber'+selectedRecord).val().length!=10){
			alert('Enter valid Mobile Numebr');
			return;
		}
		if($('#email'+selectedRecord).val()=='' || !isEmail($('#email'+selectedRecord).val())){
			alert('Enter Valid Email');
			return;
		}
		$.ajax({
			url : 'getTourBooked',
			data : {
					name 		 : $('#name'+selectedRecord).val(),
					noOfTourist  : $('#numberOfTourist'+selectedRecord).val(),
					mobileNumber : $('#contactNumber'+selectedRecord).val(),
					emailId  	 : $('#email'+selectedRecord).val(),
					tourNo  	 : selectedRecord
					},
			type : 'POST',
			success : function(response){
				if(response==""){
					alert("Sorry for inconvenience, The Service is Down. Try After Some Time. ")
				}else{
						alert("Dear "+$('#name'+selectedRecord).val()+" oue representive will contact you soon");
				}				
			},
			error : function(xhr, status, errorThrown){
				
			}
		});
		console.log('entered');
		return false;
	}
	$(document).ready(function(){		
		$(".selectBox").click(function(){			
			if($(this).is(':checked')){
				selectedRecord = this.id;
				for(var i=1;i<=recordCount;i++){
					if(this.id!=i){
						$("#tableId"+i).css('display','none');
						  $("#"+i).attr("checked", false);
					}else{
						$("#tableId"+i).css('display','block');
					}				
				}
			}
		});	
		
		
		$(".confirmBooking").click(function(){
			console.log("shrikant ghuge")
			var resultArr = [];
				for(var i=0; i<$($(this.parentElement.parentElement.parentElement.parentElement).find("label")).length ;i++){
				console.log($($(this.parentElement.parentElement.parentElement.parentElement).find("label")[i]).html());
				resultArr.push($($(this.parentElement.parentElement.parentElement.parentElement).find("label")[i]).html());	
			}
			for(var j=1;j<4;j++){
				resultArr.push($($(this.parentElement.parentElement.parentElement.parentElement).find("input")[j]).val());
			}
			resultArr.push($(this.parentElement.parentElement.parentElement.parentElement).find("select").val());
			$.ajax({
				url : "contactUs",
				data : {
					data : resultArr
				},
				type : "POST",
				success : function(response){
					if(response==""){
						alert("Sorry for inconvenience, The Service is Down. Try After Some Time. ")
					}else{
							alert("Dear "+$("#userName").val()+" oue representive will contact you soon");
							//location.replace("index.html");
					}				
				},
				error : function(xhr, status, errorThrown){
					alert("There is an error in request!!")
					
				}
			});
		});
		
		
	});
	
	/*To Store the Enquiry Information: START*/
	function contactUSFun(){	
		if($('#userName').val()==''){
			alert('Please Enter name');
			return;
		}
		if($('#userEmail').val()=='' || !isEmail($('#userEmail').val())){
			alert('Enter Valid Email');
			return;
		}		
		if($('#userPhone').val()=='' || $('#userPhone').val().length!=10){
			alert('Enter valid Mobile Numebr');
			return;
		}		
		/*if($('#userMsg').val()==''){
			alert('Querry cannot be blank');
			return;
		}*/
		$.ajax({
			url : 'https://maps.googleapis.com/maps/api/geocode/json?address='+$($("#address").children()[1]).children()[1].value+' '+$($("#address").children()[2]).children()[1].value+' '+$($("#address").children()[3]).children()[1].value+'&key=AIzaSyD_7jZK5UXt287KSmlC6Fga1_RTavxUX1M',
			data : {
									
					},
			type : 'GET',
			success : function(response){
				if(response==""){
					alert("Sorry for inconvenience, The Service is Down. Try After Some Time. ")
				}else{
					$.ajax({
						url : "../fcs/user/theCollectionRequest",
						data : {
							name 		 : $('#userName').val(),
							requestQuantity  : $('#userQuantity').val(),
							mobileNumber : $('#userPhone').val(),
							emailId  	 : $('#userEmail').val(),							
							address : $($("#address").children()[0]).children()[1].value +" , "+$($("#address").children()[1]).children()[1].value +" , "+$($("#address").children()[2]).children()[1].value+ " , " +$($("#address").children()[3]).children()[1].value +" , Pin :" + $($("#address").children()[4]).children()[1].value,
							location : response.results[0].geometry.location.lat+","+response.results[0].geometry.location.lng 
							
						},
						type : "POST",
						success : function(response){
							if(response==""){
								alert("Sorry for inconvenience, The Service is Down. Try After Some Time. ")
							}else{
									alert("Dear "+$("#userName").val()+" oue representive will contact you soon");
									//location.replace("index.html");
							}				
						},
						error : function(xhr, status, errorThrown){
							alert("There is an error in request!!")
							
						}
					});
					
					
					
					
					
						alert("Dear "+$('#userName').val()+" oue representive will contact you soon");
						//location.replace("index.html");
				}				
			},
			error : function(xhr, status, errorThrown){
				alert('There is an error in request!!')
				
			}
		});
		console.log('entered');
		return false;
	}
	/*To Store the Enquiry Information: END
	*/
	
	/*this confirms the bus booking : START*/
	/*function confirmBusBooking(var thisVar){
		//return false;
			console.log("In confirm bus booking")
			var resultArr = [];
				for(var i=0; i<$($(thisVar.parentElement.parentElement.parentElement.parentElement).find("label")).length ;i++){
				console.log($($(thisVar.parentElement.parentElement.parentElement.parentElement).find("label")[i]).html());
				resultArr.push($($(thisVar.parentElement.parentElement.parentElement.parentElement).find("label")[i]).html());	
			}
			for(var j=1;j<4;j++){
				resultArr.push($($(thisVar.parentElement.parentElement.parentElement.parentElement).find("input")[j]).val());
			}
			resultArr.push($(thisVar.parentElement.parentElement.parentElement.parentElement).find("select").val());
			$.ajax({
				url : "contactUs",
				data : {
					data : resultArr
				},
				type : "POST",
				success : function(response){
					if(response==""){
						alert("Sorry for inconvenience, The Service is Down. Try After Some Time. ")
					}else{
							alert("Dear "+$("#userName").val()+" oue representive will contact you soon");
							//location.replace("index.html");
					}				
				},
				error : function(xhr, status, errorThrown){
					alert("There is an error in request!!")
					
				}
			});	
			return false;
	}*/
	/*this confirms the bus booking : END*/
	
	
	