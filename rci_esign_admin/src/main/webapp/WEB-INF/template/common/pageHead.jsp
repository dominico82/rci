<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<div class="head">
	<div class="back">
		<div class="lBtn">
			<input type="button" value="btn gnb" id="c-button--push-left" class="c-button btnGnbMobile" />
		</div>
		<h2><strong>${currentMenu.name}</strong></h2>
		<div class="rBtn MrBtn">
			<div>
				<span><strong>${sessionScope.realName} (${sessionScope.loginId})</strong></span>
				<span><input type="button" value="HOME" class="btnHome" onclick="location.href='${ct:url('MAIN.INDEX')}' "/></span>
				<span><input type="button" value="로그아웃" class="btnLogout" onclick="location.href='${ct:url('AUTH.LOGOUT')}' "/></span>
				<span><input type="button" value="사이트 바로가기" class="btnSite" /></span>
			</div>
		</div>
	</div>
</div>