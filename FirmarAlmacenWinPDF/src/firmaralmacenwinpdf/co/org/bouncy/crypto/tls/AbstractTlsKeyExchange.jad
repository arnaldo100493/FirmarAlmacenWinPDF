// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTlsKeyExchange.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsKeyExchange, ProtocolVersion, TlsContext, 
//            TlsUtils, TlsCredentials, Certificate

public abstract class AbstractTlsKeyExchange
    implements TlsKeyExchange
{

    protected AbstractTlsKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms)
    {
        this.keyExchange = keyExchange;
        this.supportedSignatureAlgorithms = supportedSignatureAlgorithms;
    }

    public void init(TlsContext context)
    {
        this.context = context;
        ProtocolVersion clientVersion = context.getClientVersion();
        if(TlsUtils.isSignatureAlgorithmsExtensionAllowed(clientVersion))
        {
            if(supportedSignatureAlgorithms == null)
                switch(keyExchange)
                {
                case 3: // '\003'
                case 7: // '\007'
                case 22: // '\026'
                    supportedSignatureAlgorithms = TlsUtils.getDefaultDSSSignatureAlgorithms();
                    break;

                case 16: // '\020'
                case 17: // '\021'
                    supportedSignatureAlgorithms = TlsUtils.getDefaultECDSASignatureAlgorithms();
                    break;

                case 1: // '\001'
                case 5: // '\005'
                case 9: // '\t'
                case 15: // '\017'
                case 18: // '\022'
                case 19: // '\023'
                case 23: // '\027'
                    supportedSignatureAlgorithms = TlsUtils.getDefaultRSASignatureAlgorithms();
                    break;

                case 2: // '\002'
                case 4: // '\004'
                case 6: // '\006'
                case 8: // '\b'
                case 10: // '\n'
                case 11: // '\013'
                case 12: // '\f'
                case 13: // '\r'
                case 14: // '\016'
                case 20: // '\024'
                case 21: // '\025'
                default:
                    throw new IllegalStateException("unsupported key exchange algorithm");
                }
        } else
        if(supportedSignatureAlgorithms != null)
            throw new IllegalStateException((new StringBuilder()).append("supported_signature_algorithms not allowed for ").append(clientVersion).toString());
    }

    public void processServerCertificate(Certificate serverCertificate)
        throws IOException
    {
        if(supportedSignatureAlgorithms != null);
    }

    public void processServerCredentials(TlsCredentials serverCredentials)
        throws IOException
    {
        processServerCertificate(serverCredentials.getCertificate());
    }

    public boolean requiresServerKeyExchange()
    {
        return false;
    }

    public byte[] generateServerKeyExchange()
        throws IOException
    {
        if(requiresServerKeyExchange())
            throw new TlsFatalAlert((short)80);
        else
            return null;
    }

    public void skipServerKeyExchange()
        throws IOException
    {
        if(requiresServerKeyExchange())
            throw new TlsFatalAlert((short)10);
        else
            return;
    }

    public void processServerKeyExchange(InputStream input)
        throws IOException
    {
        if(!requiresServerKeyExchange())
            throw new TlsFatalAlert((short)10);
        else
            return;
    }

    public void skipClientCredentials()
        throws IOException
    {
    }

    public void processClientCertificate(Certificate certificate)
        throws IOException
    {
    }

    public void processClientKeyExchange(InputStream input)
        throws IOException
    {
        throw new TlsFatalAlert((short)80);
    }

    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;
    protected TlsContext context;
}
