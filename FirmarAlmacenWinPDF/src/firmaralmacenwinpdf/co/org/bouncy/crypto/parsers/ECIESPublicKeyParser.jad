// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECIESPublicKeyParser.java

package co.org.bouncy.crypto.parsers;

import co.org.bouncy.crypto.KeyParser;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.ECCurve;
import java.io.IOException;
import java.io.InputStream;

public class ECIESPublicKeyParser
    implements KeyParser
{

    public ECIESPublicKeyParser(ECDomainParameters ecParams)
    {
        this.ecParams = ecParams;
    }

    public AsymmetricKeyParameter readKey(InputStream stream)
        throws IOException
    {
        int first = stream.read();
        byte V[];
        switch(first)
        {
        case 0: // '\0'
            throw new IOException("Sender's public key invalid.");

        case 2: // '\002'
        case 3: // '\003'
            V = new byte[1 + (ecParams.getCurve().getFieldSize() + 7) / 8];
            break;

        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
            V = new byte[1 + 2 * ((ecParams.getCurve().getFieldSize() + 7) / 8)];
            break;

        case 1: // '\001'
        case 5: // '\005'
        default:
            throw new IOException((new StringBuilder()).append("Sender's public key has invalid point encoding 0x").append(Integer.toString(first, 16)).toString());
        }
        V[0] = (byte)first;
        stream.read(V, 1, V.length - 1);
        return new ECPublicKeyParameters(ecParams.getCurve().decodePoint(V), ecParams);
    }

    private ECDomainParameters ecParams;
}
