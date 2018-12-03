package interconnection;

import java.util.Iterator;
import java.util.Set;

public class UserConsoleInterconnection implements Interconnection {

    @Override
    public void showFacts(Set<String> facts) {
        Iterator itr = facts.iterator();
        // The  set of facts has been verified previously, so this method should not throw an exception.
        System.out.print(itr.next());
        while (itr.hasNext()) {
            System.out.print("," + itr.next());
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);

    }

}
