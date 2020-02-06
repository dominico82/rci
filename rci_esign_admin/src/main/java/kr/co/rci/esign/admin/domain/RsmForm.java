package kr.co.rci.esign.admin.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * RSM 전자 계약서 최상위 객체
 * @author Eddie
 * @version 1.0.0
 */

@SuppressWarnings("serial")
@Getter @Setter
public abstract class RsmForm extends ComBean {
	/** 견적 번호 */
	private String quotationNo;
	/** 담당 DSCA */
	private BestUser salesMan;

	/**
	 * <pre>
	 * {@code true} : 전자계약
	 * {@code false} : 지류계약
	 * </pre>
	 * @return 전자계약 여부
	 */
	public abstract boolean isElectronic();
	/**
	 * <pre>
	 * {@code true} : 전자계약
	 * {@code false} : 지류계약
	 * </pre>
	 * @param electronic 전자계약 여부
	 */
	public void setElectronic(boolean electronic) {};
}
