package karate.webClient;

import com.intuit.karate.junit5.Karate;

public class WebClientConfigRunner {
    @Karate.Test
    Karate testUsers() {
        return Karate.run("webClientConfig").relativeTo(getClass());
    }
}
