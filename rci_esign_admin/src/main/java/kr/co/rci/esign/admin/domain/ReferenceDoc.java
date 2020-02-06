package kr.co.rci.esign.admin.domain;

import lombok.NonNull;

/**
 * 참조 서류
 * @author Eddie
 * @version 1.0.0
 */
public class ReferenceDoc extends RsmContract {
	private static final long serialVersionUID = 1L;

	/** 첨부파일 아이디 (`SAL_CONTRACT`테이블의 `REFERENCE_ID##`컬럼의 값과 매칭) */
	private String fileId;

	/** 증빙 자료 레퍼런스 번호 */
	private String referenceIdNo;
	/** 증빙 자료 레퍼런스 타입 정보 */
	private ReferenceDoc.TYPE referenceType;

	/** 증빙 자료 레퍼런스 아이디 */
	private String referenceId;

	/** 파일 물리 정보 */
	private ComFileAttach comFileAttach;

	// CUSTOM Getter / Setter
	public void setReferenceIdNo( @NonNull String referenceIdNo) {
		this.referenceIdNo = referenceIdNo;
		this.referenceType = ReferenceDoc.TYPE.getByReferenceIdNo(referenceIdNo);		// TYPE 세팅
	}

	/**
	 * 서류 등록 리스트에 보여줄 증빙자료 목록
	 *  - 배열 순서대로 '서류등록' 목록에 표현됩니다.
	 */
	public static String[] REFDOC_ID_NO_LIST = {
			TYPE.PRIVACY_POLICY.refIdNo,
			TYPE.CONTRACT.refIdNo,
			TYPE.RENT_LEASE.refIdNo,
			TYPE.IDENTIFICATION.refIdNo,
			TYPE.HANDICAPPED.refIdNo,
			TYPE.BUSINESS_LICENSE.refIdNo,
			TYPE.HIPASS.refIdNo,
			TYPE.POINT_CARD.refIdNo,
			TYPE.TAX.refIdNo,
			TYPE.RECEIPT_CARD.refIdNo,
			TYPE.SETTLEMENT_REGIST.refIdNo,
			TYPE.RECEIPT_REGIST.refIdNo,
			TYPE.RECEIPT_REMITTANCE.refIdNo,
			TYPE.VEHICLE_REGIST.refIdNo,
			TYPE.ATTORNEY.refIdNo,
			TYPE.STAMP_CERT.refIdNo,
			TYPE.TAKEOVER.refIdNo,
			TYPE.RETURN.refIdNo,
			TYPE.CONTRACT_CANCEL.refIdNo,
			TYPE.ETC.refIdNo
	};

	// 증빙자료 이름
	public enum TYPE {
		PRIVACY_POLICY		( "01", "개인정보 활용동의서"		, true  ),
		CONTRACT				( "02", "계약서"				, true  ),
		RENT_LEASE			( "03", "리스/렌트 계약서"		, false ),
		IDENTIFICATION		( "04", "신분증(법인등기부등본)"	, false ),
		HANDICAPPED			( "05", "장애인 증명서"			, false ),
		BUSINESS_LICENSE		( "06", "사업자등록증 사본"		, false ),
		HIPASS				( "07", "하이패스 가입신청서"		, false ),
		POINT_CARD			( "08", "선포인트 신청서"		, false ),
		TAX					( "09", "면세 신고서"			, true ),
		RECEIPT_CARD			( "10", "입금증(카드결제)"		, false ),
		SETTLEMENT_REGIST	( "11", "등록비용정산서"			, false ),
		RECEIPT_REGIST		( "12", "등록비용 영수증(공채)"	, false ),
		RECEIPT_REMITTANCE	( "13", "정산차액 송금영수증"		, false ),
		VEHICLE_REGIST		( "14", "차량등록증(등록원부)"	, false ),
		ATTORNEY				( "15", "위임장"				, false ),
		STAMP_CERT			( "16", "인감증명서"			, false ),
		TAKEOVER				( "17", "인수증"				, false ),
		RETURN				( "18", "반품신청서"			, false ),
		CONTRACT_CANCEL		( "19", "계약철회통지서"			, true  ),
		ETC					( "20", "기타"				, false )
		;
		/** 참조파일 구분번호 */
		final private String		refIdNo;
		/** 참조파일 명칭 */
		final private String		name;
		/** 서명 기능 사용 여부 */
		final private boolean	signable;

		private TYPE( String refIdNo, String name, boolean signable ) {
			this.refIdNo		= refIdNo;
			this.name		= name;
			this.signable	= signable;
		}
		public String getName() {
			return name;
		}
		public String getRefIdNo() {
			return refIdNo;
		}
		public boolean isSignable() {
			return signable;
		}

		public static TYPE getByReferenceIdNo(String refIdNo) {
			for(TYPE e : values()) {
				if(e.refIdNo.equals(refIdNo)) return e;
			}
			return null;
		}
	}
}
