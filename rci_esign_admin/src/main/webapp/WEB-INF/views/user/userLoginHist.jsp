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
											<col width="115" />
											<col width="80" />
											<col width="75" />
											<col width="130" />
											<col width="168" />
											<col width="80" />
											<col width="50" />
										</colgroup>
										<thead>
											<tr>
												<th>No</th>
												<th>최근 접속일자</th>
												<th>사번</th>
												<th>이름</th>
												<th>휴대전화</th>
												<th>접속기기 정보</th>
												<th>본부</th>
												<th>거점</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${userList.content}" varStatus="status">
											<tr>

												<td>${userList.totalElements - (((userList.number +1)-1) * userList.size + status.index)}</td>
												<td class="ft11">${item.logDt}</td>
												<td>${item.usrNo}</td>
												<td>${item.userNm}</td>
												<td>${item.hp1}-${item.hp2}-${item.hp3}</td>
												<td>${item.logDevice }</td>
												<td>${item.deptNm}</td>
												<td>${item.branNm}</td>
											</tr>
											</c:forEach>

											<c:if test="${userList.totalElements == '0'}">
											<tr><td colspan="10">데이터가 존재하지 않습니다.</td></tr>
											</c:if>
										</tbody>
									</table>
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
