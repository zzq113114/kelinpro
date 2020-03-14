/**
 * 版权信息: © 易往信息技术
 */
package clean.platform.disorder.datasource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/*****************************************************************************
 *创建时间 : 2019年3月29日
 *实现功能 : 数据源配置信息管理类
 *作者 : xin.zhou
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年3月29日 v0.0.1  创建
 *****************************************************************************/
@Component
@ConfigurationProperties(prefix = "db.datasource")
@PropertySource("classpath:/datasource-cfg.properties")
public class DBPropertyConfiguer {
	private static Map<String, Properties> propertyMap = new HashMap<>();

	private Logger logger = LogManager.getLogger(getClass());

	private static final String DATASOURCE = "datasource.";

	@Autowired
	private Environment environment;

	private String name;

	/**
	 * ====================================================================
	 *功 能： 读取数据源配置文件
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2019年4月16日 v0.0.1 xin-zhou 创建
	====================================================================
	 */
	@PostConstruct
	public void readDataSourceProperty() {
		try {
			String[] datasourceNames = name.split(",");
			for (String sourceName : datasourceNames) {
				Properties properties = new Properties();
				Field[] fields = DataSourceProperty.class.getDeclaredFields();
				String dbName = DATASOURCE + sourceName;
				for (Field field : fields) {
					String fieldName = field.getName();
					String value = environment.getProperty(dbName + "." + fieldName);
					if (Objects.nonNull(value)){
						properties.put(fieldName, value);
					}
				}
				propertyMap.put(sourceName, properties);
			}
		} catch (Exception e) {
			logger.error("初始化数据库配置文件异常:{}", e);
		}
	}

	public Properties getDatasourceProperty(DBNameEnum dbName) {
		return propertyMap.get(dbName.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
