package com.stduy.lambda.bean;

public class Student implements Comparable<Student>{


    /**
     * 学号
     */
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Student(Integer id, String name, Integer age, Integer grade, String major, String school) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                '}';
    }


    @Override
    public int compareTo(Student s) {
        return this.age - s.age;
    }
}
