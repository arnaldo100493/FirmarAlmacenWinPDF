// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPObjectFactory.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGInputStream;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import co.org.bouncy.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPSignature, PGPException, PGPSignatureList, PGPSecretKeyRing, 
//            PGPPublicKeyRing, PGPCompressedData, PGPLiteralData, PGPEncryptedDataList, 
//            PGPOnePassSignature, PGPOnePassSignatureList, PGPMarker

public class PGPObjectFactory
{

    public PGPObjectFactory(InputStream in)
    {
        this(in, ((KeyFingerPrintCalculator) (new JcaKeyFingerprintCalculator())));
    }

    public PGPObjectFactory(InputStream in, KeyFingerPrintCalculator fingerPrintCalculator)
    {
        this.in = new BCPGInputStream(in);
        this.fingerPrintCalculator = fingerPrintCalculator;
    }

    public PGPObjectFactory(byte bytes[])
    {
        this(((InputStream) (new ByteArrayInputStream(bytes))));
    }

    public PGPObjectFactory(byte bytes[], KeyFingerPrintCalculator fingerPrintCalculator)
    {
        this(((InputStream) (new ByteArrayInputStream(bytes))), fingerPrintCalculator);
    }

    public Object nextObject()
        throws IOException
    {
        switch(in.nextPacketTag())
        {
        case -1: 
        {
            return null;
        }

        case 2: // '\002'
        {
            List l = new ArrayList();
            while(in.nextPacketTag() == 2) 
                try
                {
                    l.add(new PGPSignature(in));
                }
                catch(PGPException e)
                {
                    throw new IOException((new StringBuilder()).append("can't create signature object: ").append(e).toString());
                }
            return new PGPSignatureList((PGPSignature[])(PGPSignature[])l.toArray(new PGPSignature[l.size()]));
        }

        case 5: // '\005'
        {
            try
            {
                return new PGPSecretKeyRing(in, fingerPrintCalculator);
            }
            catch(PGPException e)
            {
                throw new IOException((new StringBuilder()).append("can't create secret key object: ").append(e).toString());
            }
        }

        case 6: // '\006'
        {
            return new PGPPublicKeyRing(in, fingerPrintCalculator);
        }

        case 14: // '\016'
        {
            try
            {
                return PGPPublicKeyRing.readSubkey(in, fingerPrintCalculator);
            }
            catch(PGPException e)
            {
                throw new IOException((new StringBuilder()).append("processing error: ").append(e.getMessage()).toString());
            }
        }

        case 8: // '\b'
        {
            return new PGPCompressedData(in);
        }

        case 11: // '\013'
        {
            return new PGPLiteralData(in);
        }

        case 1: // '\001'
        case 3: // '\003'
        {
            return new PGPEncryptedDataList(in);
        }

        case 4: // '\004'
        {
            List l = new ArrayList();
            while(in.nextPacketTag() == 4) 
                try
                {
                    l.add(new PGPOnePassSignature(in));
                }
                catch(PGPException e)
                {
                    throw new IOException((new StringBuilder()).append("can't create one pass signature object: ").append(e).toString());
                }
            return new PGPOnePassSignatureList((PGPOnePassSignature[])(PGPOnePassSignature[])l.toArray(new PGPOnePassSignature[l.size()]));
        }

        case 10: // '\n'
        {
            return new PGPMarker(in);
        }

        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        {
            return in.readPacket();
        }

        case 0: // '\0'
        case 7: // '\007'
        case 9: // '\t'
        case 12: // '\f'
        case 13: // '\r'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 27: // '\033'
        case 28: // '\034'
        case 29: // '\035'
        case 30: // '\036'
        case 31: // '\037'
        case 32: // ' '
        case 33: // '!'
        case 34: // '"'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 46: // '.'
        case 47: // '/'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
        case 58: // ':'
        case 59: // ';'
        default:
        {
            throw new IOException((new StringBuilder()).append("unknown object in stream: ").append(in.nextPacketTag()).toString());
        }
        }
    }

    private BCPGInputStream in;
    private KeyFingerPrintCalculator fingerPrintCalculator;
}
