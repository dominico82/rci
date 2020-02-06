<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<tiles:insertAttribute name="meta"/>
		<tiles:insertAttribute name="styles"/>
		<tiles:insertAttribute name="scripts"/>
		<tiles:insertAttribute name="contents-js" flush="true"/>
	</head>
	<body>
		<!-- wrap -->
		<div id="wrap">
			<tiles:insertAttribute name="header"/>
			<div id="c-mask" class="c-mask"></div><!-- /c-mask -->
			<div id="container"><tiles:insertAttribute name="contents"/></div>
		</div>
		<!-- //wrap -->
	</body>
</html>