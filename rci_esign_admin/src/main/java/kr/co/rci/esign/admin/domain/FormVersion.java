package kr.co.rci.esign.admin.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * 서식 버전
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
@Alias("formVersion")
@Getter @Setter
public class FormVersion extends ComBean {
	public String formIdx           ;    // 서식 버전 인덱스
	public String formType      ;    // 상위 분류 (Mandatory)
	public String formTypeName      ;    // 상위 분류 (Mandatory)
	public String formDocType   ;    // 하위 분류 (Optional)
	public String formDocTypeName;
	public String formBuyerType   ;    // 매수인 분류
	public String formBuyerTypeName   ;    // 매수인 분류
	public String formPublishDt       ;    // 서식 버전
	public String formMemo          ;    // 비고
	public String formFileIdx1       ;    // 서식 파일 인덱스
	public String formFileIdx2       ;    // 서식 파일 인덱스
	public String formFileIdx3       ;    // 서식 파일 인덱스
	public String formFileIdx4       ;    // 서식 파일 인덱스
	public String formFileIdx5       ;    // 서식 파일 인덱스
	public String formFileIdx6       ;    // 서식 파일 인덱스
	public String formFileIdx7       ;    // 서식 파일 인덱스
	public String formFileIdx8       ;    // 서식 파일 인덱스
	public String formFileIdx9       ;    // 서식 파일 인덱스
	public String formFileIdx10       ;    // 서식 파일 인덱스

	public String formTarget;
	public String formKey;
	/** 수정가능여부 */
	public String editableFlag = "N";
	/** 현재 적용중 여부 */
	public String isCurrentForm = "N"	;

	public FileInfo formFileInfo1       ;
	public FileInfo formFileInfo2       ;
	public FileInfo formFileInfo3       ;
	public FileInfo formFileInfo4       ;
	public FileInfo formFileInfo5       ;
	public FileInfo formFileInfo6       ;
	public FileInfo formFileInfo7       ;
	public FileInfo formFileInfo8       ;
	public FileInfo formFileInfo9       ;
	public FileInfo formFileInfo10      ;
	public String cpId;


	private static final long serialVersionUID = 1L;
}
