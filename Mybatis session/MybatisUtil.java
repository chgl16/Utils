package io.bbc13.util;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil  {
	// ���������ļ���Ϣ���������ݿ�����ӣ����������ݿ��������
	public static SqlSession openSqlSession() throws Exception {
		String sqlConfigXML = "mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(sqlConfigXML);
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
		return build.openSession();
	}
}
