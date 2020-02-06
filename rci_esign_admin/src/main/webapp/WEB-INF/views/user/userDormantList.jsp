<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>


			<!-- 상태변경팝업  -->
			<div id="statCdUpdatePopup" class="dimPop alertPop" style="display:none">
				<div class="popBack">
					<div class="popData">
						<div> 상태변경
							<select id="statCd" name="statCd">
								<option value="01">정상</option>
								<option value="02">휴면</option>
								<option value="03">퇴사</option>
							</select>
						</div>
					</div>
					<div class="btn">
						<button id="btnSave" class="btnC blue">저장</button>
						<button id="btnCancel" class="btnC Red">취소</button>
					</div>
				</div>
			</div>


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
							<div class="contents">
								<!-- boardTop -->
								<div class="boardTop">
									<div class="left">전체 <span>${userList.totalElements}</span>개</div>
									<div class="right">
										<!---->
										<div class="list-search">
											<a id="btnReset" class="btnC blue">초기화</a>
											<em class="mr5">최근 접속일자</em>
											<span class="calArea">
												<span class="calSet"><input type="text" id="schStartDate" name="schStartDate" class="cal" value="${param.schStartDate}"/><button class="btnCal startBtn">달력</button></span>
												 ~
												<span class="calSet"><input type="text" id="schEndDate" name="schEndDate" class="cal" value="${param.schEndDate}"/><button class="btnCal endBtn">달력</button></span>
											</span>
											<div>
												<input type="text" placeholder="검색어를 입력하세요" id="searchWord" name="searchWord" value="${param.searchWord}"/>
												<input type="submit" class="search-icon" value="search" id="btnSearch"/>
											</div>
										</div>
										<!---->
									</div>
								</div>
								<!-- databox-line -->
								<div class="rsls1">
									<table cellpadding="0" cellspacing="0" border="0">
										<colgroup>
											<col width="50" />
											<col width="50" />
											<col width="115" />
											<col width="80" />
											<col width="75" />
											<col width="100" />
											<col width="80" />
											<col width="50" />
											<col width="115" />
											<col width="30" />
										</colgroup>
										<thead>
											<tr>
												<th class="w30">
													<span class="checkSet noTxt"><input type="checkbox" name="chkAll" id="chkAll" /><label for="chkAll"><i></i></label></span>
												</th>
												<th>No</th>
												<th>최근접속일자</th>
												<th>사번</th>
												<th>이름</th>
												<th>휴대전화</th>
												<th>본부</th>
												<th>거점</th>
												<th>가입일자</th>
												<th>상태</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${userList.content}" varStatus="status">
											<tr>
												<td class="w30">
													<span class="checkSet noTxt"><input type="checkbox" name="chkUsrNo" value="${item.usrNo}" id="chkUsrNo${status.index}" /><label for="chkUsrNo${status.index}"><i></i></label></span>
												</td>
												<td>${userList.totalElements - (((userList.number +1)-1) * userList.size + status.index)}</td>
												<td class="ft11">${item.logDt}</td>
												<td>${item.usrNo}</td>
												<td>${item.userNm}</td>
												<td>${item.hp1}-${item.hp2}-${item.hp3}</td>
												<td>${item.deptNm}</td>
												<td>${item.branNm}</td>
												<td class="ft11">${item.regDt}</td>
												<td>${item.statCdNm}</td>
											</tr>
											</c:forEach>

											<c:if test="${userList.totalElements == '0'}">
											<tr><td colspan="10">데이터가 존재하지 않습니다.</td></tr>
											</c:if>

										</tbody>
									</table>
								</div>
								<div class="btnBox">
									<button id="btnDelete" class="btnS red">선택삭제</button>
									<button id="btnUpdate" class="btnS blue right">상태변경</button>
								</div>
								<!--Paginate-->
								<c:if test="${not empty userList and userList.totalPages > 1}">
								<div class="paginate">
									${ct:pagination(userList.number, userList.totalElements, userList.size, 'goList')}
								</div>
								</c:if>
								<!--//-->
							</div>
							<!-- //contents -->
						</div>
					</article>
					<!---->
				</section>
			</div>
