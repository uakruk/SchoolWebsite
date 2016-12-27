package ua.edu.kordelschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.edu.kordelschool.utils.AppInfo;

@SpringBootApplication
public class KodreslchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodreslchoolApplication.class, args);
		System.out.println("App version: "+AppInfo.PROJECT_VERSION);
	}
}
