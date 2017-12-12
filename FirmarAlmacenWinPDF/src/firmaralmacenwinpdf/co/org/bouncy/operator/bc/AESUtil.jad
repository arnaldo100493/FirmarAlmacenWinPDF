// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AESUtil.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.params.KeyParameter;

class AESUtil
{

    AESUtil()
    {
    }

    static AlgorithmIdentifier determineKeyEncAlg(KeyParameter key)
    {
        int length = key.getKey().length * 8;
        ASN1ObjectIdentifier wrapOid;
        if(length == 128)
            wrapOid = NISTObjectIdentifiers.id_aes128_wrap;
        else
        if(length == 192)
            wrapOid = NISTObjectIdentifiers.id_aes192_wrap;
        else
        if(length == 256)
            wrapOid = NISTObjectIdentifiers.id_aes256_wrap;
        else
            throw new IllegalArgumentException("illegal keysize in AES");
        return new AlgorithmIdentifier(wrapOid);
    }
}
