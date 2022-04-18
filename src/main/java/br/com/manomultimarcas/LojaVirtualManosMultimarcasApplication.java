package br.com.manomultimarcas;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@EntityScan(basePackages = "br.com.manomultimarcas.model")
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.manomultimarcas.repository"})
@EnableTransactionManagement
public class LojaVirtualManosMultimarcasApplication implements AsyncConfigurer {

	public static void main(String[] args) {
	
		SpringApplication.run(LojaVirtualManosMultimarcasApplication.class, args);
	}
	
	@Override
	@Bean
	public Executor getAsyncExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
				executor.setCorePoolSize(10);
				executor.setMaxPoolSize(20);
				executor.setQueueCapacity(500);
				executor.setThreadNamePrefix("Asyncrono Thread");
				executor.initialize();
				return executor;
		
	}
}