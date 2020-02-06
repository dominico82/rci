<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<!-- header -->
<div class="mobileMenu c-menu c-menu--push-left">
	<header id="header">
		<h1>
			<img src="${ctxPath}/images/img_logo.png" alt="e-Sign" />
			<input type="button" value="snb close" class="c-menu__close" />
		</h1>
		<c:set var="currentMenu" value="" />
		<nav>
			<div class="gnb">
				<!-- gnbMenu -->
				<ul class="firstGnb">
				<c:forEach var="item" items="${ct:getMenuList()}" varStatus="status">
					<c:if test="${fn:contains(remoteURI,item.url)}"><c:set var="currentMenu" value="${item}" scope="request" /></c:if>
					<c:choose>
						<c:when test="${not empty item.menuList}">
							<li class="snbCase ${fn:contains(remoteURI,item.url) ? 'on' : ''}"><span><strong>${item.name}</strong></span>
								<c:forEach var="subItem" items="${item.menuList}" varStatus="subStatus">
									<c:if test="${fn:contains(remoteURI,subItem.url)}"><c:set var="currentMenu" value="${subItem}" scope="request"/></c:if>
									<c:if test="${subStatus.first}"><ul ${fn:contains(remoteURI,item.url) ? '' : 'style="display:none;"'}></c:if>
									<li ${fn:contains(remoteURI,subItem.url) ? 'class="on"' : ''}><span><a href="${ctxPath}${subItem.url}">${subItem.name}</a></span></li>
									<c:if test="${subStatus.last}"></ul></c:if>
								</c:forEach>
							</li>
						</c:when>
						<c:otherwise>
							<li class="snbCase ${fn:contains(remoteURI,item.url) ? 'on' : ''}"><a href="${ctxPath}${item.url}"><span><strong>${item.name}</strong></span></a></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				</ul>
				<!-- //gnbMenu -->

			</div>
		</nav>
	</header>
</div>
<!-- //header -->
