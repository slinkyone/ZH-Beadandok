package encoding;

public class ROT{
	private static final byte v = 13;
	private static final ROTDict dict = new ROTDict(v);

	public static String encode(String data){
		String str = "";
		for (int i= 0; i< data.length(); ++i) {
			str += dict.encode(data.charAt(i));
		}
		return str;
	}

	public static String decode(String data){
		String str = "";
		for (int i= 0; i< data.length(); ++i) {
			str += dict.decode(data.charAt(i));
		}
		return str;
	}
}
