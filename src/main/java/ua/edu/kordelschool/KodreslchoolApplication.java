package ua.edu.kordelschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.edu.kordelschool.utils.AppInfo;

@SpringBootApplication
public class KodreslchoolApplication {

	public static void main(String[] args) {
		System.out.println(AppInfo.PROJECT_VERSION);
		SpringApplication.run(KodreslchoolApplication.class, args);
	}
}
