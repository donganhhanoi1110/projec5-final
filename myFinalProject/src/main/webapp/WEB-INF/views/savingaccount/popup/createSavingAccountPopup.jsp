
				<div class="popupContainer">
					<div class="popup">
						<button class="popupCloseButton">X</button>
						<form:form action="createSavingAccount.html" method="post"
							modelAttribute="savingaccount" class="createSavingForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table class="mytable2">
								<tr>
									<th valign="top"><form:label path="savingAccountNumber">Saving Account Number:</form:label>
									</th>
									<td><form:input readonly="true" path="savingAccountNumber"
											class="textox" id="savingAccountNumber"
											value="${savingAccountNumber }" /></td>
									<th valign="top"><form:label path="balanceAmount">Balance Amount:</form:label>
									</th>
									<td><form:input path="balanceAmount" class="textox" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="dateStart">Date Start:</form:label></th>
									<td><form:input path="dateStart" class="textox datepicker" /></td>
									<th valign="top"><form:label path="dateEnd">Date End:</form:label></th>
									<td><form:input path="dateEnd" class="textox datepicker" /></td>
								</tr>

								<tr>
									<th valign="top"><form:label path="customerId.id">Customer:</form:label></th>
									<td><form:select path="customerId.id" multiple="false"
											class="textox">
											<c:forEach var="customer" items="${customerList}">
												<form:option value="${customer.id}">
													<c:out
														value="${customer.lastName} ${customer.midName} ${customer.firstName}" />
												</form:option>
											</c:forEach>
										</form:select></td>
									<th valign="top"><form:label path="interestRateId.id">Period:</form:label></th>
									<td><form:select path="interestRateId.id" multiple="false"
											class="textox">
											<form:options items="${interestrateList}" itemValue="id"
												itemLabel="savingAccountType" />
										</form:select></td>


								</tr>
								<tr>
									<th valign="top"><form:label path="state">State:</form:label></th>
									<td><form:select path="state" class="textox">
											<form:options items="${states }" />
										</form:select></td>
									<th valign="top"><form:label path="repeatable">Repeatable:</form:label>
									</th>
									<td><form:input path="repeatable" class="textox" /></td>

								</tr>
								<tr>
									<td></td>
									<td><input type="submit" class="myButton" value="Save"
										id="addSavingAccountSubmit" /></td>

								</tr>
							</table>
						</form:form>

						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div>
									<span> Interest Rate Information</span>
								</div>
								<table class=" table table-striped table-bordered table-hover "
									id="popUpTable">
									<thead>
										<tr>
											<th>ID</th>
											<th>Interest Rate Type</th>
											<th>Rate</th>
											<th>Currency</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="interest" items="${interestrateList}">
											<c:if test="${interest.id != null}">
												<tr>
													<td>${interest.id}</td>
													<td>${interest.savingAccountType}</td>
													<td>${interest.interestRate}</td>
													<td>${interest.currency}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
				<!-- End popupContainer -->