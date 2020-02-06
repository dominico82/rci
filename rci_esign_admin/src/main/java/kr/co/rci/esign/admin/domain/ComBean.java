package kr.co.rci.esign.admin.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 프로젝트 DTO의 최상위 Object
 *  - 모든 Object에서 공통으로 사용하는 Property나 Method는 여기에 위치합니다.
 * @author Eddie Cho
 * @version 1.0.0
 */
@SuppressWarnings("serial")
@Getter @Setter
public abstract class ComBean implements Serializable {

	// [START] BBS Items
	private int     itemPerPage      = 15;    // 한 페이지에 출력할 아이템 기본 개수
	private int     totalItemSize    = 0;    // 게시물 아이템 전체 개수
	private int     currPage         = 1;    // 현재 페이지
	private int     startIndex       = 0;    // 아이템 시작 인덱스

	private int 	totalPages		 = 0;	 // 페이지 수

	private int     pagePerBlock     = 5;    // 페이지 버튼블록 개수
	private int     totalBlockSize   = 0;    // 페이지 버튼블록 전체 개수
	private int     currBlock;               // 몇 번 째 블록인지
	private int     blockStart;              // 시작 블록 숫자
	private int     blockEnd;                // 끝 블록 숫자

	/** 범용 검색조건 : 키워드 */
	private String searchWord;      // 검색어
	private String schStartDate;
	private String schEndDate;
	private String schKeyword;

	private String  createId;        // 등록자
	private String  createTime;      // 등록일자
	private String  modifyId;        // 수정자
	private String  modifyTime;      // 수정일자
	private String  deleteId;        // 삭제자
	private String  deleteTime;      // 삭제일자
	private String  delYn = "N";     // 삭제 여부
	private String useYn;
	// [END] BBS Items

	// Common Result Codes
	private String  resultCode = "99";						// 요청 결과 코드
	private String  resultMessage = "Unexpected error.";		// 요청 결과 메세지

	/** @param 모든 아이템의 개수 */
	public void setTotalItemSize(int totalItemSize) {
		this.totalItemSize = totalItemSize;

		// 몇개 페이지인지 계산
		this.totalBlockSize = totalItemSize/itemPerPage < 1 ? 1 : totalItemSize%itemPerPage==0?totalItemSize/itemPerPage:(totalItemSize/itemPerPage)+1;

		// 아이템 시작 인덱스
		this.startIndex = (currPage-1)*itemPerPage;

		// 전체 블록 개수
		int _totalBlockSize = (totalBlockSize / pagePerBlock);
		if(totalBlockSize != pagePerBlock) _totalBlockSize++;
		this.totalBlockSize = _totalBlockSize;

		// 현재 블록 번호
		int _currBlock = ((currPage-1) / pagePerBlock) + 1;
		this.currBlock = _currBlock;

		// 시작 블록 번호
		int _blockStart = ((_currBlock-1) * pagePerBlock) + 1;
		this.blockStart = _blockStart;

		// 끝 블록 번호
		int _blockEnd = _blockStart+pagePerBlock-1;
		if( _blockEnd > totalBlockSize )
			_blockEnd = totalBlockSize;

		this.blockEnd = _blockEnd;
	}
	/**
	 * @param 결과 코드
	 * @param 결과 메세지
	 */
	public void setResult(String resultCode, String resultmessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultmessage;
	}
	/** 삭제 여부 */
	public boolean isDeleted() {
		return this.delYn.equals("Y");
	}

//	@SuppressWarnings("unchecked")
//	public Map<String,Object> toMap(){
//		Map<String,Object>  converted = new HashMap<String, Object>();
//		String tempJsonStr = new GsonBuilder().create().toJson(this);
//		converted = new Gson().fromJson(tempJsonStr, converted.getClass());
//		return converted;
//	}
}
