package com.example.oopcwspringangular;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

//This is the abstract class will act as parent class for all type of club
@Component
public abstract class SportsClub implements Serializable {
    private String name;
    private String location;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return name.equals(that.name) &&
                location.equals(that.location);
    }
    //hashcode returns the integer representing the current instance of the class
    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    //get the name of the club
    public String getName() {
        return name;
    }

    //get the name of the location of the club
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }


}


