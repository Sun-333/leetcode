package com.example.pro.basics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentInfo implements Comparable<StudentInfo> {

    //名称
    private String name;

    //性别 true男 false女
    private Boolean gender;

    //年龄
    private Integer age;

    //身高
    private Double height;

    //出生日期
    private LocalDate birthday;

    public StudentInfo(String name, Boolean gender, Integer age, Double height, LocalDate birthday){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }

    public String toString(){
        String info = String.format("%s\t\t%s\t\t%s\t\t\t%s\t\t%s",this.name,this.gender.toString(),this.age.toString(),this.height.toString(),birthday.toString());
        return info;
    }

    public static void printStudents(List<StudentInfo> studentInfos){
        System.out.println("[姓名]\t\t[性别]\t\t[年龄]\t\t[身高]\t\t[生日]");
        System.out.println("----------------------------------------------------------");
        studentInfos.forEach(s->System.out.println(s.toString()));
        System.out.println(" ");
    }

    @Override
    public int compareTo(StudentInfo ob) {
        return this.age.compareTo(ob.getAge());
        //return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        //测试数据，请不要纠结数据的严谨性
        List<StudentInfo> studentList = new ArrayList<>();
        studentList.add(new StudentInfo("李小明",true,18,1.76,LocalDate.of(2001,3,23)));
        studentList.add(new StudentInfo("张小丽",false,18,1.61,LocalDate.of(2001,6,3)));
        studentList.add(new StudentInfo("王大朋",true,19,1.82,LocalDate.of(2000,3,11)));
        studentList.add(new StudentInfo("陈小跑",false,17,1.67,LocalDate.of(2002,10,18)));
        StudentInfo.printStudents(studentList);
        List<StudentInfo> boys = studentList.stream().filter(s->s.getGender() && s.getHeight() >= 1.8).collect(Collectors.toList());
//输出查找结果
        StudentInfo.printStudents(boys);
    }
}