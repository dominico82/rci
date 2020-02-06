<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>


	<!-- container -->
			<div id="container">
				<section>
					<%@ include file="/WEB-INF/template/common/pageHead.jsp"%>
					<!---->
					<article>
						<div class="comLayout">
							<!-- navigator.jsp -->
							<%@ include file="/WEB-INF/template/common/navigator.jsp"%>
							<!-- // navigator.jsp -->
							<div class="contents">
								<!-- boardTop -->
								<div class="boardTop">
									<div class="left">전체 <span>${boardList.totalElements}</span>개</div>
									<div class="right">
										<!---->
										<div class="list-search">
											<span class="calArea">
												<span class="calSet"><input type="text" id="schStartDate" name="schStartDate" class="cal" value="${param.schStartDate}" /><button class="btnCal startBtn">달력</button></span>
												 ~
												<span class="calSet"><input type="text" id="schEndDate" name="schEndDate" class="cal" value="${param.schEndDate}" /><button class="btnCal endBtn">달력</button></span>
											</span>
											<div>
												<input type="text" placeholder="검색어를 입력하세요" id="searchWord" name="searchWord" value="${param.searchWord}" />
												<input type="submit" class="search-icon" value="search" id="btnSearch" />
											</div>
										</div>
										<!---->
									</div>
								</div>
								<!--// boardTop -->
								<!--Board List-->
								<div class="rsls1">
									<table cellpadding="0" cellspacing="0" border="0">
										<colgroup>
											<col width="50" />
											<col />
											<col width="123"/>
											<col width="75" />
											<col width="80"/>
										</colgroup>
										<thead>
											<tr>
												<th>NO</th>
												<th>제목</th>
												<th>작성일자</th>
												<th>작성자</th>
												<th>노출여부</th>
											</tr>
										</thead>

										<tbody>
<c:forEach var="board" items="${boardList.content}" varStatus="status">
<!-- Loop -->
											<tr>
												<td>${board.postNo}</td>
												<td class="subject"><a href="#" onclick='javascript:fn_view(${board.postNo})'>${board.title}</a><img src="${ctxPath}/images/icon_new.png" class="iconNew" alt="N" /></td>
												<td class="ft11">${board.regDt}</td>
												<td>${board.auth}</td>
												<td>
													<span class="exposure mr5">
													<c:if test="${board.isUse==1 }">
														<input type="checkbox" checked>
														<i></i>
													</c:if>
													<c:if test="${board.isUse==0 }">
														<input type="checkbox">
														<i></i>
													</c:if>
													</span>
												</td>
											</tr>
<!-- //Loop -->
</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="btnBox">
									<a href="#" class="btnS blue right" >신규등록</a>
								</div>
								<!--Paginate-->
								<c:if test="${not empty boardList and boardList.totalPages > 1}">
								<div class="paginate">
								${ct:pagination(boardList.number, boardList.totalElements, boardList.size, 'goList')}
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
			<!-- //container -->
