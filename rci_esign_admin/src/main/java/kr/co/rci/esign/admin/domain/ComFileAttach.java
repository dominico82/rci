package kr.co.rci.esign.admin.domain;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ComFileAttach {
	private static final long serialVersionUID = 1L;

	private String fileId;       // 첨부파일ID;
	private String fileSeq;      // 파일순서
	private String referenceId;  // 파일첨부작업구분 ID
	private String referenceIdNo; // 파일첨부 작업 구분 번호 (01 ~ 21)
	private String referenceModule; // 파일첨부작업구분 시스템
	private String referenceSubmodule; // 파일첨부작업구분 서브시스템
	transient private String filesavePath; // 실제 파일 저장 경로
	private String filesaveName; // 실제 파일 저장 이름
	private String filerealName; // 원본 파일 실제 이름
	private String fileExtension;// 파일 확장자
	private long   fileSize;     // 파일 크기
	private String delFlag = "N";// 삭제 플래그 (Y: 삭제, N: 기본)

	private File   attachFile;
	private File   originalFile;

	private String eDocType;	// 전자 서식 구분 코드
}
