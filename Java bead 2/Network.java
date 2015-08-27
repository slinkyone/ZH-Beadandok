import router.IPAddress;
import router.Node;
    
public class Network {
        
    public static Node createNetwork() {
        Node n1 = new Node(IPAddress.fromString("8.8.8.8"),
                           IPAddress.fromString("10.0.0.0"),
                           IPAddress.fromString("192.168.255.255"));

        Node n2 = new Node(n1, IPAddress.fromString("10.0.0.1"),
                           IPAddress.fromString("10.0.0.0"),
                           IPAddress.fromString("10.0.0.255"));
        Node n3 = new Node(n1, IPAddress.fromString("192.168.0.1"),
                           IPAddress.fromString("192.168.0.0"),
                           IPAddress.fromString("192.168.0.255"));

        Node n4 = new Node(n2, IPAddress.fromString("10.0.0.3"),
                           IPAddress.fromString("10.0.0.3"),
                           IPAddress.fromString("10.0.0.3"));
        Node n5 = new Node(n2, IPAddress.fromString("10.0.0.11"),
                           IPAddress.fromString("10.0.0.11"),
                           IPAddress.fromString("10.0.0.11"));
        Node n6 = new Node(n3, IPAddress.fromString("192.168.0.5"),
                           IPAddress.fromString("192.168.0.5"),
                           IPAddress.fromString("192.168.0.5"));
        Node n7 = new Node(n3, IPAddress.fromString("192.168.0.7"),
                           IPAddress.fromString("192.168.0.7"),
                           IPAddress.fromString("192.168.0.7"));

        return n4;
    }

}
