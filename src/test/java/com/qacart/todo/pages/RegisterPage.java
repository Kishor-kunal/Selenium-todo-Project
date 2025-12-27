package com.qacart.todo.pages;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private  static RegisterPage registerPage;
    //Elements
    //private final By signupButtonInput= By.xpath("//*[text()='Signup']");
    private final By firstNameInput=By.xpath("//*[@data-testid='first-name']");
    private final By lastNameInput=By.xpath("//*[@data-testid='last-name']");
    private final By emailInput=By.xpath("//*[@data-testid='email']");
    private final By passwordInput=By.xpath("//*[@data-testid='password']");
    private final By confirmPasswordInput=By.xpath("//*[@data-testid='confirm-password']");
    private final By submitInput=By.xpath("//*[@data-testid='submit']");



    //Constructor
    private RegisterPage(){}
    public static RegisterPage getInstance(){
        if(registerPage==null){
            registerPage=new RegisterPage();
        }
        return registerPage;
    }

    //Methods
    @Step("Register user UI")
    public void register(WebDriver driver, User user){
        //driver.findElement(signupButtonInput).click();
        driver.findElement(firstNameInput).sendKeys(user.getFirstName());
        driver.findElement(lastNameInput).sendKeys(user.getLastName());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(confirmPasswordInput).sendKeys(user.getPassword());
        driver.findElement(submitInput).click();
    }
    @Step("visit the registration page")
    public void loadURL(WebDriver driver){
        driver.get(ConfigUtils.getInstance().getBaseUrl()+"/signup");

    }
    @Step("Register user using API")
    public void registerUsingApi(WebDriver driver, User user) {
        // call API to register and set cookies for browser session
        Response res= UserApi.getInstance().register(user);
        String access_token=res.path("access_token");
        String userID=res.path("userID");
        String firstName=res.path("firstName");
        // set access token on the user instance
        user.setAccessToken(access_token);
        Cookie accessTokenCookie=new Cookie("access_token",access_token);
        Cookie userIDCookie=new Cookie("userID",userID);
        Cookie firstNameCookie=new Cookie("firstName",firstName);
        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIDCookie);
        driver.manage().addCookie(firstNameCookie);
        RegisterPage.getInstance().loadURL(driver);
    }
}
