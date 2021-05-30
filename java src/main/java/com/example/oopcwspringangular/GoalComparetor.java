package com.example.oopcwspringangular;

import org.springframework.stereotype.Component;

import java.util.Comparator;

//This class is used to compare the goals scored between the teams adn display them in descending order
@Component
public class GoalComparetor implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t, FootballClub t1) {

        if(t.getScoredGoalsCount() > t1.getScoredGoalsCount())
            return -1;
        else
        if (t.getScoredGoalsCount() < t1.getScoredGoalsCount())
            return 1;
        else {
            int goalCount = 0;
            int goalCount1 = 0;
            return Integer.compare(goalCount, goalCount1);
        }
    }
}

