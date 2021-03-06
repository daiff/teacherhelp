package com.example.administrator.teacherhelper.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.NOTICE;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Activity.Calendar_Item;
import com.example.administrator.teacherhelper.view.Activity.Innovate_Item;
import com.example.administrator.teacherhelper.view.Activity.Mark_List;
import com.example.administrator.teacherhelper.view.Activity.PaperAnalysis_List;
import com.example.administrator.teacherhelper.view.Activity.Program_item;
import com.example.administrator.teacherhelper.view.Activity.Speed_Item;
import com.example.administrator.teacherhelper.view.Activity.Summary_Item;
import com.example.administrator.teacherhelper.view.Activity.max.Max_MessegeDetial;
import com.example.administrator.teacherhelper.view.Activity.zcj_Activity;
import com.example.administrator.teacherhelper.view.Adapter.Messege;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
    @Bind(R.id.listt)
    ListView listt;
    Messege adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        BmobQuery<NOTICE> notice = new BmobQuery<>();
        notice.findObjects(new FindListener<NOTICE>() {
            @Override
            public void done(final List<NOTICE> list, BmobException e) {
                if (e == null) {
                    adapter = new Messege(list, getActivity());
                    listt.setAdapter(adapter);
                    listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), Max_MessegeDetial.class);
                            intent.putExtra("messege", list.get(position));
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                Intent intent = new Intent(getActivity(), Calendar_Item.class);
                intent.putExtra("resource", CommenDate.main);
                startActivity(intent);
                break;
            case R.id.home_jxdg:
                Intent intent1 = new Intent(getActivity(), Program_item.class);
                intent1.putExtra("resource", CommenDate.main);
                startActivity(intent1);
                break;
            case R.id.home_jxjd:
                Intent intent2 = new Intent(getActivity(), Speed_Item.class);
                intent2.putExtra("resource", CommenDate.main);
                startActivity(intent2);
                break;
            case R.id.home_gzzj:
                Intent intent3 = new Intent(getActivity(), Summary_Item.class);
                intent3.putExtra("resource", CommenDate.main);

                startActivity(intent3);
                break;
            case R.id.home_pscj:
                Intent intent4 = new Intent(getActivity(), Mark_List.class);
                intent4.putExtra("resource", CommenDate.main);
                startActivity(intent4);
                break;
            case R.id.home_zcj:
                Intent intent5 = new Intent(getActivity(), zcj_Activity.class);
                intent5.putExtra("resource", CommenDate.main);
                startActivity(intent5);
                break;
            case R.id.home_sjfx:
                Intent intent6 = new Intent(getActivity(), PaperAnalysis_List.class);
                intent6.putExtra("resource", CommenDate.main);
                startActivity(intent6);
                break;
            case R.id.home_dg:
                Intent intent7 = new Intent(getActivity(), Innovate_Item.class);
                intent7.putExtra("resource", CommenDate.main);
                startActivity(intent7);
                break;
        }
    }
}
