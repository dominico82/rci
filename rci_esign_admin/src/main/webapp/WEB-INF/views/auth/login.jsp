<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<!DOCTYPE html>
<html lang="ko" class="loginCase">
	<head>
		<tiles:insertAttribute name="meta"/>
		<tiles:insertAttribute name="styles"/>
		<tiles:insertAttribute name="scripts"/>
	</head>
	<body class="loginCase">
		<div id="login">
			<div class="loginLayout">
				<div class="loginForm">
					<h1>관리자 로그인</h1>
					<p>RCI eSign 관리시스템 입니다.</p>
					<form id="login_form" action="${ct:url('AUTH.LOGIN_PROC')}" method="post">
						<div class="inputBox">
							<input type="text" id="adminId" name="un" placeholder="ID" value="admin" />
						</div>
						<div class="inputBox">
							<input type="password" id="adminPassword" name="up" placeholder="Password" value="1234"/>
						</div>
						<div>
							<span class="checkSet">
								<input type="checkbox" id="saveId" name="saveId" value="" checked />
								<label><i></i>아이디 저장</label>
							</span>
						</div>
						<button type="button" id="btnLogin" onClick="" class="btnLogin">Login</button>
					</form>
				</div>
			</div>
		</div>
	</body>

	<script type="text/javascript">
 	$(document).ready(function() {
 		// 화면 최초 진입시 아이디 입력부에 focus
 		$("#adminId").focus();

		$("#adminId").val(getCookie("userId"));

// 		console.log("call id" , getCookie("userId"));

		if(!!($.trim($("#adminId").val()))){
			$("#saveId").attr("checked",true);
		}

		// 로그인 버튼 클릭시
		$("#btnLogin").click(function() {
			$("#login_form").submit();
		});
	});

	function validate() {
		// 아이디 빈값 체크
		if ( !$("#adminId").val() ) {
			alert('아이디를 입력해주세요.');
			return false;
		}
		// 패스워드 빈값 체크
		if ( !$("#adminPassword").val() ) {
			alert('비밀번호를 입력해주세요.');
			return false;
		}
		return true;
	}

	function keyz(key) {
		if (key === 13) {
			$("#login_form").submit();
		}
	}

	document.onkeydown = function(event) {
		var holder;
		//IE, Chrome etc use this
		if (window.event) {
			holder = window.event.keyCode;
		}
		//FF uses this
		else {
			holder = event.which;
		}
		keyz(holder);
	}

	$('#login_form').ajaxForm({
		beforeSubmit : function(arr, $form, options){
			return validate();
		},
		success		: function(result, textStatus, jqXHR) {
			// 로그인 성공
			if( result.resultCode == "00" ) {

				// 성공한 아이디를 쿠키에 저장
 				saveId();

				location.href = result.targetUrl;
			}

			// 올바르지 않은 계정
			else {
				alert(result.resultMessage);
			}
		},
		error		: function(jqXHR, textStatus, errorThrown) {
			console.log("error:", jqXHR, textStatus, errorThrown);
		}
	});

	function saveId(){
		var $obj = $("#saveId");

		if($obj.is(":checked")){
			setCookie("userId",$("#adminId").val(),7)
		}else{
			deleteCookie("userId");
		}
	}

	function setCookie(cookieName, value, exdays){
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;

		console.log("set id succ");
	}

	function deleteCookie(cookieName){
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();

		console.log("del id succ");
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if(start != -1){
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if(end == -1)end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}


	</script>
</html>