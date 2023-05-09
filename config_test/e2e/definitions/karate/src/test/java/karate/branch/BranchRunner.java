package karate.branch;

import com.intuit.karate.junit5.Karate;

public class BranchRunner {


    @Karate.Test
    Karate testBranch() {
        return Karate.run("branch", "account", "accountType").relativeTo(getClass());
    }

}
