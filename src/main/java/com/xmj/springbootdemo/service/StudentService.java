package com.xmj.springbootdemo.service;

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
    
    /*
    * @Author: xieMengJie
    * @Date: 2018/12/1 20:50
    * @Param: []
    * @return: java.lang.String
    * @Description: 查询全部
    */
    String findStudentAll();

    /*
    * @Author: xieMengJie
    * @Date: 2018/12/1 20:50
    * @Param: []
    * @return: java.lang.String
    * @Description: 多数据源测试查询
    */
    String findStudentTest001All();

    /*
    * @Author: xieMengJie
    * @Date: 2018/12/1 20:49
    * @Param: [response]
    * @return: void
    * @Description:  excle导出
    */
    void exportStudents(HttpServletResponse response) throws Exception;
    
    /*
    * @Author: xieMengJie
    * @Date: 2018/12/1 20:49
    * @Param: [paramMap]
    * @return: void
    * @Description:  初始化数据
    */
    void initStudents(List<Map<String,Object>> paramMap);

    /*
    * @Author: xieMengJie
    * @Date: 2018/12/1 20:49
    * @Param: []
    * @return: java.lang.String
    * @Description:  批量insert
    */
    String addStudents();

    /**
     * mybatis集成pageHelper分页查询
     * @param page
     * @param pageSize
     * @return
     */
    public String selectStudentsPageInfo(int page,int pageSize);


    public void sendMq(String message);


    /**
     * 批量插入数据
     */
    public void inseartBatch();

}
