<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<script type="text/javascript">

	$( document ).ready(function() {

		$(".upload").upload({
			action: CTX_PATH+"/file/uploadAjax"
			, multiple: false
			//, maxSize: 1073741824
			//, beforeSend: onBeforeSend
			//, maxFiles: 1
			, maxSize : 5242880
			, accept: 'image'
			, dataType : 'json'
			, label : 'FILE SEARCH'
		})
		.on("filecomplete.upload", onFileComplete);

		evt.init();
	});

	function onFileComplete(e, file, response) {
	// 	var _data = JSON.parse(response);
		var _data = response;
		if(_data.result == "SUCC") {
			var targetId = $(this)[0].id;
			console.log(_data.fileInfo.filePath);
			console.log(_data.fileInfo.fileNm);
			var encodePath = encodeURIComponent(_data.fileInfo.filePath + _data.fileInfo.fileNm);
			var encodeType = encodeURIComponent(_data.fileInfo.contentType);
			//$("#"+targetId+"_imgEle").attr("src","<c:url value='/imgShow?'/>path=" + encodePath + "&type=" + encodeType);
			$("#"+targetId+"_imgEle").attr("src","<c:url value='/imgShow?'/>fId=" + _data.fileInfo.fileNo);
			$("#"+targetId+"_img").show();
			$("#"+targetId+"_file_name").show();
			$("#"+targetId+"_file_name").html('<span>' + file.name + " (" + numberWithCommas(file.size) + " byte)" + '<input type="button" value="삭제" class="imgdelete" onclick="deleteImg(this)"  data-target="'+targetId+'"></span>');
			$("#"+targetId+"_file_no").val(_data.fileInfo.fileNo);
		} else {
			alert("error");
		}

	}

	function deleteImg(obj){
		var targetId = $(obj).data('target');
		$("#"+targetId+"_img").hide();
		$("#"+targetId+"_file_name").hide();
		$("#"+targetId+"_file_name").html('');
		$("#"+targetId+"_file_no").val("");
	}

	function sizePrint(size){
		console.log(size);
		if ( typeof size != "undefined" && size != "" ){
			var sizeString = numberWithCommas(size);
			document.write(sizeString)
		}
	}

	function encodeUrl(path , name, type){
		var enPath = encodeURIComponent(path + name);
		var enType = encodeURIComponent(type);
		var encodePath = "<c:url value='/imgShow?'/>path=" + enPath + "&type=" + enType;
		document.write(encodePath)
	}


	// 이벤트
	var evt = {
		init: function(){
			var submitAction = function(e) {
				e.preventDefault();
			    e.stopPropagation();
			};
			$('form').bind('submit', submitAction);

			$('#btnSave').on("click", function(){

				ajax.setUpdate();
			});

		}

	}

	var ajax = {
		// 이미지 저장
		setUpdate: function(){

			var postData = $('#mainImgForm').serialize();

			$.ajax({
				type: 'POST',
				url: "${ct:url('CONTENTS.VISUAL_SETTING_UPDATE')}",
				datatype: 'json',
				data: postData,
				success : function (data) {
					if(data.result == "SUCCESS"){
						alert("저장되었습니다.");
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