package com.example.administrator.teacherhelper.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.Schedule;
import com.example.administrator.teacherhelper.until.AccountUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by daiff on 2017/12/26.
 * for:收件箱
 */

public class InboxFragment extends Fragment {


    @Bind(R.id.top_time)
    TextView topTime;
    RelativeLayout weekday;
    @Bind(R.id.week1_one)
    TextView week1One;
    @Bind(R.id.week2_one)
    TextView week2One;
    @Bind(R.id.week3_one)
    TextView week3One;
    @Bind(R.id.week4_one)
    TextView week4One;
    @Bind(R.id.week5_one)
    TextView week5One;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.week1_two)
    TextView week1Two;
    @Bind(R.id.week2_two)
    TextView week2Two;
    @Bind(R.id.week3_two)
    TextView week3Two;
    @Bind(R.id.week4_two)
    TextView week4Two;
    @Bind(R.id.week5_two)
    TextView week5Two;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.week1_three)
    TextView week1Three;
    @Bind(R.id.week2_three)
    TextView week2Three;
    @Bind(R.id.week3_three)
    TextView week3Three;
    @Bind(R.id.week4_three)
    TextView week4Three;
    @Bind(R.id.week5_three)
    TextView week5Three;
    @Bind(R.id.week1_four)
    TextView week1Four;
    @Bind(R.id.week2_four)
    TextView week2Four;
    @Bind(R.id.week3_four)
    TextView week3Four;
    @Bind(R.id.week4_four)
    TextView week4Four;
    @Bind(R.id.week5_four)
    TextView week5Four;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classschedule, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        BmobQuery<Schedule> sche = new BmobQuery<>();
        BmobUser user = BmobUser.getCurrentUser();
        sche.addWhereEqualTo("teacher", user);
        sche.addWhereEqualTo("schoolyear", AccountUtils.getyear(getActivity()));
        sche.findObjects(new FindListener<Schedule>() {
            @Override
            public void done(List<Schedule> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getXingqi().equals("一")) {
                                if (list.get(i).getSection().equals("1")) {
                                    week1One.setText(list.get(i).getJiaoxue() + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("2")) {
                                    week1Two.setText(list.get(i).getJiaoxue() + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("3")) {
                                    week1Three.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("4")) {
                                    week1Four.setText(list.get(i).getJiaoxue() + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                }
                            } else if (list.get(i).getXingqi().equals("二")) {
                                if (list.get(i).getSection().equals("1")) {
                                    week2One.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("2")) {
                                    week2Two.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("3")) {
                                    week2Three.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("4")) {
                                    week2Four.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                }
                            } else if (list.get(i).getXingqi().equals("三")) {
                                if (list.get(i).getSection().equals("1")) {
                                    week3One.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("2")) {
                                    week3Two.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("3")) {
                                    week3Three.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("4")) {
                                    week3Four.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                }
                            } else if (list.get(i).getXingqi().equals("四")) {
                                if (list.get(i).getSection().equals("1")) {
                                    week4One.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("2")) {
                                    week4Two.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("3")) {
                                    week4Three.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("4")) {
                                    week4Four.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                }
                            } else if (list.get(i).getXingqi().equals("五")) {
                                if (list.get(i).getSection().equals("1")) {
                                    week5One.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("2")) {
                                    week5Two.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("3")) {
                                    week5Three.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                } else if (list.get(i).getSection().equals("4")) {
                                    week5Four.setText(list.get(i).getJiaoxue()  + "("+list.get(i).getWeek() + ")\n"+ list.get(i).getClassroom()+ "\n"+ list.get(i).getClasses());
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initView() {
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 ");
        Date curDate = new Date(System.currentTimeMillis());
        topTime.setText(formatter.format(curDate));
    }
}
