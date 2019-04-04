package com.agoda.assignment;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaseClass {

    protected Boolean ChangePassword(String oldPassword, String newPassword) {
        Boolean flag1, flag2;

        flag1 = checkRegex(oldPassword,newPassword);
        flag2 = checkIfCharacterFrequencyGreaterThan4(newPassword);

        return flag1 && flag2;
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


    /*
     * Purpose: Returns a Map with set of characters as key and their occurrence as values values
     *
     */
    protected Map<Character, Integer> getFrequency(String str){
        return str.chars().boxed().collect(
                Collectors.toMap(
                        k -> Character.valueOf((char) k.intValue()),
                        v -> 1,
                        Integer::sum
                )
        );
    }


    /*
     * Purpose: Returns a Map with set of characters ignoring their case as key and their occurrence as values values
     *
     */
    protected Map<Character, Integer> getFrequencyIgnoringCase(String str){
        return str.toLowerCase().chars().boxed().collect(
                Collectors.toMap(
                        k -> Character.valueOf((char) k.intValue()),
                        v -> 1,
                        Integer::sum
                )
        );
    }


    /*
     * Purpose: Checks if character frequency <= 4 to return true else false
     *
     */
    protected Boolean checkIfCharacterFrequencyGreaterThan4(String password){
        return (getFrequencyIgnoringCase(password).values().stream().filter(v ->v>4).count()>0) ? false:true;
    }


}


