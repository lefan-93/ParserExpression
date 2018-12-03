package interconnection;

import java.sql.Struct;
import java.util.Set;

public interface Interconnection {

    void showFacts(Set<String> facts);

    void showMessage(String message);

}
