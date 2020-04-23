package com.step.validator;

import com.step.enums.Genre;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public interface Validator {
   
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
   
    public static String verificationInputString(String inputKeyboard)throws IOException{   
             while(true){
                 for (char c : inputKeyboard.toCharArray()){
                    if (!Character.isDigit(c)){           
                        return inputKeyboard;}
                    else{
                     System.out.println("Wrong input, please repeat insert **character type**  !!!! ");
                     inputKeyboard=reader.readLine();
                     break;
                    }
              }         
        }
   }

   public static int verificationInputInteger(String inputKeyboard) throws IOException{
      int responsInteger=0;
           boolean isDigit = false;    
              while(!isDigit){

                  for (char c: inputKeyboard.toCharArray()){
                     if (Character.isDigit(c)){         
                         responsInteger=Integer.parseInt(inputKeyboard);
                         isDigit=true; 
                         break;}
                     else{
                      System.out.println("Wrong input, please repeat insert **number type**  !!!! ");
                      inputKeyboard=reader.readLine();
                      break;
                     }
               }         
         }   
                   return responsInteger; 
}
        

public static Genre verificationInputGenre(String inputKeyboard)throws IOException{
        while(true){  
        if(inputKeyboard.contentEquals(Genre.Male.toString().toUpperCase())||inputKeyboard.contentEquals(Genre.Male.toString().toLowerCase())||inputKeyboard.contentEquals(Genre.Male.toString())){
            return Genre.Male;
       }   
        else if(inputKeyboard.contentEquals(Genre.Female.toString().toUpperCase())||inputKeyboard.contentEquals(Genre.Female.toString().toLowerCase())||inputKeyboard.contentEquals(Genre.Female.toString())){
            return Genre.Female;
       }        
        else
            System.out.println("Error input, please repeat insert genre and respect condition Male or Female"); 
            inputKeyboard=reader.readLine();
        }       
    }
    
public static LocalDate verificationInputBirthDate(String inputKeyboard) throws IOException{
      LocalDate dateBirth = null; 
   
       try{
       dateBirth=LocalDate.parse(inputKeyboard, DateTimeFormatter.ofPattern("dd.MM.yyyy")); 
       }
       catch(DateTimeParseException ex){ 
           System.out.println("Error input format BirthDate *".concat(inputKeyboard).concat("*  please repeat insert BirthDate and respect format for example **|01.01.1970|** ")); 
       dateBirth=verificationInputBirthDate(reader.readLine());
       }
     return dateBirth; 
    }
}





