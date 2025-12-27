package com.qacart.todo.testcases;
import com.github.javafaker.Faker;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TodoTest extends BaseTest {
    @Test(description="should be able to add todo item into the application")
    public void shouldBeAbleToAddTodo(){
        User user=new User();
        RegisterPage.getInstance().loadURL(driver.get());
        RegisterPage.getInstance().registerUsingApi(driver.get(),user);
        TodoPage.getInstance().clickOnplusButton(driver.get());
        NewTodoPage.getInstance().addTodo(driver.get(),"learn selenium");
        //TodoPage.getInstance().load(driver);
        String text=TodoPage.getInstance().getTodoText(driver.get());
        Assert.assertEquals(text,"learn selenium");
    }
    @Test(description="should be able to delete todo item from the application")
    public void deleteToDoFromApplication(){

        User user=new User();
        RegisterPage.getInstance().loadURL(driver.get());
        RegisterPage.getInstance().registerUsingApi(driver.get(),user);
        NewTodoPage.getInstance().addTodoUsingApi(user,"learn selenium");
        TodoPage.getInstance().load(driver.get());
        TodoPage.getInstance().deleteTodo(driver.get());
        boolean notododisplayed=TodoPage.getInstance().isNoTodoMessageDisplayed(driver.get());
        Assert.assertTrue(notododisplayed);
    }
}
