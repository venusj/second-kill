package com.venusj.secondkill.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * @author zhangjh
 * @date 2020/8/20
 * @desc mybatisplus 配置加载
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.venusj.secondkill.mapper"})
public class MybatisPlusConfig {
    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 通用字段填充
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("deleted", 0 , metaObject);
                // TODO 创建者  修改者  可扩展jwt中获取进行设置
                this.setFieldValByName("whenCreated", LocalDateTime.now(), metaObject);
                this.setFieldValByName("whenModified", LocalDateTime.now() , metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("whenModified", LocalDateTime.now() , metaObject);
                // 同上可扩展
            }
        };
    }
}
