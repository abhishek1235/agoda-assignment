package com.agoda.assignment;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaseClass {

    protected Boolean ChangePassword(String oldPassword, String newPassword) {
        Boolean flag1, flag2, flag3, flag4;

        flag1 = checkRegex(oldPassword,newPassword);
        flag2 = checkIfCharacterFrequencyGreaterThan4(newPassword);
        flag3 = checkIfSpecialCharacterFrequencyGreaterThan4(newPassword);
        flag4 = checkIfNumbersCoverMoreThanFiftyPercentOfPassword(newPassword);

        return flag1 && flag2 && flag3 && flag4;
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


    /*
     * Purpose: Checks if special character frequency <= to return true else false
     *
     */
    protected Boolean checkIfSpecialCharacterFrequencyGreaterThan4(String password){
        return (password.chars().filter(ch-> "!@#$&*".indexOf(ch)>-1).count() > 4) ? false:true;
    }

    /*
     * Purpose: Checks count of digits <= 50% of entire password to return true else false
     *
     */
    protected Boolean checkIfNumbersCoverMoreThanFiftyPercentOfPassword(String password){
        return (password.chars().filter(ch-> "0123456789".indexOf(ch)>-1).count() > password.length()/2 ) ? false:true;
    }


}


