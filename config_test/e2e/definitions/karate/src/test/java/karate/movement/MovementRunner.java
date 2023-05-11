package karate.movement;

import com.intuit.karate.junit5.Karate;

public class MovementRunner {

    @Karate.Test
    Karate testMovement() {
        return Karate.run("movement","movementType").relativeTo(getClass());
    }
}
