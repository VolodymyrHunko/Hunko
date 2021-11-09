package seleniumTesting;

import pageObjects.PageObjectExample;

/**
 * to keep the state of common class and share it among all Steps files
 * the solution is PicoContainer
 */
public class TestContext {


    PageObjectExample page;

    public TestContext(){

        page = new PageObjectExample();
    }


    public PageObjectExample getPage(){
        return page;
    }
}
