package com.agoda.assignment;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaseClass {

    private final String NUMBERS="0123456789";
    private final String SPECIALCHARS="!@#$&*";

    protected Boolean ChangePassword(String oldPassword, String newPassword) {
        Boolean flag1, flag2, flag3, flag4, flag5;

        flag1 = checkRegex(oldPassword,newPassword);
        flag2 = checkIfCharacterFrequencyGreaterThan4(newPassword);
        flag3 = checkIfSpecialCharacterFrequencyGreaterThan4(newPassword);
        flag4 = checkIfNumbersCoverMoreThanFiftyPercentOfPassword(newPassword);
        flag5 = checkPasswordMatchLessThanEightyPercent(oldPassword, newPassword);

        if(!flag1)
            System.out.println("The new Password should have at least 18 alphanumeric,1 Upper case, 1 lower case ,least 1 numeric, 1 special character from !@#$&*");
        if(!flag2)
            System.out.println("The new Password should not have duplicate characters more than 4 times");
        if(!flag3)
            System.out.println("The new Password should not have special characters more than 4 times");
        if(!flag4)
            System.out.println("The new Password should not have numbers more than 50% of the length");
        if(!flag5)
            System.out.println("The new Password should not match the old password by 80% or more");

        return flag1 && flag2 && flag3 && flag4&& flag5;
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
     * i.e. 'a' and 'A' are considered different
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
     * i.e. 'a' and 'A' are considered same
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
        return (getFrequencyIgnoringCase(password).values().stream().filter(v -> v > 4).count()>0) ? false:true;
    }


    /*
     * Purpose: Checks if special character frequency <= to return true else false
     *
     */
    protected Boolean checkIfSpecialCharacterFrequencyGreaterThan4(String password){
        return (password.chars().filter(ch-> SPECIALCHARS.indexOf(ch)>-1).count() > 4) ? false:true;
    }

    /*
     * Purpose: Checks count of digits <= 50% of entire password to return true else false
     *
     */
    protected Boolean checkIfNumbersCoverMoreThanFiftyPercentOfPassword(String password){
        return (password.chars().filter(ch-> NUMBERS.indexOf(ch)>-1).count() > password.length()/2 ) ? false:true;
    }



    /*
     * Assumption : Since there is nothing mentioned about substring match considering character matching
     * Purpose: Returns the match percent of old and new password based on characters. Here 'a' and 'A' are considered different
     * The algorithm followed is : (length of (A∩B))/(Min length of (A,B)).
     * i.e. If new Password is a subset of old password then 100% match
     *      If old Password is a subset of new password then 100% match
     *      If new Password contains some part of old password or vice-versa then it is calculated as:  Length of (A∩B)/Min length of (A,B)
     *
     */
    protected Boolean checkPasswordMatchLessThanEightyPercent(String oldPassword, String newPassword){
        double shorter = Math.min(newPassword.length(),oldPassword.length());
        double longer = Math.max(newPassword.length(),oldPassword.length());
        HashMap<Character,Integer> longerPassword;
        HashMap<Character,Integer> shorterPassword;
        int count=0;
        if(newPassword.length() > oldPassword.length()) {
            longerPassword= (HashMap<Character, Integer>) getFrequency(newPassword);
            shorterPassword= (HashMap<Character, Integer>) getFrequency(oldPassword);
        }else{
            longerPassword= (HashMap<Character, Integer>) getFrequency(oldPassword);
            shorterPassword= (HashMap<Character, Integer>) getFrequency(newPassword);
        }

        for(Character ch: shorterPassword.keySet()){
            if(longerPassword.containsKey(ch)){
                count=count+ Math.min(longerPassword.get(ch),shorterPassword.get(ch));
            }
        }
        double matchPercent =(count/shorter)*100;
        return matchPercent<80.0 ? true:false;

    }



}


