package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage {
    private static TodoPage todoPage;
    //Elements
    private final By welcomeMessage= By.xpath("//*[@data-testid='welcome']");
    private final By plusButton=By.xpath("//*[@data-testid='add']");
    private final  By todoItem=By.xpath("//*[@data-testid='todo-item']");
    private final By deleteIcon=By.xpath("//*[@data-testid='delete']");
    private final By noTodoItem=By.xpath("//*[@data-testid='no-todos']");
    //Constructor
    private TodoPage() {}
    public static TodoPage getInstance(){
        if(todoPage==null){
            todoPage=new TodoPage();
        }
        return todoPage;
    }
    @Step("visiting the todo page")
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstance().getBaseUrl()+"/todo");
    }

    //methods
    @Step("checking if welcome message is displayed")
    public boolean isWelecomeMessageDisplayed(WebDriver driver){
        return  driver.findElement(welcomeMessage).isDisplayed();
    }
    @Step("clicking on plus button to add todo")
    public void clickOnplusButton(WebDriver driver){
        driver.findElement(plusButton).click();
    }
    public String getTodoText(WebDriver driver){
        return driver.findElement(todoItem).getText();
    }
    @Step("deleting todo item")
    public void deleteTodo(WebDriver driver){
        driver.findElement(deleteIcon).click();
    }
    public boolean isNoTodoMessageDisplayed(WebDriver driver){
        return driver.findElement(noTodoItem).isDisplayed();
    }
}
