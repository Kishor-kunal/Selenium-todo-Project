package com.qacart.todo.testcases;
import com.github.javafaker.Faker;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

public class UserTest extends BaseTest {
    @Test(description="should be able to register into the application")
    public void shouldBeAbleToRegisterIntoTheApplication() {

        User user=new User();
        RegisterPage.getInstance().loadURL(driver.get());
        RegisterPage.getInstance().register(driver.get(),user);
        boolean isDisplayedWelcome = TodoPage.getInstance().isWelecomeMessageDisplayed(driver.get());
        Assert.assertTrue(isDisplayedWelcome);

    }
}
