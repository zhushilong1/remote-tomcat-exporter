package com.youlu.remote.tomcat.exporter;

import com.alibaba.fastjson.JSON;
import com.youlu.remote.tomcat.exporter.cmd.SystemCtlCmd;
import com.youlu.remote.tomcat.exporter.cmd.SystemEnableCmd;
import com.youlu.remote.tomcat.exporter.cmd.SystemStartCmd;
import com.youlu.remote.tomcat.exporter.kit.RemoteShellKit;
import com.youlu.remote.tomcat.exporter.model.CmdResModel;
import com.youlu.remote.tomcat.exporter.model.TomcatServerModel;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhushilong
 */
@SpringBootApplication
public class RemoteTomcatExporterApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RemoteTomcatExporterApplication.class, args);
    }


    private  static final int ARGS_LENGTH =3;
    @Override
    public void run(String... args) throws Exception {
        //
        if (args.length != ARGS_LENGTH) {
            System.out.println(" args must be three ");
            return;
        }
        // 解析json路径参数
        File file = new File(args[0]);
        if(!file.exists()) {
            System.out.println(" file is not exist ");
            return;
        }
        String jsonData = IOUtils.toString(file.toURI(), "UTF-8");
        if(StringUtils.isEmpty(jsonData)) {
            System.out.println(" json is null ");
            return;
        }

        List<TomcatServerModel> tomcatServerModels = JSON.parseArray(jsonData, TomcatServerModel.class);
        if(tomcatServerModels == null || tomcatServerModels.size() < 1 ) {
            System.out.println(" json arr is null ");
            return;
        }

        tomcatServerModels.forEach(tomcatServerModel -> {
            // 生成命令
            SystemCtlCmd cmd = new SystemCtlCmd();
            String cmdContent = cmd.content(tomcatServerModel);
            try {
                CmdResModel executor = RemoteShellKit.executor(args[1], args[2], tomcatServerModel.getIp(), cmdContent);
                System.out.println("命令执行情况: " + (executor.isOk() ? "OK": executor.getErrMsg()));
                if(executor.isOk()) {

                    // 启动
                    SystemStartCmd startCmd = new SystemStartCmd();
                    String startCmdContent = startCmd.content(tomcatServerModel);
                    CmdResModel executorStart = RemoteShellKit.executor(args[1], args[2], tomcatServerModel.getIp(), startCmdContent);
                    System.out.println("命令服务启动情况: " + (executorStart.isOk() ? "OK": executorStart.getErrMsg()));

                    //开机启动
                    SystemEnableCmd enableCmd = new SystemEnableCmd();
                    String enableCmdContent = enableCmd.enable(tomcatServerModel);
                    CmdResModel enableStart = RemoteShellKit.executor(args[1], args[2], tomcatServerModel.getIp(), enableCmdContent);
                    System.out.println("命令开机服务启动情况: " + (enableStart.isOk() ? "OK": enableStart.getErrMsg()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
