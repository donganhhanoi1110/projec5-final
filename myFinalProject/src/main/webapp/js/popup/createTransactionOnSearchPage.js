$(document)
			.ready(
					function() {
						$("#table").DataTable({
							responsive : true
						});
						$("#show").click(function() {
							$("#hide").slideToggle();
						});
						$(document)
								.on(
										"click",
										"#addTransactionJson",
										function(e) {
											e.preventDefault();
											var mydata = $("#transaction")
											.serialize();
											console.log(mydata);
											var confirmMessage = confirm("Do you want to Add this Transaction?");
											if (confirmMessage) {
												console.log("\n"+mydata);
												//to delete Transaction page
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteTransaction?TransactionId=' + TransactionId;
												// if you Transaction ajax
												$
														.ajax({
															type : 'post',
															url : 'createTransactionJson',
															data : mydata,
															datatype : 'json',
															success : function(
																	data) {
																console
																		.log(data);
																if (data.login == true) {
																	if (data.success == true) {
																		alert("Create successfully! <br>"
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
															},	error : function(a, b,
																	c) {
																
																console.log(a);
															}
														});

											} else {
												//do nothing
											}
										});

						/*For Popup only  */
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
								})
						$(".createTransaction").bind("click", function(e) {
							e.preventDefault();
							$(".popupContainerCreateTransaction").show();
						})

						$(".popupCloseButton").bind("click", function(e) {
							$(".popupContainerCreateTransaction").hide();
						})
					});