// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SRPTlsClient.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsClient, TlsFatalAlert, TlsSRPKeyExchange, TlsUtils, 
//            TlsCipherFactory, TlsKeyExchange, TlsCipher

public abstract class SRPTlsClient extends AbstractTlsClient
{

    public SRPTlsClient(byte identity[], byte password[])
    {
        this.identity = Arrays.clone(identity);
        this.password = Arrays.clone(password);
    }

    public SRPTlsClient(TlsCipherFactory cipherFactory, byte identity[], byte password[])
    {
        super(cipherFactory);
        this.identity = Arrays.clone(identity);
        this.password = Arrays.clone(password);
    }

    public int[] getCipherSuites()
    {
        return (new int[] {
            49185, 49182, 49179, 49184, 49181, 49178
        });
    }

    public Hashtable getClientExtensions()
        throws IOException
    {
        Hashtable clientExtensions = super.getClientExtensions();
        if(clientExtensions == null)
            clientExtensions = new Hashtable();
        ByteArrayOutputStream srpData = new ByteArrayOutputStream();
        TlsUtils.writeOpaque8(identity, srpData);
        clientExtensions.put(EXT_SRP, srpData.toByteArray());
        return clientExtensions;
    }

    public void processServerExtensions(Hashtable serverExtensions)
        throws IOException
    {
        if(serverExtensions != null)
        {
            byte extValue[] = (byte[])(byte[])serverExtensions.get(EXT_SRP);
            if(extValue != null && extValue.length > 0)
                throw new TlsFatalAlert((short)47);
        }
    }

    public TlsKeyExchange getKeyExchange()
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 49178: 
        case 49181: 
        case 49184: 
            return createSRPKeyExchange(21);

        case 49179: 
        case 49182: 
        case 49185: 
            return createSRPKeyExchange(23);

        case 49180: 
        case 49183: 
        case 49186: 
            return createSRPKeyExchange(22);
        }
        throw new TlsFatalAlert((short)80);
    }

    public TlsCipher getCipher()
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 49178: 
        case 49179: 
        case 49180: 
            return cipherFactory.createCipher(context, 7, 2);

        case 49181: 
        case 49182: 
        case 49183: 
            return cipherFactory.createCipher(context, 8, 2);

        case 49184: 
        case 49185: 
        case 49186: 
            return cipherFactory.createCipher(context, 9, 2);
        }
        throw new TlsFatalAlert((short)80);
    }

    protected TlsKeyExchange createSRPKeyExchange(int keyExchange)
    {
        return new TlsSRPKeyExchange(keyExchange, supportedSignatureAlgorithms, identity, password);
    }

    public static final Integer EXT_SRP = Integers.valueOf(12);
    protected byte identity[];
    protected byte password[];

}
