// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.pkcs.RSAPrivateKey;
import co.org.bouncy.util.io.pem.PemObject;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$RSAKeyPairParser extends PEMReader.KeyPairParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            ASN1Sequence seq = readKeyPair(obj);
            if(seq.size() != 9)
            {
                throw new PEMException("malformed sequence in RSA private key");
            } else
            {
                RSAPrivateKey keyStruct = RSAPrivateKey.getInstance(seq);
                RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(keyStruct.getModulus(), keyStruct.getPublicExponent());
                RSAPrivateCrtKeySpec privSpec = new RSAPrivateCrtKeySpec(keyStruct.getModulus(), keyStruct.getPublicExponent(), keyStruct.getPrivateExponent(), keyStruct.getPrime1(), keyStruct.getPrime2(), keyStruct.getExponent1(), keyStruct.getExponent2(), keyStruct.getCoefficient());
                KeyFactory fact = KeyFactory.getInstance("RSA", asymProvider);
                return new KeyPair(fact.generatePublic(pubSpec), fact.generatePrivate(privSpec));
            }
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem creating RSA private key: ").append(e.toString()).toString(), e);
        }
    }

    private String asymProvider;
    final PEMReader this$0;

    public PEMReader$RSAKeyPairParser(String symProvider, String asymProvider)
    {
        this$0 = PEMReader.this;
        super(PEMReader.this, symProvider);
        this.asymProvider = asymProvider;
    }
}
