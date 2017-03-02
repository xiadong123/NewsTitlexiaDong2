package items.bwei.com.newstitlexiadong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.activity.HomeActivity;

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
    private ImageView qq;
    private Tencent mTencent;
    private IUiListener loginListener;
    private ImageView phone;
    private ImageView weixin;
    private TextView nana;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_no_login, null);


        return view;


    }

    private void qqLogin() {
            qq = (ImageView) view.findViewById(R.id.QQdl);
            qq.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    mTencent = Tencent.createInstance("1105602574", getActivity());

                    mTencent.login(NoLoginFragment.this, "all", loginListener);

                    //登录成功后回调该方法,可以跳转相关的页面
                    loginListener = new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            //登录成功后回调该方法,可以跳转相关的页面

                            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();

                            JSONObject object = (JSONObject) o;

                            try {

                                String accessToken = object.getString("access_token");

                                String expires = object.getString("expires_in");

                                String openID = object.getString("openid");

                                mTencent.setAccessToken(accessToken, expires);

                                mTencent.setOpenId(openID);

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(UiError uiError) {

                        }

                        @Override
                        public void onCancel() {

                        }

                    };
                }


            });
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
        phone = (ImageView) view.findViewById(R.id.phone);
        weixin = (ImageView) view.findViewById(R.id.weixin);
        nana = (TextView) view.findViewById(R.id.nana);

        login_day_night.setImageResource(R.drawable.dayicon_profile);
        login_night_day.setText("日间");

        relativeLayout = (RelativeLayout) view.findViewById(R.id.login_Rl);
        relativeLayout.setOnClickListener(this);
        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_day_night.setImageResource(R.drawable.nighticon_profile);
                login_night_day.setText("夜间");

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
                qqLogin();

                break;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_LOGIN) {

            if (resultCode == -1) {

                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);

                Tencent.handleResultData(data, loginListener);

                UserInfo info = new UserInfo(getActivity(), mTencent.getQQToken());


                info.getUserInfo(new IUiListener() {

                    @Override

                    public void onComplete(Object o) {
                        try {

                            JSONObject info = (JSONObject) o;

                            String nickName = info.getString("nickname");//获取用户昵称

                            String iconUrl = info.getString("figureurl_qq_2");//获取用户头像的url

                            Toast.makeText(getActivity(), "昵称：" + nickName, Toast.LENGTH_SHORT).show();

                            //Glide解析获取用户头像.
                            DisplayImageOptions options=DisplayImageOptions.createSimple();
                            ImageLoader.getInstance().displayImage(iconUrl,qq,options);
                            nana.setText(nickName);
                            phone.setVisibility(View.GONE);
                            weixin.setVisibility(View.GONE);


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                    }


                    @Override

                    public void onError(UiError uiError) {
                    Log.i("lll",uiError.toString());
                    }


                    @Override

                    public void onCancel() {


                    }
                });
            }
        }
    }
}

