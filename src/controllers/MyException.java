package controllers;

public class MyException extends Exception {
    String errorName;

    public MyException(String name) {
        errorName = name;
    }

    public String toString() {
        return String.format("%s", errorName);
    }
}
