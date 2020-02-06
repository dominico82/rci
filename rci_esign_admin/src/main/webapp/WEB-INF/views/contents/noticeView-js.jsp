<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

 <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=w6kwnesj59nlkvboy6kfw1b8npka2l1hc3z7z8qi5994el2e"></script>
 <script>tinymce.init({ selector:'textarea' });</script>
 <script type="text/javascript">

	$(document).ready(function(){
		evt.init();
	});

	// 이벤트
	var evt = {
		init: function(){

			$('#btnUpdate').on("click", function(){
				ajax.setUpdate();
			});
		},

		goList: function(){
			location.href = "${ct:url('CONTENTS.NOTICE_LIST')}";
		}
	}

	var ajax = {
			// 목록 조회
		setUpdate: function(){
			var postData = $('#regForm').serialize();
			$.ajax({
				type: 'POST',
				url: "${ct:url('CONTENTS.NOTICE_UPDATE')}",
				datatype: 'json',
				data: postData,
				success : function (data) {
						alert("저장 되었습니다.");
						evt.goList();
				},
				error : function(request,status,error){
				}
			});
		}
	}
</script>

