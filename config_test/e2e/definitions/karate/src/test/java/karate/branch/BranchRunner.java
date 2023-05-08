package karate.branch;

import com.intuit.karate.junit5.Karate;

public class BranchRunner {


    @Karate.Test
    Karate testEmployee() {
        return Karate.run("branch").relativeTo(getClass());
    }

}
