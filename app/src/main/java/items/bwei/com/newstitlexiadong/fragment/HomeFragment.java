package items.bwei.com.newstitlexiadong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.adapter.MyFragmentPagerAdapter;
import items.bwei.com.newstitlexiadong.activity.ChannelActivity;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class HomeFragment extends Fragment{

    private String[] title = {"推荐", "热点", "阳光宽带", "社会", "娱乐", "科技", "汽车", "体育", "财经", "军事", "旅游", "CBA", "笑话", "正能量", "电影"};
    String[] titles = {"T1348647909107", "T1370583240249", "T1351233117091", "T1348648037603", "T1348648517839", "T1348649580692", "T1348654060988", "T1348649079062", "T1348648756099", "T1348648141035", "T1348654204705", "T1348649475931", "T1350383429665", "T1348654225495", "T1348648650048"};
    private List<Fragment> list;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private View view;
    private ImageView add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment_home, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), title, list);

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_title);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        add = (ImageView) view.findViewById(R.id.add_item);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChannelActivity.class);
                startActivity(intent);
            }
        });
    }

    //初始化数据
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            TitleFragment fragment = new TitleFragment();

            //fragment利用Bundle传值
            Bundle bundle = new Bundle();
            bundle.putString("type", titles[i]);
            fragment.setArguments(bundle);
            list.add(fragment);

        }
    }

}