package com.agoda.assignment;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest extends BaseClass
{

    @DataProvider(name = "ChangePassword_Positive_Scenario")
    public static Object[][] credentials_Positive_Scenario() {
        return new Object[][]{
                {"A@quickbrownf0xjump$over!", "1@Qu!ckBr0wnFoxJumpsOver"},    //0. Valid Password
                {"A@quickbrownf0xjump$over!", "P@ck1myb#xw!thfivedzenjug$"},  //1. Boundary Value : Exactly 4 special characters present
                {"A@quickbrownf0xjump$over!", "P@ck1myb#xw!thfivedzenjug"},   //2. Boundary Value : Exactly 3 special characters present
                {"A@quickbrownf0xjump$over!", "Pack1mybaxwathfivedazenjug$"}, //3. Boundary Value : exactly 4 duplicate characters 4 'a'
                {"A@quickbrownf0xjump$over!", "Pack1mybaxwathfivedozenjug$"}, //4. Boundary Value : exactly 3 duplicate characters 4 'a'
                {"A@quickbrownf0xjump$over!", "Pack1mybAxwAthfivedazenjug$"}, //5. Boundary Value : exactly 4 duplicate characters 2 'a' and 2 'A'
                {"A@quickbrownf0xjump$over!", "P1a2r3r2o4t3i5$8b5i6r7d9"},    //6. Boundary Value : exactly 50% password is number
                {"A@quickbrownf0xjump$over!", "P1a2r3r2o4t3i5$8b5ird"},       //7. Boundary Value : less than 50% password is number
                {"A@quickbrownf0xjump$over!", "A@qvickbrownflxjum$e123#"},    //8. Boundary Value : 79% match for new password
                {"A@quickbrownf0xjump$over!", "Pack1myboxw!thfived0zenjug$"}, //9. Boundary Value : Exactly 1 Upper case and other valid pre-requisite
                {"A@quickbrownf0xjump$over!", "Pack1myboxw!thfivedozenjug$"}, //10.Boundary Value : Exactly 1 Number and other valid pre-requisite
                {"A@quickbrownf0xjump$over!", "Pack1myboxwithfived0zenjug$"}, //11.Boundary Value : Exactly 1 Special Character and other valid pre-requisite
                {"A@quickbrownf0xjump$over!", "pACK1MYBOXWITHFIVEDOZENJUG$"}, //12.Boundary Value : Exactly 1 Lower  case and other valid pre-requisite
                {"A@quickbrownf0xjump$over!", "Pack1myboxwithfivedozenjug$"}, //13.Boundary Value : 79% match for new password
                {"A@quickbrownf0xjump$over!", "Pack1myboxwithfiv$"},          //14.Boundary Value : Exactly 18 Character length
        };
    }

    @DataProvider(name = "ChangePassword_Negative_Scenario")
    public static Object[][] credentials_Negative_Scenario() {
        return new Object[][]{
                {"A@quickbrownf0xjump$over!", "Pack1myboxwithfi$"},            //15. Boundary Value : Exactly 17 Character length
                {"A@quickbrownf0xjump$over!", "pack1myboxwithfivedozenjug$"},  //16. No Uppercase
                {"A@quickbrownf0xjump$over!", "PACK1MYBOXWITHFIVEDOZENJUG$"},  //17. No Lowercase
                {"A@quickbrownf0xjump$over!", "PackmybAxwathfivedAzenajug$"},  //18. No Numbers
                {"A@quickbrownf0xjump$over!", "Pack1mybAxwathfivedAzenajug$"}, //19. Contains 3'a' and 2'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrownf0xjump$over!", "Pack1mybaxwathfivedazenajug$"}, //20. Contains 5'a' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrownf0xjump$over!", "PAck1mybAxwAthfivedAzenAjug$"}, //21. Contains 5'A' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrownf0xjump$over!", "P1ck1myb1xw1thfived1zenajug$"}, //22. Contains 5'1' : No duplicate characters more than 4 times, a and A are considered same in this case
                {"A@quickbrownf0xjump$over!", "P@ck1myb#xw!thfived&zenjug$"},  //23. More than 4 special characters present
                {"A@quickbrownf0xjump$over!", "P1232b435f567e89100382n7$"},    //24. More than 50% password is number
                {"A@quickbrownf0xjump$over!", "A@quickbrownf0xjump$over!"},    //25. Same password 100% match
                {"A@quickbrownf0xjump$over!", "A@quickbrownf0xjump$over!lzy"}, //26. New password is superset of old password so it is a 100% match
                {"A@quickbrownf0xjump$over!", "A@quickbrownf0xjump$o"},        //27. New password is subset of old password so it is a 100% match
                {"A@quickbrownfoxjump$over!", "A@qvickbrownflxjump$e123#Zdog"},//28. More than 80% match
                {"A@quickbrownfoxjump$over!", "A@qvickbrownflxjump$e123#"},    //29. Boundary Value : 80% match for new password
        };
    }


    /**
     * Positive Scenarios with True Assertion
     */

    @Test(priority=0,dataProvider = "ChangePassword_Positive_Scenario")
    public void TestPassword_Positive_Scenarios( String oldPassword, String newPassword )
    {
       //Positive Scenarios
       Assert.assertTrue(ChangePassword(oldPassword,newPassword),"New Password Doesn't Match the expected criteria");
    }


    /**
     * Negative Scenarios with True Assertion
     */

    @Test(priority=1,dataProvider = "ChangePassword_Negative_Scenario")
    public void TestPassword_Negative_Scenarios( String oldPassword, String newPassword )
    {
        //Negative Scenarios
        Assert.assertFalse(ChangePassword(oldPassword,newPassword),"New Password Doesn't Match the expected criteria");
    }
}