// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultTlsClient.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsClient, TlsFatalAlert, TlsDHKeyExchange, TlsDHEKeyExchange, 
//            TlsECDHKeyExchange, TlsECDHEKeyExchange, TlsRSAKeyExchange, TlsECCUtils, 
//            TlsCipherFactory, TlsKeyExchange, TlsCipher

public abstract class DefaultTlsClient extends AbstractTlsClient
{

    public DefaultTlsClient()
    {
    }

    public DefaultTlsClient(TlsCipherFactory cipherFactory)
    {
        super(cipherFactory);
    }

    public int[] getCipherSuites()
    {
        return (new int[] {
            49172, 49171, 49170, 57, 51, 22, 53, 47, 10
        });
    }

    public Hashtable getClientExtensions()
        throws IOException
    {
        Hashtable clientExtensions = super.getClientExtensions();
        if(TlsECCUtils.containsECCCipherSuites(getCipherSuites()))
        {
            namedCurves = (new int[] {
                23, 7, 21, 4, 19, 65282, 65281
            });
            clientECPointFormats = (new short[] {
                2, 1, 0
            });
            if(clientExtensions == null)
                clientExtensions = new Hashtable();
            TlsECCUtils.addSupportedEllipticCurvesExtension(clientExtensions, namedCurves);
            TlsECCUtils.addSupportedPointFormatsExtension(clientExtensions, clientECPointFormats);
        }
        return clientExtensions;
    }

    public void processServerExtensions(Hashtable serverExtensions)
        throws IOException
    {
        super.processServerExtensions(serverExtensions);
        if(serverExtensions != null)
        {
            int namedCurves[] = TlsECCUtils.getSupportedEllipticCurvesExtension(serverExtensions);
            if(namedCurves != null)
                throw new TlsFatalAlert((short)47);
            serverECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(serverExtensions);
            if(serverECPointFormats != null && !TlsECCUtils.isECCCipherSuite(selectedCipherSuite))
                throw new TlsFatalAlert((short)47);
        }
    }

    public TlsKeyExchange getKeyExchange()
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 13: // '\r'
        case 48: // '0'
        case 54: // '6'
        case 62: // '>'
        case 66: // 'B'
        case 104: // 'h'
        case 133: 
        case 151: 
        case 164: 
        case 165: 
            return createDHKeyExchange(7);

        case 16: // '\020'
        case 49: // '1'
        case 55: // '7'
        case 63: // '?'
        case 67: // 'C'
        case 105: // 'i'
        case 134: 
        case 152: 
        case 160: 
        case 161: 
            return createDHKeyExchange(9);

        case 19: // '\023'
        case 50: // '2'
        case 56: // '8'
        case 64: // '@'
        case 68: // 'D'
        case 106: // 'j'
        case 135: 
        case 153: 
        case 162: 
        case 163: 
            return createDHEKeyExchange(3);

        case 22: // '\026'
        case 51: // '3'
        case 57: // '9'
        case 69: // 'E'
        case 103: // 'g'
        case 107: // 'k'
        case 136: 
        case 154: 
        case 158: 
        case 159: 
            return createDHEKeyExchange(5);

        case 49153: 
        case 49154: 
        case 49155: 
        case 49156: 
        case 49157: 
        case 49189: 
        case 49190: 
        case 49197: 
        case 49198: 
            return createECDHKeyExchange(16);

        case 49163: 
        case 49164: 
        case 49165: 
        case 49166: 
        case 49167: 
        case 49193: 
        case 49194: 
        case 49201: 
        case 49202: 
            return createECDHKeyExchange(18);

        case 49158: 
        case 49159: 
        case 49160: 
        case 49161: 
        case 49162: 
        case 49187: 
        case 49188: 
        case 49195: 
        case 49196: 
            return createECDHEKeyExchange(17);

