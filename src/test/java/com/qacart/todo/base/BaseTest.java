package com.qacart.todo.base;
import com.qacart.todo.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseTest {

   protected ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    @BeforeMethod
    public void setup(){

        WebDriver driver=new DriverFactory().initializeDriver();
        this.driver.set(driver);
    }
    @AfterMethod
    public void teardown(ITestResult result){
        String testcaseName=result.getMethod().getMethodName();
        File destfile=new File("target"+File.separator+"screenshots"+File.separator+testcaseName+".png");
        takeScreenShot(destfile);
        driver.get().quit();
    }
    public void takeScreenShot(File destfile){
        File file=((TakesScreenshot)driver.get()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file,destfile);
            InputStream is=new FileInputStream(destfile);
            Allure.addAttachment("screenshot",is);
    }catch (IOException e){
            throw new RuntimeException(e);
}
    }
}
