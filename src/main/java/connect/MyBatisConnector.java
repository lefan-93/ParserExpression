package connect;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.Reader;
import java.util.Properties;

import static java.util.Objects.isNull;

public class MyBatisConnector {

    private static SqlSessionFactory sqlSessionFactory;

    private static void initSqlSessionFactory(String dbPropertyFile) throws Exception {

        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            Properties dbProperty = new Properties();
            dbProperty.load(new FileInputStream(dbPropertyFile) {
            });
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, dbProperty);
        }
    }

    private static SqlSessionFactory getSqlSessionFactory(String dbPropertyFile)throws Exception {

        if (isNull(sqlSessionFactory)) {
            initSqlSessionFactory(dbPropertyFile);
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSession(String dbPropertyFile) throws Exception{

        return getSqlSessionFactory(dbPropertyFile).openSession();
    }
}
