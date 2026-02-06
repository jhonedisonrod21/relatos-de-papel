package co.com.unir.msbooksordersmanagement.application.error;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message){
        super(message);
    }
}
