package router;

public class IPFormatException extends RuntimeException {

	public IPFormatException(String wrong){
		super(wrong);
	}

	@Override
	public String getMessage(){
		return "Invalid IP address format: " + super.getMessage();
	}
}