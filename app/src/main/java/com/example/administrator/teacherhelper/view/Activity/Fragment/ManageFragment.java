package com.example.administrator.teacherhelper.view.Activity.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dc_Activity;
import com.example.administrator.teacherhelper.view.Activity.gzzj_Activity;
import com.example.administrator.teacherhelper.view.Activity.jxdg_Activity;
import com.example.administrator.teacherhelper.view.Activity.jxjd_Activity;
import com.example.administrator.teacherhelper.view.Activity.jxrl_Activity;
import com.example.administrator.teacherhelper.view.Activity.pscj_Activity;
import com.example.administrator.teacherhelper.view.Activity.shjfx_list;
import com.example.administrator.teacherhelper.view.Activity.zcj_Activity;
import com.example.administrator.teacherhelper.widght.SwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by daiff on 2017/12/26.
 * for:
 */

public class ManageFragment extends Fragment {
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.home_jxrl)
    LinearLayout homeJxrl;
    @Bind(R.id.home_jxdg)
    LinearLayout homeJxdg;
    @Bind(R.id.home_jxjd)
    LinearLayout homeJxjd;
    @Bind(R.id.home_gzzj)
    LinearLayout homeGzzj;
    @Bind(R.id.home_pscj)
    LinearLayout homePscj;
    @Bind(R.id.home_zcj)
    LinearLayout homeZcj;
    @Bind(R.id.home_sjfx)
    LinearLayout homeSjfx;
    @Bind(R.id.home_dg)
    LinearLayout homeDg;
    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerViewId;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.have_not_data_id)
    LinearLayout haveNotDataId;
    @Bind(R.id.have_not_data_id1)
    LinearLayout haveNotDataId1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.home_jxrl, R.id.home_jxdg, R.id.home_jxjd, R.id.home_gzzj, R.id.home_pscj, R.id.home_zcj, R.id.home_sjfx, R.id.home_dg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_jxrl:
                Intent intent = new Intent(getActivity(),jxrl_Activity.class);
                startActivity(intent);
                break;
            case R.id.home_jxdg:
                Intent intent1 = new Intent(getActivity(),jxdg_Activity.class);
                startActivity(intent1);
                break;
            case R.id.home_jxjd:
                Intent intent2 = new Intent(getActivity(),jxjd_Activity.class);
                startActivity(intent2);
                break;
            case R.id.home_gzzj:
                Intent intent3 = new Intent(getActivity(),gzzj_Activity.class);
                startActivity(intent3);
                break;
            case R.id.home_pscj:
                Intent intent4 = new Intent(getActivity(),pscj_Activity.class);
                startActivity(intent4);
                break;
            case R.id.home_zcj:
                Intent intent5 = new Intent(getActivity(),zcj_Activity.class);
                startActivity(intent5);
                break;
            case R.id.home_sjfx:
                Intent intent6 = new Intent(getActivity(),shjfx_list.class);
                startActivity(intent6);
                break;
            case R.id.home_dg:
                Intent intent7 = new Intent(getActivity(),dc_Activity.class);
                startActivity(intent7);
                break;
        }
    }
}
