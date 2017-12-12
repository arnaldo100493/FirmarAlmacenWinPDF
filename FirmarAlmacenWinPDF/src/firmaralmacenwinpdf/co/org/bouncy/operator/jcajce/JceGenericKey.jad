// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceGenericKey.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.GenericKey;
import java.security.Key;

public class JceGenericKey extends GenericKey
{

    private static Object getRepresentation(Key key)
    {
        byte keyBytes[] = key.getEncoded();
        if(keyBytes != null)
            return keyBytes;
        else
            return key;
    }

    public JceGenericKey(AlgorithmIdentifier algorithmIdentifier, Key representation)
    {
        super(algorithmIdentifier, getRepresentation(representation));
    }
}
