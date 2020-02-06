package kr.co.rci.esign.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.rci.esign.admin.domain.ContentsBean;
import kr.co.rci.esign.admin.domain.CustomPageable;

public interface ContentsMapper {

	/**
	 * 메인이미지 삭제
	 * @param ContentsBean
	 * @return
	 */
	int deleteMainImageInfo(ContentsBean bean);

	/**
	 * 메인이미지 정보
	 * @param ContentsBean
	 * @return
	 */

	int updateMainImageInfo(ContentsBean bean);

	/**
	 * 메인이미지 정보
	 * @param ContentsBean
	 * @return
	 */
	ContentsBean getMainImageInfo(ContentsBean bean);

	/**
	 * 공지사항 목록 - 조회
	 * @param ContentsBean, CustomPageable
	 * @return
	 */
	List<ContentsBean> getNoticeList(@Param("ctbean") ContentsBean bean, @Param("pageable") CustomPageable pageable);

	/**
	 * 공지사항 목록 - 목록 갯수
	 * @param ContentsBean
	 * @return
	 */
	int getNoticeTotalCount(@Param("ctbean") ContentsBean bean);

	/**
	 * 공지사항 목록 - 등록
	 * @param ContentsBean
	 * @return
	 */
	public void insertBoard(ContentsBean bean) throws Exception;

	/**
	 * 공지사항 목록 - 수정
	 * @param ContentsBean
	 * @return
	 */
	public void updateBoard(ContentsBean bean) throws Exception;

	/**
	 * 공지사항 목록 - 삭제
	 * @param ContentsBean
	 * @return
	 */
	public void deleteBoard(int postNo) throws Exception;


	/**
	 * 공지사항 목록 - 게시물 조회
	 * @param ContentsBean
	 * @return
	 */
	public ContentsBean selectNoticeByNo(ContentsBean bean) throws Exception;

	/**
	 * faq 목록 - 조회
	 * @param ContentsBean, CustomPageable
	 * @return
	 */
	List<ContentsBean> getFaqList(@Param("ctbean") ContentsBean bean, @Param("pageable") CustomPageable pageable);

	/**
	 * faq목록 - 목록 갯수
	 * @param ContentsBean
	 * @return
	 */
	int getFaqTotalCount(@Param("ctbean") ContentsBean bean);

	/**
	 * Guide 목록 - 조회
	 * @param ContentsBean, CustomPageable
	 * @return
	 */
	List<ContentsBean> getGuideList(@Param("ctbean") ContentsBean bean, @Param("pageable") CustomPageable pageable);

	/**
	 * Guide 목록 - 목록 갯수
	 * @param ContentsBean
	 * @return
	 */
	int getGuideTotalCount(@Param("ctbean") ContentsBean bean);

}
