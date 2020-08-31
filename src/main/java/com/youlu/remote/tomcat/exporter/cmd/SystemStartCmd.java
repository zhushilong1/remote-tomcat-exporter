package com.youlu.remote.tomcat.exporter.cmd;

import com.youlu.remote.tomcat.exporter.model.TomcatServerModel;

/**
 * @author zhushilong
 */
public class SystemStartCmd {

    public String content(TomcatServerModel tomcatServerModel){
        return "systemctl start youlu-tomcat-exporter-"+tomcatServerModel.getTomcatName()+".service";
    }
}
