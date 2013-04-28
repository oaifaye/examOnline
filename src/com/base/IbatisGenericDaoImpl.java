package com.base;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;

/**
 * Dao基类实现类
 * @文件名称: IbatisGenericDaoImpl.java
 * @类路径: com.base
 */
public class IbatisGenericDaoImpl<T extends BaseEntity> extends SqlMapClientDaoSupport implements BaseDao<T> {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static final int BATCH_SIZE = 50;
	protected Class<T> entityClass;
	protected String entityClassName;
	List<T> resultList = null;
	private SqlExecutor sqlExecutor;

	@SuppressWarnings("unchecked")
	public IbatisGenericDaoImpl() {
		try {			
			Object genericClz=getClass().getGenericSuperclass();
			
			if(genericClz instanceof ParameterizedType) {				
				entityClass = (Class<T>) ((ParameterizedType)genericClz).getActualTypeArguments()[0];
				entityClassName = entityClass.getSimpleName();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public SqlExecutor getSqlExecutor() {
		return sqlExecutor;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}

	public Class<T> getEntityName() {
		return entityClass;
	}

	public void setEntityName(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	/**
	 * @描述:分页查询公共模块
	 * @参数:sqlid:映射文件中的ID
	 *       params:查询用的参数
	 *       pageNo:从第几条数据开始
	 *       limit: 每页显示的行数
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String sqlid, Map<String, Object> params, 
			                  int pageNo, int limit) {
		try {
			resultList = getSqlMapClientTemplate().queryForList(
					sqlid, params, pageNo, limit);
			
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findByPage", 
					               new String[]{entityClass.getName()},e);
		}
		return resultList;
	}

	public void insert(T entity){
		Assert.notNull(entity);
		String insertSqlMapId = getEntityClassName() + "." + PRE_INSERT;
		try {
			this.getSqlMapClientTemplate().insert(insertSqlMapId, entity);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.insert", new String[]{entityClass.getName()},e);
		}
		
	}
	
	/**插入后返回插入数据的id*/
	public String insertInfo(T entity){
		Assert.notNull(entity);
		String insertSqlMapId = getEntityClassName() + "." + PRE_INSERT;
		
		String id = null;
		try {
			id = (String) this.getSqlMapClientTemplate().insert(insertSqlMapId, entity);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.insert", new String[]{entityClass.getName()},e);
		}
		return id;
	}
	
	public void insertAll(final Collection<T> collection){
		if (collection == null || collection.isEmpty())
			return;
		try {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// do iBatis Batch operations here
					int i = 0;
					String insertSqlMapId = getEntityClassName() + "." + PRE_INSERT;
					for (T entity : collection) {
						executor.insert(insertSqlMapId, entity);
						if (i++ > 0 && i % BATCH_SIZE == 0) {
							executor.executeBatch();
						}
					}
					executor.executeBatch();
					return null;
				}
			});
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.insertAll", new String[]{entityClass.getName()},e);
		}
	}
	
	public int delete(T entity) {
		String deleteSqlMapId = getEntityClassName() + "." + PRE_DELETE;
		try {
			return this.getSqlMapClientTemplate().delete(deleteSqlMapId,
					entity.getId());
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.delete", new String[] { entityClass.getName(),entity.getId().toString()},e);
		}
	}
	

	public int deleteByPrimaryKey(Integer sid) {
        String deleteSqlMapId = getEntityClassName() + "." + PRE_DELETE;
        try {
                return this.getSqlMapClientTemplate().delete(deleteSqlMapId, sid);
        } catch (DataAccessException e) {
                throw new DaoException("jdbc.error.code.Common.delete", new String[] { entityClass.getName(),sid.toString()},e);
        }
	}
	public void deleteAll(final Collection<T> collection) {
		if (collection == null || collection.isEmpty())
			return;
		try {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// do iBatis Batch operations here
					int i = 0;
					String deleteSqlMapId = getEntityClassName() + "." + PRE_DELETE;
					for (T entity : collection) {
						executor.delete(deleteSqlMapId,
								(entity.getId()));
						if (i++ > 0 && i % BATCH_SIZE == 0) {
							executor.executeBatch();
						}
					}
					executor.executeBatch();
					return null;
				}
			});
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.deleteAll", new String[] { entityClass.getName()},e);
		}
	}
	
	public void update(T entity) {
		String updateSqlMapId = getEntityClassName() + "." + PRE_UPDATE;
		try {
			this.getSqlMapClientTemplate().update(updateSqlMapId, entity);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.update", new String[] { entityClass.getName(),entity.getId().toString()},e);
		}
	}
	
	public void updateAll(final Collection<T> collection) {
		if (collection == null || collection.isEmpty())
			return;
		try {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// do iBatis Batch operations here
					int i = 0;
					String updateSqlMapId = getEntityClassName() + "." + PRE_UPDATE;
					for (T entity : collection) {
						entity.setUpdateDate(new Date());
						executor.update(updateSqlMapId, entity);
						if (i++ > 0 && i % BATCH_SIZE == 0) {
							executor.executeBatch();
							executor.startBatch();
						}
					}
					executor.executeBatch();
					return null;
				}
			});
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.updateAll", new String[] { entityClass.getName()},e);
		}
	}
	@SuppressWarnings("unchecked")
	public T findUniqueByParams(Map<String, Object> params) {
		String findAllSqlMapId = getEntityClassName() + "." + PRE_FIND;
		try {
		    List<T> list= this.getSqlMapClientTemplate().queryForList(
                    findAllSqlMapId, params);
		    if(list==null||list.size()==0){
		        return null;
		    }else if(list.size()==1){
		        return list.get(0);
		    }else{
		        throw new DaoException("jdbc.error.code.Common.findUniqueByParams.notUique", new String[] { entityClass.getName(),params.toString()},null);
		    }
		} catch (DataAccessException e) {
			throw e;
			//throw new DaoException("jdbc.error.code.Common.findUniqueByParams", new String[] { entityClass.getName(),params.toString()},e);
		}
	}
	
	public T findByPrimaryKey(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return findUniqueByParams(params);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByParams(Map<String, Object> params) {
		if(params==null){
			params=new HashMap<String, Object>();
		}
		String findSqlMapId = getEntityClassName() + "." + PRE_FIND;
		try {
			return (List<T>) this.getSqlMapClientTemplate().queryForList(
					findSqlMapId, params);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findByParams", new String[] { entityClass.getName(),params.toString()},e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String sqlid,Map<String, Object> params) {
		try {
			resultList = getSqlMapClientTemplate().queryForList(sqlid,params);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findAll", 
					               new String[]{entityClass.getName()},e);
		}
		return resultList;
	}
	
	public int findRowNum(String sqlid,Map<String, Object> params){
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(sqlid,params);
	}
	
	//procedure=========================================================================
	public String proInsert(T entity){
		Assert.notNull(entity);
		String insertSqlMapId = getEntityClassName() + "." + PRO_INSERT;
		
		String id = null;
		try {
			this.getSqlMapClientTemplate().queryForObject(insertSqlMapId, entity);
			id=entity.getId();
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.proInsert", new String[]{entityClass.getName()},e);
		}
		return id;
	}
	
}
