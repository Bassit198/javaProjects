package bassit.attendancesystemapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttendanceSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApiApplication.class, args);

		PrintMessage("Application successfully started!");
	}

	public static void PrintMessage(String message){
		System.out.println(message);
	}

}
