package spit.matrix2017.HelperClasses;

/**
 * Created by USER on 13-08-2017.
 */

public class Feedback {
    private String feedback,uid;
    private int rating;

    public Feedback(String feedback, int rating,String uid) {
        this.feedback = feedback;
        this.rating = rating;
        this.uid = uid;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getUid() {
        return uid;
    }

    public int getRating() {
        return rating;
    }
}
