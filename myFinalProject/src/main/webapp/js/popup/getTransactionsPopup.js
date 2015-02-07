$(document)
		.ready(
				function() {
					$(function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");
						$(document).ajaxSend(function(e, xhr, options) {
							xhr.setRequestHeader(header, token);
						});
					});
					/* Create SavingAccount Json */

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

																if (data.error_code == '0') {
																	window.location.href = 'viewAllSavingAccount?ERROR_CODE='
																			+ data.error_code;
																} else {
																	/*
																	 * window.location.href =
																	 * 'viewAllSavingAccount';
																	 */
																	var table = $(
																			'#tableGetTransactions')
																			.DataTable();
																	table
																			.clear();
																	for (var i = 0; i < data.listTransactions.length; i++) {
																		$(
																		".myState")
																		.addClass(
																				data.listTransactions[i].state);
																		console
																				.log(data.listTransactions[i].id);
																		table.row
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

																}
															} else {
																$(
																		".popupContainerGetTransactions")
																		.hide();
																alert("Fail:"
																		+ data.message);

															}
														} else {
															window.location.href = 'login';
														}
													},
													error : function(a, b, c) {
														alert("Fail:"
																+ "Get Error When Get Transactions");
														$(
																".popupContainerGetTransactions")
																.fadeOut('fast');
													
														console.log(a);
													}
												});

									});
					/* End Get Transactions Json */
					$("#exitGetTransaction").bind("click", function(e) {
						$(".popupContainerGetTransactions").fadeOut();

					});
					$(".popupCloseButtonGetTransactions").bind("click",
							function(e) {
								$(".popupContainerGetTransactions").fadeOut();

							});
					$(".getMyTransactions").bind(
							"click",
							function(e) {

								e.preventDefault();
								$(".popupContainerGetTransactions").fadeIn(
										"slow"), function() {

								}
							});
					$(".popupContainerGetTransactions").bind("click",
							function(e) {
								if (e.target == this) {
									$(this).fadeOut();

								}
							})

				});
