<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tlds/common-taglibs.tld" %>
<%@ taglib prefix="rci" uri="/WEB-INF/tlds/rci-taglibs.tld" %>
<%@ taglib prefix="navigator" uri="/WEB-INF/tlds/navigator.tld" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath eq '/' ? '' : pageContext.request.contextPath}" />
<c:set var="remoteURI" value="${ctxPath}${requestScope['javax.servlet.forward.servlet_path']}" />