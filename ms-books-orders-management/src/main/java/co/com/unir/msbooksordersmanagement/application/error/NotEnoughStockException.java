package co.com.unir.msbooksordersmanagement.application.error;

public class NotEnoughStockException extends Exception{
    public NotEnoughStockException(String message){
        super(message);
    }
}
