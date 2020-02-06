<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
	<c:if test="${not empty paginate and paginate.totalPages > 1}">
		<div class="paginate">
			<span>
				<c:if test="${not paginate.first}">
					<a class="first">처음</a>
					<a class="prev">이전</a>
				</c:if>
				<c:forEach var="pgCnt" begin="1" step="1" end="${paginate.totalPages}">
					<c:choose>
						<c:when test="${pgCnt eq paginate.number}"><a class="on">${pgCnt}</a></c:when>
						<c:otherwise><a href="">${pgCnt}</a></c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${not paginate.last}">
					<a class="next">다음</a>
					<a class="last">마지막</a>
				</c:if>
				
			</span>
		</div>
	</c:if>

	<c:remove var="paginate"/>