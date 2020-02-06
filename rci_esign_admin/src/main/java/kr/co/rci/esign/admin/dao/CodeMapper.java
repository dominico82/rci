package kr.co.rci.esign.admin.dao;

import java.util.List;

import kr.co.rci.esign.admin.domain.CodeBean;

/**
 * CodeMapper dao
 * @author
 * @version 1.0.0
 */
public interface CodeMapper {
	
	public List<CodeBean> selectCodeGrp(); 		
	
	public List<CodeBean> selectCode(CodeBean codebean);
	
	public void insertCodeGrp(CodeBean codebean);
	
	public void insertCode(List<CodeBean> codebean);
}
