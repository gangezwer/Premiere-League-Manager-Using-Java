package com.example.oopcwspringangular;

import org.springframework.stereotype.Component;

import java.util.Comparator;

//This class is used to compare the win counts between the teams and display them in descending order
@Component
public class WinComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t, FootballClub t1) {

        if(t.getWinCount() > t1.getWinCount())
            return -1;
        else
        if (t.getWinCount() < t1.getWinCount())
            return 1;
        else {
            int winCount = 0;
            int  winCount1 =0;
            return Integer.compare(winCount, winCount1);
        }
    }
}

