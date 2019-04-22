package utility;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BTime {
	public static String getTime() {
		Date data = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String time = simpleDateFormat.format(data);
		return time;
		
	}
	public static String getTimeShort() {
		Date data = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
		String time = simpleDateFormat.format(data);
		return time;
		
	}
}
