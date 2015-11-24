

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeString {
	Calendar calendar = new GregorianCalendar();
	private String valueOfString(String str, int len) {
		String string = "";
		for (int i = 0; i < len - str.length(); i++) {
			string = string + "0";
		}
		return (str.length() == len) ? (str) : (string + str);
	}
	/**
	 * 杩斿洖褰撳墠鏃堕棿锛屾牸寮忎负锛�2014-12-18 15:11:50
	 * @return
	 */
	public String getSimpleDateFormat(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date()); 		 
	}	
	
	/**
	 * 杩斿洖褰撳墠鏃堕棿鎴�
	 * @return
	 */
	public String getTime(){
		return String.valueOf(new Date().getTime());
	}
	public String getDate()
	{
	
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = this.valueOfString(String.valueOf(calendar.get(Calendar.MONTH) + 1),2);	
		String day = this.valueOfString(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),2);
		return year+month+day;
	}
	
	/**
	 * 鐢熸垚涓�涓暱搴︿负17鐨勬椂闂村瓧绗︿覆锛岀簿纭埌姣
	 * @return
	 */
	public String getTimeString() {
		String hour = this.valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),2);
		String minute = this.valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)),2);
		String second = this.valueOfString(String.valueOf(calendar.get(Calendar.SECOND)),2);
		String millisecond = this.valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)),3);
		return hour+minute+second+millisecond;

	}	
	
	public static void main(String[] args) {
		TimeString ts = new TimeString();
		for (int i = 0; i < 2000; i++) {
			System.out.println(ts.getTimeString());
		}
		
	}
}
