package ru.omsu.imit.khokhlov.barbershop.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
@Component
public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtils.class);
    @PostConstruct
    public static boolean initSqlSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return true;
        } catch (IOException e) {
            LOGGER.error("Error loading mybatis-config.xml", e);
            return false;
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}