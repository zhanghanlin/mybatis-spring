# mybatis-spring
本项目为mybatis + spring测试demo
#<a name="index"/>目录
* 项目说明
* 模块划分
  * [Service模块](#service)
  * [Util模块](#util)
  * [Web模块](#web)
* MyBatis配置
 * [mybatis与spring整合配置](#mybatis-spring)
 * [mybatis数据源以及注入配置](#mybatis-data)
 * [mybatis自动生成代码](#mybatis-generator)
* [Spring配置](#spring-config)
* [Junit测试](#junit)

##模块划分
<a name="service"/>
###Service模块
主要存放实体以及相关核心代码

<a name="util"/>
###Util模块
主要存放项目需要的工具包

<a name="web"/>
###Web模块
主要用于展示页面

##MyBatis配置
<a name="mybatis-spring"/>
###mybatis与spring整合配置
相关配置文件对应Web模块resources/spring/applicationContext-mybatis.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
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
<a name="mybatis-generator"/>
###mybatis自动生成代码

<a name="spring-config"/>
##Spring配置

<a name="junit">
##Junit测试
