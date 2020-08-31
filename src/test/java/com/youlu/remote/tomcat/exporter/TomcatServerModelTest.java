package com.youlu.remote.tomcat.exporter;


import com.alibaba.fastjson.JSON;
import com.youlu.remote.tomcat.exporter.model.TomcatServerModel;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TomcatServerModelTest {

    @Test
    public void test() throws IOException {
        InputStream serverJsonInput = getClass().getClassLoader().getResourceAsStream("server.json");
        String jsonData = IOUtils.toString(serverJsonInput, "UTF-8");
        List<TomcatServerModel> tomcatServerModels = JSON.parseArray(jsonData, TomcatServerModel.class);
        Assert.notEmpty(tomcatServerModels,"");
    }

    @Test
    public void test1() throws IOException {
        InputStream serverJsonInput = getClass().getClassLoader().getResourceAsStream("server.json");
        String jsonData = IOUtils.toString(serverJsonInput, "UTF-8");
        List<TomcatServerModel> tomcatServerModels = JSON.parseArray(jsonData, TomcatServerModel.class);
        StringBuffer buffer=new StringBuffer("");
        for (TomcatServerModel tomcatServerModel: tomcatServerModels) {
            String  aaa  =  " \n" +
                    " - job_name: 'tomcat-"+ tomcatServerModel.getTomcatName() +"-01"+"'\n" +
                    "   static_configs:\n" +
                    "   - targets: ['"+tomcatServerModel.getIp()+":"+ tomcatServerModel.getExportPort()+ "']\n" +
                    "     labels:\n" +
                    "       group: 'tomcat-xintiao'\n" ;
            buffer.append(aaa);
        }
        System.out.println(buffer);
       }

}
