package com.xmj.springbootdemo.service;

import com.xmj.springbootdemo.entity.student.Student;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * author:谢孟杰
 * Date:2018/11/3
 * Time:16:37
 */
public interface StudentService {
    /**
     * 11111
     * @return
     */
    String findStudentAll();

    String findStudentTest001All();

    void exportStudents(HttpServletResponse response) throws Exception;

    void initStudents(List<Map<String,Object>> paramMap);

    String addStudents();

}
