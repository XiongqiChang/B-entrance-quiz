package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.pojo.Student;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import com.thoughtworks.capability.gtb.entrancequiz.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// GTB: 最好不要加注释
/**
 * @Author: xqc
 * @Date: 2020/10/13 - 10 - 13 - 13:49
 * @Description: com.thoughtworks.capability.gtb.entrancequiz.api
 * @version: 1.0
 */
@RestController
public class StudentController {

    // GTB: 推荐使用构造函数注入
    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public ResponseEntity getStudentList(){
        List<Student> studentList = studentService.getStudentList();
        return  ResponseEntity.ok().body(studentList);
    }

    // GTB: url不符合Restful实践
    @PostMapping("/student")
    // GTB: Post请求应该返回201
    // GTB: ResponseEntity应该使用泛型
    public ResponseEntity addStudent(@RequestBody StudentVO studentVo){
        studentService.addStudent(studentVo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/group")
    public ResponseEntity groupStudent(){
        Map map = studentService.groupStudent();
        return ResponseEntity.ok().body(map);
    }
}
