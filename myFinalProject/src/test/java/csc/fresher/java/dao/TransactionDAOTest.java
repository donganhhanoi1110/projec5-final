package csc.fresher.java.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csc.fresher.java.dao.SavingAccountDAO;
import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/test-spring-servlet.xml" })
public class TransactionDAOTest {
	
	@Autowired
	TransactionDAO transactionDAO;
	@Autowired
	SavingAccountDAO savingAccountDAO;
	
	Transaction tran = new Transaction();
	

	SavingAccount savingAccount = new SavingAccount();
	
	@Test
	public void getAccountbyTranID(){
		Integer savingaccount_number = 123568;
		savingAccount = savingAccountDAO.getSavingAccountByNumber(savingaccount_number);
		tran.setSavingAccountId(savingAccount);
		savingAccount = transactionDAO.getAccountbyTranID(tran);
		Assert.assertEquals(savingaccount_number, savingAccount.getSavingAccountNumber());
	}
	
	@Test
	public void getTransaction(){
		Integer savingaccount_number = 123568;
		Assert.assertEquals(savingaccount_number, transactionDAO.getTransaction(101).getSavingAccountId().getSavingAccountNumber());
	}
	
	@Test
	public void getTransactionByState() {
		String state = "active";
		List<Transaction> list = transactionDAO
				.getTransactionByState(state);
		for (Transaction tran : list) {
			Assert.assertEquals(state, tran.getState());

		}
	}
}
