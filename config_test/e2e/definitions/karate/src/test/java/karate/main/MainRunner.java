package karate.main;

import com.intuit.karate.junit5.Karate;

public class MainRunner {
    @Karate.Test
    Karate testUsers() {
        return Karate.run("main").relativeTo(getClass());
    }
}
