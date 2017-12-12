// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.pkcs.RSAPublicKey;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.spec.RSAPublicKeySpec;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$RSAPublicKeyParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            ASN1InputStream ais = new ASN1InputStream(obj.getContent());
            Object asnObject = ais.readObject();
            ASN1Sequence sequence = (ASN1Sequence)asnObject;
            RSAPublicKey rsaPubStructure = RSAPublicKey.getInstance(sequence);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(rsaPubStructure.getModulus(), rsaPubStructure.getPublicExponent());
            KeyFactory keyFact = KeyFactory.getInstance("RSA", provider);
            return keyFact.generatePublic(keySpec);
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(NoSuchProviderException e)
        {
            throw new IOException((new StringBuilder()).append("can't find provider ").append(provider).toString());
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem extracting key: ").append(e.toString()).toString(), e);
        }
    }

    private String provider;
    final PEMReader this$0;

    public PEMReader$RSAPublicKeyParser(String provider)
    {
        this$0 = PEMReader.this;
        super();
        this.provider = provider;
    }
}
