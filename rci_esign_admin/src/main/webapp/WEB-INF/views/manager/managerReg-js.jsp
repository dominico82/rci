<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<script type="text/javascript">

	var dupIdChk = false;
	var confirmId = "";

	$(document).ready(function(){

		evt.init();

	});


	// 이벤트
	var evt = {
		init: function(){

			// form submit
			var submitAction = function(e) {
				e.preventDefault();
			    e.stopPropagation();
			};
			$('form').bind('submit', submitAction);

			// 중복확인
			$("#btnIdDupCheck").on("click", function(){
				// 아이디 Validation check
				if(!evt.idValidation($("#adminId").val())){
					return;
				}

				ajax.getIdDupCheck()
			});

			// 이메일선택
			$("#emailSelect").on("change", function(){
				$("#adminEmail2").val($(this).val());
			});

			$('#btnSave').on("click", function(){
				// 필수체크
				if($("#realName").val() == ""){
					alert("이름을 입력해 주세요.");
					return;
				}
				// 아이디 Validation check
				if(!evt.idValidation($("#adminId").val())){
					return;
				}
				// 권한
				if($("#roleTpCd").val() == ""){
					alert("권한을 선택해 주세요.");
					return;
				}
				// 중복체크 여부
				if(!dupIdChk || confirmId != $("#adminId").val()){
					alert("아이디 중복체크 해주세요.");
					return;
				}

				ajax.setInsert();
			});

			// 취소버튼
			$('#btnCancel').on("click", function(){
				history.back();
			});

			// 전화번호 숫자만 입력가능
			$(".numberType").on("keyup", function(ev) {
				// 숫자만 입력
				$(this).val($(this).val().replace(/[^0-9]/g,""));
			});

		},
		idValidation: function(idVal){
			var numAndEng = /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

			// 아이디 유효성 체크
			if(idVal == ""){
				alert("아이디를 입력해 주세요.");
				return;
			}
			// 영문숫자조합
			if(!numAndEng.test(idVal)){
				alert("아이디는 영문 소문자와 숫자 조합 5자 이상이어야  가능합니다.");
				return false;
			}
			// 5자 이상
			if(idVal.length < 5 ){
				alert("아이디는 영문 소문자와 숫자 조합 5자 이상이어야  가능합니다.");
				return false;
			}
			return true;
		},
		goList: function(){
			location.href = "${ct:url('MANAGER.MANAGER_LIST')}";
		}
	}

	var ajax = {
		getIdDupCheck: function(){
			var postData = $('#regForm').serialize();
			$.ajax({
				type: 'GET',
				url: "${ct:url('MANAGER.MANAGER_ID_DUP_CHECK_AJAX')}",
				datatype: 'json',
				data: postData,
				success : function (data) {
					if(data.result == "SUCCESS"){
						dupIdChk = true;
						confirmId = data.checkId;
						alert("중복체크 완료 되었습니다.");
					}
					else{
						dupIdChk = false;
						alert(data.resultMsg);
					}
				},
				error : function(request,status,error){
					dupIdChk = false;
				}
			});
		},
		setInsert: function(){
			// 이메일 입력시
			if($("#adminEmail1").val() != "" && $("#adminEmail2").val() != ""){
				$("#adminEmail").val($("#adminEmail1").val() + "@" + $("#adminEmail2").val());
			}
			var postData = $('#regForm').serialize();

			$.ajax({
				type: 'POST',
				url: "${ct:url('MANAGER.MANAGER_REG_AJAX')}",
				datatype: 'json',
				data: postData,
				success : function (data) {
					if(data.result == "SUCCESS"){
						alert("저장 되었습니다.");
						evt.goList();
					}
					else{
						alert(data.resultMsg);
					}
				},
				error : function(request,status,error){
				}
			});
		}
	}
</script>