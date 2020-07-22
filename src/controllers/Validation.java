package controllers;

public class Validation {
    public static boolean isValidName(String name) {// validates name
        if (name.matches("[A-Za-z ,&:0-9]+"))
            return true;
        else
            return false;
    }

    public static boolean isValidId(String id) {
        if (id.matches("[0-9]+"))
            return true;
        else
            return false;
    }

    public static boolean isValidType(String name) {// validates name
        if (name.matches("[A-Za-z ,&:]+"))
            return true;
        else
            return false;
    }

    public static boolean isValidShowTime(String time) {
        if (time.matches("[0-2][0-4]:[0-5][0-9]||[0-1][0-9]:[0-5][0-9]|"))
            return true;
        else
            return false;
    }

    public static boolean isValidCharacter(String str){
        if(str.matches("[A-Za-z]+"))
            return true;
        else
            return false;
    }


    public static void main(String[] args) {
        System.out.println(Validation.isValidShowTime("24:00"));
        System.out.println(Validation.isValidId("1"));
        System.out.println(Validation.isValidCharacter("A "));
    }
}
