// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultSecretKeyProvider.java

package co.org.bouncy.operator;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.ntt.NTTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.util.Integers;
import java.util.*;

// Referenced classes of package co.org.bouncy.operator:
//            SecretKeySizeProvider

public class DefaultSecretKeyProvider
    implements SecretKeySizeProvider
{

    public DefaultSecretKeyProvider()
    {
    }

    public int getKeySize(AlgorithmIdentifier algorithmIdentifier)
    {
        Integer keySize = (Integer)KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
        if(keySize != null)
            return keySize.intValue();
        else
            return -1;
    }

    public static final SecretKeySizeProvider INSTANCE = new DefaultSecretKeyProvider();
    private static final Map KEY_SIZES;

    static 
    {
        Map keySizes = new HashMap();
        keySizes.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
        keySizes.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        keySizes.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
        keySizes.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
        keySizes.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
        keySizes.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
        keySizes.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
        keySizes.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
        KEY_SIZES = Collections.unmodifiableMap(keySizes);
    }
}
