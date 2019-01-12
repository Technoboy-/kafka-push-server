package com.owl.kafka.push.server.biz.service;

import com.owl.kafka.client.service.LoadBalance;
import com.owl.kafka.client.transport.Connection;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Tboy
 */
public class RoundRobinLoadBalance implements LoadBalance<Connection> {

    private final AtomicInteger index = new AtomicInteger(0);

    public Connection select(List<Connection> invokers) {
        if(invokers.size() <= 0){
            return null;
        }
        if(index.get() >= invokers.size()){
            index.set(0);
        }
        Connection connection = invokers.get(index.get());
        index.incrementAndGet();
        return connection;
    }
}