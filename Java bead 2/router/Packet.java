package router;

import encoding.ROT;
import encoding.Textualizable;
import java.util.List;
import java.util.LinkedList;

public class Packet <T extends Textualizable> {
    private static final int INIT_TTL = 50;
    private String str = "";

    public Packet(T data, IPAddress dest) {
        str=ROT.encode(data.toText());
        this.data = data;
        data.reset();
        destination = dest;
        ttl = INIT_TTL;
    }

    public IPAddress getDestination() {
        return destination;
    }

    public void recordPath(IPAddress currLoc) {
        path.add(currLoc);
        ttl = ttl - 1;
        if(destination.equals(currLoc)){
            data.fromText(ROT.decode(str));
            str = null;
        }
    }

    public boolean isLive() {
        return ttl > 0;
    }

    public Object getData() {
        if(str != null)
            return str;
        else 
            return data;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IPAddress ip : path) {
            sb.append(ip.toString()).append(" , ");
        }
        sb.append(getData().toString());
        return sb.toString();
    }

    private T data;
    private IPAddress destination;
    private int ttl;
    private List<IPAddress> path = new LinkedList<IPAddress>();
}