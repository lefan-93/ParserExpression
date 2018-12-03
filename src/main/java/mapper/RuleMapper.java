package mapper;

import dto.RuleDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface RuleMapper {

    ArrayList<RuleDto> getRules();

    void insertRule(@Param("expression_id") Integer id,@Param("fact") String fact);

}
