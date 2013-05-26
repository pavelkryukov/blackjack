package base;

import java.io.Serializable;

public class Request implements Serializable {	
	private static final long serialVersionUID = 1L;
	public enum Type {
		CONNECT,    // User wants to join Casino
		DISCONNECT, // User wants to leave Casino
		START,      // User is ready to start a new game
		RESIGN,     // User does not want to continue current game
		GIVE,		// User wants to get a new card
		REFRESH,    // Empty request
	}

	final private String id;
	final private Type type;

	public Request(String id, Type type) {
		this.id = id;
		this.type = type;
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
	public Boolean IsGive() {
		return type == Type.GIVE;
	}
	public Boolean IsStart() {
		return type == Type.START;
	}
	public Type GetType() {
		return type;
	}
}