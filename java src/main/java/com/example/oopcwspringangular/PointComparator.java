package com.example.oopcwspringangular;

import org.springframework.stereotype.Component;

import java.util.Comparator;
//This class is used to compare the points between the teams and display them in descending order
@Component
public class PointComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t, FootballClub t1) {

        if(t.getPoints() > t1.getPoints())
            return -1;
        else
        if (t.getPoints() < t1.getPoints())
            return 1;
        else {
            int goalDifference = t.getScoredGoalsCount()-t.getReceivedGoalsCount();
            int goalDifference1 = t1.getScoredGoalsCount()-t1.getReceivedGoalsCount();
            return Integer.compare(goalDifference1, goalDifference);
        }
    }
}
