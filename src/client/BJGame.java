package client;

import java.io.IOException;
import java.io.ObjectInputStream;
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

	public static String id;
	public static String ip;
	
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

	public static void main(String[] arguments) {
        try {
        	if (arguments.length == 0) {
        		id = "player" + Integer.toString((new Random()).nextInt(100000));
        		ip = "127.0.0.1";
        	} else if (arguments.length == 2) {
        		ip = arguments[0];
        		id = arguments[1];
        	} else {
                System.out.println("Wrong number of parametrs");
                return;
        	}
        	

        	System.out.println("Welcome, " + id + " !");
            
        	AppGameContainer app = new AppGameContainer(new BJGame());
            app.setDisplayMode(1280, 800, false);
            app.start();
            System.out.println("Chao!");
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
        
	private void receiveCards() {
		System.out.println("Receiver Start");

		try {
			Socket socket = new Socket(InetAddress.getByName(ip), 7300);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            try {
                Object object = (CasinoPublic) in.readObject();
                CasinoPublic tmp = (CasinoPublic) object;
                casino = new CasinoPublic(tmp);
            } catch (ClassNotFoundException e) {
            	System.out.println("Incorrect request is received");
            }
            socket.close();
		} catch (Exception e) {
            
		}
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
    	
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Luba");
        Player player3 = new Player("Vachik");
        Player player4 = new Player("Kryukov Pavel I");

        for(int i = 0; i < 4; i++) {
            player1.GetCard(Card.GetRandomCard());
            player2.GetCard(Card.GetRandomCard());
    }
    
    for(int i = 0; i < 4; i++) {
            player2.GetCard(Card.GetRandomCard());
    }
    
            player3.GetCard(Card.GetRandomCard());
            player4.GetCard(Card.GetRandomCard());
            player4.GetCard(Card.GetRandomCard());
            player2.GetCard(Card.GetRandomCard());
            player2.GetCard(Card.GetRandomCard());
            


    casino.players.put(player1.getName(), player1);
    casino.players.put(player2.getName(), player2);
    casino.players.put(player3.getName(), player3);
    casino.players.put(player4.getName(), player4);

        listener = new StickyListener();
        container.getInput().addListener(listener);

        refresh_button = new SimpleButton(new Rectangle(330, 350, 150, 50), resources.refresh_up, resources.refresh_down);
        refresh_button.addListener(new ClickListener() {
            public void onClick(Button clicked, float mx, float my) {ButtonAction(Request.Type.REFRESH);}
            public void onDoubleClick(Button clicked, float mx, float my) {}
            public void onRightClick(Button clicked, float mx, float my) {}
        });
        listener.add(refresh_button);

        hit_button = new SimpleButton(new Rectangle(490, 350, 150, 50), resources.hit_up, resources.hit_down);
        hit_button.addListener(new ClickListener() {
            public void onClick(Button clicked, float mx, float my) {ButtonAction(Request.Type.GIVE);}
            public void onDoubleClick(Button clicked, float mx, float my) {}
            public void onRightClick(Button clicked, float mx, float my) {}
        });
        listener.add(hit_button);

        stand_button = new SimpleButton(new Rectangle(650, 350, 150, 50), resources.stand_up, resources.stand_down);
        stand_button.addListener(new ClickListener() {
            public void onClick(Button clicked, float mx, float my) {ButtonAction(Request.Type.RESIGN);}
            public void onDoubleClick(Button clicked, float mx, float my) {}
            public void onRightClick(Button clicked, float mx, float my) {}
        });
        listener.add(stand_button);
 
        start_button = new SimpleButton(new Rectangle(810, 350, 150, 50), resources.start_up, resources.start_down);
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
    		System.out.println("Sending request");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        hit_button.update(container, delta);
        stand_button.update(container, delta);
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
    	resources.desk.draw();
    	receiveCards();
    	cdr.DrawCasino(casino, id);
   
    //	if (casino.isGame) {
    	    start_button.render(container, g);
    		hit_button.render(container, g);
    		stand_button.render(container, g);
    		refresh_button.render(container, g);
    //	}
    }
}
