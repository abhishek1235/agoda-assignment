package com.agoda.assignment;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest extends BaseClass
{

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][]{
                {"A@quickbrown1fox3jump$over!", "1@Qu!ckBr0wnFoxJumpsOver"},// Valid Password
                {"A@quickbrown1fox3jump$over!", "Pack1mybAxwathfivedAzenajug$"},//Contains 3'a' and 2'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "Pack1mybaxwathfivedazenajug$"},//Contains 5'a' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "PAck1mybAxwAthfivedAzenAjug$"},//Contains 5'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrown1fox3jump$over!", "P1ck1myb1xw1thfived1zenajug$"},//Contains 5'1' : No duplicate characters more than 4 times, a and A are considered same in this case
        };
    }


    /**
     * Testng Test
     */

    @Test(priority=0,dataProvider = "Authentication")
    public void TestPassword( String oldPassword, String newPassword )
    {
        Boolean flag = ChangePassword(oldPassword,newPassword);
        if(flag){
            Assert.assertTrue(ChangePassword(oldPassword,newPassword));
        }else{
            Assert.fail("New Password Doesn't Match the expected criteria");
        }
    }
}