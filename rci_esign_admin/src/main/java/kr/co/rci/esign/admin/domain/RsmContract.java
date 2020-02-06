package kr.co.rci.esign.admin.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * RSM 전자 계약서 최상위 객체
 * @author Eddie
 * @version 1.0.0
 */

@SuppressWarnings("serial")
@Getter
@Setter
public abstract class RsmContract extends ComBean {

	/** 견적 번호 */
	private String quotationNo;
	/** 담당 DSCA */
	private Dsca salesMan;
	/** 전자계약여부 */
	private boolean electronic = true;
	/** 서식 버전  */
	private FormVersion formVersion;

	private String SalesmanId;
}
