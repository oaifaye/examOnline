package com.base;

import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * dao 基本基类方法接口
 * @文件名称: BaseDao.java
 * @类路径: common.core
 */
public interface BaseDao<T> {
	public static final String PRE_INSERT = "insert";
	
	public static final String PRO_INSERT = "pro_insert";

	public static final String PRE_UPDATE = "update";

	public static final String PRE_DELETE = "delete";
	
	public static final String PRE_DELETE_BY_PARAMS = "deleteByParams";

	public static final String PRE_FIND = "find";
	
	public static final String PRE_INSERT_HISTORY = "insertHistory";
	
	public static final String PARAMS_KEY_ORDER = "order";
	
	public static final String GLOBAL_FACILITY_KEY="globalFacilityId";
	/**
	 * 分页查询
	 * @param params sqlid
	 * @param params 放置参数的Map对象，key=参数名，value=参数值
	 * @param pageNo 查询页码 
	 * @param limit 每页限定记录数
	 * @return
	 */
	public List<T> findByPage(String sqlid, Map<String, Object> 
	                          params, int pageNo, int limit);

	/**
	 * 创建实体对象
	 * @param entity
	 * @return TODO
	 */
	public void insert(T entity);

	/**创建实体对象
	 * 插入后返回插入数据的id*/
	public String insertInfo(T entity);
	/**
	 * 批量创建实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	public void insertAll(final Collection<T> collection);

	/**
	 * 更新实体对象
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 批量更新实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	public void updateAll(final Collection<T> collection);

	/**
	 * 查询全部记录
	 * @return 记录集合
	 */
	public List<T> findAll(String sqlid,Map<String, Object> params);

	/**
	 * 查询全部记录数量
	 * 为分页查询准备数据。
	 * @param sqlid
	 * @param params
	 * @return
	 */
	public int findRowNum(String sqlid,Map<String, Object> params);
	/**
	 * 删除实体对象
	 * @param entity
	 */
	public int delete(T entity);
	
	/**
	 * 按照Map参数进行条件删除
	 * @param params
	 */
	//public int deleteByParams(Map<String,Object> params);
	
	/**
	 * 根据主键删除实体对象
	 * @param entity
	 */
	public int deleteByPrimaryKey(Integer sid);
	
	/**
	 * 根据主键逻辑删除实体对象
	 * @param entity
	 */
	//public int deleteLogicByPrimaryKey(Long sid);

	/**
	 * 批量删除实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	public void deleteAll(final Collection<T> collection);
	
	/**
	 * 根据主键批量删除实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	//public void deleteAllByPrimaryKey(final Collection<Long> sids);

	/**
	 * 根据主键返回唯一对象
	 * @param sid 序列主键
	 * @return 实体对象
	 */
	public T findByPrimaryKey(String sid);

	/**
	 * 根据主键数组返回一批对象
	 * @param sids 序列主键数组
	 * @return 实体对象链表
	 */
	//public List<T> findByPrimaryKeys(Long[] sids);

	/**
	 * 根据参数返回一个实体对象，一般用于查询具有唯一约束条件的记录
	 * 必须保证传入的参数确实能定位唯一一个对象，否则将iBatis会抛出运行异常
	 * @param params 参数Map对象
	 * @return 实体对象 
	 */
	public T findUniqueByParams(Map<String, Object> params);

	/**
	 * 根据参数返回实体对象集合
	 * @param params 参数Map对象
	 * @return 实体对象链表
	 */
	public List<T> findByParams(Map<String, Object> params);
	public String proInsert(T entity);

}
