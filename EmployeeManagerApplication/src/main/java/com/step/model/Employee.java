
package com.step.model;
import com.step.enums.Genre;
import java.time.LocalDate;
import java.util.Objects;


public class Employee   {

    
    String name,surname;
    Genre genre;
    int idEmployeeDb,age,identityCode;
    LocalDate birthdate;

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.age != other.age) {
            return false;
        }
        if (this.identityCode != other.identityCode) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (this.genre != other.genre) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        return true;
    }

   
    public String getName() {
        return name;
    }

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

