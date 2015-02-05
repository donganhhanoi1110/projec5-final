package com.csc.fresher.java.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.fresher.java.dao.SavingAccountDAO;



public class ScheduleService implements Job {
	@Autowired
	private SavingAccountService savingAccountService;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		String message=savingAccountService.myAfterBalanceAmount();
		System.out.println(message);
	}

}
