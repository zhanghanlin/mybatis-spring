# mybatis-spring
#<a name="index"/>目录
* [项目说明](#info)
* [模块划分](#mobile)
  * [Service模块](#service)
  * [Util模块](#util)
  * [Web模块](#web)
* [MyBatis配置](#mybatis)
 * [mybatis与spring整合配置](#mybatis-spring)
 * [mybatis数据源以及注入配置](#mybatis-data)
 * [mybatis自动生成代码](#mybatis-generator)
* [Spring配置](#spring-config)
* [Junit测试](#junit)

<a name="info"/>
##项目说明
* 本项目作用为将Mybatis与Spring整合到一起
* 项目处于开发阶段
* ......

<a name="mobile"/>
##模块划分
<a name="service"/>
###Service模块
* 主要存放实体以及相关核心代码
* 主要分为四个子模块
	* dao
		* Mybatis自动生成,一般存放*Mapper.java,相当于Hibernate中的Dao
	* entity
		* Mybatis自动生成,存放对于数据库的Bean
	* service
		* 业务层,对外提供服务
	* resources/mapper
		* Mybatis自动生成,一般存放*Mapper.xml,为*Mapper.java的映射文件

<a name="util"/>
###Util模块
* 主要存放项目需要的工具类

<a name="web"/>
###Web模块
* 主要用于展示页面,相当于View层

<a name="mybatis"/>
##MyBatis配置
<a name="mybatis-spring"/>
###mybatis与spring整合配置
* 相关配置文件对应Web模块resources/spring/applicationContext-mybatis.xml
```xml
<configuration>
	<settings>
		<!-- 这个配置使全局的映射器启用或禁用缓存 -->
		<setting name="cacheEnabled" value="true" />
		<!-- 允许 JDBC 支持生成的键。需要适合的驱动。如果设置为 true 则这个设置强制生成的键被使用，尽管一些驱动拒绝兼容但仍然有效（比如 
			Derby） -->
		<setting name="useGeneratedKeys" value="true" />
		<!-- 配置默认的执行器。SIMPLE 执行器没有什么特别之处。REUSE 执行器重用预处理语句。BATCH 执行器重用语句和批量更新 -->
		<setting name="defaultExecutorType" value="REUSE" />
		<!-- 全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载。 -->
		<setting name="lazyLoadingEnabled" value="true" />
		<!-- 设置超时时间，它决定驱动等待一个数据库响应的时间。 -->
		<setting name="defaultStatementTimeout" value="25000" />
	</settings>
	<!-- 别名配置 -->
	<typeAliases></typeAliases>
	<!-- 指定映射器路径 -->
	<mappers>
		<mapper resource="mapper/*.xml" />
	</mappers>
</configuration>
```
<a name="mybatis-data"/>
###mybatis数据源以及注入配置
* 相关配置文件对应Web模块resources/spring/applicationContext-dataSource.xml
```xml
<!-- Mybatis -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	<!-- 数据源引用 -->
	<property name="dataSource" ref="dataSource" />
	<!-- mybatis的映射文件 -->
	<property name="mapperLocations" value="classpath:mapper/*.xml" />
	<!-- 要映射类的包路径，如果使用了这种方式,则configLocation中不必再进行声明 -->
	<property name="typeAliasesPackage" value="com.demo.java.entity" />
</bean>
<!-- 这段配置会扫描com.demo.java.dao下的所有接口,然后创建各自接口的动态代理类 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<property name="basePackage" value="com.demo.java.dao" />
</bean>
```
<a name="mybatis-generator"/>
###mybatis自动生成代码
* mybatis支持根据数据库表自动生成bean dao xml文件
* 对应配置文件Service模块resources/generatorConfig.xml
* 注：因为本Demo中bean dao xml等文件都存放在Service中,所以配置文件放到Service模块
* 配置完成后使用DOS命令进入到Service模块根目录执行以下命令
```Bash
mvn mybatis-generator:generate
```
```xml
...
<!-- 引入配置文件 -->
<properties resource="generatorConfig.properties" />
<!-- 指定数据连接驱动jar地址 -->
<classPathEntry location="${jdbc.jar.path}" />
<!-- 一个数据库一个context -->
<context id="infoGuardian">
	<!-- 注释 -->
	<commentGenerator>
		<!-- 是否取消注释 -->
		<property name="suppressAllComments" value="true" />
	</commentGenerator>
		<!-- jdbc连接 -->
	<jdbcConnection driverClass="${jdbc.driver}"
		connectionURL="${jdbc.url}" userId="${jdbc.username}" password="${jdbc.password}" />
	<!-- 类型转换 -->
	<javaTypeResolver>
		<!-- 是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.） -->
		<property name="forceBigDecimals" value="false" />
	</javaTypeResolver>
	<!-- 生成实体类地址 -->
	<javaModelGenerator targetPackage="com.demo.java.entity"
		targetProject="${project.src}">
		<property name="enableSubPackages" value="false" />
		<!-- 是否针对string类型的字段在set的时候进行trim调用 -->
		<property name="trimStrings" value="true" />
	</javaModelGenerator>
	<!-- 生成mapxml文件 -->
	<sqlMapGenerator targetPackage="mapper"
		targetProject="${project.resources}">
		<property name="enableSubPackages" value="false" />
	</sqlMapGenerator>
	<!-- 生成mapxml对应client，也就是接口dao -->
	<javaClientGenerator targetPackage="com.demo.java.dao"
		targetProject="${project.src}" type="XMLMAPPER">
		<property name="enableSubPackages" value="false" />
	</javaClientGenerator>
	<!-- 配置表信息 -->
	<table tableName="p_user" domainObjectName="User"
		enableCountByExample="false" enableDeleteByExample="false"
		enableSelectByExample="false" enableUpdateByExample="false">
	</table>
</context>
```

<a name="spring-config"/>
##Spring配置
* Spring配置文件存放位置：Web模块resources/spring目录下
	* applicationContext-common.xml
		* 用于配置Spring公共配置:增加扫描注解,消息定制等
	* applicationContext-dataSource.xml
		* 用于配置数据源:DruidDataSource以及Mybatis相关配置
	* applicationContext-mybatis.xml
		* Mybatis配置文件
	* applicationContext-profile.xml
		* 用于配置多个需要加载的属性文件
		* Junit由@ActiveProfiles([profile])指定加载的属性文件
		* Web容器启动则需要在web.xml中增加以下配置
		```
		<context-param>
			<param-name>spring.profiles.default</param-name>
			<param-value>[profile]</param-value>
		</context-param>
		```
	* applicationContext.xml
		* Web容器启动时需要只需加载该文件即可,其他需要加载的配置文件在该文件中配置

<a name="junit">
##Junit测试
* Junit测试目录为Web模块下src/test/java目录
* 新增Junit测试类直接集成com.demo.java.test.AbstractTest即可
* 加载配置文件信息以在com.demo.java.test.AbstractTest配置完毕
```java
@RunWith(SpringJUnit4ClassRunner.class)
//指定需要加载的属性文件
@ActiveProfiles("test")
//指定需要加载的配置文件
@ContextConfiguration({ "classpath:spring/applicationContext.xml" })
```
