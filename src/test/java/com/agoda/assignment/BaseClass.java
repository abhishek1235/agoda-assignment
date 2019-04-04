package com.agoda.assignment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseClass {

    protected Boolean ChangePassword(String oldPassword, String newPassword) {
        Boolean flag1;

        flag1 = checkRegex(oldPassword,newPassword);

        return flag1;
    };




    /*
     * Purpose: Checks for  1. At least 18 alphanumeric characters and list of special chars !@#$&*
     *                      2. At least 1 Upper case, 1 lower case ,least 1 numeric, 1 special character
     *
     */
    protected boolean checkRegex(String oldPassword, String newPassword){
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$&*])[A-Za-z\\d!@#$&*]{18,}$");
        Matcher matcher = pattern.matcher(newPassword);
        return matcher.matches();
    }


}


