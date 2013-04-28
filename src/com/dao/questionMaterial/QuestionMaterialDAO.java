package com.dao.questionMaterial;

import com.base.BaseDao;
import com.model.QuestionMaterial;

public interface QuestionMaterialDAO extends BaseDao<QuestionMaterial> {
	public String proInsert(QuestionMaterial questionMaterial);
}
