package spit.matrix2017.HelperClasses;

/**
 * Created by USER on 13-08-2017.
 */

public class Feedback {
    private String feedback,rating;

    public Feedback(String feedback, String rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
