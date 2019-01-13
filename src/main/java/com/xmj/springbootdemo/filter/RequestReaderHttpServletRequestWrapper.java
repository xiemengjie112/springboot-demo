package com.xmj.springbootdemo.filter;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Description: 包装获取RequestBody参数
 * Author: xieMengJie
 * CreateDate: 2019/1/13 15:42
 */
public class RequestReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public RequestReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException{
        super(request);
        body = this.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }


    public static String getBodyString(HttpServletRequest request) throws IOException {
        return StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
    }


}
