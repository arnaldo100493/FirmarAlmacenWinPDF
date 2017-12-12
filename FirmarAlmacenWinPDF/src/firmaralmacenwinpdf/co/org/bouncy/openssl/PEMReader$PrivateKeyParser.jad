// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$PrivateKeyParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            PrivateKeyInfo info = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(obj.getContent()));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(obj.getContent());
            KeyFactory keyFact = KeyFactory.getInstance(info.getPrivateKeyAlgorithm().getAlgorithm().getId(), provider);
            return keyFact.generatePrivate(keySpec);
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing PRIVATE KEY: ").append(e.toString()).toString(), e);
        }
    }

    private String provider;
    final PEMReader this$0;

    public PEMReader$PrivateKeyParser(String provider)
    {
        this$0 = PEMReader.this;
        super();
        this.provider = provider;
    }
}
