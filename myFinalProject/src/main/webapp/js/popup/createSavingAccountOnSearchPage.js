$(document)
		.ready(
				function() {

					/* Create SavingAccount Json */

					$(document)
							.on(
									"click",
									"#addSavingAccountSubmit",
									function(e) {
										e.preventDefault();
										// Validate javascript
									
										var mydata = $("#savingAccountFormId")
												.serialize();
										// if you Transaction ajax
										$
												.ajax({
													type : 'post',
													url : 'createSavingAccountJson',
													data : mydata,
													datatype : 'json',
													success : function(data) {
														console.log(data);
														if (data.login == true) {
															if (!data.errorValidattionCheck) {
																if (data.success == true) {
																	alert("Create successfully!");
																	if (data.error_code == '0') {
																		window.location.href = 'viewAllSavingAccount?ERROR_CODE='
																				+ data.error_code;
																	} else {

																		$(
																				".popupContainerSavingAccount")
																				.hide();
																	}
																} else {
																	alert("Fail!!! "
																			+ data.message);
																}
																// ErrorValidation
															} else {
																errorInfo = "Please correct following errors:";
																for (var i = 0; i < data.errorValidation.length; i++) {
																	errorInfo += "\n"
																			+ (i + 1)
																			+ ". "
																			+ data.errorValidation[i].defaultMessage;

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
														alert("Create Saving Account get Errors!!!");
														console.log(a);
													}
												});

									});
					$("#createMySavingAccount").bind(
							"click",
							function(e) {
								$(".popupContainerSavingAccount").fadeIn(
										"fast",
										function() {

											$.ajax({
												type : 'post',
												url : 'getSavingAccountNumber',
												data : "",
												datatype : 'json',
												success : function(response) {
													console.log(response);
													$(".createSavingForm")
															.trigger("reset");
													$("#savingAccountNumber")
															.val(response);
												},
												error : function(a, b, c) {
													$("#errorPane").html(
															a.responseText);
													console.log(a);
												}
											});

										});
							})
					/* Popup Saving Account Close */
					$(".popupCloseButton").bind("click", function(e) {
						$(".popupContainerSavingAccount").hide();
					});
					$(".popupContainerSavingAccount").bind("click",
							function(e) {
								if (e.target == this) {
									$(this).hide();
								}
							})
					/* End Create SavingAccount Json */
				});