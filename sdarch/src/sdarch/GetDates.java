package sdarch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDates {
	public String getMonth(String dateValue) throws ParseException {
		String monthValue;
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyy");
		Date date1;
 		date1 = formatter.parse(dateValue);
		monthValue = new SimpleDateFormat("MMM").format(date1);
		return monthValue;
	}
 
}
