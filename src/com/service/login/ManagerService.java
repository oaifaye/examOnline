package com.service.login;

import java.util.List;
import java.util.Map;

import com.model.Manager;

public interface ManagerService {
	/**
	 * 根据参数查询管理员信息
	 * @param params 由参数组成的Map
	 * */
	public List<Manager> findByParams(Map<String, Object> params);
}
