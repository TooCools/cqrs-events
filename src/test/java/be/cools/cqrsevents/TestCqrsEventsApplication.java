package be.cools.cqrsevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestCqrsEventsApplication
{
	public static void main( String[] args ) {
		SpringApplication.from( CqrsEventsApplication::main ).with( TestCqrsEventsApplication.class ).run( args );
	}

	@Bean
	@ServiceConnection
	MongoDBContainer mongoContainer() {
		return new MongoDBContainer( "mongo:4.0.10" );
	}

}
