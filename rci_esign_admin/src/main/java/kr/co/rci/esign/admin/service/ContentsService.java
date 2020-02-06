package kr.co.rci.esign.admin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import kr.co.rci.esign.admin.dao.ContentsMapper;
import kr.co.rci.esign.admin.dao.FileMapper;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.ContentsBean;
import kr.co.rci.esign.admin.domain.CustomPageable;

@Service
public class ContentsService extends CoTopComponent{

	@Autowired
	ContentsMapper contentsMapper;

	@Autowired
	FileMapper fileMapper;

	/**
	 *
	 * 메인이미지 저장
	 *
	 * @param
	 * @return
	 */
	public int updateMainImageInfo(ContentsBean bean) {
		contentsMapper.deleteMainImageInfo(bean);
		int result = contentsMapper.updateMainImageInfo(bean);
		return result;
	}

	/**
	 *
	 * 메인이미지 조회
	 *
	 * @param
	 * @return
	 */
	public ContentsBean getMainImageInfo(ContentsBean bean) {
		ContentsBean result = new ContentsBean();
		result = contentsMapper.getMainImageInfo(bean);

		if(result != null){
			if(StringUtils.isNotEmpty(result.getMoImgFileNo())){
				result.setMoImgFileInfo(fileMapper.getFileInfo(result.getMoImgFileNo()));
			}
			if(StringUtils.isNotEmpty(result.getPcImgFileNo())){
				result.setPcImgFileInfo(fileMapper.getFileInfo(result.getPcImgFileNo()));
			}
		}

		return result;
	}


	/**
	 *
	 * 메인이미지 조회
	 *
	 * @param
	 * @return
	 */
	public Page<ContentsBean> getNoticeList(ContentsBean bean, CustomPageable pageable){

		int total = contentsMapper.getNoticeTotalCount(bean);
		List<ContentsBean> result = contentsMapper.getNoticeList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	};


	/**
	 *
	 * 게시판 입력
	 *
	 * @param
	 * @return
	 */
	public void insertBoard(ContentsBean bean) throws Exception {
		contentsMapper.insertBoard(bean);
	}


	/**
	 *
	 * 게시판 수정
	 *
	 * @param
	 * @return
	 */
	public void updateBoard(ContentsBean bean) throws Exception {
		contentsMapper.updateBoard(bean);
	}

	/**
	 *
	 * 게시판 삭제
	 *
	 * @param
	 * @return
	 */
	public void deleteBoard(int postNo) throws Exception {
		contentsMapper.deleteBoard(postNo);
	}

	/**
	 *
	 * 게시물 조회
	 *
	 * @param
	 * @return
	 */
	public ContentsBean selectNoticeByNo(ContentsBean bean) throws Exception {
		ContentsBean result = contentsMapper.selectNoticeByNo(bean);
		return result;
	}

	/**
	 *
	 * faq 목록 조회
	 *
	 * @param
	 * @return
	 */
	public Page<ContentsBean> getFaqList(ContentsBean bean, CustomPageable pageable){

		int total = contentsMapper.getFaqTotalCount(bean);
		List<ContentsBean> result = contentsMapper.getFaqList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	};


	/**
	 *
	 * Guide 목록 조회
	 *
	 * @param
	 * @return
	 */
	public Page<ContentsBean> getGuideList(ContentsBean bean, CustomPageable pageable){

		int total = contentsMapper.getGuideTotalCount(bean);
		List<ContentsBean> result = contentsMapper.getGuideList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	};


}
