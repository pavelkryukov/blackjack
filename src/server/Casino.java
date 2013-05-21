package server;

import java.util.Vector;
import base.Request;

public class Casino {
	private Vector<Player> players;

	public Casino() {
		this.players = new Vector<Player>();
	}

	public void ProcessRequest(Request req) {
		players.get(req.GetId()).ProcessRequest(req);
		this.UpdateState();
	}

	private void UpdateState() {
		// TODO Auto-generated method stub
		
	}
}
