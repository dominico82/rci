<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<%-- <tiles:insertAttribute name="styles"/> --%>
	</head>
		<tiles:insertAttribute name="contents"/>
		<%-- <tiles:insertAttribute name="scripts"/> --%>
		<tiles:insertAttribute name="contents-js" flush="true"/>
</html>