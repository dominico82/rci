<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>



			<div id="container">
				<section>
					<div class="head">
						<div class="back">
							<div class="lBtn">
								<input type="button" value="btn gnb" id="c-button--push-left" class="c-button btnGnbMobile" />
							</div>
							<h2><strong>자주 묻는 질문</strong></h2>
							<div class="rBtn MrBtn">
								<div>
									<span><input type="button" value="HOME" class="btnHome" onclick="location.href='main_setting.html' " /></span>
									<span><input type="button" value="로그아웃" class="btnLogout" onclick="location.href='login.html' "/></span>
									<span><input type="button" value="사이트 바로가기" class="btnSite" /></span>
								</div>
							</div>
						</div>
					</div>
					<!---->
					<article>
						<div class="comLayout">
							<div class="root">
								<div class="rootBack">
									<span class="pagePos"><a href="main_setting.html" class="rootTit"></a><a href="code_list.html" class="list">게시판 관리</a><span>공지사항</span></span>
								</div>
							</div>
							<div class="contents">
								<!-- databox-line -->
								<div class="databox-line">
									<div class="rsws1">
										<dl>
											<dt class="title">CODE<sup>필수항목</sup></dt>
											<dd>
												<input type="text" value="${codeListDetail[0].codeId}" name="titleField" id="titleField" class="wFull" placeholder="제목을 입력하세요." />
											</dd>
										</dl>
										<dl>
											<dt class="title">작성일시</dt>
											<dd>2018-11-01 14:35:20</dd>
										</dl>
									</div>
								</div>
								<div class="databox-line mt20">
									<div class="rsws1">
									
										<c:forEach var="codelist" items="${codeListDetail}" varStatus="status">
										<dl>
											<dt class="title tCenter">${codelist.code}</dt>
											<dd>
												<span class="left">${codelist.codeNm}</span>
											</dd>
											<dd class="w50">
												<button class="btnS blue w50">수정</button>
												<button class="btnS red w50">삭제</button>
											</dd>
<!-- 											<dd class="w100"> -->
<!-- 												<button class="btnC black w30">△</button> -->
<!-- 												<button class="btnC black w30">▽</button> -->
<!-- 											</dd>													 -->
										</dl>
										</c:forEach>
									</div>
								</div>	
								<!-- //databox-line -->
								<div class="btnBox">
									<a href="code_list.html" class="btnS red">취소</a> <!-- 신규일 경우 -->
									<a class="btnS black" href="code_list.html">목록</a>
									<div class="right">
										<button id="regi" class="btnS black">추가</button>
										<button id="check" class="btnS blue">확인</button>
									</div>
								</div>
							</div>
							<!-- //contents -->
