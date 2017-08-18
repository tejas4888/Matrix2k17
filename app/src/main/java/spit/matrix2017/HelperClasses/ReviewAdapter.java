package spit.matrix2017.HelperClasses;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import spit.matrix2017.R;

/**
 * Created by Tejas on 19-08-2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    ArrayList<Feedback> feedbacks;
    Context context;

    public ReviewAdapter(ArrayList<Feedback> feedbacks,Context context)
    {
        this.feedbacks=feedbacks;
        this.context=context;
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reviewitem, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewViewHolder holder, int position) {

        //Perform operation here
        //Eg.
        Feedback f = feedbacks.get(position);
        holder.emailtext.setText(f.getEmail());
        holder.feedbacktext.setText(f.getFeedback());
        holder.ratingtext.setText(f.getRating() + "/5");


    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView emailtext,ratingtext,feedbacktext;
        CardView reviewcontainer;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            emailtext=(TextView)itemView.findViewById(R.id.review_email);
            ratingtext=(TextView)itemView.findViewById(R.id.review_rating);
            feedbacktext=(TextView)itemView.findViewById(R.id.review_review);
            reviewcontainer=(CardView)itemView.findViewById(R.id.review_cardcontainer);
        }
    }
}
