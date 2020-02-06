<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<script type="text/javascript">

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
				if($("#adminId").val() == ""){
					alert("아이디을 입력해 주세요.");
					return;
				}
				if($("#roleTpCd").val() == ""){
					alert("권한을 선택해 주세요.");
					return;
				}

				ajax.setUpdate();
			});

			// 취소버튼
			$('#btnCancel').on("click", function(){
				history.back();
			});

			// 삭제버튼
			$('#btnDelete').on("click", function(){
				if(confirm("삭제 하시겠습니까?")){
					ajax.setDelete();
				}
			});

			// 비밀번호변경 클릭시
			$("#btnPwdChange").on("click", function(){
			 	$('.btnPw').hide();
			 	$('.pwchange').show();
			});

			$("#btnPwdChangeCancel").on("click", function(){
			 	$('.pwchange').hide();
			 	$('.btnPw').show();
			});

			// 전화번호 숫자만 입력가능
			$(".numberType").on("keyup", function(ev) {
				// 숫자만 입력
				$(this).val($(this).val().replace(/[^0-9]/g,""));
			});

		},
		goList: function(){
			location.href = "${ct:url('MANAGER.MANAGER_LIST')}";
		}
	}

	var ajax = {
		setUpdate: function(){
			// 이메일 입력시
			if($("#adminEmail1").val() != "" && $("#adminEmail2").val() != ""){
				$("#adminEmail").val($("#adminEmail1").val() + "@" + $("#adminEmail2").val());
			}
			var postData = $('#regForm').serialize();

			$.ajax({
				type: 'POST',
				url: "${ct:url('MANAGER.MANAGER_MOD_AJAX')}",
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
		},
		setDelete: function(){
			var postParam = {};
			var adminListArr = new Array();

			adminListArr.push({
				"adminId" : $("#adminId").val(),
				"enabled" : 0
			});

			postParam["adminList"] = adminListArr;

			$.ajax({
				type: 'POST',
				url: "${ct:url('MANAGER.MANAGER_DELETE_AJAX')}",
				dataType: "json",
				contentType : "application/json",
				data: JSON.stringify(postParam),
				success : function (data) {
					if(data.result == "SUCCESS"){
						alert("삭제 되었습니다.");
						evt.goList();
					}
					else{
						alert(data.resultMsg);
					}
				},
				error : function(request,status,error){
					alert("error!!!");
				}
			});
		}
	}
</script>