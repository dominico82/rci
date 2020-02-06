package kr.co.rci.esign.admin.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class UserBean extends ComBean{
	private String usrNo;
	private String kindCd;
	private String loginId;
	private String pwd;
	private String userNm;
	private String email;
	private String hp1;
	private String hp2;
	private String hp3;
	private String tel;
	private String sexCd;
	private String bornDt;
	private String statCd;
	private String statCdNm;
	private String leaveWhy;
	private String leaveDt;
	private String memo;
	private String regDt;
	private String regUsrNo;
	private String modDt;
	private String modUsrNo;
	private String isUse;
	private String deptCd;
	private String deptNm;
	private String branCd;
	private String branNm;
	private String pwdRst;
	private String pwdRstDt;

	/** 로그정보 */
	private String logNo;
	private String logCd;
	private String logIp;
	private String logUrl;
	private String logReq;
	private String logRes;
	private String logDevice;
	private String logUser;
	private String logDt;

	private List<UserBean> userList;


}
