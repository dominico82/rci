package kr.co.rci.esign.admin.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * 매수인 정보
 *
 * @author Eddie Cho
 * @version
 */
@Alias("customer")
@Getter @Setter
public class Customer {
	/** 정렬 순서 */
	private int orderSeq;

	/** 등록 타입 ( 1: 주계약자, 2:부계약자 )*/
	private String registerType;
	/** 고객 타입 */
	private String custType;

	/** 고객 번호 */
	private String custNo;
	/** 고객(사업자) 명 */
	private String custName;

	/** 고객 주민등록 번호 */
	private String residentNo;
	/** 실명 인증 일시 */
	private String realnameauthTime;

	/** 사업자 등록 번호 */
	private String bizregisterNo;
	/** 사업자 번호 인증 일시 */
	private String biznoauthTime;

	/** 법인 번호 */
	private String corpNo;
	/** 법인 대표자 명 */
	private String corprepName;

	/** 연락처 */
	private String contactinfo;
	/** 관리지점코드 */
	private String branchCode;

	/** 영업담당자 id */
	private String salesmanId;
	/** 고객확보채널 */
	/** (71)소개, (10)개척, (72)매장활동, (73)기타 */
	private String custChannel;
	/** EMAIL */
	private String email;

	/** EMAIL수신여부 */
	private String emailreceiveFlag;

	/** SMS수신여부 */
	private String smsreceiveFlag;

	/** DM수신여부 */
	private String dmreceiveFlag;

	/** DM수신지코드 */
	private String dmreceiveCode;

	/** 주소구분(R주민등록,C회사,H자택) */
	private String addrType;

	/** 우편번호 */
	private String zipCode;

	/** 주소유형(지번,도로) */
	private String addrFlag;

	/** 주소(검색용) */
	private String addr;

	/** 주소(도로명) */
	private String addrRoad;

	/** 주소상세 */
	private String addrDetail;

	private String basecarclassName;	/** (2018-06-22 석민현) */

	/** 매수인 타입이 개인 */
	public boolean isPerson() {
		return TYPE.PERSON.equals(custType);
	}
	/** 매수인 타입이 법인 */
	public boolean isCorporate() {
		return TYPE.CORPORATE.equals(custType);
	}
	/** 매수인 타입이 개인 사업자 */
	public boolean isBusiness() {
		return TYPE.BUSINESS.equals(custType);
	}

	// TODO: 개발 후 삭제
	@Override
	public String toString() {
		return "Customer [orderSeq=" + orderSeq + ", registerType=" + registerType + ", custType=" + custType
				+ ", custNo=" + custNo + ", custName=" + custName + ", residentNo=" + residentNo + ", realnameauthTime="
				+ realnameauthTime + ", bizregisterNo=" + bizregisterNo + ", biznoauthTime=" + biznoauthTime
				+ ", corpNo=" + corpNo + ", corprepName=" + corprepName + "]";
	}

	// TODO: SELECT * FROM TB_COM_MASTERDATANAME WHERE MASTER_CLASS ='COM0104' AND LANG_CODE = 'KO'; -> 메모리에 올려놓기
	public static class TYPE {
		/** 고객타입 : 개인 */
		public static final String PERSON    = "01";
		/** 고객타입 : 법인 */
		public static final String CORPORATE = "02";
		/** 고객타입 : 개인 사업자 */
		public static final String BUSINESS  = "03";
	}
}