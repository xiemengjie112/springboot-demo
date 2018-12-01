package com.xmj.springbootdemo.service.impl;

import com.csvreader.CsvWriter;
import com.xmj.springbootdemo.entity.student.Student;
import com.xmj.springbootdemo.entity.student.StudentExample;
import com.xmj.springbootdemo.mapper.student.StudentMapper;
import com.xmj.springbootdemo.mapper.test001.StudentTest001Mapper;
import com.xmj.springbootdemo.service.StudentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        student1.setPkStudent(UUID.randomUUID().toString());
        Student student2 = new Student();
        student2.setAge(1);
        student2.setDr("1");
        student2.setStudentName("测试2");
        student2.setPkStudent(UUID.randomUUID().toString());
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



    @Override
    public void exportStudents(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> listMap = studentMapper.findStudents();
        File tempFile = File.createTempFile("datas", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        String[] title = {"姓名", "年龄"};
        csvWriter.writeRecord(title);
        // 定义表的标题
        for (int i = 0; i < listMap.size(); i++) {
            Map<String, Object> per = listMap.get(i);
            csvWriter.write(per.get("studentName").toString());
            csvWriter.write(per.get("age").toString());
            csvWriter.endRecord();
        }
        csvWriter.close();
        outCsvStream(response, tempFile);

    }

    /**
     * 写入csv结束，写出流
     */
    public static void outCsvStream(HttpServletResponse response, File tempFile) throws IOException {
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[10240];
        File fileLoad = new File(tempFile.getCanonicalPath());
        response.reset();
        response.setContentType("application/csv");
        String fileName = new String("学生明细".getBytes(), "iso-8859-1");
        response.setHeader("content-disposition", "attachment; filename=" + fileName + ".csv");
        FileInputStream in = new FileInputStream(fileLoad);
        int n;
        //为了保证excel打开csv不出现中文乱码
        out.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        while ((n = in.read(b)) != -1) {
            //每次写入out1024字节
            out.write(b, 0, n);
        }
        in.close();
        out.close();
    }

}
