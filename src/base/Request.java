package base;

public class Request {	
	public enum Type {
		CONNECT,    // User wants to join Casino
		DISCONNECT, // User wants to leave Casino
		START,      // User is ready to start a new game
		RESIGN,     // User do not want to continue current game
	}

	private String id;
	private Type type;

	public Request(String id) {
		this.id = id;
	}
	public String GetId() {
		return id;
	}
	public Boolean IsConnect() {
		return type == Type.CONNECT;
	}
	public Boolean IsDisconnect() {
		return type == Type.DISCONNECT;
	}
	public Type GetType() {
		return type;
	}
}