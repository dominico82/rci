package kr.co.rci.esign.admin.domain;

import java.io.File;
import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
@Alias("fileInfo")
@Getter @Setter
public class FileInfo extends ComBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 파일 SEQ */
	private String fileNo;
	/** 파일 원본 이름 */
	private String orgFileNm;
	/** 파일 이름 변경 명 */
	private String fileNm;
	/** 파일 경로 */
	private String filePath;
	/** 파일 확장자 */
	private String fileExt;
	/** 파일 사이즈 */
	private long fileSize;
	/** 파일 타입 */
	private String contentType;
	/** 파일 객체 */
	private File fileObject;

	private String refNo;
	private String refKindCd;
	private String subKindCd;
	private String kindCd;
	private String setCd;

}