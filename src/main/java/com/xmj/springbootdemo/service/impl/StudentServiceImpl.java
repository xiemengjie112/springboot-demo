package com.xmj.springbootdemo.service.impl;

import com.xmj.springbootdemo.entity.student.Student;
import com.xmj.springbootdemo.entity.student.StudentExample;
import com.xmj.springbootdemo.mapper.student.StudentMapper;
import com.xmj.springbootdemo.mapper.test001.StudentTest001Mapper;
import com.xmj.springbootdemo.service.StudentService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * author:谢孟杰
 * Date:2018/11/3
 * Time:16:38
 */
@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentTest001Mapper studentTest001Mapper;

    @Override
    public String findStudentTest001All() {
        StudentExample studentExample = new StudentExample();
        List<Student> students = studentTest001Mapper.selectByExample(studentExample);
        return JSONArray.fromObject(students).toString();
    }

    @Override
    public String addStudents() {
        List<Student> students = new LinkedList();
        Student student1 = new Student();
        student1.setAge(1);
        student1.setDr("1");
        student1.setStudentName("测试1");
        student1.setPkStudent("111113");
        Student student2 = new Student();
        student2.setAge(1);
        student2.setDr("1");
        student2.setStudentName("测试2");
        student2.setPkStudent("1111114");
        students.add(student1);
        students.add(student2);
        int count = studentMapper.addStudents(students);
        if (count > 0){
            return "OK";
        }
        return "error";
    }

    @Override
    public void initStudents(List<Map<String, Object>> paramMap) {
        paramMap.addAll(studentMapper.findStudents());
    }

    @Override
    public String findStudentAll() {
        StudentExample studentExample = new StudentExample();
        List<Student> students = studentMapper.selectByExample(studentExample);
        return JSONArray.fromObject(students).toString();
    }



/*    @Override
    public void exportStudents(HttpServletResponse response) throws Exception {
        List<Student> listMap = studentMapper.findStudents();
        LinkedHashMap map = new LinkedHashMap();
        map.put("dr","学生状态");
        map.put("studentName","学生姓名");
        map.put("age","学生年龄");
        *//*for (int i = 0; i < listMap.size(); i++) {
            Map paramMap =listMap.get(i);
            map.put("listMap.dr",paramMap.get("dr").toString());
            map.put("map.studentName",paramMap.get("studentName"));
            map.put("paramMap.age",paramMap.get("age").toString());
        }*//*
       1
        //HashMap<String,Object> dataMap = new HashMap<>();

      *//*  for(int i=0;i<30;i++){
            dataMap.put("datetime", "2017-12-13 10:43:00");
            dataMap.put("person", "张翠山");
            dataMap.put("type", "文本");
            dataMap.put("content", "工作一定要认真，态度要端正，作风要优良，行事要效率，力争打造一个完美的产品出来。");
            listMap.add(dataMap);
        }*//*
     *//*
        String title = "学生列表";
        String[] rowsName = new String[]{"序号", "姓名", "年龄"};
        List<Object[]> dataList = new ArrayList<>();
        Object[] objs ;
        for (int i = 0; i < listMap.size(); i++) {
            Map<String, Object> data = listMap.get(i);
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = data.get("studentName");
            objs[2] = data.get("age");
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
        ex.export(response);*//*
    }*/
}
