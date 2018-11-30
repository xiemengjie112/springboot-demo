package com.xmj.springbootdemo.util;

import com.aliyun.oss.OSSClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static net.sf.json.test.JSONAssert.assertEquals;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2018/11/5 20:41
 */
public class OSSUnitTest {

    //log
    private static final Logger LOG = LoggerFactory.getLogger(OSSUnitTest.class);

    private ALiYunOSS ossunit = null;
    private OSSClient client = null;
    private String bucketName = "";
    @Before
    public void initUnit(){
        ossunit = new ALiYunOSS();
        client = ALiYunOSS.getOSSClient();
    }

    @Test
    public void testCreateBucket() {
        String bucketName = "oss-test-my";
        //创建bucket
        assertEquals(true, ALiYunOSS.createBucket(client, bucketName));
    }

    @Test
    public void testDeleteBucket(){
        String bucketName = "oss-test-my";
        //删除bucket
        ALiYunOSS.deleteBucket(client, bucketName);
        //console log :删除oss-test-myBucket成功
    }

    @Test
    public void testUploadObject2OSS(){
        //上传文件
        String flilePathName = "F:/timg.jpg";
        File file = new File(flilePathName);
        String diskName = "datas/image/";
        String md5key = ALiYunOSS.uploadObject2OSS(client, file, bucketName, diskName);
        LOG.info("上传后的文件MD5数字唯一签名:" + md5key);  //上传后的文件MD5数字唯一签名:A30B046A34EB326C4A3BBD784333B017
    }

    @Test
    public void testGetOSS2InputStream(){
        //获取文件
        try {
            BufferedInputStream bis = new BufferedInputStream(ALiYunOSS.getOSS2InputStream(client, bucketName, "datas/image/", "preossimg.jpg"));
            String resfile = "C:/Users/Tony_tian/Desktop/csdnimg/preossimg_res.jpg";
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(resfile)));
            int itemp = 0;
            while((itemp = bis.read()) != -1){
                bos.write(itemp);
            }
            LOG.info("文件获取成功"); //console log :文件获取成功
            bis.close();
            bos.close();
        } catch (Exception e) {
            LOG.error("从OSS获取文件失败:" + e.getMessage(), e);
        }
    }

    @Test
    public void testDeleteFile(){
        //注：文件夹下只有一个文件，则文件夹也会一起删除；如果多个文件，只会删除指定文件名的文件
        //删除文件
        ALiYunOSS.deleteFile(client, bucketName, "datas/image/", "preossimg.jpg");
        //console log : 删除oss-test-my下的文件datas/image/preossimg.jpg成功
    }


}
