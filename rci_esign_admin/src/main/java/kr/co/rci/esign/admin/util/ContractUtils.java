package kr.co.rci.esign.admin.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.Customer;
import kr.co.rci.esign.admin.domain.ReferenceDoc;
import lombok.NonNull;

/**
 * e-BEST에서 계약서 정보를 처리해주는 Utility Class
 *
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
public class ContractUtils extends CoTopComponent {
	private static final Logger log = LoggerFactory.getLogger( ContractUtils.class );

	/**
	 * 전자 계약일 경우 계약번호 앞에 'e'를 붙여줍니다.
	 * <p> 계약번호의 공통 처리를 하기 위한 유틸입니다. 계약 번호가 없을 경우 {@code null}을 반환합니다.
	 *
	 * @param contract
	 * @return
	 */
//	public static String contractNo(final SalContract contract) {
//		if (contract == null || contract.getContractNo() == null) {
//			return "-";
//		}
//
//		String contractNo = contract.getContractNo();
////		if(contract.isElectronic()) {
////			return "e "+contractNo;
////		} else {
//			return contractNo;
////		}
//	}

	/**
	 * 매수인들의 이름을 정리해서 문자열로 반환합니다.
	 * <pre>
	 * 매수인 1명 => '홍길동 님'
	 * 매수인 2명 => '홍길동 님(이순신 님)'
	 * 매수인 3명 => '홍길동 님 외 2'
	 * 매수인 4명 => '홍길동 님 외 3'
	 * </pre>
	 *
	 * @return 매수인 이름
	 */
//	public static String customerNames(@NonNull final SalContract salContract) {
//		// 매수인 1 이름
//		String customerNames = customerName(salContract.getCustomer1(), "님");
//
//		final int customerCount = getCustomerCount(salContract);
//
//		if (customerCount == 2) {
//			customerNames += " ("+customerName(salContract.getCustomer2(), "님")+")";
//		} else if(customerCount > 2) {
//			customerNames += (" 외 ") + (customerCount - 1) /* + " 명" */;
//		}
//		return customerNames;
//	}

	/**
	 * <p>레퍼런스 번호를 통해, 해당 번호에 맞는 증빙자료 이름을 반환합니다.</p>
	 * <pre>
	 * ContractUtils.documentName("01") = "개인정보 활용동의서"
	 * ContractUtils.documentName("02") = "계약서"
	 * ...
	 * ContractUtils.documentName(null) = ""
	 * ContractUtils.documentName("") = ""
	 * ContractUtils.documentName("ABCD 꽤꽤") = ""
	 * </pre>
	 *
	 * @param referenceIdNo 참조파일
	 * @return
	 */
	public static String documentName(final String referenceIdNo) {
		ReferenceDoc.TYPE ref = null;
		if( StringUtils.isEmpty(referenceIdNo) ) {
			log.warn("'referenceIdNo'가 없어 공백분자를 반환합니다.");
			return "";
		}
		try {
			ref = ReferenceDoc.TYPE.getByReferenceIdNo(referenceIdNo);
		} catch (Exception e) {
			log.error("["+referenceIdNo+"]" + e.getMessage());
			return "undefined";
		}
		return ref.getName();
	}

	/*public static String referenceIdByNo( SalContract contract , final String referenceIdNo ) {
		return contract.getReferenceIdByNo( referenceIdNo );
	}*/

	/**
	 * <p>참조파일 번호를 통해 해당 계약서에서 참조파일 번호에 해당하는 증빙자료의 파일 개수를 반환합니다.</p>
	 * <pre>
	 * ContractUtils.referenceDocCount("01") = 2
	 * ContractUtils.referenceDocCount("02") = 3
	 * ...
	 * ContractUtils.referenceDocCount(null) = 0
	 * ContractUtils.referenceDocCount("") = 0
	 * ContractUtils.referenceDocCount("ABCD 꽤꽤") = 0
	 * </pre>
	 *
	 * @param contract
	 * @param referenceIdNo 참조파일 번호
	 * @return
	 */
//	public static int referenceDocCount( SalContract contract, final String referenceIdNo ) {
//		return contract.getRefDocCountByNo( referenceIdNo );
//	}

	/**
	 * <p>참조파일 번호를 통해 해당 계약서에서 참조파일 번호에 해당하는 증빙자료 수정일자(생성일자)를 반환합니다..</p>
	 * <pre>
	 * ContractUtils.referenceDocCount("01") = 20180613
	 * ContractUtils.referenceDocCount("02") = 20180633
	 * ...
	 * ContractUtils.referenceDocCount(null) = ""
	 * ContractUtils.referenceDocCount("") = ""
	 * ContractUtils.referenceDocCount("ABCD 꽤꽤") = ""
	 * </pre>
	 *
	 * @param contract
	 * @param referenceIdNo 참조파일
	 * @return
	 */
