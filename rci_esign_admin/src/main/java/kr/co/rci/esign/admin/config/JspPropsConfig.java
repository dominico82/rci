package kr.co.rci.esign.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

public class JspPropsConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
	@Value("${spring.profiles.active}")
	private String profile;

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		System.out.println("JspPropsConfig");

		// Tomcat의 web.xml의 설정을 아래에서 context에 세팅합니다.
//		if (factory instanceof TomcatServletWebServerFactory) {
//			TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
//
//			// 기본 connector 설정
//			tomcat.addConnectorCustomizers((connector) -> {
//				connector.setMaxPostSize( 524288000 ); // 500MB
//			});
//			
////			tomcat.setContextPath("/WEB-INF/views");
//			
//			// Context 설정
//			tomcat.addContextCustomizers(new TomcatContextCustomizer() {
//				@Override
//				public void customize(Context context) {
//					System.out.println("customize : ");
//					// 세션 타임 아웃 시간
//					context.setSessionTimeout( 30 );
//
//					// 에러 페이지 지정
////					setErrorPages(context);
//
//					// JSP 설정 [START]
//					final Collection<JspPropertyGroupDescriptor> jspPropertyGroups = new ArrayList<>();
//
//					final JspPropertyGroup group = new JspPropertyGroup();
//					group.addUrlPattern( "*.jsp" );
//					group.setPageEncoding( "UTF-8" );
//
//					jspPropertyGroups.add( new JspPropertyGroupDescriptorImpl(group) );
//
//					// 커스텀 태그 라이브러리 설정 (불필요 : spring boot에서 tld 자동 감지)
//					final Collection<TaglibDescriptor> taglibs = new ArrayList<>();
//
//					final JspConfigDescriptor jspConfigDescriptor = new JspConfigDescriptorImpl(jspPropertyGroups, taglibs);
//					context.setJspConfigDescriptor(jspConfigDescriptor);
//					// JSP 설정 [END]
//				}
//
//				/**
//				 * JSP 에러 페이지 세팅
//				 * @param context
//				 */
//				private void setErrorPages(Context context) {
////					final ErrorPage err302 = new ErrorPage();
////					err302.setErrorCode(302);
////					err302.setLocation( ERROR.FOUND );
////
////					final ErrorPage err403 = new ErrorPage();
////					err403.setErrorCode(403);
////					err403.setLocation( ERROR.FORBIDDEN );
////					
////					final ErrorPage err404 = new ErrorPage();
////					err404.setErrorCode(404);
////					err404.setLocation( ERROR.NOT_FOUND );
////
////					final ErrorPage err500 = new ErrorPage();
////					err500.setErrorCode(500);
////					err500.setLocation( ERROR.SERVER_ERROR );
////
////					context.addErrorPage( err302 );
////					context.addErrorPage( err403 );
////					context.addErrorPage( err404 );
////					context.addErrorPage( err500 );
//				}
//			});
//		}
		
	}
}
