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
												<input type="text" id="realName" name="realName"/>
											</dd>
										</dl>
										<dl>
											<dt class="title">아이디 <sup>필수항목</sup></dt>
											<dd>
												<input type="text" id="adminId" name="adminId"/>
												<button class="btnL black ml10" id="btnIdDupCheck">중복확인</button>
												<em class="ftRed ml10 ft12">아이디는 영문 소문자와 숫자 조합 5자 이상이어야  가능합니다.</em>
											</dd>
										</dl>
										<dl>
											<dt class="title">비밀번호 <sup>필수항목</sup></dt>
											<dd>
												<input type="text" readonly="readonly" />
												<em class="ftRed ml10 ft12">초기 비밀번호는 ID와 동일하게 자동 설정됩니다.</em>
											</dd>
										</dl>
										<dl>
											<dt class="title">이메일</dt>
											<dd>
												<span class="mailSet">
													<span><input type="text" id="adminEmail1" name="adminEmail1" class="wFull"></span>
													<span>@</span>
													<span><input type="text" id="adminEmail2" name="adminEmail2" class="wFull"></span>
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
															<strong>010</strong>
															<select id="adminHp1" name="adminHp1">
																<option value="010" >010</option>
																<option value="011" >011</option>
																<option value="016" >016</option>
																<option value="017" >017</option>
															</select>
														</span>
													</span>
													<span><input type="tel" class="wFull numberType" maxlength="4" id="adminHp2" name="adminHp2" /></span>
													<span><input type="tel" class="wFull numberType" maxlength="4" id="adminHp3" name="adminHp3" /></span>
												</span>
											</dd>
										</dl>
										<dl>
											<dt class="title">권한관리 <sup>필수항목</sup></dt>
											<dd>
												<span class="selectSet">
													<strong>최고관리자</strong>
													<select id="roleTpCd" name="roleTpCd">
														<c:if test="${sessionScope.roleTpCd eq '01'}"><option value="01">최고관리자</option></c:if>
														<option value="02">운영관리자</option>
														<option value="03">FS관리자</option>
													</select>
												</span>
											</dd>
										</dl>
									</div>
								</div>
								<!-- //databox-line -->
								<div class="btnBox">
									<div class="tCenter">
										<button class="btnS blue" id="btnSave">등록</button>
										<button class="btnS red" id="btnCancel">취소</button>
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
