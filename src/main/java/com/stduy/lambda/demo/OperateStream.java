package com.stduy.lambda.demo;

import com.stduy.lambda.bean.Student;
import com.stduy.util.StreamUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperateStream {

    private List<Student> studentList = null;

    @PostConstruct
    public void init(){
        studentList = new ArrayList<Student>(){
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "公瑾", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "伯符", 24, 5, "土木工程", "南京大学"));
            }
        };
    }


    /**
     * 筛选出所有武汉大学的学生
     */
    @Test
    public void testFilter(){
        studentList = studentList.stream().filter(student -> Objects.equals(student.getSchool(),"武汉大学")).collect(Collectors.toList());
        studentList.forEach(student -> System.out.println(student));
    }

    /**
     * 筛选出学校不重复的student
     *  1.distinct只能对整体对象去重，不能实现对对象里的某个字段进行判定去重
     *   所以可以先定义一个过滤器，再通过filter去筛选
     *  2.treeSet方式
     */
    @Test
    public void testDistinct(){
        //1.
        studentList.stream().filter(StreamUtils.distinctByKey(Student::getSchool)).collect(Collectors.toList()).forEach(student -> System.out.println(student));
        //2.这种写法不知道什么意思，是倒序去取的数据
        studentList.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(Student::getSchool))
                ), ArrayList::new)).forEach(student -> System.out.println(student));
    }

    /**
     * 返回前两个土木工程专业的学生
     */
    @Test
    public void testLimit(){
        studentList.stream().filter(student -> Objects.equals(student.getMajor(),"土木工程")).limit(2).collect(Collectors.toList()).forEach(student -> System.out.println(student));
    }

    /**
     * sorted排序，要求待比较的元素必须实现Comparable接口，如果没有实现，可以将比较器作为参数传递给sorted(Comparator<? super T> comparator)
     */
    @Test
    public void testSorted(){
        /**
         * 筛选出土木工程专业的学生，并且按年龄从小到大排序，筛选出年龄最小的两个学生
         */
        studentList.stream().filter(student -> Objects.equals(student.getMajor(),"土木工程")).sorted(Comparator.comparing(Student::getAge)).limit(2).collect(Collectors.toList()).forEach(student -> System.out.println(student));

        /**
         * 根据年龄排序（正序）
         */
        studentList.stream().sorted(Comparator.comparing(Student::getAge)).forEach(student -> System.out.println(student));

        /**
         * 根据年龄排序（倒序）
         */
        studentList.stream().sorted(Comparator.comparing(Student::getAge).reversed()).forEach(student -> System.out.println(student));

        /**
         * 自然排序
         */
        studentList.stream().sorted().forEach(student -> System.out.println(student));
    }

    /**
     * skip跳过
     */
    @Test
    public void testSkip(){

        /**
         * 筛选中所有土木工程的学生，找到排序在第二个之后的学生，即跳过两个学生
         */
        studentList.stream().filter(student -> Objects.equals(student.getMajor(),"土木工程")).skip(2).collect(Collectors.toList()).forEach(student -> System.out.println(student));


        /**
         * 如果跳过的个数n，大于集合的长度，则会返回一个空集合
         */
        studentList = studentList.stream().filter(student -> Objects.equals(student.getMajor(),"土木工程")).skip(5).collect(Collectors.toList());
        System.out.println("集合长度为：" + studentList.size());
    }

    /**
     * 映射  类似于数据库select的映射操作，可以仅输出我们想要的字段数据，流式处理的映射操作也是同一目的。java8中，主要包含两种映射操作：map和flatMap
     */
    @Test
    public void testMap(){
        /**
         * 筛选出所有专业为计算机科学的学生的姓名
         */
//        List<String> nameList = studentList.stream().filter(student -> Objects.equals(student.getMajor(),"计算机科学")).map(Student::getName).collect(Collectors.toList());
//        nameList.forEach(s -> System.out.println(s));


        /**
         *
         */
        int totalAge = studentList.stream().filter(student -> Objects.equals(student.getMajor(), "计算机科学")).mapToInt(Student::getAge).sum();
        System.out.println(totalAge);
    }











}
