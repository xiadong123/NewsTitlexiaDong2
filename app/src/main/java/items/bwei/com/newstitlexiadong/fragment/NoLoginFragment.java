package items.bwei.com.newstitlexiadong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.activity.HomeActivity;
import items.bwei.com.newstitlexiadong.activity.SystemSetingActivity;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class NoLoginFragment extends Fragment implements View.OnClickListener{
    private View view;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Boolean flag = false;
    private LinearLayout yejian;
    private ImageView login_day_night;
    private TextView login_night_day;
    private RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_no_login, null);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp = getActivity().getSharedPreferences("flaging", Context.MODE_APPEND);
        editor = sp.edit();
        editor.putBoolean("zt", flag);
        editor.commit();
        //初始化
        ininView();


    }

    private void ininView() {
        yejian = (LinearLayout) view.findViewById(R.id.yejian);
        login_day_night = (ImageView) view.findViewById(R.id.login_day_night);
        login_night_day = (TextView) view.findViewById(R.id.login_night_day);

        login_day_night.setImageResource(R.drawable.nighticon_profile);
        login_night_day.setText("夜间");

        relativeLayout = (RelativeLayout) view.findViewById(R.id.login_Rl);
        relativeLayout.setOnClickListener(this);
        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_day_night.setImageResource(R.drawable.dayicon_profile);
                login_night_day.setText("日间");

                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

                ((HomeActivity)getActivity()).getDelegate().setLocalNightMode(currentNightMode ==  Configuration.UI_MODE_NIGHT_NO?
                        AppCompatDelegate.MODE_NIGHT_YES :AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                getActivity().recreate();

                boolean hf = sp.getBoolean("zt",flag);

                if(hf==false){
                    flag = true;
                    editor.putBoolean("zt",flag);
                    editor.commit();
                    login_day_night.setImageResource(R.drawable.nighticon_profile);
                    login_night_day.setText("夜间");
                }else if(true) {
                    flag = false;
                    editor.putBoolean("zt", flag);
                    editor.commit();
                    login_day_night.setImageResource(R.drawable.dayicon_profile);
                    login_night_day.setText("日间");


                }
                }
        });

}
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_Rl:
                Intent intent = new Intent(getActivity(), SystemSetingActivity.class);
                startActivity(intent);
                break;
        }

    }

}

