/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import base.CasinoPublic;
import base.Request;

public final class GameServer {
    private final Broadcaster broadcaster;
    
    /**
     * Casino state
     */
    private final Casino casino;
    
    /**
     * Serialized version of casino for transmission
     */
    private final ByteArrayOutputStream casinoPublic;
    public ByteArrayOutputStream GetCasinoPublic() {
        return casinoPublic;
    }
    
    /**
     * Reading socket
     */
    private ServerSocket serverSocket;

    /**
     * Constructor
     */
    public GameServer()
    {
        // Initialize game situation
        this.casino = new Casino();
        this.casinoPublic = new ByteArrayOutputStream();
        this.UpdateCasinoPublic();

        // Initialize and start broadcaster
        this.broadcaster = new Broadcaster(this);
        broadcaster.start();

        // Initialize socket
        try {
            this.serverSocket = new ServerSocket(7200);
        } catch (IOException e) {
            System.out.println("Error during server initialization");
            e.printStackTrace();
        }
    }

    /**
     * Program main method
     */
    private void Run()
    {
        while (true) {
            this.casino.ProcessRequest(ReadRequest());
            UpdateCasinoPublic();             // Update visible state
        }
    }
    
    /**
     * Updates version of casino for broadcaster
     */
    private void UpdateCasinoPublic() {
        synchronized (this.casinoPublic) {
            this.casinoPublic.reset();
            try {
                // Serialize object to bytes and save
                ObjectOutputStream oos = new ObjectOutputStream(this.casinoPublic);
                oos.writeObject(new CasinoPublic(casino));
                oos.close();
            } catch (IOException e) {
                System.out.println("Error during copy of casino to byte array");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Read request from clients
     */
    private Request ReadRequest() {
        try {                
            Socket clientConn = serverSocket.accept();
            broadcaster.AddAddress(clientConn.getInetAddress());
            System.out.println("Request from..." + clientConn.getInetAddress().toString());
            ObjectInputStream objectInput = new ObjectInputStream(clientConn.getInputStream());
            try {
                Object object = (Request) objectInput.readObject();
                Request tmp = (Request) object;
                return tmp;
            } catch (ClassNotFoundException e) {
                System.out.println("Incorrect request is received");
                e.printStackTrace();
            }
            System.out.println("Request from " + clientConn.getInetAddress() + " received");
            clientConn.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Entry point
     * @param arguments ignored
     */
    public static void main(String[] arguments) {
        try {
            System.out.println("BlackJack Server v0.1");
            GameServer game = new GameServer();
            game.Run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}