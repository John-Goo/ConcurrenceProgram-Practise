package com.training.conc.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;

public class CuratorUtil {

    final static String ZK_IPs = "192.168.0.71:2181,192.168.0.72:2181,192.168.0.73:2181";


    /**
     * 创建Curator 客户端
     * @return
     */
    public static CuratorFramework newClient(String rootPath) {
        CuratorFramework client=null;
        ACLProvider aclProvider = new ACLProvider() {
            private List<ACL> acl;

            //初始化操作
            private synchronized void init() {
                if (acl == null) {
                    acl = new ArrayList<ACL>();
                    acl.add(new ACL(ZooDefs.Perms.ALL, new Id("auth", "admin:admin")));
                }
            }

            @Override
            public List<ACL> getDefaultAcl() {
                if (acl == null) {
                    init();
                }
                return this.acl;
            }

            @Override
            public List<ACL> getAclForPath(String path) {
                if (acl == null) {
                    init();
                }
                return this.acl;
            }
        };
        String scheme = "digest";
        byte[] auth = "admin:admin".getBytes();
        int connectionTimeoutMs = 5000;
        String connectString = ZK_IPs;
        byte[] defaultData = "默认数据".getBytes();
        int maxCloseWaitMs = 5000;
        RetryPolicy retryPolicy = new RetryNTimes(Integer.MAX_VALUE, 5000);
        CuratorFramework clientinit = CuratorFrameworkFactory
                .builder()
                .aclProvider(aclProvider)
                .authorization(scheme, auth)
                .connectionTimeoutMs(connectionTimeoutMs)
                .connectString(connectString)
                .defaultData(defaultData)
                .maxCloseWaitMs(maxCloseWaitMs)
                .namespace(rootPath)
                .retryPolicy(retryPolicy)
                .build();
        clientinit.start();
        client = clientinit;
        return client;
    }

}
