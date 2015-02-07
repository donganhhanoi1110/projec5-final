package csc.fresher.java.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.csc.fresher.java.dao.SavingAccountDAO;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/test-spring-servlet.xml" })
public class SavingAccountDAOTest {

	@Autowired
	SavingAccountDAO savingAccountDAO;

	SavingAccount savingAccount = new SavingAccount();

	@Test
	public void getSavingAccountByNumber() {
		Integer savingaccount_number = 123568;
		SavingAccount savingAccount = savingAccountDAO
				.getSavingAccountByNumber(savingaccount_number);
		Assert.assertEquals(savingaccount_number,
				savingAccount.getSavingAccountNumber());

		/*
		 * Integer savingaccount_number2 = 2377749 ; SavingAccount
		 * savingAccount2=
		 * savingAccountDAO.getSavingAccountByNumber(savingaccount_number);
		 * Assert.assertEquals(savingaccount_number2,
		 * savingAccount2.getSavingAccountNumber());
		 */
	}

	@Test
	public void getSavingAccountByState() {
		String state = "active";
		List<SavingAccount> list = savingAccountDAO
				.getSavingAccountByState(state);
		for (SavingAccount sc : list) {
			Assert.assertEquals(state, sc.getState());

		}
	}

}
