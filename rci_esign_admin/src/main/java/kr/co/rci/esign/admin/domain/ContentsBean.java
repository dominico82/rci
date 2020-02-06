package kr.co.rci.esign.admin.domain;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ContentsBean extends ComBean{


	/** 메인이미지 */
	private String moImgFileNo;
	private String pcImgFileNo;
	private FileInfo moImgFileInfo;
	private FileInfo pcImgFileInfo;

	private String regNo;
	private String modNo;


	/** 공지사항 */
	private String postNo;			//글번호NN
	private String pPostNo;			//상위글번호
	private String title;			//제목
	private String cont;			//내용
	private String authKindCd;		//작성자구분코드
	private String auth;			//작성자
	private String passwd;			//비밀번호
	private String ip;				//IP
	private String viewCnt;			//조회수
	private String likeCnt;			//종아요수
	private String shareCnt;		//공유수
	private String isNoti;			//공지여부NN
	private String acceLevCd;		//접근레벨코드
	private String ord;				//순서
	private String statCd;			//상태코드
	private String regDt;			//등록일시
	private String regUsrNo;		//등록자번호
	private String modDt;			//수정일시
	private String modUsrNo;		//수정자번호
	private String isUse;			//사용여부NN
	private String BbsCd;			//게시판종류코드NN
	private String startDate;		//팝업시작일
	private String startHh;			//팝업시작시
	private String startMi;			//팝업시작분
	private String endDate;			//팝업종료일
	private String endHh;			//팝업종료시간
	private String endMi;			//팝업종료분
	private String popupUseYn;		//팝업노출여부
}
