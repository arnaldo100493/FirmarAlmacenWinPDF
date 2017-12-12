// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHIESPublicKeyParser.java

package co.org.bouncy.crypto.parsers;

import co.org.bouncy.crypto.KeyParser;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class DHIESPublicKeyParser
    implements KeyParser
{

    public DHIESPublicKeyParser(DHParameters dhParams)
    {
        this.dhParams = dhParams;
    }

    public AsymmetricKeyParameter readKey(InputStream stream)
        throws IOException
    {
        byte V[] = new byte[(dhParams.getP().bitLength() + 7) / 8];
        stream.read(V, 0, V.length);
        return new DHPublicKeyParameters(new BigInteger(1, V), dhParams);
    }

    private DHParameters dhParams;
}
