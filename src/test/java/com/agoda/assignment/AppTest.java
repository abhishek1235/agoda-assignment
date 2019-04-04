package com.agoda.assignment;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest extends BaseClass
{

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][]{
                {"OldPassword", "NewPassword"}
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
            Assert.fail("New Password Doesnot Match the expected criteria");
        }
    }
}