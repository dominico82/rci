$(document).ready(function(){
	$('.firstGnb>li>span strong').click(function () {
			$('.firstGnb ul li').removeClass('on');
			$('.firstGnb>li').removeClass('on');
			$('.gnb ul li ul').slideUp();
			$(this).parents('li').addClass('on');
			return false;
		}); // gnb list click

	$('.firstGnb li.snbCase>span strong').click(function () {
		$(this).parents('span').siblings('ul').slideDown();
		$('.gnb ul li ul li:first-child').addClass('on');
		return false;
	}); // gnb ow rank menu

	$(".tab-con").hide();
	$(".tab-con:first").show();
	$(".tab span input").click(function () {
		$(".tab span").removeClass("on");
		$(this).parent("span").addClass("on");
		$(".tab-con").hide();
		var activeTab = $(this).attr("data-name");
		$("." + activeTab).fadeIn();
	});

	 $('.btnCal').duDatepicker({
		 format: 'yyyy-mm-dd', // Determines the date format
		  theme: 'blue',        // Determines the color theme of the date picker
		  readOnly: false,       // Determines if input element is readonly (key input is disabled)
		  clearBtn: false,      // Determines if Clear button is displayed
		  cancelBtn: false,     // Determines if Cancel button is displayed
		  overlayClose: true    // Determines if clicking the overlay will close the date picker
	 });	// Datepicker 2018-03-27 format / 2018-03-02

	 $('.timepicker').mdtimepicker();

	var select = $(".selectSet select");
	select.change(function(){
		var select_name = $(this).children("option:selected").text();
		$(this).siblings("strong").text(select_name);
	});
	for (i=0; i<$(".selectSet select").length; i++){
		var select_ = $(".selectSet select").get(i);
		if($(select_).is(":disabled")) $(select_).siblings('strong').css('opacity','0.5');
	} // select disabled opacity
});

function goList(page) {
	var query = $.query;

//	console.log($.query);
	// 페이지가 기본(1)일 경우 URL querystring에서 제외시킵니다.
	if(page === 0) {
		query = query.remove("page");
	} else {
		query = query.set("page", page);
	}

	location.href = location.pathname + query.toString();
}

function goListAjax(page) { // hty : write 하단 pageing 처리 하기위한 method
	var params = new Object();
	params["page"] = page;

	if($("#quotationNo").length > 0)
		params["quotationNo"] = $("#quotationNo").val();

//	if(url.length <= 0){
//		alert("url이 없습니다.");
//		return false;
//	}
	console.log(params);
	$.ajax({
		url : location.pathname,
//		url : CTX_PATH+url,
		data : params,
		type : "GET",
		dataType : 'html',
		cache : false,
		success : function(detailResult){
			console.log(detailResult);
			//$("#container").html(detailResult);
		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			//alertify.error('<spring:message code="msg.common.valid2" />');
		}
	});
}

function numberWithCommas (x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}