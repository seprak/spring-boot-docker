package com.example.springbootdocker;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdocker.model.GroceryItem;
import com.example.springbootdocker.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
@RestController
public class SpringBootDockerApplication {
	
	Logger log = LoggerFactory.getLogger(SpringBootDockerApplication.class);
	
	@Autowired
    ItemRepository groceryItemRepo;

	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		byte [] nbt = new byte[1024*1024];
		new Random().nextBytes(nbt);
		
//		for (int i = 4; i < 1000; i++) {
//			groceryItemRepo.save(new GroceryItem(i+"", "mlieko" +i, i, "mliecne"));
//		}
		
//		System.out.println("Size of nbt is " + VM.current().sizeOf(nbt));
		try {
			Thread thread = new Thread() { 
				public void run() {try {
					log.info("Starting running thread {}", this.getName());
					Thread.sleep(150*1000L);
					System.out.println("Finishing thread " + this.getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}};
				
			};
			thread.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "HI2 - Hello Docker World " + request.getRemoteHost()  + ":"+ request.getRemotePort() +":: local :::" + request.getLocalAddr() + ":" + request.getLocalPort();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerApplication.class, args);
	}
	
	@RequestMapping("/oom")
	public void generateOOM() throws Exception {
		int iteratorValue = 20;
		System.out.println("\n=================> OOM test started..\n");
		for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
			System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			int[] memoryFillIntVar = new int[iteratorValue];
			// feel memoryFillIntVar array in loop..
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);
			iteratorValue = iteratorValue * 5;
			System.out.println("\nRequired Memory for next loop: " + iteratorValue);
			Thread.sleep(1000);
		}
	}

}
