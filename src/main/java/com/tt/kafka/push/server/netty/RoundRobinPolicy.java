package com.tt.kafka.push.server.netty;

import com.tt.kafka.client.netty.transport.Connection;
import com.tt.kafka.client.service.LoadBalancePolicy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Tboy
 */
public class RoundRobinPolicy implements LoadBalancePolicy<Connection> {

    private final AtomicInteger index = new AtomicInteger(0);

    private final PushClientRegistry clientRegistry;

    public RoundRobinPolicy(PushClientRegistry clientRegistry){
        this.clientRegistry = clientRegistry;
    }

    @Override
    public Connection get() {
        Collection<Connection> values = clientRegistry.getClients();
        List<Connection> clients = new ArrayList<>(values);
        if(clients.size() <= 0){
            return null;
        }
        return clients.get(index.incrementAndGet() % clients.size());
    }
}
