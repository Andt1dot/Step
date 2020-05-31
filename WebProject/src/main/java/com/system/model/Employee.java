package com.system.model;

import com.system.enums.Genre;
import java.time.LocalDate;

public class Employee {
    
    private String name,surname;
    private Genre genre;
    private int idEmployeeDb,age,identityCode;
    private LocalDate birthdate;



    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public int getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(int identityCode) {
        this.identityCode = identityCode;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdEmployeeDb() {
        return idEmployeeDb;
    }

    public void setIdEmployeeDb(int idEmployeeDb) {
        this.idEmployeeDb = idEmployeeDb;
    }
    
    
    
    public LocalDate getBirthdate() {
        return birthdate;
    }

    
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Employee(String name, String surname, int age, int identityCode, Genre genre,LocalDate birthdate){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.identityCode = identityCode;
        this.genre = genre;
        this.birthdate = birthdate;
    }

   
    public Employee(int idEmployeeDb, String name, String surname, Genre genre, int age, int identityCode, LocalDate birthdate) {
        this.idEmployeeDb = idEmployeeDb;
        this.name = name;
        this.surname = surname;
        this.genre = genre;
        this.age = age;
        this.identityCode = identityCode;
        this.birthdate = birthdate;
    }

    
}


