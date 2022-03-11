package com.jiahe.jiahecloudspringboot;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.jiahe.jiahecloudspringboot.mapper")
public class JiahecloudspringbootApplication {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    //注册后台serlvetbean，用于显示后台界面
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> params = new HashMap<String, String>();
        params.put("loginUsername","project");
        params.put("loginPassword","project");
        params.put("allow", "");
        params.put("deny", "22.44.33.22");
        bean.setInitParameters(params);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(JiahecloudspringbootApplication.class, args);
    }

}
