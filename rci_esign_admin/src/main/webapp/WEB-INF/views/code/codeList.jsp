<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
									<div class="left">전체 <span id="totalList"></span>건</div>
									<div class="right">
										<!---->
										<div class="list-search">
											<span class="calArea">
												<span class="calSet"><input type="text" class="cal" value="2018-03-02" /><button class="btnCal">달력</button></span>
												 ~
												<span class="calSet"><input type="text" class="cal" /><button class="btnCal">달력</button></span>
											</span>
											<div>
												<input style="width: 220px;" type="text" placeholder="이름,휴대전화를 입력해주세요." />
												<input type="submit" class="search-icon" value="search" />
											</div>
										</div>
										<!-- //-->
									</div>
								</div>
								<!-- databox-line -->
								<div class="rsls1">
									<table cellpadding="0" cellspacing="0" border="0">
										<colgroup>
											<col width="6%" />
											<col/>
											<col width="16%" />
											<col width="16%" />
										</colgroup>
										<thead>
											<tr>
												<th>No</th>
												<th>코드구분</th>
												<th>작성일자</th>
												<th>작성자</th>
											</tr>
										</thead>
										<tbody>
											<!-- Loop -->
											<c:forEach var="codelist" items="${codeList}" varStatus="status"> 
											<tr>
												<td class="ft12">${status.count}</td>
												<td class="ft12 listDetail" id="${codelist.grpId}" onclick="listDetail()" >${codelist.grpId}</td>
												<td class="ft12">2018-01-01 00:00:00</td>
												<td class="ft12">홍길동</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!--Paginate-->
								<div class="btnBox">
									<a class="btnS red right" href="code_register.html">신규등록</a>
								</div>
								<div class="paginate">
									<span>
										<a class="first">처음</a>
										<a class="prev">이전</a>
											<a class="on">1</a>
											<a>2</a>
											<a>3</a>
											<a>4</a>
											<a>5</a>
										<a class="next">다음</a>
										<a class="last">마지막</a>
									</span>
								</div>
								<!--//-->
							</div>
							<!-- //contents -->
						</div>
					</article>
					<!---->
				</section>
			</div>
