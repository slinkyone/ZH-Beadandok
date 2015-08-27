package router;

import java.util.Arrays;

public class IPAddress implements Comparable<IPAddress> {
    public IPAddress(int[] address) {
        data = Arrays.copyOf(address, address.length);
    }

    public static IPAddress fromString(String ipstr) {
        String[] nums = ipstr.split("\\.");
        if (nums.length!=4)
            throw(new IPFormatException(ipstr));
        int[] data = new int[nums.length];
        for(int i = 0; i < data.length; ++i) {
            data[i] = Integer.parseInt(nums[i]);
            if(data[i] < 0 || data[i] > 255)
                throw(new IPFormatException(ipstr));
        }
        return new IPAddress(data);
    }

    @Override
    public int compareTo(IPAddress other){
        long[] d = new long[4];
        for (int i=0;i<4;++i)
            d[i]=other.data[i];
        long a = d[0]*255*255*255+d[1]*255*255+d[2]*255+d[3];
        for (int i=0;i<4;++i)
            d[i]=data[i];
        long b = d[0]*255*255*255+d[1]*255*255+d[2]*255+d[3];
            return Math.round(b-a);
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof IPAddress))
            return false;
        return toString().compareTo(other.toString())==0;
    }

    @Override
    public int hashCode(){
        int sum = 0;
        for (int i = 0; i < data.length-1; i++){
            sum+= data[i];
        }
        return sum;
    }

    public boolean insideRng(IPAddress lowBound, IPAddress highBound) {
        return compareTo(lowBound)>=0 && compareTo(highBound)<=0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length - 1; ++i) {
            sb.append(data[i]).append('.');
        }
        sb.append(data[data.length - 1]);
        return sb.toString();
    }
    private int[] data;
}