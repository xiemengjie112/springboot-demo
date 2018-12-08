package com.xmj.springbootdemo.controller;
import com.xmj.springbootdemo.annotation.MyLog;
import com.xmj.springbootdemo.entity.student.Student;
import com.xmj.springbootdemo.exception.ExcelException;
import com.xmj.springbootdemo.service.StudentService;
import com.xmj.springbootdemo.util.ExcelUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Describe:
 * author:谢孟杰
 * Date:2018/11/3
 * Time:16:41
 */
@Controller
public class StudentController {

    @Autowired
    private  StudentService studentServicel;

    @GetMapping(value = "/index")
    public String toIndex() {
        return "/index";
    }

    /*
     * @Author: xieMengJie
     * @Date: 2018/12/1 15:39
     * @Param: [request, file]
     * @return: java.lang.String
     * @Description:  报表导入
     */
    @PostMapping(value = "/submitExcel")
    @ResponseBody
    public String submitExcel( @RequestParam(value = "uploadFile") MultipartFile file) {
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            LinkedHashMap map = new LinkedHashMap();
            map.put("学生状态", "dr");
            map.put("学生姓名", "studentName");
            map.put("学生年龄", "age");
            //String [] pk = new String[]{"p"};
            List<T> students = ExcelUtil.excelToList(inputStream, "学生明细", Student.class, map, null);
            System.out.println(students);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcelException e) {
            e.printStackTrace();
        }
        return "ok";
    }


    @MyLog("查询全部学生信息")
    @GetMapping(value = "/students")
    @ResponseBody
    public String findAllStudent() {
        return studentServicel.findStudentAll();
    }

    @MyLog("批量插入学生信息")
    @GetMapping(value = "/add/Students")
    @ResponseBody
    public String addStudents(HttpServletRequest request) {
        return studentServicel.addStudents();
    }

    /*
     * @Author: xieMengJie
     * @Date: 2018/12/1 15:39
     * @Param: [response]
     * @return: void
     * @Description: csv导出
     */
    @MyLog("excel导出")
    @GetMapping(value = "/exportstudents")
    @ResponseBody
    public void exportStudent(HttpServletResponse response) throws Exception {
        studentServicel.exportStudents(response);
    }

    /**
     * mybatis集成pageHelper分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @MyLog("分页查询学生信息")
    @GetMapping(value = "/student/page/list")
    @ResponseBody
    public String studentsPageInfo(int page, int pageSize) {
        return studentServicel.selectStudentsPageInfo(page, pageSize);
    }


    @RequestMapping(value = "/send/mq")
    @ResponseBody
    public void sendMq(String message){
         studentServicel.sendMq(message);
   }

}
