package router;

import java.util.List;
import java.util.LinkedList;

public class Node {
    private Node parent;
    private List<Node> children = new LinkedList<Node>();
    private IPAddress selfIP;
    private IPAddress lowerBound;
    private IPAddress upperBound;

    public Node(IPAddress ip, IPAddress lowB, IPAddress upB) {
        selfIP = ip;
        lowerBound = lowB;
        upperBound = upB;
    }

    public Node(Node parent, IPAddress ip, IPAddress lowB, IPAddress upB) {
        this.parent = parent;
        parent.addChild(this);
        selfIP = ip;
        lowerBound = lowB;
        upperBound = upB;
    }

    private void addChild(Node child) {
        children.add(child);
    }

    private boolean insideRng(IPAddress ip) {
        return ip.insideRng(lowerBound, upperBound);
    }

    public String tracePacket(Packet pkt) {
        pkt.recordPath(selfIP);
        if( !pkt.isLive() ) return "LOOP";
        if ( pkt.getDestination().equals(selfIP) ) {
            return pkt.toString();
        } else {
            for ( Node child : children ) {
                if (child.insideRng(pkt.getDestination())) {
                    return child.tracePacket(pkt);
                }
            }
            if (parent != null) {
                return parent.tracePacket(pkt);
            } else { return "OUT"; }
        }
    }
}
