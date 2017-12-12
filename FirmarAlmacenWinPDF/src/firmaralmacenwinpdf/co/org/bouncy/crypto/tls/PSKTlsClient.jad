// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PSKTlsClient.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsClient, TlsFatalAlert, TlsPSKKeyExchange, TlsCipherFactory, 
//            TlsPSKIdentity, TlsKeyExchange, TlsCipher

public abstract class PSKTlsClient extends AbstractTlsClient
{

    public PSKTlsClient(TlsPSKIdentity pskIdentity)
    {
        this.pskIdentity = pskIdentity;
    }

    public PSKTlsClient(TlsCipherFactory cipherFactory, TlsPSKIdentity pskIdentity)
    {
        super(cipherFactory);
        this.pskIdentity = pskIdentity;
    }

    public int[] getCipherSuites()
    {
        return (new int[] {
            145, 144, 143, 142, 149, 148, 147, 146, 141, 140, 
            139, 138
        });
    }

    public TlsKeyExchange getKeyExchange()
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 44: // ','
        case 138: 
        case 139: 
        case 140: 
        case 141: 
            return createPSKKeyExchange(13);

        case 46: // '.'
        case 146: 
        case 147: 
        case 148: 
        case 149: 
            return createPSKKeyExchange(15);

        case 45: // '-'
        case 142: 
        case 143: 
        case 144: 
        case 145: 
            return createPSKKeyExchange(14);
        }
        throw new TlsFatalAlert((short)80);
    }

    public TlsCipher getCipher()
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 139: 
        case 143: 
        case 147: 
            return cipherFactory.createCipher(context, 7, 2);

        case 140: 
        case 144: 
        case 148: 
            return cipherFactory.createCipher(context, 8, 2);

        case 141: 
        case 145: 
        case 149: 
            return cipherFactory.createCipher(context, 9, 2);

        case 44: // ','
        case 45: // '-'
        case 46: // '.'
            return cipherFactory.createCipher(context, 0, 2);

        case 138: 
        case 142: 
        case 146: 
            return cipherFactory.createCipher(context, 2, 2);
        }
        throw new TlsFatalAlert((short)80);
    }

    protected TlsKeyExchange createPSKKeyExchange(int keyExchange)
    {
        return new TlsPSKKeyExchange(keyExchange, supportedSignatureAlgorithms, pskIdentity);
    }

    protected TlsPSKIdentity pskIdentity;
}
