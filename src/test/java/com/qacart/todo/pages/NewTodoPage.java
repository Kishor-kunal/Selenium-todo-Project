package com.qacart.todo.pages;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.models.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewTodoPage {
    private static NewTodoPage newTodoPage;
    //Elements
    private final By newTodoItem= By.xpath("//*[@data-testid='new-todo']");
    private final By newTodoSubmit=By.xpath("//*[@data-testid='submit-newTask']");

    //Constructor
    private NewTodoPage(){}
    public static NewTodoPage getInstance(){
        if(newTodoPage==null){
            newTodoPage=new NewTodoPage();
        }
        return newTodoPage;
    }

    //Methods
    @Step("adding new todo item: {item}")
    public void addTodo(WebDriver driver,String item){
        driver.findElement(newTodoItem).sendKeys(item);
        driver.findElement(newTodoSubmit).click();
    }
    @Step("adding todo item using API: {item}")
    public void addTodoUsingApi(User user, String item) {
        // TODO Auto-generated method stub
        TodoApi.getInstance().addTodo(user,item);
    }

}


