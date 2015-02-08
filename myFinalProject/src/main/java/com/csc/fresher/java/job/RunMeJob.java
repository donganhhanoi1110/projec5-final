package com.csc.fresher.java.job;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.csc.fresher.java.dao.SavingAccountDAO;

public class RunMeJob extends QuartzJobBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// RunMeTask runMeTask;
	//
	// public RunMeTask getRunMeTask() {
	// return runMeTask;
	// }
	//
	// public void setRunMeTask(RunMeTask runMeTask) {
	// this.runMeTask = runMeTask;
	// }

//	@Autowired
//	SavingAccountDAO savingDAO;

//	public SavingAccountDAO getSavingDAO() {
//		return savingDAO;
//	}
//
//	public void setSavingDAO(SavingAccountDAO savingDAO) {
//		this.savingDAO = savingDAO;
//	}

	/*
	 * @Autowired private SavingAccountService savingService;
	 * 
	 * public SavingAccountService getSavingService() { return savingService; }
	 * 
	 * public void setSavingService(SavingAccountService savingService) {
	 * this.savingService = savingService; }
	 */

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		// Process @Autowired injection for the given target object, based on
		// the current web application context.

		try {
			System.out.println("\n!!*****Quartz-Minh-Before*****!!");
			/*
			 * ApplicationContext appCtx = (ApplicationContext) context
			 * .getScheduler().getContext()
			 * .get("applicationContextSchedulerContextKey"); SavingAccountDAO
			 * savingDAO = (SavingAccountDAO) appCtx
			 * .getBean("savingAccountDAO");
			 */
			
			ApplicationContextProvider appContext = new ApplicationContextProvider();
			SavingAccountDAO savingDAO = appContext.getApplicationContext().getBean("savingAccountDAO", SavingAccountDAO.class);
		
			savingDAO.myAfterBalanceAmount();

			//runMeTask.printMe();
			System.out.println("!!*****Quartz-Minh-Done*****!!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
