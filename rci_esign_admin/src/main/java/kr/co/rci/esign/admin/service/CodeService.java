package kr.co.rci.esign.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rci.esign.admin.dao.CodeMapper;
import kr.co.rci.esign.admin.domain.CodeBean;


@Service
public class CodeService {

	
	@Autowired
	private CodeMapper codemapper;
	
	
	public List<CodeBean> selectCodeGrp() {
		return codemapper.selectCodeGrp();
	} 
	
	public List<CodeBean> selectCode(CodeBean codebean) {
		return codemapper.selectCode(codebean);
	}
	
	public void insertCodeGrp(CodeBean codebean) {
		codemapper.insertCodeGrp(codebean);
	}
	public void insertCode(List<CodeBean> codebean) {
		codemapper.insertCode(codebean);
	}
	
}
