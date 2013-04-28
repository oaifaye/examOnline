package com.service.login;

import java.util.List;
import java.util.Map;

import com.dao.login.ManagerDAO;
import com.model.Manager;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDAO managerDAO;

	public List<Manager> findByParams(Map<String, Object> params) {
		return managerDAO.findByParams(params);
	}

	public ManagerDAO getManagerDAO() {
		return managerDAO;
	}

	public void setManagerDAO(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}

}
