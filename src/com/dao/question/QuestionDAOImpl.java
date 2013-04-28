package com.dao.question;

import java.io.IOException;
import java.sql.SQLException;
import com.base.IbatisGenericDaoImpl;
import com.model.Question;
import com.model.QuestionMaterial;

public class QuestionDAOImpl extends IbatisGenericDaoImpl<Question> implements
		QuestionDAO{
	QuestionMaterial questionMaterial=new QuestionMaterial();
	public void insertPro() throws IOException,SQLException {
		getSqlMapClientTemplate().queryForObject("QuestionMaterial.pro_insert",questionMaterial);
	}

}
