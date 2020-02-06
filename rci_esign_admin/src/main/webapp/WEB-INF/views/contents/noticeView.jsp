<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>


<form id="regForm">
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
												<input type="text" name="title" id="title" class="wFull" placeholder="제목을 입력하세요." value="${boardList.title}" />
											</dd>
										</dl>
										<dl>
											<dt class="title">노출여부</dt>
											<dd>
												<span class="exposure mr10">
													<c:if test="${boardList.isUse==1}">
													<input type="checkbox" name="exposure1" id="exp1" checked>
													<i></i>
													</c:if>
													<c:if test="${boardList.isUse==0}">
													<input type="checkbox" name="exposure1" id="exp1" >
													<i></i>
													</c:if>
													<label for="exp1">게시글 사용여부</label>
												</span>
												<span class="exposure frMain mr10">
													<c:if test="${boardList.popupUseYn=='Y'}">
													<input type="checkbox" name="exposure2" id="exp2" checked>
													<i></i>
													</c:if>
													<c:if test="${boardList.popupUseYn=='N'}">
													<input type="checkbox" name="exposure2" id="exp2" >
													<i></i>
													</c:if>
													<c:if test="${boardList.popupUseYn==NULL}">
													<input type="checkbox" name="exposure2" id="exp2" >
													<i></i>
													</c:if>
													<label for="exp2">로그인 전 팝업 노출여부</label>
												</span>
											</dd>
										</dl>
										<dl>
											<dt class="title">공지구분</dt>
											<dd>
												<div>
														<span class="radioSet"><input type="radio" id="a1" name="isNoti" value="1" <c:if test='${boardList.isNoti} == "1"'>checked</c:if> /> <label for="a1"><i></i>긴급</label></span>
														<span class="radioSet"><input type="radio" id="a2" name="isNoti" value="0" <c:if test='${boardList.isNoti} == "0"'>checked</c:if> /> <label for="a2"><i></i>정기</label></span>
														<span class="radioSet"><input type="radio" id="a3" name="isNoti" value="2" <c:if test='${boardList.isNoti} == "2"'>checked</c:if> /> <label for="a3"><i></i>알림</label></span>
												</div>
											</dd>
										</dl>
										<dl>
											<dt class="title">팝업기간</dt>
											<dd>
												<fmt:parseDate value="${boardList.startDate}" var="dateFmt" pattern="yyyyMMdd"/>
												<span class="calSet"><input type="text" class="cal" value="<fmt:formatDate value="${dateFmt}" pattern="yyyyMMdd"/>" /><button class="btnCal">달력</button></span>
												<span class="selectSet">
													<strong>${boardList.startHh}</strong>
													<select >
														<option value="01">01</option>
														<option value="02">02</option>
														<option value="03">03</option>
														<option value="04">04</option>
														<option value="05">05</option>
														<option value="06">06</option>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09">09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>

													</select>
												</span> 시
												<span class="selectSet">
													<strong>${boardList.startMi}</strong>
													<select>
													<option value="0">0</option>
													<option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option>
													<option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option>
													<option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option>
													<option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option>
													<option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option>
													<option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option>
													<option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option>
													<option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option>
													<option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option>
													<option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option>
													<option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option>
													<option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option><option value="60">60</option>
													</select>
												</span> 분
												<span class="mr10">~</span>
												<fmt:parseDate value="${boardList.endDate}" var="dateFmt" pattern="yyyyMMdd"/>
												<span class="calSet"><input type="text" class="cal" value="<fmt:formatDate value="${dateFmt}" pattern="yyyyMMdd"/>" /><button class="btnCal">달력</button></span>
												<span class="selectSet">
													<strong>${boardList.endHh}</strong>
													<select >
														<option value="01">01</option>
														<option value="02">02</option>
														<option value="03">03</option>
														<option value="04">04</option>
														<option value="05">05</option>
														<option value="06">06</option>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09">09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>

													</select>
												</span> 시
												<span class="selectSet">
													<strong>${boardList.endMi}</strong>
													<select>
													<option value="0">0</option>
													<option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option>
													<option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option>
													<option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option>
													<option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option>
													<option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option>
													<option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option>
													<option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option>
													<option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option>
													<option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option>
													<option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option>
													<option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option>
													<option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option><option value="60">60</option>
													</select>
												</span> 분
											</dd>
										</dl>
										<dl>
											<dt class="title">내용 <sup>필수항목</sup></dt>
											<dd>
												<textarea id="textField" placeholder="내용을 입력하세요." rows="10" class="wFull">${boardList.cont}</textarea>
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
									<a id="btnlete" class="btnS red" href="/contents/noticeDelete?postNo=${boardList.postNo}">삭제</a> <!-- 수정일 경우 -->
									<div class="right">
										<a class="btnS black" href="/contents/noticeList">목록</a>
										<button id="btnUpdate" class="btnS blue">확인</button>
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