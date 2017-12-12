// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UDPTransport.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DatagramTransport

public class UDPTransport
    implements DatagramTransport
{

    public UDPTransport(DatagramSocket socket, int mtu)
        throws IOException
    {
        if(!socket.isBound() || !socket.isConnected())
        {
            throw new IllegalArgumentException("'socket' must be bound and connected");
        } else
        {
            this.socket = socket;
            receiveLimit = mtu - 20 - 8;
            sendLimit = mtu - 84 - 8;
            return;
        }
    }

    public int getReceiveLimit()
    {
        return receiveLimit;
    }

    public int getSendLimit()
    {
        return sendLimit;
    }

    public int receive(byte buf[], int off, int len, int waitMillis)
        throws IOException
    {
        socket.setSoTimeout(waitMillis);
        DatagramPacket packet = new DatagramPacket(buf, off, len);
        socket.receive(packet);
        return packet.getLength();
    }

    public void send(byte buf[], int off, int len)
        throws IOException
    {
        if(len <= getSendLimit());
        DatagramPacket packet = new DatagramPacket(buf, off, len);
        socket.send(packet);
    }

    public void close()
        throws IOException
    {
        socket.close();
    }

    private static final int MIN_IP_OVERHEAD = 20;
    private static final int MAX_IP_OVERHEAD = 84;
    private static final int UDP_OVERHEAD = 8;
    private final DatagramSocket socket;
    private final int receiveLimit;
    private final int sendLimit;
}
