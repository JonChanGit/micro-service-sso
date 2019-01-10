package cn.com.jonpad.oauth;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  MyBatisPlus代码生成器
 * @author Jon Chan
 * @date 2018/11/16 22:50
 */
public class MyBatisPlusCodeGenerator {

	private static final String AUTHOR = "Jon Chan";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/pig?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";
    private static final String PACKAGE_NAME = "cn.com.jonpad.oauth";
    private static final String SUPER_ENTITY_CLASS = "cn.com.jonpad.oauth.entity.BaseEntity";
    private static final String SUPER_CONTROLLER_CLASS = "cn.com.jonpad.oauth.controller.BaseController";

    private static final String MODULE_PATH = "/oauth/";



	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + MODULE_PATH + "/src/main/java")
				.setAuthor(AUTHOR)
				.setOpen(false)
				.setSwagger2(true)
				.setDateType(DateType.TIME_PACK)
				.setMapperName("%sMapper")
				.setXmlName("%sMapper")
				.setServiceName("%sService")
				.setServiceImplName("%sServiceImpl")
				.setControllerName("%sController")
				.setIdType(IdType.AUTO);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(DB_URL)
				.setDbType(DbType.MYSQL)
				.setDriverName("com.mysql.jdbc.Driver")
				.setUsername(DB_USERNAME)
				.setPassword(DB_PASSWORD);
		mpg.setDataSource(dsc);
		// dsc.setSchemaName("public");

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName("");// 设置了全类名可以不用设置模块名
		pc.setParent(PACKAGE_NAME);
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + MODULE_PATH + "/src/main/resources/mapper/" + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig()
				.setNaming(NamingStrategy.underline_to_camel)
				.setColumnNaming(NamingStrategy.underline_to_camel)
				.setSuperEntityClass(SUPER_ENTITY_CLASS)
				.setEntityLombokModel(true)
				.setRestControllerStyle(true)
				.setSuperControllerClass(SUPER_CONTROLLER_CLASS)
				.setInclude(scanner("表名"))
				.setSuperEntityColumns("id")
				.entityTableFieldAnnotationEnable(true)
				.setControllerMappingHyphenStyle(true)
				.setTablePrefix(StringUtils.isEmpty(pc.getModuleName()) || "null".equals(pc.getModuleName().trim())?"": pc.getModuleName()+ "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
