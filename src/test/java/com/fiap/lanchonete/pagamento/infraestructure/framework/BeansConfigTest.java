package com.fiap.lanchonete.pagamento.infraestructure.framework;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.fiap.lanchonete.ApplicationPedido;

@SpringBootTest(classes = ApplicationPedido.class)
@Testcontainers
class BeansConfigTest {
    @Autowired
    private ApplicationContext context;

    @Container
    private static final CassandraContainer<?> cassandraContainer = new CassandraContainer<>("cassandra:latest")
            .withExposedPorts(9042);
    
    @BeforeAll
    static void setupCassandraConnectionProperties() {
    	createKeyspace(cassandraContainer.getCluster());
    	System.setProperty("spring.cassandra.keyspace-name", "spring_cassandra");
        System.setProperty("spring.cassandra.contact-points", cassandraContainer.getContainerIpAddress());
        System.setProperty("spring.cassandra.port", String.valueOf(cassandraContainer.getMappedPort(9042)));
        System.setProperty("spring.cassandra.local-datacenter", String.valueOf(cassandraContainer.getLocalDatacenter()));

    }
    private static void createKeyspace(Cluster cluster) {
        try (Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS " + "spring_cassandra" +
              " WITH replication = " +
              "{'class':'SimpleStrategy','replication_factor':'1'};");
        }
    }
    @Container
    RabbitMQContainer rabbit = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.13.1-management"));

 

    @Test
    void testBeansExistence() {
        assertThat(context.getBean("pedidoInteractorBean")).isNotNull();
        assertThat(context.getBean("pedidoGateway")).isNotNull();
        assertThat(context.getBean("pedidoMapper")).isNotNull();
     }
   
 
}
