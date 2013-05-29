package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import base.*;

import com.aem.sticky.StickyListener;
import com.aem.sticky.button.Button;
import com.aem.sticky.button.SimpleButton;
import com.aem.sticky.button.events.ClickListener;

public class BJGame extends BasicGame {
	private static String id;
	private static String ip;
	
	public static CasinoPublic casino;
	public CasinoDrawer cdr;

	public SimpleButton hit_button;
	public SimpleButton stand_button;
	public SimpleButton start_button;
	public SimpleButton refresh_button;

	public static Resources resources;
    private StickyListener listener;

    public BJGame() {
		super("BJ Game");
	}
 
    public static void SetCasino(CasinoPublic value) {
    	synchronized (casino) {
    		casino = new CasinoPublic(value);
    	}
    }

	public static void main(String[] arguments) {
        try {
        	if (arguments.length == 0) {
        		ip = "127.0.0.1";
        		id = "player" + Integer.toString((new Random()).nextInt(100000));
        	} else if (arguments.length == 2) {
        		ip = arguments[0];
        		id = arguments[1];
        	} else {
                System.out.println("Wrong number of parametrs");
                return;
        	}

        	System.out.println("Welcome, " + id + " !");

        	Receiver recv = new Receiver();
        	recv.start();
        	AppGameContainer app = new AppGameContainer(new BJGame());
            app.setDisplayMode(1280, 800, false);
            app.start();
            System.out.println("Ciao!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	private void sendReqest(Request.Type req) throws IOException, InterruptedException {
        System.out.println("Sending request...");
		while (true) {
            try {
                InetAddress addr = InetAddress.getByName(ip);
                Socket s = new Socket(addr, 7200);
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(new Request(id, req));
                s.close();
                break;
            } catch (ConnectException ce) {
               // wait for a while and repeat
               System.out.println("waiting for server to start...");
               Thread.sleep(1000);
               continue;
            }
		}
        System.out.println("Request sent");
	}

	public boolean closeRequested() {
		try {
			sendReqest(Request.Type.DISCONNECT);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("bye bye");
		return true;
	}

    public void init(GameContainer container) throws SlickException {
    	resources = new Resources();
    	resources.load();
    
		try {
			sendReqest(Request.Type.CONNECT);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

    	cdr = new CasinoDrawer(resources);
    	casino = new CasinoPublic();

        listener = new StickyListener();
        container.getInput().addListener(listener);

        hit_button = new SimpleButton(new Rectangle(410, 350, 150, 50), resources.hit_up, resources.hit_down);
        hit_button.addListener(new ClickListener() {
            public void onClick(Button clicked, float mx, float my) {ButtonAction(Request.Type.HIT);}
            public void onDoubleClick(Button clicked, float mx, float my) {}
            public void onRightClick(Button clicked, float mx, float my) {}
        });
        listener.add(hit_button);

        stand_button = new SimpleButton(new Rectangle(570, 350, 150, 50), resources.stand_up, resources.stand_down);
        stand_button.addListener(new ClickListener() {
            public void onClick(Button clicked, float mx, float my) {ButtonAction(Request.Type.STAND);}
            public void onDoubleClick(Button clicked, float mx, float my) {}
            public void onRightClick(Button clicked, float mx, float my) {}
        });
        listener.add(stand_button);
 
        start_button = new SimpleButton(new Rectangle(730, 350, 150, 50), resources.start_up, resources.start_down);
        start_button.addListener(new ClickListener() {
            public void onClick(Button clicked, float mx, float my) {ButtonAction(Request.Type.START);}
            public void onDoubleClick(Button clicked, float mx, float my) {}
            public void onRightClick(Button clicked, float mx, float my) {}
        });
        listener.add(start_button);
    }
    
    public void ButtonAction(Request.Type type) {
    		try {
				sendReqest(type);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }


    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        hit_button.update(container, delta);
        stand_button.update(container, delta);
	    start_button.update(container, delta);
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
    	resources.desk.draw();
    	synchronized(casino) {
    		cdr.DrawCasino(casino, id);
    	}

   	    start_button.render(container, g);
   		hit_button.render(container, g);
   		stand_button.render(container, g);
    }
}
