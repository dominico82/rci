<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<form id="writeForm" name="writeForm" method="post">
	<div id="container">
				<section>
					<div class="head">
						<div class="back">
							<div class="lBtn">
								<input type="button" value="btn gnb" id="c-button--push-left" class="c-button btnGnbMobile" />
							</div>
							<h2><strong>공지사항</strong></h2>
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
									<span class="pagePos"><a href="main_setting.html" class="rootTit"></a><a href="visual_setting.html" class="list">게시판 관리</a><span>공지사항</span></span>
								</div>
							</div>
							<div class="contents">
								<!-- databox-line -->
								<div class="databox-line">
									<div class="rsws1">
										<dl>
											<dt class="title">타이틀 <sup>필수항목</sup></dt>
											<dd>
												<input type="text" name="titleField" id="titleField" class="wFull" placeholder="제목을 입력하세요." value="" />
											</dd>
										</dl>
										<dl>
											<dt class="title">노출여부</dt>
											<dd>
												<span class="exposure mr10">
													<input type="checkbox" name="exposure1" id="exp1">
													<i></i>
													<label for="exp1">게시글 사용여부</label>
												</span>
												<span class="exposure frMain mr10">
													<input type="checkbox" name="exposure2" id="exp2">
													<i></i>
													<label for="exp2">로그인 전 팝업 노출여부</label>
												</span>
											</dd>
										</dl>
										<dl>
											<dt class="title">공지구분</dt>
											<dd>
												<div>
													<span class="radioSet"><input type="radio" id="a1" name="a" checked /><label for="a1"><i></i>긴급</label></span>
													<span class="radioSet"><input type="radio" id="a2" name="a" /><label for="a2"><i></i>정기</label></span>
													<span class="radioSet"><input type="radio" id="a3" name="a" /><label for="a3"><i></i>알림</label></span>
												</div>
											</dd>
										</dl>
										<dl>
											<dt class="title">팝업기간</dt>
											<dd>
												<span class="calSet"><input type="text" class="cal" value="2018-12-12" /><button class="btnCal">달력</button></span>
												<span class="selectSet">
													<strong>01</strong>
													<select>
														<option>01</option>
														<option>02</option>
														<option>03</option>
														<option>04</option>
														<option>05</option>
														<option>06</option>
														<option>07</option>
														<option>08</option>
														<option>09</option>
														<option>10</option>
														<option>11</option>
														<option>12</option>
														<option>13</option>
														<option>14</option>
														<option>15</option>
														<option>16</option>
														<option>17</option>
														<option>18</option>
														<option>19</option>
														<option>20</option>
														<option>21</option>
														<option>22</option>
														<option>23</option>
														<option>24</option>
													</select>
												</span> 시
												<span class="selectSet">
													<strong>01</strong>
													<select>
														<option>01</option>
														<option>02</option>
														<option>03</option>
													</select>
												</span> 분
												<span class="mr10">~</span>
												<span class="calSet"><input type="text" class="cal" value="2018-12-12" /><button class="btnCal">달력</button></span>
												<span class="selectSet">
													<strong>01</strong>
													<select>
														<option>01</option>
														<option>02</option>
														<option>03</option>
														<option>04</option>
														<option>05</option>
														<option>06</option>
														<option>07</option>
														<option>08</option>
														<option>09</option>
														<option>10</option>
														<option>11</option>
														<option>12</option>
														<option>13</option>
														<option>14</option>
														<option>15</option>
														<option>16</option>
														<option>17</option>
														<option>18</option>
														<option>19</option>
														<option>20</option>
														<option>21</option>
														<option>22</option>
														<option>23</option>
														<option>24</option>
													</select>
												</span> 시
												<span class="selectSet">
													<strong>01</strong>
													<select>
														<option>01</option>
														<option>02</option>
														<option>03</option>
													</select>
												</span> 분
											</dd>
										</dl>
										<dl>
											<dt class="title">내용 <sup>필수항목</sup></dt>
											<dd>
												<textarea id="textField" placeholder="내용을 입력하세요." rows="10" class="wFull">내용을 입력하세요.</textarea>
											</dd>
										</dl>
										<dl>
											<dt>첨부파일</dt>
											<dd>
												<div>
													<span class="fileex_back">
														<input type="file" class="fileex">
														<span class="imgup"><label>FILE SEARCH</label></span>
													</span>
													<span class="imgselected">Filename.pdf (1,000byte)<input type="button" value="삭제" class="imgdelete" /></span>
													<em>※ 파일 권장용량 : max 10MB / 확장자 : pdf, jpg, png</em>
												</div>
												<div class="mt5 mb5">
													<span class="fileex_back">
														<input type="file" class="fileex">
														<span class="imgup"><label>FILE SEARCH</label></span>
													</span>
													<span class="imgselected">Filename.png (1,000byte)<input type="button" value="삭제" class="imgdelete" /></span>
													<em>※ 파일 권장용량 : max 10MB / 확장자 : pdf, jpg, png</em>
												</div>
												<div>
													<span class="fileex_back">
														<input type="file" class="fileex">
														<span class="imgup"><label>FILE SEARCH</label></span>
													</span>
													<span class="imgselected">Filename.jpg (1,000byte)<input type="button" value="삭제" class="imgdelete" /></span>
													<em>※ 파일 권장용량 : max 10MB / 확장자 : pdf, jpg, png</em>
												</div>
											</dd>
										</dl>
									</div>
								</div>
								<!-- //databox-line -->
								<div class="btnBox">
									<a id="btn_form_reset" href="#" class="btnS red">취소</a> <!-- 신규일 경우 -->
									<div class="right">
										<a class="btnS black" href="/contents/noticeList">목록</a>
										<button id="check" class="btnS blue" ">확인</button>
									</div>
								</div>
								<!--//-->
							</div>
							<!-- //contents -->
						</div>
					</article>
					<!---->
				</section>
			</div>
</form>