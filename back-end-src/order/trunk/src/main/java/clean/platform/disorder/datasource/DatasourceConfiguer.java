/**
 * 版权信息: © 易往信息技术
 */
package clean.platform.disorder.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;



/*****************************************************************************
 *创建时间 : 2019年3月28日
 *实现功能 : 租户服务数据库配置类
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年3月28日 v0.0.1 xin.zhou 创建
 *****************************************************************************/
@Configuration
@MapperScan(basePackages = { "clean.platform.disorder.mapper"}, sqlSessionFactoryRef = "masterDataSourceSqlSessionFactory")
@EnableTransactionManagement
public class DatasourceConfiguer {

	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private DBPropertyConfiguer dbPropertyConfiguer;

	@Bean("masterDataSource")
	public DataSource buildDataSource() {
		DataSource dataSource = new DruidDataSource();
		logger.info("*****************主数据源初始化********************");
		Properties properties = dbPropertyConfiguer.getDatasourceProperty(DBNameEnum.master);
		try {
			dataSource = DruidDataSourceFactory.createDataSource(properties);
			logger.info("*****************主数据源初始化成功******************");
		} catch (Exception e) {
			logger.error("主数据源初始化失败:{}", e);
		}
		return dataSource;
	}

	@Bean("masterDataSourceTranscation")
	public DataSourceTransactionManager masterTranscationManager(@Qualifier("masterDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("masterDataSourceSqlSessionFactory")
	public SqlSessionFactory createSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource, @Qualifier("pageHelper") PageHelper pageHelper) {
		SqlSessionFactory sessionFactory = null;
		try {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			factoryBean.setDataSource(dataSource);
			factoryBean.setPlugins(new Interceptor[] { pageHelper });
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");
			factoryBean.setMapperLocations(resources);
			sessionFactory = factoryBean.getObject();
			sessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		} catch (Exception e) {
			logger.error("创建SqlSessionFactory异常:{}", e);
		}
		return sessionFactory;
	}

	@Bean("masterSqlSessionTemplate")
	public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("masterDataSourceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sessionTemplate;
	}

	@Bean("pageHelper")
	public PageHelper pageHelper() {
		logger.info("**********************创建分页对象****************************");
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
