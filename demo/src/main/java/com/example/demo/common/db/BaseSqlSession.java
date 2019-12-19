package com.example.demo.common.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseSqlSession {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public SqlSession getDefaultSqlSession() {
        SqlSession sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSession;

    }
}
