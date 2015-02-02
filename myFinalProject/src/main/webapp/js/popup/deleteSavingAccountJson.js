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
										"#linkDeleteSavingAccount",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you  to delete this SavingAccount?");
											if (confirmMessage) {
												//to delete SavingAccount page
												var savingAccountId = $(this)
														.attr("SavingAccount");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteSavingAccount?SavingAccountId=' + SavingAccountId;
												// if you SavingAccount ajax
												$
														.ajax({
															type : 'post',
															url : 'deleteSavingAccountJson',
															data : 'savingAccountId='
																	+ savingAccountId,
															datatype : 'json',
															success : function(
																	data) {
																console
																		.log(data);
																if (data.login == true) {
																	if (data.success == true) {
																		alert("Delete successfully!");
																		if (data.error_code == '0') {
																			window.location.href = 'homeSavingAccount?ERROR_CODE='
																					+ data.error_code;
																		} else {
																			window.location.href = 'homeSavingAccount';
																		}

																	} else {
																		alert("Delete Failed");
																	}
																} else {
																	window.location.href = 'login';
																}
															}
														});

											} else {
												//do nothing
											}
										});
						/* End linkDeleteSavingAccount */
						
						
					});