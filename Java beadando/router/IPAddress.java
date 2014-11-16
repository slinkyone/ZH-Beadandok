package router;
//                      IPAddress

public class IPAddress{

	private int[] Array;

    public IPAddress(int[] Array) {
        this.Array = Array;
    }

    public static IPAddress fromString(String random){
    	String[] parts = random.split("\\.");
        int[] a={Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])};
        IPAddress ipaddress = new IPAddress(a);
    	return ipaddress;
    }

    public boolean isTheSame(IPAddress ip){
    	if(Array[0] == ip.Array[0] && Array[1] == ip.Array[1] && Array[2] == ip.Array[2] && Array[3] ==ip.Array[3]){
    		return true;
    	}else{
    		return false;
    	}
    }

    public boolean insideRng(IPAddress ip2, IPAddress ip3){
	
		long[] d = new long[4];
		for (int i=0;i<4;++i)
			d[i]=ip2.Array[i];
        long a = d[0]*255*255*255+d[1]*255*255+d[2]*255+d[3];
		for (int i=0;i<4;++i)
			d[i]=Array[i];
		long b = d[0]*255*255*255+d[1]*255*255+d[2]*255+d[3];
		for (int i=0;i<4;++i)
			d[i]=ip3.Array[i];
        long c = d[0]*255*255*255+d[1]*255*255+d[2]*255+d[3];
            return a<=b&&b<=c;
    }

   public String toString(){
    	return Array[0] + "." + Array[1] + "." + Array[2] + "." + Array[3];
    }
		
}