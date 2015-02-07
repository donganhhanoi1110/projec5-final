package com.csc.fresher.java.job;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.csc.fresher.java.dao.SavingAccountDAO;
import com.csc.fresher.java.service.SavingAccountService;


public class RunMeJob extends QuartzJobBean implements Serializable {
	private static final long serialVersionUID = 1L;

	RunMeTask runMeTask;

	public RunMeTask getRunMeTask() {
		return runMeTask;
	}

	public void setRunMeTask(RunMeTask runMeTask) {
		this.runMeTask = runMeTask;
	}

	/*
	 * SavingAccountDAO savingDAO;
	 * 
	 * public SavingAccountDAO getSavingDAO() { return savingDAO; }
	 * 
	 * public void setSavingDAO(SavingAccountDAO savingDAO) { this.savingDAO =
	 * savingDAO; }
	 */

	/*
	 * @Autowired private SavingAccountService savingService;
	 * 
	 * public SavingAccountService getSavingService() { return savingService; }
	 * 
	 * public void setSavingService(SavingAccountService savingService) {
	 * this.savingService = savingService; }
	 */

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		// Process @Autowired injection for the given target object, based on
		// the current web application context.

		System.out.println("Minh1");
		// savingDAO.myAfterBalanceAmount();

		runMeTask.printMe();
		System.out.println("Minh2");
	}

}
