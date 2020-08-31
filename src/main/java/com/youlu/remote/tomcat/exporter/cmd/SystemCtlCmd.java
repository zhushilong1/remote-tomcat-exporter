package com.youlu.remote.tomcat.exporter.cmd;

import com.youlu.remote.tomcat.exporter.model.TomcatServerModel;

/**
 * @author zhushilong
 */
public class SystemCtlCmd {

    public String content(TomcatServerModel tomcatServerModel){
        return "cat << EOF >/usr/lib/systemd/system/youlu-tomcat-exporter-"+tomcatServerModel.getTomcatName()+".service                                              \n" +
                "[Unit]                                                                                                         \n" +
                "Description="+tomcatServerModel.getTomcatName()+" heartbeat                                                    \n" +
                "After=network.target                                                                                           \n" +
                "                                                                                                               \n" +
                "[Service]                                                                                                      \n" +
                "Environment=HEARTBEAT_ADDR=http://127.0.0.1:"+tomcatServerModel.getTomcatPort()+"/"+tomcatServerModel.getTomcatName()+"/api/core/test/heartbeat                    \n" +
                "Environment=YOULU_TOMCAT_EXPORTER_WEB_LISTEN_ADDRESS=:"+tomcatServerModel.getExportPort()+"                                                     \n" +
                "ExecStart=/usr/local/bin/youlu-tomcat-exporter                                                                 \n" +
                "                                                                                                               \n" +
                "[Install]                                                                                                      \n" +
                "WantedBy=multi-user.target  \n" +
                "  \n" +
                "EOF";




    }
}
