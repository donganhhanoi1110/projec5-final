$(document)
		.ready(
				function() {

					$(function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr(
								"content");
						$(document).ajaxSend(function(e, xhr, options) {
							xhr.setRequestHeader(header, token);
						});
					});

					/* Create SavingAccount Json */
					$(document)
							.on(
									"click",
									"#addSavingAccountSubmit",
									function(e) {
										e.preventDefault();
										var mydata = $("#savingaccount")
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
																								data.savingAccount.savingAccountNumber,
																								data.savingAccount.customerId.lastName
																										+ '&nbsp;'
																										+ data.savingAccount.customerId.midName
																										+ '&nbsp;'
																										+ data.savingAccount.customerId.firstName,
																								data.savingAccount.balanceAmount,
																								data.savingAccount.repeatable,
																								data.savingAccount.interestRateId.savingAccountType
																										+ '&nbsp; '
																										+ data.savingAccount.interestRateId.interestRate
																										+ '% &nbsp;'
																										+ '('
																										+ data.savingAccount.interestRateId.currency
																										+ ')',
																								data.savingAccount.state,
																								data.savingAccount.dateStart,
																								data.savingAccount.dateEnd,
																								'<a href='
																										+ '"submitSavingAccount.html?SavingAccountId='+data.savingAccount.id+'"'
																										+ 'class="myButton">Submit</a>',
																								'<a href='
																										+ '"editSavingAccount.html?SavingAccountId='+data.savingAccount.id+'"'
																										+ 'class="myButton">Edit</a>' ]

																				)
																				.draw();
																		$(
																				"#"
																						+ data.savingAccount.savingAccountNumber)
																				.toggleClass(
																						data.savingAccount.state);
																		$(
																				".popupContainer")
																				.hide();
																	}
																	// else
																	// success
																} else {
																	alert("Fail: "
																			+ data.message);
																}
																// Close
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
														alert("Error When Create Saving Account!!! ");
														console.log(a);
													}
												});

									});
					/* End Create SavingAccount Json */

					$(".addSavingAccount").bind("click", function(e) {
						$(".popupContainer").fadeIn("fast", function() {

							$.ajax({
								type : 'post',
								url : 'getSavingAccountNumber',
								data : "",
								datatype : 'json',
								success : function(response) {
									console.log(response);
									$(".createSavingForm").trigger("reset");
									$("#savingAccountNumber").val(response);
								},
								error : function(a, b, c) {
									$("#errorPane").html(a.responseText);
									console.log(a);
								}
							});

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
