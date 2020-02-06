package kr.co.rci.esign.admin.config;

import static kr.co.rci.esign.admin.constant.AppConstants.ENTITY_PACKAGE_NAME;
import static kr.co.rci.esign.admin.constant.ServerConstants.MAPPER_PACKAGE;
import static kr.co.rci.esign.admin.constant.ServerConstants.MAPPER_XML_PACKAGE;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * eBest Database 설정
 * <p>참조 : '{@code application-*.properties}'의 '{@code spring.datasource}' prefix 아이템 참조
 * @author Eddie Cho
 * @author Felix Park
 * @version 1.0.0
 */
@Configuration
@Lazy
@EnableTransactionManagement
@MapperScan(basePackages= MAPPER_PACKAGE )
public class DatabaseConfig {

	/**
	 * Session Factory 설정
	 * <p>{@code dataSource}의 jdbc정보는 '{@code application-*.properties}'의 '{@code spring.datasource}' prefix 아이템 참조
	 * @param dataSource
	 * @throws Exception
	 */
	@Bean
	public  SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		// 리졸버에 등록된 패키지 하위의 dao 클래스를 스캔합니다.
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources(MAPPER_XML_PACKAGE));

		// Type Alias Setting -> Entity들이 있는 Package 정보
		sessionFactory.setTypeAliasesPackage( ENTITY_PACKAGE_NAME );
		return sessionFactory.getObject();
	}

	/**
	 * Mybatis template 설정
	 * @param sqlSessionFactory
	 * @throws Exception
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		// underscore를 camelCase로 매칭 : 예) user_id -> userId
		sqlSessionTemplate.getConfiguration().setMapUnderscoreToCamelCase(true);
		// insert시 생성되는 pk를 bean으로 반환
		sqlSessionTemplate.getConfiguration().setUseGeneratedKeys(true);
		return sqlSessionTemplate;
	}

	/** 트랜잭션 매니저 등록 */
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
