<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

			<div id="container">
				<section>
					<!-- pageHead.jsp -->
					<%@ include file="/WEB-INF/template/common/pageHead.jsp"%>
					<!-- // pageHead.jsp -->
					<article>
						<div class="comLayout">
							<!-- navigator.jsp -->
							<%@ include file="/WEB-INF/template/common/navigator.jsp"%>
							<!-- // navigator.jsp -->
							<form id="regForm">
							<input type="hidden" id="adminEmail" name="adminEmail"/>
							<div class="contents">
								<!-- databox-line -->
								<div class="databox-line mt10">
									<div class="rsws1">
										<dl>
											<dt class="title">이름 <sup>필수항목</sup></dt>
											<dd>
												<input type="text" id="realName" name="realName" value="${managerInfo.realName}" />
											</dd>
										</dl>
										<dl>
											<dt class="title">아이디 <sup>필수항목</sup></dt>
											<dd>
												<input type="text" id="adminId" name="adminId" value="${managerInfo.adminId}" readonly="readonly"/>
												<em class="ftRed ml10 ft12">아이디는 변경 불가능 합니다.</em>
											</dd>
										</dl>
										<c:if test="${sessionScope.roleTpCd eq '01' or sessionScope.loginId eq managerInfo.adminId}">
										<dl>
											<dt class="title">비밀번호 <sup>필수항목</sup></dt>
											<dd>
												<button class="btnL dBlue w150 btnPw" id="btnPwdChange">비밀번호 변경</button> <!-- 수정일 경우 -->
												<input autocomplete="new-password" type="password" class="pwchange" style="display:none;" id="password" name="password" /><!-- 비밀번호 입력창 -->
												<button class="btnL black ml10 pwchange" style="display:none;" id="btnPwdChangeCancel">취소</button><!-- 비밀번호 취소 -->
											</dd>
										</dl>
										</c:if>
										<dl>
											<dt class="title">이메일</dt>
											<dd>
												<span class="mailSet">
													<span><input type="text" id="adminEmail1" name="adminEmail1" class="wFull" value="${(empty managerInfo.adminEmail) ? '' : fn:split(managerInfo.adminEmail, '@')[0]}"></span>
													<span>@</span>
													<span><input type="text" id="adminEmail2" name="adminEmail2" class="wFull" value="${(empty managerInfo.adminEmail) ? '' : fn:split(managerInfo.adminEmail, '@')[1]}"></span>
													<span>
														<span class="selectSet wFull">
															<strong>직접입력</strong>
															<select class="wFull" id="emailSelect" name="emailSelect">
																<option value="naver.com">naver.com</option>
																<option value="hanmail.net">hanmail.net</option>
																<option value="gmail.com">gamil.com</option>
																<option value="" selected>직접입력</option>
															</select>
														</span>
													</span>
												</span>
											</dd>
										</dl>
										<dl>
											<dt class="title">핸드폰번호</dt>
											<dd>
												<span class="telSet">
													<span>
														<span class="selectSet">
															<strong>
																<c:choose>
																	<c:when test="${managerInfo.adminHp1 eq '010'}">010</c:when>
																	<c:when test="${managerInfo.adminHp1 eq '011'}">011</c:when>
																	<c:when test="${managerInfo.adminHp1 eq '016'}">016</c:when>
																	<c:when test="${managerInfo.adminHp1 eq '017'}">017</c:when>
																</c:choose>
															</strong>
															<select id="adminHp1" name="adminHp1">
																<option value="010" <c:if test="${managerInfo.adminHp1 eq '010'}">selected</c:if>>010</option>
																<option value="011" <c:if test="${managerInfo.adminHp1 eq '011'}">selected</c:if>>011</option>
																<option value="016" <c:if test="${managerInfo.adminHp1 eq '016'}">selected</c:if>>016</option>
																<option value="017" <c:if test="${managerInfo.adminHp1 eq '017'}">selected</c:if>>017</option>
															</select>
														</span>
													</span>
													<span><input type="tel" class="wFull numberType" maxlength="4" id="adminHp2" name="adminHp2" value="${managerInfo.adminHp2}"/></span>
													<span><input type="tel" class="wFull numberType" maxlength="4" id="adminHp3" name="adminHp3" value="${managerInfo.adminHp3}"/></span>
												</span>
											</dd>
										</dl>
										<c:if test="${sessionScope.roleTpCd eq '01'}">
										<dl>
											<dt class="title">권한관리 <sup>필수항목</sup></dt>
											<dd>
												<span class="selectSet">
													<strong>
														<c:choose>
															<c:when test="${managerInfo.roleTpCd eq '01'}">최고관리자</c:when>
															<c:when test="${managerInfo.roleTpCd eq '02'}">운영관리자</c:when>
															<c:when test="${managerInfo.roleTpCd eq '03'}">FS관리자</c:when>
														</c:choose>
													</strong>
													<select id="roleTpCd" name="roleTpCd">
														<option value="01" <c:if test="${managerInfo.roleTpCd eq '01'}">selected</c:if>>최고관리자</option>
														<option value="02" <c:if test="${managerInfo.roleTpCd eq '02'}">selected</c:if>>운영관리자</option>
														<option value="03" <c:if test="${managerInfo.roleTpCd eq '03'}">selected</c:if>>FS관리자</option>
													</select>
												</span>
											</dd>
										</dl>
										</c:if>
									</div>
								</div>
								<!-- //databox-line -->
								<div class="btnBox">
									<div class="tCenter">
										<button class="btnS red" id="btnSave">수정저장</button>
										<button class="btnS blue" id="btnCancel">취소</button>
										<c:if test="${sessionScope.roleTpCd eq '01'}"><button class="btnS blue" id="btnDelete">삭제</button></c:if>
									</div>
								</div>
							</div>
							</form>
							<!-- //contents -->
						</div>
					</article>
					<!---->
				</section>
			</div>
