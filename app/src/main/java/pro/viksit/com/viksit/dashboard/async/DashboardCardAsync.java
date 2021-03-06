package pro.viksit.com.viksit.dashboard.async;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.activity.NoTaskActivity;
import pro.viksit.com.viksit.dashboard.adapter.CardAdapter.CarouselPagerAdapter;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 10-04-2017.
 */

public class DashboardCardAsync extends AsyncTask<String, Integer, String> {

    private Context context;
    private FragmentManager fm;
    private Gson gson = new Gson();
    private int userid;
    private SharedPreferences sharedPreferences;
    public int loop;
    public int lastposition;
    public ImageView[] dots;
    private LinearLayout pager_indicator;
    private DashboardActivity activity;
    private ViewPager pager;
    private ProgressBar progressBar;
    private RelativeLayout error_layout;

    public DashboardCardAsync(Context context,
                              FragmentManager fm, int userid,
                              SharedPreferences sharedPreferences,
                              LinearLayout pager_indicator, DashboardActivity activity, ViewPager pager, int loop, ProgressBar progressBar, RelativeLayout error_layout) {
        this.context = context;
        this.fm = fm;
        this.userid = userid;
        this.sharedPreferences = sharedPreferences;
        this.pager_indicator = pager_indicator;
        this.activity = activity;
        this.pager = pager;
        this.loop = loop;
        this.progressBar = progressBar;
        this.error_layout = error_layout;
    }

    @Override
    protected String doInBackground(String... params) {
        return postData(context, fm, sharedPreferences);
    }


    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        error_layout.setVisibility(View.GONE);

    }

    @Override
    protected void onPostExecute(String jsonresponse) {


        if (jsonresponse != null && !jsonresponse.equalsIgnoreCase("null")
                && !jsonresponse.equalsIgnoreCase("") && !jsonresponse.contains("HTTP Status")

                ) {

            if(!jsonresponse.equalsIgnoreCase("[]")){
            System.out.println("jsonresponse " + jsonresponse);
            Type listType = new TypeToken<List<DashboardCard>>() {
            }.getType();
                ArrayList<DashboardCard> dashboardCards = (ArrayList<DashboardCard>) gson.fromJson(jsonresponse, listType);


            if (dashboardCards.size() < 7) {
                loop = dashboardCards.size();
            } else {
                loop = 7;
                lastposition = (7 * (dashboardCards.size() / 7));
            }
            dots = new ImageView[dashboardCards.size()];
            for (int i = 0; i < loop; i++) {
                dots[i] = new ImageView(context);
                dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(4, 0, 4, 0);
                pager_indicator.addView(dots[i], params);
            }
            dots[0].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));
            CarouselPagerAdapter carouselPagerAdapter = new CarouselPagerAdapter(activity, fm, dashboardCards, loop);
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int pageMargin = ((metrics.widthPixels / 12));
            System.out.println("pageMargin " + pageMargin);
            pager.setClipToPadding(false);
            pager.setPadding(pageMargin, pageMargin / 2, pageMargin, 0);
            pager.setAdapter(carouselPagerAdapter);
            carouselPagerAdapter.notifyDataSetChanged();
            pager.addOnPageChangeListener(carouselPagerAdapter);
            pager.setCurrentItem(1);
            pager.setOffscreenPageLimit(3);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.dashboardcards), jsonresponse);
            editor.apply();
            editor.commit();

        } else{
                Intent notask = new Intent(context, NoTaskActivity.class);
                context.startActivity(notask);
                ((Activity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

            }


            } else {
                error_layout.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(View.GONE);

    }


    private String postData(Context context,
                            FragmentManager fm, SharedPreferences sharedPreferences) {

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + (context.getResources().getString(R.string.dashboardcardurl).replaceAll("user_id", userid + "")));
        httpUtil.setType("GET");
        String jsonresponse = httpUtil.getStringResponse();
        return jsonresponse;
    }

}
