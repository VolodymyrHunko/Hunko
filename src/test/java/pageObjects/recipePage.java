package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class recipePage  {

    recipePage(){

    }
    static WebDriver driver;
    public recipePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(how=How.CSS, using="span.title.ng-binding:nth-of-type(1)")
    public static WebElement food_1;

    public String getFood_1 (){
        return food_1.getText();
    }

    @FindBy(how=How.CSS, using="span.title.ng-binding:nth-of-type(3)")
    public static WebElement food_2;

    @FindBy(how=How.CSS, using="span.title.ng-binding:nth-of-type(5)")
    public static WebElement food_3;

    @FindBy(how=How.CSS, using="span.title.ng-binding:nth-of-type(7)")
    public static WebElement food_4;

    @FindBy(how=How.CSS, using="span.title.ng-binding:nth-of-type(9)")
    public static WebElement food_5;

    /**
     * method for printing Map entries
     */
    public static void printRecipe(){
        List<WebElement> allServings = driver.findElements(By.cssSelector("span.description"));
        List<WebElement> allFoods = driver.findElements(By.cssSelector("span.title"));
        int count = allServings.size();
        Map<String, String> recipe = new LinkedHashMap<String, String>();
        for (int i = 0; i < count; i++) {
            String servings = allServings.get(i).getText();
            String food = allFoods.get(i).getText();
            recipe.put(food, servings);
        }
        for(Entry<String,String> entry : recipe.entrySet() ) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

}
