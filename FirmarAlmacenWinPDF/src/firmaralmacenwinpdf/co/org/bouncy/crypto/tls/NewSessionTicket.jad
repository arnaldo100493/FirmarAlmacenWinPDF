// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewSessionTicket.java

package co.org.bouncy.crypto.tls;

import java.io.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsUtils

public class NewSessionTicket
{

    public NewSessionTicket(long ticketLifetimeHint, byte ticket[])
    {
        this.ticketLifetimeHint = ticketLifetimeHint;
        this.ticket = ticket;
    }

    public long getTicketLifetimeHint()
    {
        return ticketLifetimeHint;
    }

    public byte[] getTicket()
    {
        return ticket;
    }

    public void encode(OutputStream output)
        throws IOException
    {
        TlsUtils.writeUint32(ticketLifetimeHint, output);
        TlsUtils.writeOpaque16(ticket, output);
    }

    public static NewSessionTicket parse(InputStream input)
        throws IOException
    {
        long ticketLifetimeHint = TlsUtils.readUint32(input);
        byte ticket[] = TlsUtils.readOpaque16(input);
        return new NewSessionTicket(ticketLifetimeHint, ticket);
    }

    protected long ticketLifetimeHint;
    protected byte ticket[];
}
