$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
$(document)
		.ready(
				function() {
					$("#table").DataTable({
						responsive : true
					});
					$("#popUpTable").DataTable({
						responsive : true
					});
					$("#show").click(function() {
						$("#hide").slideToggle();
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
																	var transaction = $('#transactions')
																	t.row
																			.add(
																					[
																							data.savingAccount.savingAccountNumber,
																							data.savingAccount.customerId.lastName
																									+ ''
																									+ data.savingAccount.customerId.midName
																									+ ''
																									+ data.savingAccount.customerId.lastName,
																							data.savingAccount.balanceAmount,
																							data.savingAccount.repeatable,
																							data.savingAccount.interestRateId.savingAccountType
																									+ ' '
																									+ data.savingAccount.interestRateId.interestRate
																									+ ' '
																									+ data.savingAccount.interestRateId.currency,
																							data.savingAccount.state,
																							data.savingAccount.dateStart,
																							data.savingAccount.dateEnd,
																							'<a href='
																									+ '"viewListTransaction.html?SavingAccountId=${savingAccount.id}"'
																									+ 'class="myButton" id="transactions">Transactions</a>' ]

																			)
																			.draw();
																	$(
																			".popupContainer")
																			.hide();
																}
															} else {
																alert("Create Failed");
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

					/* Get Transactions Json */
					$(document)
					.on(
							"click",
							".getMyTransactions",
							function(e) {
								e.preventDefault();

								var mydata = $(this).attr(
										"SavingAccount");
								// if you Transaction ajax
								$
										.ajax({
											type : 'post',
											url : 'viewListTransactionJson',
											data : "savingAccountNumber="
													+ mydata,
											datatype : 'json',
											success : function(data) {
												console.log(data);
												if (data.login == true) {
													if (data.success == true) {
														alert("Success:"+data.message+"!!!");
														if (data.error_code == '0') {
															window.location.href = 'viewAllSavingAccount?ERROR_CODE='
																	+ data.error_code;
														} else {
															/*
															 * window.location.href =
															 * 'viewAllSavingAccount';
															 */
															var t = $(
																	'#tableGetTransactions')
																	.DataTable();
															for (var i = 0; i < data.listTransactions.length; i++) {
																console.log(data.listTransactions[i].id);
																t.row
																		.add(
																				[
																						data.listTransactions[i].id,
																						data.listTransactions[i].amount,
																						data.listTransactions[i].dateStart,
																						data.listTransactions[i].dateEnd,
																						data.savingAccount.savingAccountNumber,
																						data.listTransactions[i].state,
																						data.listTransactions[i].transactionType,
																						data.listTransactions[i].currentBalance,
																						data.listTransactions[i].afterBalance ]

																		)
																		.draw();
															}
															$(
																	".popupContainerGetTransactions")
																	.fadeOut();
														}
													} else {
														alert( "Fail:"+data.message);
														$(
														".popupContainerGetTransactions")
														.fadeOut();
													}
												} else {
													window.location.href = 'login';
												}
											},
											error : function(a, b, c) {
												alert("Fail:"+"Get Error When Get Transactions");
												$(
												".popupContainerGetTransactions")
												.fadeOut();
												$("#errorPane").html(
														a.responseText);
												console.log(a);
											}
										});

							
							
							});
					/* End Get Transactions Json */
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
					$(".popupCloseButtonGetTransactions").bind("click", function(e) {
						$(".popupContainerGetTransactions").hide();
					});
					$(".getMyTransactions").bind(
							"click",
							function(e) {
							
								e.preventDefault();
								$(".popupContainerGetTransactions").fadeIn(
										"fast"), function() {
								
								}
							});
					$(".popupContainerGetTransactions").bind("click", function(e) {
						if (e.target == this) {
							$(this).hide();
						}
					})
					$(function() {
						$(".datepicker").datepicker({
							inline : true,
							dateFormat : 'dd/mm/yy'
						});
					});

				});
