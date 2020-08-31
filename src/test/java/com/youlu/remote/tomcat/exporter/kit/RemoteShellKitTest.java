package com.youlu.remote.tomcat.exporter.kit;

import com.youlu.remote.tomcat.exporter.model.CmdResModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RemoteShellKitTest {

    @Test
    void executor() throws IOException {
        CmdResModel niaoshuai = RemoteShellKit.executor("niaoshuai", "123", "192.168.15.40", "ls -al");
        assertTrue(niaoshuai.isOk());
    }
}