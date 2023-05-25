package karate.fileManager;

import com.intuit.karate.junit5.Karate;

public class FileManagerRunner {

    @Karate.Test
    Karate testUsers() {
        return Karate.run("fileManager").relativeTo(getClass());
    }
}
