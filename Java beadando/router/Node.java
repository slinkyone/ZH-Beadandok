package router;
import router.IPAddress;
import router.Packet;
import java.util.LinkedList;
//                      NODE

public class Node{

	private Node parent;
	private LinkedList<Node> children = new LinkedList<Node>();
	private IPAddress nodeip;
	private IPAddress startip;
	private IPAddress endip;

	public Node(Node parent, IPAddress nodeip, IPAddress startip, IPAddress endip){
		this.parent = parent;
		this.nodeip = nodeip;
		this.startip = startip;
		this.endip = endip;
		parent.children.add(this);
	}

	public Node(IPAddress nodeip, IPAddress startip, IPAddress endip){
		this.nodeip = nodeip;
		this.startip = startip;
		this.endip = endip;
		parent = null;
	}

	public String tracePacket(Packet actualpacket){
		actualpacket.recordPath(nodeip);
		if (nodeip.isTheSame(actualpacket.getDestination())) {
			return actualpacket.toString();
		}else{
			if (actualpacket.getDestination().insideRng(startip,endip)) {
				for (int i = 0; i < children.size(); ++i) {
					if (actualpacket.getDestination().insideRng(children.get(i).startip,children.get(i).endip)) 
						return children.get(i).tracePacket(actualpacket);
					}
				}
			else{
				if (parent == null) {
					for (int i = 0; i < children.size(); ++i)
						if (actualpacket.getDestination().insideRng(children.get(i).startip,children.get(i).endip)) 
								return children.get(i).tracePacket(actualpacket);
					return "OUT";
				}else{
					return parent.tracePacket(actualpacket);
				}
			}
		}
		if (actualpacket.isLive())
			tracePacket(actualpacket);
		return "LOOP";
	}

}