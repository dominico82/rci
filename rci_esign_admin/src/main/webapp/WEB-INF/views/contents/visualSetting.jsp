<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>


			<div id="container">
				<section>
					<!-- pageHead.jsp -->
					<%@ include file="/WEB-INF/template/common/pageHead.jsp"%>
					<!-- // pageHead.jsp -->
					<article>
						<div class="comLayout">
							<!-- navigator.jsp -->
							<%@ include file="/WEB-INF/template/common/navigator.jsp"%>
							<!-- // navigator.jsp -->
							<div class="contents">
								<form id="mainImgForm">
								<div class="setCase">
									<div class="tab">
										<div>
											<span class="on"><input type="button" value="메인 이미지" data-name="tab-con0" /></span>
										</div>
									</div>
									<!-------------- 메인이미지 -------------->
									<div class="tab-con tab-con0">
										<div class="sizeBox">
											<span class="ftRed right ft12 mt10">PC버전  : 00*00 (7:4 비율) |  모바일  : 00*00 (7:4 비율) | 파일 권장용량 : 5MB  | 확장자 : jpg,png,gif</span>
										</div>
										<!-- databox-line -->
										<div class="databox-line mt10">
											<div class="dboxeT">모바일 버전 <span class="checkSet ml20"></span></div>
											<div class="rsws1">
												<dl>
													<dd class="pd15">
														<span class="upload fileex_back mr5 mb5" id="moImgFile"></span>
														<span class="imageArea inBlock" id="moImgFile_img" <c:if test="${empty mainImgInfo.moImgFileInfo }">style="display:none"</c:if>>
															<span class="viThum">
																<span class="tbspan"><img id="moImgFile_imgEle" src="${ctxPath}/imgShow?fId=${mainImgInfo.moImgFileNo}"/></span>
															</span>
														</span>
														<span class="imgselected mr5" id="moImgFile_file_name" <c:if test="${empty mainImgInfo.moImgFileInfo }">style="display:none"</c:if>>
															<span>
																${mainImgInfo.moImgFileInfo.orgFileNm} (<script>sizePrint(${mainImgInfo.moImgFileInfo.fileSize})</script>byte)
																<input type="button" value="삭제" class="imgdelete" onclick="deleteImg(this)" data-target="moImgFile">
															</span>
														</span>
														<input type="hidden" id="moImgFile_file_no" name="moImgFileNo" value="${mainImgInfo.moImgFileNo}"/>
													</dd>
												</dl>
											</div>
										</div>
										<!-- //databox-line -->
										<!-- databox-line -->
										<div class="databox-line mt10">
											<div class="dboxeT">테블릿 버전 <span class="checkSet ml20"></span></div>
											<div class="rsws1">
												<dl>
													<dd class="pd15">
														<span class="upload fileex_back mr5 mb5" id="pcImgFile"></span>
														<span class="imageArea inBlock" id="pcImgFile_img" <c:if test="${empty mainImgInfo.pcImgFileInfo }">style="display:none"</c:if>>
															<span class="viThum">
																<span class="tbspan"><img id="pcImgFile_imgEle" src="${ctxPath}/imgShow?fId=${mainImgInfo.pcImgFileNo}"/></span>
															</span>
														</span>
														<span class="imgselected mr5" id="pcImgFile_file_name" <c:if test="${empty mainImgInfo.pcImgFileInfo }">style="display:none"</c:if>>
															<span>
																${mainImgInfo.pcImgFileInfo.orgFileNm} (<script>sizePrint(${mainImgInfo.pcImgFileInfo.fileSize})</script>byte)
																<input type="button" value="삭제" class="imgdelete" onclick="deleteImg(this)" data-target="pcImgFile">
															</span>
														</span>
														<input type="hidden" id="pcImgFile_file_no" name="pcImgFileNo" value="${mainImgInfo.pcImgFileNo}" />
													</dd>
												</dl>
											</div>
										</div>
										<!-- //databox-line -->
										<div class="btnBox">
											<span class="right">
												<button class="btnS blue" id="btnSave">저장</button>
											</span>
										</div>
									</div>
									<!-------------- //메인이미지 -------------->
								</div>
								<!-- //setCase  -->
								</form>
							</div>
							<!-- //contents -->
						</div>
					</article>
					<!---->
				</section>
			</div>