        case 49168: 
        case 49169: 
        case 49170: 
        case 49171: 
        case 49172: 
        case 49191: 
        case 49192: 
        case 49199: 
        case 49200: 
            return createECDHEKeyExchange(19);

        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        case 10: // '\n'
        case 47: // '/'
        case 53: // '5'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 65: // 'A'
        case 132: 
        case 150: 
        case 156: 
        case 157: 
            return createRSAKeyExchange();
        }
        throw new TlsFatalAlert((short)80);
    }

    public TlsCipher getCipher()
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 10: // '\n'
        case 13: // '\r'
        case 16: // '\020'
        case 19: // '\023'
        case 22: // '\026'
        case 49155: 
        case 49160: 
        case 49165: 
        case 49170: 
            return cipherFactory.createCipher(context, 7, 2);

        case 47: // '/'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 49156: 
        case 49161: 
        case 49166: 
        case 49171: 
            return cipherFactory.createCipher(context, 8, 2);

        case 60: // '<'
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 103: // 'g'
        case 49187: 
        case 49189: 
        case 49191: 
        case 49193: 
            return cipherFactory.createCipher(context, 8, 3);

        case 156: 
        case 158: 
        case 160: 
        case 162: 
        case 164: 
        case 49195: 
        case 49197: 
        case 49199: 
        case 49201: 
            return cipherFactory.createCipher(context, 10, 0);

        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
        case 49157: 
        case 49162: 
        case 49167: 
        case 49172: 
            return cipherFactory.createCipher(context, 9, 2);

        case 61: // '='
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
            return cipherFactory.createCipher(context, 9, 3);

        case 49188: 
        case 49190: 
        case 49192: 
        case 49194: 
            return cipherFactory.createCipher(context, 9, 4);

        case 157: 
        case 159: 
        case 161: 
        case 163: 
        case 165: 
        case 49196: 
        case 49198: 
        case 49200: 
        case 49202: 
            return cipherFactory.createCipher(context, 11, 0);

        case 65: // 'A'
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 69: // 'E'
            return cipherFactory.createCipher(context, 12, 2);

        case 132: 
        case 133: 
        case 134: 
        case 135: 
        case 136: 
            return cipherFactory.createCipher(context, 13, 2);

        case 1: // '\001'
            return cipherFactory.createCipher(context, 0, 1);

        case 2: // '\002'
        case 49153: 
        case 49158: 
        case 49163: 
        case 49168: 
            return cipherFactory.createCipher(context, 0, 2);

        case 59: // ';'
            return cipherFactory.createCipher(context, 0, 3);

        case 4: // '\004'
            return cipherFactory.createCipher(context, 2, 1);

        case 5: // '\005'
        case 49154: 
        case 49159: 
        case 49164: 
        case 49169: 
            return cipherFactory.createCipher(context, 2, 2);

        case 150: 
        case 151: 
        case 152: 
        case 153: 
        case 154: 
            return cipherFactory.createCipher(context, 14, 2);
        }
        throw new TlsFatalAlert((short)80);
    }

    protected TlsKeyExchange createDHKeyExchange(int keyExchange)
    {
        return new TlsDHKeyExchange(keyExchange, supportedSignatureAlgorithms, null);
    }

    protected TlsKeyExchange createDHEKeyExchange(int keyExchange)
    {
        return new TlsDHEKeyExchange(keyExchange, supportedSignatureAlgorithms, null);
    }

    protected TlsKeyExchange createECDHKeyExchange(int keyExchange)
    {
        return new TlsECDHKeyExchange(keyExchange, supportedSignatureAlgorithms, namedCurves, clientECPointFormats, serverECPointFormats);
    }

    protected TlsKeyExchange createECDHEKeyExchange(int keyExchange)
    {
        return new TlsECDHEKeyExchange(keyExchange, supportedSignatureAlgorithms, namedCurves, clientECPointFormats, serverECPointFormats);
    }

    protected TlsKeyExchange createRSAKeyExchange()
    {
        return new TlsRSAKeyExchange(supportedSignatureAlgorithms);
    }

    protected int namedCurves[];
    protected short clientECPointFormats[];
    protected short serverECPointFormats[];
}
