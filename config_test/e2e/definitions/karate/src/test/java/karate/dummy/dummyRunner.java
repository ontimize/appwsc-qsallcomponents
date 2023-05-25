package karate.dummy;

import com.intuit.karate.junit5.Karate;

public class dummyRunner {

    @Karate.Test
    Karate testUsers() {
        return Karate.run("dummy").relativeTo(getClass());
    }

}
