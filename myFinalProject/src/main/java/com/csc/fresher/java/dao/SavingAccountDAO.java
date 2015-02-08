// saving AccountDAO

package com.csc.fresher.java.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.service.SavingAccountService;

@Repository("savingAccountDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class SavingAccountDAO implements Serializable {

	@PersistenceContext
	public EntityManager entityManager;

	SavingAccountService savingAccSer = new SavingAccountService();

	@Transactional
	public SavingAccount getSavingAccount(int id) {
		SavingAccount SavingAccount = null;
		try {
			SavingAccount = entityManager.find(SavingAccount.class, id);
			if (SavingAccount == null) {
				throw new EntityNotFoundException(
						"Can't find SavingAccount for ID " + id);
			}
			System.out.println(SavingAccount.toString()
					+ "getSavingAccount-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting SavingAccount had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return SavingAccount;

	}

	@Transactional
	public List<SavingAccount> getSavingAccountByState(String state) {
		List<SavingAccount> list = null;
		try {
			TypedQuery<SavingAccount> query = entityManager.createQuery(
					"SELECT s FROM " + SavingAccount.class.getName()
							+ " s where s.state=:state", SavingAccount.class);
			query.setParameter("state", state);
			list = query.getResultList();
			System.out.println("Get All SavingAccounts");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	@Transactional
	public SavingAccount getSavingAccountByNumber(int savingaccount_number) {

		SavingAccount savingAccount = null;
		// new SavingAccount();

		try {
			TypedQuery<SavingAccount> query = entityManager.createQuery(
					"SELECT s FROM " + SavingAccount.class.getName()
							+ " s where s.savingAccountNumber=:number",
					SavingAccount.class);
			query.setParameter("number", savingaccount_number);
			savingAccount = query.getSingleResult();
			System.out.println("Get Saving Account List ByNumber");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List ByNumber");
			e.printStackTrace();
		}
		return savingAccount;
	}

	@Transactional
	public List<SavingAccount> getSavingAccountByCustomerIDNumber(
			String IDNumber) {

		List<SavingAccount> list = null;

		try {
			TypedQuery<SavingAccount> query = entityManager.createQuery(
					"SELECT s FROM " + SavingAccount.class.getName()
							+ " s where s.customerId.idNumber=:number",
					SavingAccount.class);
			query.setParameter("number", IDNumber);
			list = query.getResultList();
			System.out.println("Get Saving Account List ByCustomerIDNumber");
		} catch (Exception e) {
			System.out
					.println("Error get Saving Account List ByCustomerIDNumber");
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	public List<SavingAccount> getListSavingAccountByRepeatable(String repeat) {

		List<SavingAccount> list = null;

		try {

			TypedQuery<SavingAccount> query = entityManager.createQuery(
					"SELECT s FROM " + SavingAccount.class.getName()
							+ " s where s.repeatable=:repeat",
					SavingAccount.class);
			query.setParameter("repeat", repeat);
			list = query.getResultList();
			System.out.println("Get Saving Account List Repeateable");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List Repeateable");
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	public List<SavingAccount> getSavingAccountListbyCustomerId(int id) {

		List<SavingAccount> list = null;

		try {
			TypedQuery<SavingAccount> query = entityManager.createQuery(
					"SELECT s FROM " + SavingAccount.class.getName()
							+ " s where s.customerId.id=:id",
					SavingAccount.class);
			query.setParameter("id", id);

			list = query.getResultList();
			System.out.println("Get Saving Account List CustomerId");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List CustomerId");
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	public List<SavingAccount> getSavingAccountList() {

		List<SavingAccount> list = null;

		try {
			TypedQuery<SavingAccount> query = entityManager.createQuery(
					"SELECT s FROM " + SavingAccount.class.getName() + " s",
					SavingAccount.class);

			list = query.getResultList();
			System.out.println("Get Saving Account List");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List");
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	public boolean createSavingAccount(SavingAccount savingAccount) {
		boolean check = false;

		try {

			// Insert a row to SavingAccount table
			entityManager.persist(savingAccount);
			check = true;

		} catch (Exception e) {
			System.out.println("\nGet Error with Create SavingAccount " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	@Transactional
	public boolean updateSavingAccount(SavingAccount savingAccount) {

		boolean check = false;
		try {

			entityManager.merge(savingAccount);

			check = true;
			System.out.println("SavingAccount " + savingAccount.getId()
					+ "updated");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nUpdate SavingAccount get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	@Transactional
	public boolean deleteSavingAccountById(int savingAccountId) {
		boolean check = false;
		try {
			SavingAccount savingAccount = entityManager.find(
					SavingAccount.class, savingAccountId);
			if (savingAccount == null) {
				check = false;
				throw new EntityNotFoundException(
						"Can't find SavingAccount for ID " + savingAccountId);
			}
			System.out.println(savingAccount.toString());
			if (savingAccount != null) {

				entityManager.remove(savingAccount);
				check = true;
				System.out.println("delete SavingAccount by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete SavingAccount by ID get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	// calculator amount interest rate for after balance and calculator amount
	// interest rate to present
	public String myAfterBalanceAmount() {
		int count=0;
		List<SavingAccount> acc = null;
		try {
			System.out.println("Before Repeat");
			acc = getSavingAccountList();

			for (SavingAccount savingAcc : acc) {
				if (!("0".equals(savingAcc.getState()))) {
					if (savingAcc.getState().equals("active")) {
						int maxRepeat = Integer.parseInt(savingAcc
								.getRepeatable());

						Date date = new Date();
						Date systemDate = savingAccSer
								.convertStringToDateDDmmYYYY(savingAccSer
										.convertDateToStringDDmmYYYY(date));

						Date dateEnd = savingAccSer
								.convertStringToDateDDmmYYYY(savingAcc
										.getDateEnd());

						if (systemDate.getTime() == dateEnd.getTime()) {
							Date newEndDate = DateUtils.addMonths(systemDate,
									savingAcc.getInterestRateId().getMonth());

							float balance = savingAcc.getBalanceAmount();
							float interest = savingAcc.getInterestRateId()
									.getInterestRate();

							int month = savingAcc.getInterestRateId()
									.getMonth();
							int days = Days
									.daysBetween(
											new DateTime(
													savingAccSer
															.convertStringToDate(savingAcc
																	.getDateStart())),
											new DateTime(
													savingAccSer
															.convertStringToDate(savingAcc
																	.getDateEnd())))
									.getDays();
							float amountAll = balance
									+ (balance * ((interest / (100)) / 360) * days);
							savingAcc.setDateStart(savingAccSer
									.convertDateToString(systemDate));
							savingAcc.setDateEnd(savingAccSer
									.convertDateToString(newEndDate));
							savingAcc.setBalanceAmount(amountAll);
							savingAcc.setRepeatable("" + (maxRepeat + 1));
							updateSavingAccount(savingAcc);
							count+=1;
						} else {
							continue;
						}

					}
				}

			}

			System.out.println("Successfully!! "+ count +" Saving Account has been updated. Minh Map!!!");
			
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception");

		}
		return "Fail";
	}

}
