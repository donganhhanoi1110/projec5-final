package com.csc.fresher.java.controller;

import java.util.Date;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.joda.time.Days;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import com.csc.fresher.java.service.*;

/**
 * @author onlinetechvision.com
 * @since 17 Sept 2011
 * @version 1.0.0
 *
 */
public class JobScheduler implements ServletContextListener {
	
	public void doJob() {
		
		Date toDay = new Date();
		toDay.setHours(0);
		toDay.setMinutes(0);
		toDay.setSeconds(5);
		
		try {
			
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(ScheduleService.class)
			    .withIdentity("ScheduleService")
			    .build();
			
			// specify the running period of the job
			SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
			simpleTrigger.setStartTime(toDay);
			//simpleTrigger.setStartTime(toDay);
			simpleTrigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
			simpleTrigger.setRepeatInterval(5000);
			simpleTrigger.setName("TestQuartz");
			
	    	
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
	    	sch.start();	    	
	    	sch.scheduleJob(job, simpleTrigger);		
		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		doJob();
	}
	
}
