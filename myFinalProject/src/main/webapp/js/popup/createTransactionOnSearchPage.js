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
					$(document)
							.on(
									"click",
									"#addTransactionJsonOnSearchPage",
									function(e) {
										e.preventDefault();
										var mydata = $(
												"#formCreateTransactionId")
												.serialize();
										var confirmMessage = confirm("Do you want to Add this Transaction?");
										if (confirmMessage) {

											$
													.ajax({
														type : 'post',
														url : 'createTransactionJson',
														data : mydata,
														datatype : 'json',
														success : function(data) {
															console.log(data);
															if (data.login == true) {
																if (data.success == true) {
																	alert("Create successfully! \n"
																			+ data.message);
																	if (data.error_code == '0') {
																		window.location.href = 'searchSavingAccount?ERROR_CODE='
																				+ data.error_code;
																	} else {

																		$(
																				".popupContainerCreateTransaction")
																				.hide();
																	}

																} else {
																	alert("Create Failed"
																			+ data.message);
																}
															} else {
																window.location.href = 'login';
															}
														},
														error : function(a, b,
																c) {
															alert(a.responseText);
															console.log(a);
														}
													});

										} else {
											// do nothing
										}
									});

					/* For Popup only */
					$(".withDraw").bind("click", function(e) {
						e.preventDefault();
						$(".popupContainer").show();
					})

					$(".popupCloseButton").bind("click", function(e) {
						$(".popupContainer").hide();
					})
					$(".popupContainer").bind("click", function(e) {
						if (e.target == this) {
							$(this).hide();
						}
					})
					$(".popupContainerCreateTransaction").bind("click",
							function(e) {
								if (e.target == this) {
									$(this).hide();
								}
							});
					$(".createTransaction")
							.bind(
									"click",

									function(e) {
										var thisElement = $(this);
										e.preventDefault();
										$(".popupContainerCreateTransaction")
												.fadeIn(
														'slow',
														function() {
															var savingAccountNumber = thisElement
																	.attr("savingaccountnumber");

															$
																	.ajax({
																		type : 'post',
																		url : 'getSavingAccountInfoJson',
																		data : "savingAccountNumber="
																				+ savingAccountNumber,
																		datatype : 'json',
																		success : function(
																				savingAccount) {

																			/* $('#formCreateTransactionId').trigger("reset"); */

																			var table = $(
																					'#tableShowSavingAccInfo')
																					.DataTable(
																							{
																								retrieve : true,
																								paging : false
																							});
																			table
																					.clear();
																			table.row
																					.add(
																							[
																									savingAccount.savingAccountNumber,
																									savingAccount.customerId.lastName
																											+ '&nbsp;'
																											+ savingAccount.customerId.midName
																											+ '&nbsp;'
																											+ savingAccount.customerId.firstName,
																									savingAccount.balanceAmount,
																									savingAccount.repeatable,
																									savingAccount.interestRateId.savingAccountType
																											+ '&nbsp;'
																											+ savingAccount.interestRateId.interestRate
																											+ '%&nbsp;'
																											+ savingAccount.interestRateId.currency,
																									savingAccount.state,
																									savingAccount.dateStart,
																									savingAccount.dateEnd ])
																					.draw();
																			$(
																					"#savingAccountId")
																					.val(
																							'');

																			$(
																					"#savingAccountId")
																					.val(
																							savingAccount.savingAccountNumber);

																		},
																		error : function(
																				a,
																				b,
																				c) {
																			alert(a.responseText);
																			console
																					.log(a);
																		}

																	});
														});
									});

					$(".popupCloseButton").bind("click", function(e) {
						$(".popupContainerCreateTransaction").hide();
					});
				});