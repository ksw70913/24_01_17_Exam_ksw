import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Time {
	static String regDate() {

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String regDate = now.format(formatter);
		return regDate;
	}
}
 