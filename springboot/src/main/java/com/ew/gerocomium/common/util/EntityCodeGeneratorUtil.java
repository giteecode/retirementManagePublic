package com.ew.gerocomium.common.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.ew.gerocomium.dao.base.BaseEntity;

import java.util.ArrayList;

public class EntityCodeGeneratorUtil {

    public static void main(String[] args) {
        // 构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String property = System.getProperty("user.dir"); // 获取当前项目的目录
        gc.setOutputDir(property + "/src/main/java/"); // 设置生成代码的目录
        gc.setAuthor("EmperorWen"); // 生成代码的作者
        gc.setOpen(false); // 生成完成后，打开项目所在windows下的文件夹(false 不打开)
        gc.setFileOverride(false); // 是否覆盖原来的文件(false 不覆盖)
        gc.setServiceName("%sBaseService"); // 去除IService的I前缀
        gc.setServiceImplName("%sBaseServiceImpl"); // 去除IServiceImpl的I前缀
        gc.setIdType(IdType.AUTO); // 设置pojo类主键策略
        gc.setDateType(DateType.ONLY_DATE); // 设置pojo类data的类型
        gc.setSwagger2(false); // 设置swagger文档
        mpg.setGlobalConfig(gc); // 将配置丢入自动生成器内

        // 2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/gerocomium?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("242680");
        dsc.setDbType(DbType.MYSQL); // 使用的是mysql
        mpg.setDataSource(dsc);

        // 3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.ew.gerocomium");
        pc.setModuleName("dao");
        pc.setEntity("po");
        pc.setMapper("mapper");
        pc.setService("service");
//        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 4、配置策略
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(); // 设置要映射的表；多个则使用 ”,“ 分割；全部则不设置
        strategy.setNaming(NamingStrategy.underline_to_camel); // 命名规则(下划线转驼峰)
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); //列的名字(下划线转驼峰)
        strategy.setSuperEntityClass(BaseEntity.class); // 父类实体类
        strategy.setSuperEntityColumns("id","create_id","create_time","update_id","update_time"); // 去掉父类属性
        strategy.setEntityLombokModel(true); // 是否使用lombok开启注解

        // 设置逻辑删除的别名
        strategy.setLogicDeleteFieldName("deleted");

        // 设置自动填充配置
        TableFill create_id = new TableFill("create_id", FieldFill.INSERT);
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_id = new TableFill("update_id", FieldFill.INSERT_UPDATE);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFiledLists = new ArrayList<>();
        tableFiledLists.add(create_id);
        tableFiledLists.add(create_time);
        tableFiledLists.add(update_id);
        tableFiledLists.add(update_time);
        strategy.setTableFillList(tableFiledLists);

        // 乐观锁策略
        strategy.setVersionFieldName("version");

        // controller 驼峰命名
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); // requestMapping中使用下划线命名

        mpg.setStrategy(strategy);

        // 执行
        mpg.execute();
    }

}

