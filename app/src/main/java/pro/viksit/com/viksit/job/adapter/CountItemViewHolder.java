package pro.viksit.com.viksit.job.adapter;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 25-03-2017.
 */

public class CountItemViewHolder extends RecyclerView.ViewHolder {

    TextView title,subtitle,expiry;
    ImageView image,timer;


    public CountItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_opening_item_title);
        subtitle = (TextView) itemView.findViewById(R.id.tv_opening_item_subtitle);
        expiry = (TextView) itemView.findViewById(R.id.tv_opening_item_status);
        image = (ImageView) itemView.findViewById(R.id.iv_opening_item_image);
        timer = (ImageView)itemView.findViewById(R.id.iv_time);
    }

    public void render(String titles,String subtitles,String expirys, int imageResID){
        title.setText(titles);
        subtitle.setText(subtitles);
        expiry.setText(expirys);
        image.setImageResource(imageResID);
        timer.setImageResource(R.mipmap.ic_access_time_white_36dp);
        timer.setColorFilter(timer.getContext().getResources().getColor(R.color.hint_color), PorterDuff.Mode.SRC_ATOP);
    }

}
