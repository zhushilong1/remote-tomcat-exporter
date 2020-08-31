package com.youlu.remote.tomcat.exporter.cmd;

import com.youlu.remote.tomcat.exporter.model.TomcatServerModel;

/**
 * @author zhushilong
 */
public class SystemEnableCmd {

    public String enable(TomcatServerModel tomcatServerModel){
        return "systemctl enable youlu-tomcat-exporter-"+tomcatServerModel.getTomcatName()+".service";
    }
}
