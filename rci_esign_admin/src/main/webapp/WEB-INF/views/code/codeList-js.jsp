<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<script>

$(document).ready(function() {
	
	
	
	var total = $(".listDetail").length;	//리스트의 총개수
	$("#totalList").text(total);				//리스트의 총개수 적용
	
});


function listDetail() {
	
	
	var code = window.event.target.id;		// 아이디값(넘겨받을값) 가져오기
	location.href = "${ct:url('CODE.CODE_LIST_DETAIL')}?grpId="+code;
}


// 인서트 쓸때 수정해서 사용할 것 
// var ajax = {
// 		listDetailAjax : function(code) {
// 			$.ajax({
// 				type: 'GET',
// 				url: "",
// 				datatype: 'json' ,
// 				data: {"grpId" : code},
// 				async: false,
// 				success : function () {
// 					alert("안녕하세요");					
// 				},
// 				error : function(request,status,error){
// 					alert("통신상의 오류가 있습니다.");
// 				}
// 			});
// 		}  // $.ajax_end
// }	// var ajax_end











</script>
