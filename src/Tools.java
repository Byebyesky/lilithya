import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class Tools {

    String logMsg = "";

    public String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now); 
    }
    
    public void printLog(String message) {
        System.out.println(message);
    }

}