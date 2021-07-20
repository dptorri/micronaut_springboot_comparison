package springboot.rest.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(Exception ex){
        super(ex);
    }

    public BusinessException(String message, Exception ex){
        super(message, ex);
    }

}
