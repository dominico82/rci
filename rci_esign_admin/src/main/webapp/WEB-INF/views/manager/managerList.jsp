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
									<div class="left">전체 <strong>${managerList.totalElements}</strong>건</div>
									<div class="right">
										<!---->
										<div class="list-search">
											<a id="btnReset" class="btnC blue">초기화</a>
											<span class="selectSet">
												<strong>
													<c:choose>
														<c:when test="${param.roleTpCd eq '01'}">최고관리자</c:when>
														<c:when test="${param.roleTpCd eq '02'}">운영관리자</c:when>
														<c:when test="${param.roleTpCd eq '03'}">FS관리자</c:when>
														<c:otherwise>구분</c:otherwise>
													</c:choose>
												</strong>
												<select id="roleTpCd" name="roleTpCd">
													<option value="">구분</option>
													<option value="01" <c:if test="${param.roleTpCd eq '01'}">selected</c:if>>최고관리자</option>
													<option value="02" <c:if test="${param.roleTpCd eq '02'}">selected</c:if>>운영관리자</option>
													<option value="03" <c:if test="${param.roleTpCd eq '03'}">selected</c:if>>FS관리자</option>
												</select>
											</span>
											<em class="mr5">가입일자</em>
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
										<!-- // -->
<!-- 										<span class="selectSet"> -->
<!-- 											<strong class="ftGray">15</strong> -->
<!-- 											<select> -->
<!-- 												<option>15</option> -->
<!-- 												<option>30</option> -->
<!-- 												<option>50</option> -->
<!-- 												<option>100</option> -->
<!-- 											</select> -->
<!-- 										</span> -->
									</div>
								</div>
								<!--// boardTop -->
								<!--Board List-->
								<div class="rsls1">
									<table cellpadding="0" cellspacing="0" border="0">
										<colgroup>
											<col width="5%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />
											<col width="20%" />
											<col />
											<col />
										</colgroup>
										<thead>
											<tr>
												<th class="w30">
													<span class="checkSet noTxt"><input type="checkbox" name="chkAll" id="chkAll" /><label for="chkAll"><i></i></label></span>
												</th>
												<th>이름</th>
												<th>아이디</th>
												<th>권한</th>
												<th>가입날짜</th>
												<th>최종 로그인 날짜</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${managerList.content}" varStatus="status">
											<tr>
												<td class="w30">
													<span class="checkSet noTxt"><input type="checkbox" name="chkAdminId" value="${item.adminId}" id="chkAdminId${status.index}" /><label for="chkAdminId${status.index}"><i></i></label></span>
												</td>
												<td>${item.realName}</td>
												<td><a href="javascript:evt.goMod('${item.adminId}');">${item.adminId}</a></td>
												<td>${item.roleTpCdNm}</td>
												<td>${item.regDt}</td>
												<td>${item.lastLogin}</td>
											</tr>
											</c:forEach>

											<c:if test="${managerList.totalElements == '0'}">
											<tr><td colspan="6">데이터가 존재하지 않습니다.</td></tr>
											</c:if>
										</tbody>
									</table>
								</div>
								<div class="btnBox">
									<c:if test="${sessionScope.roleTpCd eq '01'}"><button class="btnS red" id="btnDelete">선택삭제</button></c:if>
									<a href="javascript:evt.goReg();" class="btnS blue right" >신규등록</a>
								</div>

								<!--Paginate-->
								<c:if test="${not empty userList and managerList.totalPages > 1}">
								<div class="paginate">
									${ct:pagination(managerList.number, managerList.totalElements, managerList.size, 'goList')}
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
