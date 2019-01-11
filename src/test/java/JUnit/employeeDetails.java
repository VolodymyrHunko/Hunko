package JUnit;

public class employeeDetails {
    String name;
    double rate;
    int hours;
    employeeDetails(String name, double rate, int hours){
        this.name = name;
        this.rate = rate;
        this.hours = hours;
    }

    public double getRate(){
        return rate;
    }

    public int getHours(){
        return hours;
    }
}
