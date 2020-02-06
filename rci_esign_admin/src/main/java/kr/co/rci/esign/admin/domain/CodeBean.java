package kr.co.rci.esign.admin.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CodeBean {
	
	/**CODE_GRP*/
	private String grpId;			// 그룹 ID
	private String kindId;			// 분류 ID
	private String grpNm;			// 그룹명
	private String isSys;			// 시스템코드여부
	private String regDt;			// 등록 일시
	private Integer regUsrNo;		// 등록자 번호
	
	/**CODE_GRP*/
	private String codeId;			// 코드 ID (그룹)
	private String code;			// 코드
	private String codeNm;			// 코드 이름
	private String codeShortNm;		// 코드 이름 (짧게)
	private String codeComment;		// 코드 설명
	private String codeUseYn; 		// 코드 사용유무
	private String codeSub;			// 코드(sub)
	
}
