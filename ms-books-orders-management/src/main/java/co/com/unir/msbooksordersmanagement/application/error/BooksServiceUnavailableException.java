package co.com.unir.msbooksordersmanagement.application.error;

public class BooksServiceUnavailableException extends Exception{
    public BooksServiceUnavailableException(String message){
        super(message);
    }
}
