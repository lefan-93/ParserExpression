package daoImplement;

import connect.MyBatisConnector;
import dao.ParserDao;
import dto.ExpressionDto;
import dto.RuleDto;
import mapper.ExpressionMapper;
import mapper.FactMapper;
import mapper.RuleMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class ParserDaoImpl implements ParserDao {

    private SqlSession sqlSession;

    public ParserDaoImpl(String dbPropertyFile) throws Exception {
        sqlSession = MyBatisConnector.getSession(dbPropertyFile);
    }

    @Override
    public ArrayList<RuleDto> getRules() {
        return sqlSession.getMapper(RuleMapper.class).getRules();
    }

    @Override
    public ArrayList<String> getKnownFacts() {
        return sqlSession.getMapper(FactMapper.class).getKnownFacts();
    }

    @Override
    public ExpressionDto getExpression(Integer id) {
        return sqlSession.getMapper(ExpressionMapper.class).getExpression(id);
    }

    @Override
    public ArrayList<ExpressionDto> getChildren(Integer id) {
        return sqlSession.getMapper(ExpressionMapper.class).getChildren(id);
    }


}
