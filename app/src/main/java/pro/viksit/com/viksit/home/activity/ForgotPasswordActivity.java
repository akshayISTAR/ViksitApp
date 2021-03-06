package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.home.async.ForgotAsync;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private TextView info,tv_error_email;
    private AppCompatEditText input;
    private Button submit;
    private Button signInDifferent;
    private ProgressBar progress;
    private LinearLayout main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        title = (TextView) findViewById(R.id.tc_forgot_title);
        info = (TextView) findViewById(R.id.tv_forgot_info);
        input = (AppCompatEditText) findViewById(R.id.apet_email);
        submit = (Button) findViewById(R.id.btn_forgot_submit);
        signInDifferent = (Button) findViewById(R.id.btn_sign_in_different);
        tv_error_email = (TextView) findViewById(R.id.tv_error_email);
        progress = (ProgressBar) findViewById(R.id.progress);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        implementActions();
        input.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
// which is u had set a imeoption
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    submit.performClick();
                    return true;
                }
                return false;
            }
        });

    }

    private void implementActions(){
        signInDifferent.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ForgotPasswordActivity.this,HomeActivity.class);
        startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btn_sign_in_different) {
            System.out.println("sign in different clicked");
            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
        }else if(id == R.id.btn_forgot_submit){
            if(input == null || input.getText().toString().equalsIgnoreCase("")){
                tv_error_email.setText("Phone number cannot be blank.");
                tv_error_email.setVisibility(View.VISIBLE);
            }else{
                    new ForgotAsync(this,input.getText().toString(),tv_error_email,input.getText().toString(),progress,main_layout).execute();
            }
        }
    }
}
