
$(document)
		.ready(
				function() {
					$(function() {
						$(".datepicker").datepicker({
							inline : true,
							dateFormat : 'dd/mm/yy'
						});
					});
					$(function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");
						$(document).ajaxSend(function(e, xhr, options) {
							xhr.setRequestHeader(header, token);
						});
					});
				
					$("#popUpTable").DataTable({
						responsive : true
					});
					$("#show").click(function() {
						$("#hide").slideToggle();
					});

					/* Create SavingAccount Json */
					$(document).on("click","#addCustomer",function(e) {
										e.preventDefault();
										var mydata = $("#customer")
												.serialize();
										// if you Transaction ajax
										$
												.ajax({
													type : 'post',
													url : 'createCustomerJson',
													data : mydata,
													datatype : 'json',
													success : function(data) {
														console.log(data);
														if (data.login == true) {
															if(!data.errorValidattionCheck){

															if (data.success == true) {
																alert("Create successfully!");
																if (data.error_code == '0') {
																	window.location.href = 'viewAllSavingAccount?ERROR_CODE='
																			+ data.error_code;
																} else {
																	/*
																	 * window.location.href =
																	 * 'viewAllSavingAccount';
																	 */
																	var t = $(
																			'#table')
																			.DataTable();
																	t.row
																			.add(
																					[
																					 	data.customer.id,
																					 	data.customer.accountNumber,
																					 	data.customer.accountType,
																					 	data.customer.firstName
																					 		+ ''
																					 		+ data.customer.midName
																					 		+ ''
																					 		+ data.customer.lastName,
																					 	data.customer.idNumber,
																					 	data.customer.phone1,
																					 	data.customer.add1,
																					 	data.customer.email,
																					 	data.customer.state,
																					 	
																						'<a customer='+'"${cus.id}"'+ 'href=""'+
																							'id="linkDeleteCustomer" class="myButton"> Delete </a>',
																						'<a href="editCustomer.html?custID=${cus.id}"'+
																							'class="myButton">Edit</a>'
																					 	]

																			)
																			.draw();
																	$(
																			".popupContainer")
																			.hide();
																}
																//else success
															} else {
																alert("Fail: "+data.message);
															}
															//Close ErrorValidation
															}else{
																errorInfo="Please correct following errors:";
																for(var i=0;i<data.errorValidation.length;i++)
																	{
																	 errorInfo += "\n" + (i + 1) +". " + data.errorValidation[i].defaultMessage;

																	}
															alert(errorInfo);
															}
														} else {
															window.location.href = 'login';
														}
													},
													error : function(a, b, c) {
														$("#errorPane").html(
																a.responseText);
														console.log(a);
													}
												});

									});
					/* End Create SavingAccount Json */

					
					$(".CreateCustomer").bind("click", function(e) {
						$(".popupContainer").fadeIn("fast", function() {


						});
					})
					/* Popup Saving Account Close */
					$(".popupCloseButton").bind("click", function(e) {
						$(".popupContainer").hide();
					});
					$(".popupContainer").bind("click", function(e) {
						if (e.target == this) {
							$(this).hide();
						}
					})
					/* Popup Saving Account Close */
			
					

				});
