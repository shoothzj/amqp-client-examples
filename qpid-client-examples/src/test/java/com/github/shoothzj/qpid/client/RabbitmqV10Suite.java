package com.github.shoothzj.qpid.client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.TimeUnit;

public class RabbitmqV10Suite {

    public static GenericContainer container;

    public static int amqpPort;

    @BeforeAll
    public static void startQpidServer() throws Exception {
        container = new GenericContainer(DockerImageName.parse("ttbb/rabbitmq:mate")).withExposedPorts(5672);
        container.start();
        amqpPort = container.getFirstMappedPort();
        TimeUnit.SECONDS.sleep(30);
        container.execInContainer("rabbitmq-plugins enable rabbitmq_amqp1_0");
        TimeUnit.SECONDS.sleep(3);
    }

    @AfterAll
    public static void shutdownQpidServer() {
        container.stop();
    }

}
