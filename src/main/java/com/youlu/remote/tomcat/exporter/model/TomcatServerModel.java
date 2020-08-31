package com.youlu.remote.tomcat.exporter.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author zhushilong
 */
public class TomcatServerModel implements Serializable {
    @JSONField(name = "tomcat_name")
    private String tomcatName;
    @JSONField(name = "tomcat_port")
    private String tomcatPort;
    @JSONField(name = "export_port")
    private String exportPort;
    private String ip;


    public String getTomcatName() {
        return tomcatName;
    }

    public void setTomcatName(String tomcatName) {
        this.tomcatName = tomcatName;
    }

    public String getTomcatPort() {
        return tomcatPort;
    }

    public void setTomcatPort(String tomcatPort) {
        this.tomcatPort = tomcatPort;
    }

    public String getExportPort() {
        return exportPort;
    }

    public void setExportPort(String exportPort) {
        this.exportPort = exportPort;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
