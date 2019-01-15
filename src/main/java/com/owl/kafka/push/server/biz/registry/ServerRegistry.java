package com.owl.kafka.push.server.biz.registry;

import com.owl.kafka.client.service.RegisterMetadata;
import com.owl.kafka.client.service.RegistryService;
import com.owl.kafka.client.transport.Address;
import com.owl.kafka.util.NamedThreadFactory;
import com.owl.kafka.util.NetUtils;
import com.owl.kafka.push.server.biz.bo.ServerConfigs;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Tboy
 */
public class ServerRegistry {

    private final RegistryService registryService;

    public ServerRegistry(RegistryService registryService){
        this.registryService = registryService;
    }

    public void register(){
        Address address = new Address(NetUtils.getLocalIp(), ServerConfigs.I.getServerPort());
        RegisterMetadata registerMetadata = new RegisterMetadata();
        registerMetadata.setPath(String.format(ServerConfigs.I.ZOOKEEPER_PROVIDERS, ServerConfigs.I.getServerTopic()));
        registerMetadata.setAddress(address);
        this.registryService.register(registerMetadata);
    }

    public void unregister(){
        Address address = new Address(NetUtils.getLocalIp(), ServerConfigs.I.getServerPort());
        RegisterMetadata registerMetadata = new RegisterMetadata();
        registerMetadata.setPath(String.format(ServerConfigs.I.ZOOKEEPER_PROVIDERS, ServerConfigs.I.getServerTopic()));
        registerMetadata.setAddress(address);
        this.registryService.unregister(registerMetadata);
    }

}
