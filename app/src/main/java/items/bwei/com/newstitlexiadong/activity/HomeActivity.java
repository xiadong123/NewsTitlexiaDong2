package items.bwei.com.newstitlexiadong.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.fragment.CareFragment;
import items.bwei.com.newstitlexiadong.fragment.HomeFragment;
import items.bwei.com.newstitlexiadong.fragment.NoLoginFragment;
import items.bwei.com.newstitlexiadong.fragment.VideoFragment;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frameLayout;
    private LinearLayout ll_home;
    private LinearLayout ll_video;
    private LinearLayout ll_care;
    private LinearLayout ll_noLogin;
    private ImageView iv_home;
    private ImageView iv_video;
    private ImageView iv_care;
    private ImageView iv_noLogin;
    private RadioButton rbt_home;
    private RadioButton rbt_video;
    private RadioButton rbt_care;
    private RadioButton rbt_noLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        //布局ID
        ll_home = (LinearLayout) findViewById(R.id.ll_Home);
        ll_video = (LinearLayout) findViewById(R.id.ll_Video);
        ll_care = (LinearLayout) findViewById(R.id.ll_Care);
        ll_noLogin = (LinearLayout) findViewById(R.id.ll_NoLogin);
        //找图片ID
        iv_home = (ImageView) findViewById(R.id.iv_Home);
        iv_video = (ImageView) findViewById(R.id.iv_Video);
        iv_care = (ImageView) findViewById(R.id.iv_Care);
        iv_noLogin = (ImageView) findViewById(R.id.iv_NoLogin);

        //找到buttonID
        rbt_home = (RadioButton) findViewById(R.id.rbt_Home);
        rbt_video = (RadioButton) findViewById(R.id.rbt_Video);
        rbt_care = (RadioButton) findViewById(R.id.rbt_Care);
        rbt_noLogin = (RadioButton) findViewById(R.id.rbt_NoLogin);

        iv_home.setImageResource(R.drawable.b_newhome_tabbar_press);
        rbt_home.setTextColor(Color.RED);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        ll_home.setOnClickListener(this);
        ll_video.setOnClickListener(this);
        ll_care.setOnClickListener(this);
        ll_noLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
                iv_home.setImageResource(R.drawable.b_newhome_tabbar_press);
                rbt_home.setTextColor(Color.RED);

                iv_video.setImageResource(R.drawable.b_newvideo_tabbar);
                rbt_video.setTextColor(Color.BLACK);

                iv_care.setImageResource(R.drawable.b_newcare_tabbar);
                rbt_care.setTextColor(Color.BLACK);

                iv_noLogin.setImageResource(R.drawable.b_newnologin_tabbar);
                rbt_noLogin.setTextColor(Color.BLACK);
                break;

            case R.id.ll_Video:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new VideoFragment()).commit();
                iv_home.setImageResource(R.drawable.b_newhome_tabbar);
                rbt_home.setTextColor(Color.BLACK);

                iv_video.setImageResource(R.drawable.b_newvideo_tabbar_press);
                rbt_video.setTextColor(Color.RED);

                iv_care.setImageResource(R.drawable.b_newcare_tabbar);
                rbt_care.setTextColor(Color.BLACK);

                iv_noLogin.setImageResource(R.drawable.b_newnologin_tabbar);
                rbt_noLogin.setTextColor(Color.BLACK);
                break;

            case R.id.ll_Care:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new CareFragment()).commit();
                iv_home.setImageResource(R.drawable.b_newhome_tabbar);
                rbt_home.setTextColor(Color.BLACK);

                iv_video.setImageResource(R.drawable.b_newvideo_tabbar);
                rbt_video.setTextColor(Color.BLACK);

                iv_care.setImageResource(R.drawable.b_newcare_tabbar_press);
                rbt_care.setTextColor(Color.RED);

                iv_noLogin.setImageResource(R.drawable.b_newnologin_tabbar);
                rbt_noLogin.setTextColor(Color.BLACK);
                break;

            case R.id.ll_NoLogin:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new NoLoginFragment()).commit();
                iv_home.setImageResource(R.drawable.b_newhome_tabbar);
                rbt_home.setTextColor(Color.BLACK);

                iv_video.setImageResource(R.drawable.b_newvideo_tabbar);
                rbt_video.setTextColor(Color.BLACK);

                iv_care.setImageResource(R.drawable.b_newcare_tabbar);
                rbt_care.setTextColor(Color.BLACK);

                iv_noLogin.setImageResource(R.drawable.b_newnologin_tabbar_press);
                rbt_noLogin.setTextColor(Color.RED);
                break;

        }
    }


}