//	public static String referenceModDt( SalContract contract , final String referenceIdNo ) {
//		return contract.getRefDocModDtByNo( referenceIdNo );
//	}

	/**
	 * <p>증빙자료 구분번호를 통해, 해당 번호에 맞는 증빙자료가 전자서명이 가능한 문서인지 확인합니다.</p>
	 *
	 * @param referenceIdNo 증빙자료 구분번호
	 * @return 전자서명 가능한 증빙자료의 경우 {@code true}
	 */
	public static boolean canElectronicSign( @NonNull final String referenceIdNo) {
		ReferenceDoc.TYPE referenceDocType = null;
		try {
			referenceDocType = ReferenceDoc.TYPE.getByReferenceIdNo(referenceIdNo);
		} catch (Exception e) {
			return false;
		}
		return referenceDocType.isSignable();
	}

	/**
	 * <p>증빙자료 구분번호와 진행 상태 코드를 통해, 해당 번호에 맞는 증빙자료가 현재 서명이 가능한지 확인합니다.</p>
	 *
	 * @param referenceIdNo 증빙자료 구분번호
	 * @param progressCode  계약 진행상태 코드
	 * @return 현재 계약상태에서 전자서명 가능한 증빙자료의 경우 {@code true}
	 */
	public static boolean signableDocument( @NonNull final String referenceIdNo, @NonNull final String progressCode, int fileCount) {
//		System.out.println("referenceIdNo : "+referenceIdNo+", progressCode : " + progressCode);
		ReferenceDoc.TYPE referenceDocType = null;
		boolean signable = false;
		try {
			referenceDocType = ReferenceDoc.TYPE.getByReferenceIdNo(referenceIdNo);
			signable = referenceDocType.isSignable(); // 전자서명 가능여부

			// 하이패스 : "40, 42, 44" 단계에서 전자서명 활성화
			if( referenceDocType == ReferenceDoc.TYPE.HIPASS ) {
				String[] signableProgress = {"40", "42", "44", "44D"};
				signable = signable && Arrays.asList(signableProgress).contains(progressCode);
			}

			// 선포인트 : "배정완료" 단계까지("20","22","24") 전자서명 활성화 / 그 이후에는 비활성화
			else if( referenceDocType == ReferenceDoc.TYPE.POINT_CARD ) {
				String[] signableProgress = {"20", "22", "24"};
				signable = signable && Arrays.asList(signableProgress).contains(progressCode);
			}
			// 계약단계 별 상관없이 항상 Open
			// 입금증 : 21,24,25,28,30,40
//			else if( referenceDocType == ReferenceDoc.TYPE.RECEIPT_CARD ) {
//				String[] signableProgress = {"21","24","25","28","30","40"};
//				signable = signable && Arrays.asList(signableProgress).contains(progressCode);
//			}

			// 인수증 : "40, 42, 44" 단계에서 전자서명 활성화
			else if( referenceDocType == ReferenceDoc.TYPE.TAKEOVER ) {
				String[] signableProgress = {"40", "42", "44", "44D"};/* 매출, 제작증발행, 차량등록, 고객인도(44D) */
				signable = signable && Arrays.asList(signableProgress).contains(progressCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return signable;
/*
(2018-08-21)
 - 하이패스 : "40, 42, 44" 단계에서 전자서명 활성화
 - 선포인트 : "배정완료" 단계까지("20","22","24") 전자서명 활성화 / 그 이후에는 비활성화
 - 면세신고서 : 전자계약 견적서 작성시 면세일 경우에만 전자서명 활성화
 - 입금증 : 계약단계 별 상관없이 항상 Open
 - 인수증 : "40, 42, 44" 단계에서 전자서명 활성화
 - 반품신고서 : 계약단계 별 상관없이 항상 Open
 - 계약철회통지서 : 계약단계 별 상관없이 항상 Open

(2018-08-27)
하이패스, 선포인트, 입금증은 촬영도 같이 활성화 되어야 함.

(2018-08-27)
서류등록의 전자서명
ㄴ지류 와 전자 구분필요 항목
ㄴ 1. 개인정보제공동의
ㄴ 2. 계약서
ㄴ 3. 인수증
ㄴ구분필요 항목을 제외한 모든 서류등록은 "전자서명"과 "촬영" 병행 가능
- 하이패스 : "40, 42, 44" 단계에서 전자서명 활성화
- 선포인트 : "배정완료" 단계까지("20","22","24") 전자서명 활성화 / 그 이후에는 비활성화
- 면세신고서 : 전자계약 견적서 작성시 면세일 경우에만 전자서명 활성화
- 입금증 : 계약단계 별 상관없이 항상 Open
ㄴ 전자서명할 입금증이 없을 경우 '서명' 비활성화
ㄴ 전자서명이 완료되었을 경우 '서명' 비활성화
ㄴ 촬영 버튼은 항상 활성화
- 인수증 : "40, 42, 44" 단계에서 전자서명 활성화
- 반품신고서 : 계약단계 별 상관없이 항상 Open
- 계약철회통지서 : 계약단계 별 상관없이 항상 Open

(2018-08-28)
* 지류이며, 법인일 경우에 카드 입금증 서명을 열것인가 아닌가 확인이 필요
ㄴ 종이 계약으로 입력 된 계약건 중
ㄴ 고객 분류 중 법인 계약 : 21,24,25,28,30,40 일 경우
ㄴ 서류 등록 서명 기능 닫기 : 카드입금증, 면세 반출 신고서, 반품확인서, 계약철회 신청서,하이패스 신청서


 */
	}

	/**
	 * <p>계약서의 진행상태를 문자열로 반환</p>
	 *
	 * <p>Note: 계약서의 진행상태는 {@link SalContract}객체의 프로퍼티인 '{@code progressCode}'와 '{@code eprocessFlag}'에 의해 결정됨
	 * <br>두 코드를 비교하여 계약서 상태를 표현함
	 * </p>
	 * <pre>
	 *    - {@code eprocessFlag}가 있을 경우 {@code progreeCode}와 상관 없이 우선 출력됨
	 *      ㄴ contract.eprocessFlag = 'P'  --> "작성중"
	 *      ㄴ contract.eprocessFlag = 'F'  --> contract.progressCode ( 전송 완료일 경우 processCode에 의해 출력됨 )
	 * </pre>
	 * <pre>
	 *    - {@code eprocessFlag}가 없을 경우 {@code progressCode}에 의해 결정됨
	 * </pre>
	 * @param contract
	 * @return
	 */
	/*public static String progressName(@NonNull SalContract contract) {
		final String eprocessFlag = contract.getEprocessFlag();
		// eprocessFlag가 있을 경우
		if( eprocessFlag.equalsIgnoreCase("P") ) {
			return "작성중";
		}

		// eprocessFlag가 없을 경우 progressCode에 의해 결정
		else if( !eprocessFlag.equalsIgnoreCase("P") && ( contract.getProgress() != null || !StringUtils.isEmpty(contract.getProgressName())) ) {
			if( contract.getProgress() != null ) {
				return contract.getProgress().getDiscript1();
			}
			else {
				return contract.getProgressName();
			}
		}

		else {
			throw new NullPointerException("계약서의 상태를 표시할 수 있는 프로퍼티가 존재하지 않습니다. - 'eprocessFlag' or 'progressCode'");
		}
	}*/

	/**
	 * 매수인의 이름을 정리해서 문자열로 반환합니다.
	 * <pre>
	 * 자연인	(custType = "01") => '홍길동 님'
	 * 사업자	(custType = "02") => '에디네전자개발구락부 / 홍길동 님'
	 * 법인	(custType = "03") => '씽크트리 / 홍길동 님'
	 * </pre>
	 *
	 * @return 매수인 이름
	 * @see #customerName(customer, suffix)
	 */
	public static final String customerName( @NonNull Customer customer ) {
		return customerName( customer, "" );
	}

	/**
	 * 매수인의 이름을 정리해서 문자열로 반환합니다.
	 * <pre>
	 * 자연인	(custType = "01") => '홍길동 님'
	 * 사업자	(custType = "02") => '에디네전자개발구락부 / 홍길동 님'
	 * 법인	(custType = "03") => '씽크트리 / 홍길동 님'
	 * </pre>
	 *
	 * @return 매수인 이름
	 * @see #customerName(Customer)
	 */
	public static final String customerName( @NonNull Customer customer, String suffix ) {
		if(suffix==null) suffix="";

		if(customer == null) return "";

		if(StringUtils.isEmpty(customer.getCustType())) {
			throw new NullPointerException("'custType'은 필수 입니다.");
		}
		String customerNm = customer.getCustName();
		if(customer.isPerson()) {
			customerNm+=(" "+suffix);
		} else {
			customerNm+=" / " + customer.getCorprepName()+(" "+suffix);
		}
		return customerNm;
	}

	/**
	 * 매수인 수
	 * @return
	 */
//	private static final int getCustomerCount( @NonNull SalContract contract ) {
//		int count = 1;
//		if(contract.getCustomer2() != null) count++;
//		if(contract.getCustomer3() != null) count++;
//		if(contract.getCustomer4() != null) count++;
//		return count;
//	}

	public static final String getPdfRefPath(String s) {
		if(s != null) {
			s = s.replaceFirst(appEnv.getProperty("rsm.default.directory"), "");
		}
		return s;
	}
}
