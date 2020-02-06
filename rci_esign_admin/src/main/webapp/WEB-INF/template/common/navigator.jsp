<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

							<div class="root">
								<div class="rootBack">
									<span class="pagePos">
										<a href="${ct:url('MAIN.INDEX')}" class="rootTit"></a>
										<a href="" class="list">${currentMenu.parent.name}</a>
										<span>${currentMenu.name}</span>
									</span>
								</div>
							</div>