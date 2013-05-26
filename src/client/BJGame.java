package client;

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
import base.*;

public class BJGame extends BasicGame {
	

   
	public static String id;
	public static String ip;
	
	public static CasinoPublic casino;
	public CasinoDrawer cdr;
	
	public static Resources resources;
	
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
        	}
    		
        	System.out.println("Welcome, " + id + " !");
    		
        	/*
            while (true) {
                try {
                    InetAddress addr = InetAddress.getByName(ip);
                    Socket s = new Socket(addr, 7000);
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(new Request(id, Request.Type.CONNECT));
                    s.close();
                    break;
                } catch (ConnectException ce) {
                   // wait for a while and repeat
                   System.out.println("waiting for server to start...");
                   Thread.sleep(1000);
                   continue;
                }
            }
            */
            
            

            System.out.println("Request sent");
        	
            AppGameContainer app = new AppGameContainer(new BJGame());
            app.setDisplayMode(1280, 800, false);
            app.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void init(GameContainer container) throws SlickException {
    	resources = new Resources();
    	resources.load();
    	
    	Player player1 = new Player("Vasya");
    	Player player2 = new Player("Luba");
    	Player player3 = new Player("Vachik");
    	Player player4 = new Player("Kryukov Pavel I");
    	Player dealer = new Player("Dealer");

    	cdr = new CasinoDrawer(resources);
    	casino = new CasinoPublic();
    	
    	for(int i = 0; i < 4; i++) {
    		player1.GetCard(Card.GetRandomCard());
    		player2.GetCard(Card.GetRandomCard());
    		dealer.GetCard(Card.GetRandomCard());
    	}
    	
    	for(int i = 0; i < 4; i++) {
    		player2.GetCard(Card.GetRandomCard());
    		dealer.GetCard(Card.GetRandomCard());
    	}
    	
		player3.GetCard(Card.GetRandomCard());
		player4.GetCard(Card.GetRandomCard());
		player4.GetCard(Card.GetRandomCard());
		player2.GetCard(Card.GetRandomCard());
		player2.GetCard(Card.GetRandomCard());
		
		dealer.GetCard(Card.GetRandomCard());
		dealer.GetCard(Card.GetRandomCard());


    	casino.players.put(player1.getName(), player1);
    	casino.players.put(player2.getName(), player2);
    	casino.players.put(player3.getName(), player3);
    	casino.players.put(player4.getName(), player4);
    	casino.dealer = dealer;
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
    	resources.desk.draw();
    	cdr.DrawCasino(casino);
    }
}
