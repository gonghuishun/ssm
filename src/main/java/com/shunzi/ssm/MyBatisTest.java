package com.shunzi.ssm;

import com.shunzi.ssm.dao.OrderDetailMapper;
import com.shunzi.ssm.dao.OrderMapper;
import com.shunzi.ssm.entity.Order;
import com.shunzi.ssm.entity.OrderDetail;
import com.shunzi.ssm.entity.Test;
import com.shunzi.ssm.entity.User;
import com.shunzi.ssm.util.MybatisSqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public class MyBatisTest {
    //SqlSession 线程不安全  不建议作为全局变量  只作为局部变量使用
//    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSession();
    @org.junit.Test
    public void mybatisTest(){
        try {
            InputStream  stream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = factoryBuilder.build(stream);
            SqlSession sqlSession = factory.openSession(true);
            Test test = sqlSession.selectOne("test.selectById",1);
            System.out.println(test.toString());
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void  userTest(){
        try{
            SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSession();
            User user = sqlSession.selectOne("user.selectById",1);
            System.out.println(user.getUserName());
            //释放资源
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //延迟加载
    @org.junit.Test
    public void mapper(){
        SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.selectByPrimaryKey(1);
        System.out.println(order.getOrderNo());
        sqlSession.close();
    }
    @org.junit.Test
    public void lazyLoad(){
        SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.lazyLoadQuery(1);
        String order_no = order.getOrderNo();
        System.out.println(order_no);
//        List<OrderDetail> orderDetailList = order.getOrderDetailList();
//        Integer amount = orderDetailList.get(0).getAmount().intValue();
//        System.out.println(amount);
    }



}
