package daoImplement;

import connect.MyBatisConnector;
import dao.ParserDao;
import dto.ExpressionDto;
import dto.RuleDto;
import mapper.ExpressionMapper;
import mapper.FactMapper;
import mapper.RuleMapper;
import model.FactExpression;
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
    public ExpressionDto getExpression(ExpressionDto expressionDto) {
        return sqlSession.getMapper(ExpressionMapper.class).getExpression(expressionDto);
    }

    @Override
    public ArrayList<ExpressionDto> getChildren(ExpressionDto expressionDto) {
        return sqlSession.getMapper(ExpressionMapper.class).getChildren(expressionDto);
    }

    @Override
    public void insertExpression(ExpressionDto expressionDto) {
        sqlSession.getMapper(ExpressionMapper.class).insertExpression(expressionDto);
        sqlSession.commit();

    }

    @Override
    public void insertChildExpressions(Integer parent_id, Integer child_id) {
        sqlSession.getMapper(ExpressionMapper.class).insertChildExpressions(parent_id,child_id);
        sqlSession.commit();
    }

    @Override
    public void insertKnownFact(String fact) {
        sqlSession.getMapper(FactMapper.class).insertKnownFact(fact);
        sqlSession.commit();

    }

    @Override
    public void insertRule(Integer expression_id, String fact) {
        sqlSession.getMapper(RuleMapper.class).insertRule(expression_id,fact);
        sqlSession.commit();
    }


}
