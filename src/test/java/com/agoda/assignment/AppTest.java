package com.agoda.assignment;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest extends BaseClass
{

    @DataProvider(name = "ChangePassword_Positive_Scenario")
    public static Object[][] credentials_Positive_Scenario() {
        return new Object[][]{
                {"A@quickbrown1fox3jump$over!", "1@Qu!ckBr0wnFoxJumpsOver"},// Valid Password
        };
    }

    @DataProvider(name = "ChangePassword_Negative_Scenario")
    public static Object[][] credentials_Negative_Scenario() {
        return new Object[][]{
                {"A@quickbrown1fox3jump$over!", "Pack1mybAxwathfivedAzenajug$"},//Contains 3'a' and 2'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "Pack1mybaxwathfivedazenajug$"},//Contains 5'a' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "PAck1mybAxwAthfivedAzenAjug$"},//Contains 5'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "P1ck1myb1xw1thfived1zenajug$"},//Contains 5'1' : No duplicate characters more than 4 times, a and A are considered same in this case
        };
    }


    /**
     * Testng Test
     */

    @Test(priority=0,dataProvider = "ChangePassword_Positive_Scenario")
    public void TestPassword_Positive_Scenarios( String oldPassword, String newPassword )
    {
       //Positive Scenarios
       Assert.assertTrue(ChangePassword(oldPassword,newPassword),"New Password Doesn't Match the expected criteria");
    }


    /**
     * Testng Test
     */

    @Test(priority=1,dataProvider = "ChangePassword_Negative_Scenario")
    public void TestPassword_Negative_Scenarios( String oldPassword, String newPassword )
    {
        //Negative Scenarios
        Assert.assertFalse(ChangePassword(oldPassword,newPassword),"New Password Doesn't Match the expected criteria");
    }
}