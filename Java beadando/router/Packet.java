package router;
import router.IPAddress;
import router.Node;
import java.util.LinkedList;
//                      Packet

public class Packet{

	private String strdata;
	private IPAddress destination;
	private int max_dest = 100;
	private LinkedList<IPAddress> ips = new LinkedList<IPAddress>();

	public Packet(String strdata, IPAddress destination){
		this.strdata = strdata;
		this.destination = destination;
	}

	public IPAddress getDestination(){
		return destination;
	}

	public void recordPath(IPAddress currentip){
		if (isLive()) {
			--max_dest;
			if (ips.size() <= max_dest) {
				ips.add(currentip);
			}
		}
	}

	public boolean isLive(){
		return max_dest!=0;
	}

	public String toString(){
		String help = new String();
		for (int i = 0; i < ips.size() ; ++i) {
			help += ips.get(i).toString() + " , ";
		}
		return help + strdata;
	}
}