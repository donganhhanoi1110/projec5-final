package com.csc.fresher.java.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.InterestRateDAO;
import com.csc.fresher.java.domain.InterestRate;
import com.csc.fresher.java.domain.InterestRate;
import com.csc.fresher.java.domain.Transaction;

@Service(value = "interestRateService")
public class InterestRateService {
	@Autowired
	private InterestRateDAO InterestRateDAO;

	public InterestRateDAO getInterestRateDAO() {
		return InterestRateDAO;
	}

	public InterestRate getInterestRate(int id) {
		return this.InterestRateDAO.getInterestRate(id);

	}

	public void setInterestRateDAO(InterestRateDAO InterestRateDAO) {
		this.InterestRateDAO = InterestRateDAO;
	}

	public List<InterestRate> getInterestRateList() {
		return this.InterestRateDAO.getInterestRateList();
	}

	public boolean createInterestRate(InterestRate InterestRate) {
		return this.getInterestRateDAO().createInterestRate(InterestRate);
	}

	public boolean updateInterestRate(InterestRate InterestRate) {
		return this.getInterestRateDAO().updateInterestRate(InterestRate);
	}

}
