// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSTransport.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, DatagramTransport, DTLSRecordLayer

public class DTLSTransport
    implements DatagramTransport
{

    DTLSTransport(DTLSRecordLayer recordLayer)
    {
        this.recordLayer = recordLayer;
    }

    public int getReceiveLimit()
        throws IOException
    {
        return recordLayer.getReceiveLimit();
    }

    public int getSendLimit()
        throws IOException
    {
        return recordLayer.getSendLimit();
    }

    public int receive(byte buf[], int off, int len, int waitMillis)
        throws IOException
    {
        try
        {
            return recordLayer.receive(buf, off, len, waitMillis);
        }
        catch(TlsFatalAlert fatalAlert)
        {
            recordLayer.fail(fatalAlert.getAlertDescription());
            throw fatalAlert;
        }
        catch(IOException e)
        {
            recordLayer.fail((short)80);
            throw e;
        }
        catch(RuntimeException e)
        {
            recordLayer.fail((short)80);
        }
        throw new TlsFatalAlert((short)80);
    }

    public void send(byte buf[], int off, int len)
        throws IOException
    {
        try
        {
            recordLayer.send(buf, off, len);
        }
        catch(TlsFatalAlert fatalAlert)
        {
            recordLayer.fail(fatalAlert.getAlertDescription());
            throw fatalAlert;
        }
        catch(IOException e)
        {
            recordLayer.fail((short)80);
            throw e;
        }
        catch(RuntimeException e)
        {
            recordLayer.fail((short)80);
            throw new TlsFatalAlert((short)80);
        }
    }

    public void close()
        throws IOException
    {
        recordLayer.close();
    }

    private final DTLSRecordLayer recordLayer;
}
