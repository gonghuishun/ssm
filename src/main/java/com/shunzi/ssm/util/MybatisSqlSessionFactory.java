package com.shunzi.ssm.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by Administrator on 2017/7/17.
 * mybatis sqlseSessionFactory 单例模式
 */
public class MybatisSqlSessionFactory {
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    //首先创建静态成员变量sqlSessionFactory，静态变量被所有的对象所共享。
    private static SqlSessionFactory sqlSessionFactory;
    private static String CONFIG_FILE_LOCATION = "sqlMapConfig.xml";
    private static String configFile = CONFIG_FILE_LOCATION;
    static{
        try {
            Reader reader =Resources.getResourceAsReader(configFile);
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MybatisSqlSessionFactory(){}

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if(sqlSession == null){
            if(sqlSessionFactory == null){
                 rebuildSqlSessionFactory();
            }
            sqlSession = (sqlSessionFactory !=null)?sqlSessionFactory.openSession():null;
            threadLocal.set(sqlSession);
        }
        return  sqlSession;
    }

    public static void rebuildSqlSessionFactory(){
        try {
            Reader reader =Resources.getResourceAsReader(configFile);
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
