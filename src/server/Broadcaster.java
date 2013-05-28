/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public final class Broadcaster extends Thread {
    /**
     * Parent GameServer
     */
    private final GameServer gs;

    /**
     * Set of addresses to send to
     */
    private final Set<InetAddress> addresses;

    /**
     * Constructor
     * @param dgs parent GameServer
     */
    public Broadcaster(GameServer dgs) {
        this.gs = dgs;
        this.addresses = new HashSet<InetAddress>();
    }

    /**
     * Add new address to send to
     * @param address new address
     */
    public void AddAddress(InetAddress address) {
        addresses.add(address);
    }

    /**
     * Main program point
     */
    public void run() {
        while (true) {
            // Get bytes from GameServer
            byte[] bytes;
            synchronized (gs.GetCasinoPublic()) {
                bytes = gs.GetCasinoPublic().toByteArray();
            }
            // Send to every address
            for (InetAddress address : addresses) {
                try {
                    Socket clientSocket = new Socket(address, 7500);
                    clientSocket.getOutputStream().write(bytes);
                    clientSocket.close();
                } catch (IOException e) {
                    // Connection failed, delete this address from set
                    System.out.println("Invalid IP " + address.toString());
                    addresses.remove(address);
                }
            }
        }
    }
}


