<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-migrate-1.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/duDatepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mdtimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.query-object.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/formstone/core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/formstone/upload.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/alertify.min.js"></script> --%>
<script>
var CTX_PATH = "${pageContext.request.contextPath}";
</script>

<%--
		<script>
			$(document).ready(function(){
				$('.tab-con1 .btnL.blue').click(function(){
				 	$('.popPro').show();
				 	$('#blind_wrap').toggle();
				 	$("body").addClass('modalOpen');
				 	return false;
				}); //프로그램 선택 팝업 열기

				$('.popPro .pclose').click(function(){
				 	$('.popPro').hide();
				 	$('#blind_wrap').toggle();
				 	$("body").removeClass('modalOpen');
				 	return false;
				}); //프로그램 선택 팝업 닫기

				var showAlert = setTimeout(function() {
					 $('#blind_wrap').hide();
					 $('.popLoad').hide();
					}, 1000);//로딩팝업 닫힘
			});
		</script>

--%>