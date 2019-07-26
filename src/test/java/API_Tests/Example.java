package API_Tests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;

public class Example {
    Assertion hardAssert = new Assertion();

    public MRData mRData;

    @Test(description = "test a set of classes represents Object of HamcrestValidation JSON response")
    public void testClass() {

        Circuit c = new Circuit();
        c.circuitId = "amarica";
        c.circuitName = "F-1";
        c.url = "www.com";

        Circuit c1 = new Circuit();
        c1.circuitId = "amarica";
        c1.circuitName = "F-1";
        c1.url = "www.com";


        CircuitTable ct = new CircuitTable();
        ct.season = "2017";
        ct.circuits.add(c);
        ct.circuits.add(c1);
        //System.out.println(ct.toString());


        hardAssert.assertTrue(ct.circuits.size() >= 1
                , "List size should be == 2");
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class MRData {

    public String xmlns;
    public String series;
    public String url;
    public String limit;
    public String offset;
    public String total;
    public CircuitTable circuitTable;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("xmlns", xmlns).append("series", series)
                .append("url", url).append("limit", limit).append("offset", offset)
                .append("total", total).append("circuitTable", circuitTable).toString();
    }

}

@JsonIgnoreProperties (ignoreUnknown = true)
class CircuitTable  {

    public String season;
    public List<Circuit> circuits = new ArrayList<>();

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("season", season).append("circuits", circuits).toString();
    }

}

@JsonIgnoreProperties (ignoreUnknown = true)
class Circuit  {

    public String circuitId;
    public String url;
    public String circuitName;
    public Location location;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("circuitId", circuitId).append("url", url).append("circuitName", circuitName).append("location", location).toString();
    }

}

@JsonIgnoreProperties (ignoreUnknown = true)
class Location  extends Circuit{

    public String lat;
    public String _long;
    public String locality;
    public String country;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("lat", lat).append("_long", _long).append("locality", locality).append("country", country).toString();
    }

}


