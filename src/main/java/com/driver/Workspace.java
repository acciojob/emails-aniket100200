package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail
{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId)
    {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar=new ArrayList<>();

    }

    public void addMeeting(Meeting meeting)
    {
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings()
    {

        //Greedy se solve hoga brother..
        if(calendar.size()==0)return 0;
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        Collections.sort(calendar,(a,b)->
        {
            if(a.getStartTime().isAfter(b.getStartTime()))return 1;
            else if(a.getStartTime().isBefore(b.getStartTime()))return -1;
            else return 0;
        });

        //we have sorted order now you can calculate the number of meetings you can attained..
        int count=0;
        LocalTime pStart=calendar.get(0).getStartTime();
        LocalTime pEnd=calendar.get(0).getEndTime();
        int n=calendar.size();
        for(int i=1;i<n;i++)
        {
           LocalTime cStart=calendar.get(i).getStartTime();
           LocalTime cEnd=calendar.get(i).getEndTime();
           if(cStart.isAfter(pEnd))
           {
               count++;
               pStart=cStart;
               pEnd=cEnd;
           }
           else
           {
               //means overlapping..
               if(pEnd.isAfter(cEnd))
               {
                   pStart=cStart;
                   pEnd=cEnd;
               }

           }
        }
        return count+1;

    }
}
