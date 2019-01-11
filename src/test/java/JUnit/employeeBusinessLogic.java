package JUnit;

public class employeeBusinessLogic {
     public double monthlyEarned(employeeDetails employee){
        return employee.rate * employee.hours;
    }

     public String fulltime (employeeDetails emmployee){
        if (emmployee.hours >= 40){
            return "fulltime";
        }else
            return "parttime";
    }
}
