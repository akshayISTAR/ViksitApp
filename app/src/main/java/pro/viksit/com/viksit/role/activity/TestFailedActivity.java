package pro.viksit.com.viksit.role.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class TestFailedActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView header, desc;
    private ImageView image;
    private Button tapToContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_failed);

        header = (TextView) findViewById(R.id.tv_test_failed_header);
        image = (ImageView) findViewById(R.id.iv_test_failed_image);
        desc = (TextView) findViewById(R.id.tv_test_failed_desc);
        tapToContinue = (Button) findViewById(R.id.btn_test_failed_tap);


        implementActions();
    }

    public void implementActions(){
        tapToContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == tapToContinue.getId()){
            //do something
        }
    }
}
