package JUnit;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class employeeTest {

    employeeDetails emp;
    employeeBusinessLogic businessLogic;
    @Before
    public void setUp() {
        emp = new employeeDetails("Volo", 50, 30);
        businessLogic = new employeeBusinessLogic();
    }

    @After
    public void tearDown(){
        emp = null;
        businessLogic = null;
    }

    @Test
    public void testMonthlyPayment (){
        assertEquals(150, businessLogic.monthlyEarned(emp), 0.0);
    }

    @Test
    public void testTipeEmployee(){
       String str = businessLogic.fulltime(emp);
       assertEquals("partime",str);
    }
}
