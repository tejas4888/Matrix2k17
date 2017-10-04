package spit.vortex17.HelperClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import spit.vortex17.R;

/**
 * Created by USER on 02-10-2017.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {


    Context context;

    ArrayList<String> arrayList;
    Integer[] drawableList;

    public ScheduleAdapter(ArrayList<String> arrayList, Integer[] drawableList) {
        this.arrayList = arrayList;
        this.drawableList = drawableList;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType) {
        return new ScheduleAdapter.ViewHolder(LayoutInflater.from(paren.getContext()).inflate(R.layout.schedule_child,paren,false));
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        holder.category.setText(arrayList.get(position));
        Picasso.with(context).load(drawableList[position]).into(holder.imageView);

        //holder.imageView.setImageResource(drawableList[position]);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView category;
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            category = (TextView)itemView.findViewById(R.id.sch_day);
            imageView = (ImageView)itemView.findViewById(R.id.sch_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if(getLink()=="NULL")
            //  Toast.makeText(context,"Webpage not available for selected sponsor", Toast.LENGTH_SHORT).show();
            //else
            //  context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getLink())));
        }


    }
}
