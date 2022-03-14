package top.cocoawork.wechat.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "top.cocoawork.wechat.dao.mapper")
public class MybatisConfig {
}
