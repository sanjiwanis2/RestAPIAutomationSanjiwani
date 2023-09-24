package restassuredApiTests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;   

public class RestUtils {
	
	
	public static String getBaseURI() {
		return("https://restful-booker.herokuapp.com");
	}
	
	public static String getUserName() {
		return ("admin");
	}
	
	public static String getPassword() {
		return ("password123");
	}
	
	public static String getFirstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("FirstName"+generatedString);
	}
	
	public static String getLastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("LastName"+generatedString);
	}
	
	public static int getTotalPrice() {
		Random random = new Random() ;
		int x = random.nextInt(1000);   
		return (x);
	}
	
	public static boolean getDepositPaid() {
		Random random = new Random() ;
		boolean m=random.nextBoolean(); 
		return(m);
	}
	
	public static String getCheckInDate() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetoday = LocalDate.now();
        String CheckInDate = datetoday.format(dateformat);
		return (CheckInDate);
	}
	
	public static String getCheckOutDate() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datetoday = LocalDate.now();
        LocalDate threeMonthsFromNow = datetoday.plusMonths(3);
        String CheckoutDate = threeMonthsFromNow.format(dateformat);
        return (CheckoutDate);
	}
	
	public static String getAdditionalNeeds() {
		
		return ("Breakfast");
	}

}
