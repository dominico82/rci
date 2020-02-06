<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<script type="text/javascript">

	$(document).ready(function(){

		evt.init();

	});


	// 이벤트
	var evt = {
		init: function(){

			// duDatepicker Setting
			var datePicker = $('.btnCal').duDatepicker({
				format: 'yyyy-mm-dd', // Determines the date format
				theme: 'blue',        // Determines the color theme of the date picker
				readOnly: false,       // Determines if input element is readonly (key input is disabled)
				clearBtn: false,      // Determines if Clear button is displayed
				cancelBtn: false,     // Determines if Cancel button is displayed
				overlayClose: true    // Determines if clicking the overlay will close the date picker
			});	// Datepicker 2018-03-27 format / 2018-03-02

			datePicker.on('datechanged', function() {
				var thisVal = this.value;
				// 시작일
				if($(this).hasClass('startBtn')) {
					$('#schStartDate').val(thisVal);
				}
				// 종료일
				else if($(this).hasClass('endBtn')){
					$('#schEndDate').val(thisVal);
				}
			});

			//초기화버튼
			$("#btnReset").on("click", function(){
				history.replaceState({}, null, location.pathname);
				location.reload();
			});

			// 검색조건 구분 변경시
			$("#roleTpCd").on("change", function(){
				ajax.getList();
			});

			$('#btnSearch').on("click", function(){
				var schStartDate = $('#schStartDate').val().replace(/\-/g,'');
				var schEndDate = $('#schEndDate').val().replace(/\-/g,'');

				if(schStartDate > schEndDate){
					alert("시작날짜는 마지막날짜보다 클수 없습니다.");
					return false;
				}
				ajax.getList();
			});

			// 선택삭제
			$('#btnDelete').on("click", function(){
				if($("[name=chkAdminId]:checked").length == 0){
					alert("선택 후 삭제 가능합니다.");
					return;
				}
				else{
					if(confirm("삭제 하시겠습니까?")){
						ajax.setDelete();
					}
				}
			});

			// 전체선택
			$("[name=chkAll]").on("click", function(){
				evt.checkAll();
			});

			// 개별선택시
			$("[name=chkAdminId]").each(function(){
				$(this).click(function(){
					evt.validCheckAll( $(this) );
				});
			});

		},
		// 전체선택
		checkAll: function(){
			$("[name=chkAdminId]").prop("checked", $("#chkAll").prop("checked"));
		},
		// 전체선택체크
		validCheckAll: function(obj){
			if($(obj).prop("checked")){
				if($("[name=chkAdminId]").length == $("[name=chkAdminId]:checked").length){
					$("#chkAll").prop("checked", true);
				}
				else{
					$("#chkAll").prop("checked", false);
				}
			}
			else{
				$("#chkAll").prop("checked", false);
			}
		},
		goMod: function(adminId){
			location.href = "${ct:url('MANAGER.MANAGER_MOD')}" + "?adminId=" + adminId;
		},
		goReg: function(adminId){
			location.href = "${ct:url('MANAGER.MANAGER_REG')}";
		}
	}

	var ajax = {
		// 목록 조회
		getList: function(){

			var params = {
				schStartDate : $('#schStartDate').val(),
				schEndDate  :  $('#schEndDate').val(),
				searchWord : $("#searchWord").val().trim(),
				roleTpCd : $("#roleTpCd").val()
			};

			location.href = location.pathname +"?"+ $.param(params);

		},
		setDelete: function(){
			var postParam = {};
			var adminListArr = new Array();

			$("[name=chkAdminId]:checked").each(function(){
				adminListArr.push({
					"adminId" : $(this).val(),
					"enabled" : 0
				});
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
						ajax.getList();
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