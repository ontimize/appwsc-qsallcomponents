package karate.users;

import com.intuit.karate.junit5.Karate;

public class usersRunner {

    @Karate.Test
    Karate testUsers() {
        return Karate.run("users", "roles", "rolesForUser").relativeTo(getClass());
    }
}
