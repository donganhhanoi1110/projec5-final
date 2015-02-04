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
																	/*
																	 * var t = $(
																	 * '#table')
																	 * .DataTable();
																	 * var
																	 * transaction =
																	 * $('#transactions')
																	 * t.row.add([
																	 * data.savingAccount.savingAccountNumber,
																	 * data.savingAccount.customerId.lastName +
																	 * '&nbsp;' +
																	 * data.savingAccount.customerId.midName +
																	 * '&nbsp;' +
																	 * data.savingAccount.customerId.firstName,
																	 * data.savingAccount.balanceAmount,
																	 * data.savingAccount.repeatable,
																	 * data.savingAccount.interestRateId.savingAccountType +
																	 * '&nbsp;' +
																	 * data.savingAccount.interestRateId.interestRate + '%
																	 * &nbsp;' +
																	 * '('+data.savingAccount.interestRateId.currency+')',
																	 * data.savingAccount.state,
																	 * data.savingAccount.dateStart,
																	 * data.savingAccount.dateEnd, '<a
																	 * href=' +
																	 * '"viewListTransaction.html?SavingAccountId=${savingAccount.id}"' +
																	 * 'class="myButton"
																	 * id="transactions">Transactions</a>'
																	 * ]).draw();
																	 */
							$(".popupContainerSavingAccount").hide();
					}
				} else {
					alert("Fail!!! "+data.message);
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
						$("#createMySavingAccount").bind("click", function(e) {
							$(".popupContainerSavingAccount").fadeIn("fast", function() {

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
							$(".popupContainerSavingAccount").hide();
						});
						$(".popupContainerSavingAccount").bind("click", function(e) {
							if (e.target == this) {
								$(this).hide();
							}
						})
						/* End Create SavingAccount Json */				
					});