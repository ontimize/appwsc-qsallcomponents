package karate.customer;

import com.intuit.karate.junit5.Karate;

public class CustomerRunner {

    @Karate.Test
    Karate testCustomer() {
        return Karate.run("customer", "customerType", "customerAccount").relativeTo(getClass());
    }
}
