// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTlsClient.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsPeer, DefaultTlsCipherFactory, SignatureAndHashAlgorithm, TlsFatalAlert, 
//            TlsNullCompression, TlsClient, ProtocolVersion, TlsClientContext, 
//            TlsUtils, TlsCipherFactory, TlsCompression, NewSessionTicket

public abstract class AbstractTlsClient extends AbstractTlsPeer
    implements TlsClient
{

    public AbstractTlsClient()
    {
        this(((TlsCipherFactory) (new DefaultTlsCipherFactory())));
    }

    public AbstractTlsClient(TlsCipherFactory cipherFactory)
    {
        this.cipherFactory = cipherFactory;
    }

    public void init(TlsClientContext context)
    {
        this.context = context;
    }

    public ProtocolVersion getClientHelloRecordLayerVersion()
    {
        return getClientVersion();
    }

    public ProtocolVersion getClientVersion()
    {
        return ProtocolVersion.TLSv11;
    }

    public Hashtable getClientExtensions()
        throws IOException
    {
        Hashtable clientExtensions = null;
        ProtocolVersion clientVersion = context.getClientVersion();
        if(TlsUtils.isSignatureAlgorithmsExtensionAllowed(clientVersion))
        {
            short hashAlgorithms[] = {
                6, 5, 4, 3, 2
            };
            short signatureAlgorithms[] = {
                1
            };
            supportedSignatureAlgorithms = new Vector();
            for(int i = 0; i < hashAlgorithms.length; i++)
            {
                for(int j = 0; j < signatureAlgorithms.length; j++)
                    supportedSignatureAlgorithms.addElement(new SignatureAndHashAlgorithm(hashAlgorithms[i], signatureAlgorithms[j]));

            }

            supportedSignatureAlgorithms.addElement(new SignatureAndHashAlgorithm((short)2, (short)2));
            if(clientExtensions == null)
                clientExtensions = new Hashtable();
            TlsUtils.addSignatureAlgorithmsExtension(clientExtensions, supportedSignatureAlgorithms);
        }
        return clientExtensions;
    }

    public ProtocolVersion getMinimumVersion()
    {
        return ProtocolVersion.TLSv10;
    }

    public void notifyServerVersion(ProtocolVersion serverVersion)
        throws IOException
    {
        if(!getMinimumVersion().isEqualOrEarlierVersionOf(serverVersion))
            throw new TlsFatalAlert((short)70);
        else
            return;
    }

    public short[] getCompressionMethods()
    {
        return (new short[] {
            0
        });
    }

    public void notifySessionID(byte abyte0[])
    {
    }

    public void notifySelectedCipherSuite(int selectedCipherSuite)
    {
        this.selectedCipherSuite = selectedCipherSuite;
    }

    public void notifySelectedCompressionMethod(short selectedCompressionMethod)
    {
        this.selectedCompressionMethod = selectedCompressionMethod;
    }

    public void notifySecureRenegotiation(boolean secureRenegotiation)
        throws IOException
    {
        if(secureRenegotiation);
    }

    public void processServerExtensions(Hashtable serverExtensions)
        throws IOException
    {
        if(serverExtensions != null && serverExtensions.containsKey(TlsUtils.EXT_signature_algorithms))
            throw new TlsFatalAlert((short)47);
        else
            return;
    }

    public void processServerSupplementalData(Vector serverSupplementalData)
        throws IOException
    {
        if(serverSupplementalData != null)
            throw new TlsFatalAlert((short)10);
        else
            return;
    }

    public Vector getClientSupplementalData()
        throws IOException
    {
        return null;
    }

    public TlsCompression getCompression()
        throws IOException
    {
        switch(selectedCompressionMethod)
        {
        case 0: // '\0'
            return new TlsNullCompression();
        }
        throw new TlsFatalAlert((short)80);
    }

    public void notifyNewSessionTicket(NewSessionTicket newsessionticket)
        throws IOException
    {
    }

    public void notifyHandshakeComplete()
        throws IOException
    {
    }

    protected TlsCipherFactory cipherFactory;
    protected TlsClientContext context;
    protected Vector supportedSignatureAlgorithms;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
}
