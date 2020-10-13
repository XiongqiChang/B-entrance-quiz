package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.pojo.Student;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xqc
 * @Date: 2020/10/13 - 10 - 13 - 13:49
 * @Description: com.thoughtworks.capability.gtb.entrancequiz.api
 * @version: 1.0
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public ResponseEntity getStudentList(){
        List<Student> studentList = studentService.getStudentList();
        return  ResponseEntity.ok().body(studentList);
    }
}
