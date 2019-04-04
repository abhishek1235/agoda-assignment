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
                {"A@quickbrown1fox3jump$over!", "P@ck1myb#xw!thfivedzenjug$"},// Boundary Value : more than 4 special characters present
                {"A@quickbrown1fox3jump$over!", "Pack1mybaxwathfivedazenjug$"},//Boundary Value : exactly 4 duplicate characters 4 'a'
                {"A@quickbrown1fox3jump$over!", "P1a2r3r2o4t3i5$8b5i6r7d9"},//Boundary Value : exactly 50% password is number
        };
    }

    @DataProvider(name = "ChangePassword_Negative_Scenario")
    public static Object[][] credentials_Negative_Scenario() {
        return new Object[][]{
                {"A@quickbrown1fox3jump$over!", "Pack1mybAxwathfivedAzenajug$"},//Contains 3'a' and 2'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "Pack1mybaxwathfivedazenajug$"},//Contains 5'a' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "PAck1mybAxwAthfivedAzenAjug$"},//Contains 5'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "P1ck1myb1xw1thfived1zenajug$"},//Contains 5'1' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "P@ck1myb#xw!thfived&zenjug$"},//more than 4 special characters present
                {"A@quickbrown1fox3jump$over!", "P1232b435f567e89100382n7$"},//more than 50% password is number
                {"A@quickbrown1fox3jump$over!", "A@quickbrown1fox3jump$over!"},//same password 100% match
                {"A@quickbrown1fox3jump$over!", "A@quickbrown1fox3jump$over!lzy"},//new password is subset of old password so it is a 100% match
                {"A@quickbrown1fox3jump$over!", "A@quickbrown1fox3jump$o"},// new password is subset of old password so it is a 100% match
                {"A@quickbrown1fox3jump$over!", "A@quickbrown1fox3jump$olazy"},// new password is subset of old password so it is a 100% match
                {"A@quickbrownfoxjump$over!", "A@qvickbrownflxjump$e123#Zdog"},// More than 80% match
                {"A@quickbrownfoxjump$over!", "A@qvickbrownflxjump$e123#"},// Boundary Value : 80% match for new password
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