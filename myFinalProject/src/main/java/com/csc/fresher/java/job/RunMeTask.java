package com.csc.fresher.java.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.csc.fresher.java.service.SavingAccountService;

public class RunMeTask {
	/*@Autowired
	SavingAccountService savingService;
*/
	public String printMe() {
		String message="";
		//String message = savingService.myAfterBalanceAmount();

		System.out.println("Quartz Minh~");
		return message;
	}
}
