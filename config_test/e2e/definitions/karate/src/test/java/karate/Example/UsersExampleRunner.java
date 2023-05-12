package karate.Example;

import com.intuit.karate.junit5.Karate;

class UsersExampleRunner {
    
    @Karate.Test
    Karate testUsersExample() {
        return Karate.run("usersExample").relativeTo(getClass());
    }    

}
