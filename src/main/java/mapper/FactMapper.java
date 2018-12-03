package mapper;

import java.util.ArrayList;
import java.util.HashSet;

public interface FactMapper {

    ArrayList<String> getKnownFacts();

    void insertKnownFact (String fact);

}
