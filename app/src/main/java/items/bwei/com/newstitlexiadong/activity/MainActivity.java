package items.bwei.com.newstitlexiadong.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import items.bwei.com.newstitlexiadong.R;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager mConnectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if(mNetworkInfo != null && mNetworkInfo.isAvailable() && mNetworkInfo.isConnected()){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                 startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();

                }
            },2000);
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this,NoNetActivity.class));
                    finish();

                }
            },2000);
        }

    }
    }


