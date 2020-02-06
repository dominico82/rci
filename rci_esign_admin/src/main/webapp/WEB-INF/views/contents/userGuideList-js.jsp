<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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

			$('#btnSearch').on("click", function(){
				var schStartDate = $('#schStartDate').val().replace(/\-/g,'');
				var schEndDate = $('#schEndDate').val().replace(/\-/g,'');

				if(schStartDate > schEndDate){
					alert("시작날짜는 마지막날짜보다 클수 없습니다.");
					return false;
				}
				ajax.getList();
			});

		},
	}

	var ajax = {
		// 목록 조회
		getList: function(){

			var params = {
				schStartDate 	:	 $('#schStartDate').val(),
				schEndDate  	:  	 $('#schEndDate').val(),
				searchWord 		: 	 $("#searchWord").val().trim()
			};

			location.href = location.pathname +"?"+ $.param(params);

		}

	}
</script>