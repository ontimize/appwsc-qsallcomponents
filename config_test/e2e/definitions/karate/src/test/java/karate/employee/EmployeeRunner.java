package karate.employee;

import com.intuit.karate.junit5.Karate;

class EmployeeRunner {


    @Karate.Test
    Karate testEmployee() {
        return Karate.run("employee").relativeTo(getClass());
    }


}
