package com.tt.kafka.push.server.biz.registry;

import com.tt.kafka.client.service.RegistryService;

/**
 * @Author: Tboy
 */
public class RegistryCenter {

    public static RegistryCenter I = new RegistryCenter();

    private final ServerRegistry serverRegistry;

    private final ClientRegistry clientRegistry;

    private RegistryCenter(){
        RegistryService registryService = new RegistryService();
        this.serverRegistry = new ServerRegistry(registryService);
        this.clientRegistry = new ClientRegistry(registryService);
    }

    public ServerRegistry getServerRegistry() {
        return serverRegistry;
    }

    public ClientRegistry getClientRegistry() {
        return clientRegistry;
    }
}
