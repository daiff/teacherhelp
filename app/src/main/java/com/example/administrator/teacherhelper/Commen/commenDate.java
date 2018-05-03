package com.example.administrator.teacherhelper.commen;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class CommenDate {

    public static final int select_major = 1; //专业选择
    public static final int select_book = 2; //教材选择
    public static final int select_book1 = 21; //教材选择
    public static final int select_book2 = 22; //教材选择
    public static final int select_book3 = 23; //教材选择
    public static final int max_year = 3; //max选择年
    public static final int select_schoolyear = 4; //max选择年
    public static final int select_course = 5; //max选择年
    public static final int select_nature = 6; //max选择年
    public static final int select_college = 7; //max选择年
    public static final int select_collegestu = 8; //max选择年
    public static final int select_grade = 9; //max选择年
    public static final int select_class = 10; //max选择年
    public static final int select_teacher = 11; //选择教师

    public static final int select_class1 = 12; //max选择年
    public static final int select_class2 = 13; //max选择年
    public static final int select_class3 = 14; //max选择年
    public static final int select_class4 = 15; //max选择年

    public static final int select_student1= 16; //大创选择学生
    public static final int select_student2 = 17; //大创选择学生
    public static final int select_student3 = 18; //大创选择学生
    public static final int select_student4 = 19; //大创选择学生
    public static final int select_student5 = 20; //大创选择学生



    public static final String value_major = "major"; //专业选择
    public static final String value_schoolyear = "schoolyear"; //专业选择
    public static final String value_schele = "schedule"; //专业选择
    public static final String value_college = "college"; //专业选择
    public static final String value_grade = "grade"; //专业选择
    public static final String value_class = "classs"; //专业选择
    public static final String value_nature = "nature"; //专业选择



    public static final String max_mycourse ="max_course"; //教材选择
    public static final String main_mycourse ="main_mycourse"; //教材选择
    public static final String maxcour_teacher ="maxcour_teacher"; //由添加课程的选择框进教师列表页面
    public static final String maxcour_book ="maxcour_book"; //由添加课程的选择框进教师列表页面
    public static final String maxcour_class ="maxcour_class"; //由添加课程的选择框进班级列表页面
    public static final String maxschedule_jiaoxue ="maxschedule_jiaoxue"; //由添加课程表的选择框进教学列表页面






    public static final String include_jiaoxue = "College,Course,Nature,Schoolyear,Team.grade,Team.major,Team.classs,,Team.college,Teacher.xi"; //专业选择--
    public static final String achieve_jiaoxue = "Course.Course,Course.Nature,Course.Team.grade,Course.Team.major,Course.Team.classs"; //专业选择


    public static final String include_teach = "College,Course,Nature,Schoolyear,Teacher,Team.classs,Team.college,Team.grade,Team.major"; //所有课程
    public static final String include_classs = "classs,college,grade,major"; //班级选择
    public static final String IncludePaperAnalysis = "jiaoxue.book,jiaoxue.kaikeyuan,jiaoxue.ke,jiaoxue.nature,jiaoxue.schoolyear,jiaoxue.teacher"; //试卷分析包括教学的内容
    public static final String IncludeCalender = "Course.College,Course.Course,Course.Nature,Course.Schoolyear,Course.Teacher,Course.Team"; //试卷分析包括教学的内容--

//    public static final String mark_jiaoxue = "Course.classs.classs,Course.kaikeyuan,jiaoxue.ke,Course.nature,Course.schoolyear,Course.teacher.xi,Course.classs.college,Course.classs.grade,Course.classs.major"; //专业选择
    public static final String mark_student = "Course.classs.classs,Course.kaikeyuan,jiaoxue.ke,Course.nature,Course.schoolyear,Course.teacher.xi,Course.classs.college,Course.classs.grade,Course.classs.major,Studentid.classs,Studentid.despration,Studentid.number"; //专业选择
    public static final String include_commen = "Course.Team,Course.Team.classs,Course.Team.college,Course.Team.grade,Course.Team.major,Course.Teacher,Course.Teacher.xi,Course.Schoolyear,Course.Nature,Course.Nature,Course.Course,Course.College"; //专业选择---
    public static final String include_summ = "teach.Team,teach.Team.classs,teach.Team.college,teach.Team.grade,teach.Team.major,teach.Teacher,teach.Teacher.xi,teach.Schoolyear,teach.Nature,teach.Nature,teach.Course,teach.College"; //专业选择---
    public static final String include_MARK = "Teach.Team,Teach.Team.classs,Teach.Team.college,Teach.Team.grade,Teach.Team.major,Teach.Teacher,Teach.Teacher.xi,Teach.Schoolyear,Teach.Nature,Teach.Nature,Teach.Course,Teach.College"; //专业选择---

    public static final String main ="main"; //由添加课程的选择框进班级列表页面
    public static final String max ="max"; //由添加课程的选择框进班级列表页面


}
