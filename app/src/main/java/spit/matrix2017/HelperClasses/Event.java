/*
 * *
 *  * This file is part of Matrix2017
 *  * Created for the annual technical festival of Sardar Patel Institute of Technology
 *  *
 *  * The original contributors of the software include:
 *  * - Adnan Ansari (psyclone20)
 *  * - Tejas Bhitle (TejasBhitle)
 *  * - Mithil Gotarne (mithilgotarne)
 *  * - Rohit Nahata (rohitnahata)
 *  * - Akshay Shah (akshah1997)
 *  *
 *  * Matrix2017 is free software: you can redistribute it and/or modify
 *  * it under the terms of the MIT License as published by the Massachusetts Institute of Technology
*/

package spit.matrix2017.HelperClasses;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    //////////Custom data type for events//////////
    private String name, description, posterUrl, dates, time, venue, pocName1, pocName2, pocNumber1, pocNumber2;
    private int fees; //Calculated per person
    private ArrayList<Feedback> eventFeedbackList;

    public Event(String name, String description, String posterUrl, String dates, String time, String venue, String pocName1, String pocName2, String pocNumber1, String pocNumber2, int fees, ArrayList<Feedback> eventFeedbackList) {
        this.name = name;
        this.description = description;
        this.posterUrl = posterUrl;
        this.dates = dates;
        this.time = time;
        this.venue = venue;
        this.pocName1 = pocName1;
        this.pocName2 = pocName2;
        this.pocNumber1 = pocNumber1;
        this.pocNumber2 = pocNumber2;
        this.fees = fees;
        this.eventFeedbackList = eventFeedbackList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getDates() {
        return dates;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getPocName1() {
        return pocName1;
    }

    public String getPocName2() {
        return pocName2;
    }

    public String getPocNumber1() {
        return pocNumber1;
    }

    public String getPocNumber2() {
        return pocNumber2;
    }

    public int getFees() {
        return fees;
    }

    public ArrayList<Feedback> getEventFeedbackList() {
        return eventFeedbackList;
    }
}