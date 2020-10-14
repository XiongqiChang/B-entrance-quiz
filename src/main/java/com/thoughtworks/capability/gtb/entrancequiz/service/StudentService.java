package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.pojo.Student;
import com.thoughtworks.capability.gtb.entrancequiz.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xqc
 * @Date: 2020/10/13 - 10 - 13 - 13:43
 * @Description: com.thoughtworks.capability.gtb.entrancequiz.service
 * @version: 1.0
 */
@Service
public class StudentService {

    // GTB: studentList不需要static,且最好加上final
    // GTB: 不符合三层架构，数据应该用xxxRepository来保存
    private static List<Student> studentList = new ArrayList<>();

    static {

        studentList.add(new Student(1, "成吉思汗"));
        studentList.add(new Student(2, "鲁班七号"));
        /*studentList.add(new Student(3, "太乙真人"));
        studentList.add(new Student(4, "钟无艳"));
        studentList.add(new Student(5, "花木兰"));
        studentList.add(new Student(6, "雅典娜"));
        studentList.add(new Student(7, "芈月"));
        studentList.add(new Student(8, "白起"));
        studentList.add(new Student(9, "刘禅"));
        studentList.add(new Student(10, "庄周"));
        studentList.add(new Student(11, "马超"));
        studentList.add(new Student(12, "刘备"));
        studentList.add(new Student(13,"哪吒"));
        studentList.add(new Student(14,"大乔"));
        studentList.add(new Student(15,"蔡文姬"));*/
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(StudentVO studentVo) {

        // GTB: “studentList.size() + 1”不可读，建议引入中间变量
        Student student = Student.builder().id(studentList.size() + 1).name(studentVo.getName()).build();
        studentList.add(student);

    }

    // GTB: 应该创建专门的对象来表示Team
    // GTB: 长方法，建议按逻辑抽取多个子方法
    public Map groupStudent() {

        Map<Integer, List<Student>> map = new HashMap<>();

        // GTB: Magic Number
        Integer groupSize = 6;

        // GTB: 随机分组算法可读性不高
        int listSize = studentList.size() / groupSize;
        for (int i = 1; i <= groupSize; i++) {
            List<Student> newList = new ArrayList<>();
            if (studentList.size() % groupSize == 0) {
                for (int j = 1; j <= listSize; ) {
                    // GTB: 随机算法实现过于复杂，可以使用Collections.shuffle()打乱学员
                    int index = (int) (Math.random() * studentList.size());
                    if (!newList.contains(studentList.get(index))) {
                        newList.add(studentList.get(index));
                        j++;
                    }
                }
                map.put(i, newList);
            } else {
                List<Student> leftList = new ArrayList<>();
                int leftSize = studentList.size() % groupSize;
                for (int k = 0; k < leftSize; k++) {
                    int index = (int) (Math.random() * studentList.size());
                    if (!leftList.contains(studentList.get(index))) {
                        leftList.add(studentList.get(index));
                    }
                }
                for (int m = 1; m <= listSize;) {
                    int index = (int) (Math.random() * studentList.size());
                    if (!leftList.contains(studentList.get(index))) {
                        if (!newList.contains(studentList.get(index))) {
                            newList.add(studentList.get(index));
                            m++;
                        }
                    }
                }

                if (leftList.size() >= i){
                    newList.add(leftList.get(i-1));
                }
                map.put(i, newList);
            }
        }
        return map;
    }
}

