package kr.co.rci.esign.admin.config;

import static kr.co.rci.esign.admin.constant.ServerConstants.REFRESH_JSP_ON_RUNTIME;
import static kr.co.rci.esign.admin.constant.ServerConstants.TILES_LAYOUT_XML_PATH;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
/**
 * Apache Tiles 설정
 * @author Eddie Cho
 * @author Felix Park
 */
@Configuration
public class TilesConfig {

	/**
	 * Tiles 설정
	 * @return tilesConfigurer
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();

		configurer.setDefinitions( TILES_LAYOUT_XML_PATH );		// tiles 설정 파일이 있는 위치
		configurer.setCheckRefresh( REFRESH_JSP_ON_RUNTIME );	// tiles 설정 파일이 runtime 중에 변경될 경우 적용 여부

		return configurer;
	}

	/**
	 * UrlBased 뷰 리졸버
	 * @return urlViewResolver
	 */
	@Bean
	public UrlBasedViewResolver urlViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
}
