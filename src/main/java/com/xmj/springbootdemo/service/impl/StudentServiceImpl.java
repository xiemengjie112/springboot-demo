package com.xmj.springbootdemo.service.impl;

import com.csvreader.CsvWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmj.springbootdemo.entity.student.Student;
import com.xmj.springbootdemo.entity.student.StudentExample;
import com.xmj.springbootdemo.mapper.student.StudentMapper;
import com.xmj.springbootdemo.mapper.test001.StudentTest001Mapper;
import com.xmj.springbootdemo.service.StudentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Describe:
 * author:谢孟杰
 * Date:2018/11/3
 * Time:16:38
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentMapper studentMapper;


    @Autowired
    private StudentTest001Mapper studentTest001Mapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        if (count > 0) {
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
    public String selectStudentsPageInfo(int page, int pageSize) {
        //mysql 查询 limt oracle 伪劣 sqlServer top
        //改写sql 添加limit
        PageHelper.startPage(page, pageSize);
        List<Map<String, Object>> listMap = studentMapper.findStudents();
        PageInfo pageInfo = new PageInfo<>(listMap);
        JSONObject result = new JSONObject();
        result.put("total", pageInfo.getTotal());
        result.put("rows,", pageInfo.getList());
        return result.toString();
    }

    @Override
    public void sendMq(String message) {
        //发送mq
        //rabbitTemplate.convertAndSend("queueA",message);
        rabbitTemplate.convertAndSend("fanoutExchange", "", message);
    }

    @Override
    public void inseartBatch() {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            Student student = new Student();
            student.setPkStudent("insert" + i);
            student.setStudentName("name"+i);
            student.setDr("1");
            student.setAge(1);
            students.add(student);
        }
        int pageSize = 10;

        long start_time = System.currentTimeMillis();//开始执行时间

        //分批数据信息
        int totalSize = students.size(); //总记录数
        int totalPage = totalSize / pageSize; //共N页

        if (totalSize % pageSize != 0) {
            totalPage += 1;
            if (totalSize < pageSize) {
                pageSize = students.size();
            }
        }

        for (int pageNum = 1; pageNum < totalPage + 1; pageNum++) {
            int starNum = (pageNum - 1) * pageSize;
            int endNum = pageNum * pageSize > totalSize ? (totalSize) : pageNum * pageSize;

                studentMapper.addStudents(students.subList(starNum, endNum));

        }

        logger.info("本次操作共耗时：约" +
                (System.currentTimeMillis() - start_time) / 1000 + "秒，约" +
                (System.currentTimeMillis() - start_time) / 60000 + "分钟,共" +
                totalPage * pageSize + "条数据");

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
