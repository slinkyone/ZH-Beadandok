package encoding;
import java.util.HashMap;

public class ROTDict extends HashMap<Character,Character>{

	public ROTDict(byte shift){
		super();
		for (int i=1;i<27;++i)
			put((char)(65+(i-1)%26),(char)(65+(i-1+shift)%26));
		for (int i=1;i<27;++i)
			put((char)(97+(i-1)%26),(char)(97+(i-1+shift)%26));
	}

	public Character encode(Character ch){
		if (containsKey(ch))
			return get(ch);
		else return ch;
	}

	public Character decode(Character ch){
	    for(Character key : keySet()){
	        if(get(key).equals(ch)){
	            return key;
	        }
	    }
	    return ch;
	}
}