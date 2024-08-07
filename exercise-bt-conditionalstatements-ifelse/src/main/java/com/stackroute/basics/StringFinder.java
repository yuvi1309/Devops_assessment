package com.stackroute.basics;

import java.io.FileReader;
import java.util.Scanner;

public class StringFinder {
  
    Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {
        //Get three strings from the user
        StringFinder sf= new StringFinder();
        String searchString= sf.getInput();
        String firstString= sf.getInput();
        String secondString= sf.getInput();
        int result= (sf.findString(searchString, firstString,secondString));
        sf.displayResult(result);
    }

    public String getInput() {
        String ex= scanner.nextLine();
        return ex;
    }

    public void displayResult(int result) {
        //displays the result
        if(result== 1){
            System.out.println("Found as expected");
        }
        else if(result==0){
            System.out.println("Not found");
        }
        else{
            System.out.println("Empty string or null");
        }
    }

    public int findString(String searchString, String firstString, String secondString) {
        if(searchString==null || firstString==null || searchString==null ||
                searchString.equals("") || firstString.equals("")|| secondString.equals("")){
            return -1;
        }

        int index1= searchString.indexOf(firstString);
        int index2=searchString.indexOf(secondString);
        if(index1!=-1 && index2!=-1 && index2 > index1 ){
            return 1;
        }
        return 0;

    }

    public void closeScanner() {
        scanner.close();
    }
}
