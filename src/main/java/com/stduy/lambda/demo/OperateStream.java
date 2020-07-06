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
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
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
        //3.
//        studentList.stream().collect(Collectors.groupingBy(Student::getSchool));


    }



}
