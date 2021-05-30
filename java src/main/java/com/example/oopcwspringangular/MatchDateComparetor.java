package com.example.oopcwspringangular;

import java.util.Comparator;

//This class is used to compare the dates of matches happened between the teams in ascending order of dates
public class MatchDateComparetor implements Comparator<Match> {

    @Override
    public int compare(Match t1,Match t2){
        return t1.getDate().compareTo(t2.getDate());
    }
}
