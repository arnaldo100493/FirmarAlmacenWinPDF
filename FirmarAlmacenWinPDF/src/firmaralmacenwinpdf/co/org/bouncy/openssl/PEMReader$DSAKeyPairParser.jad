// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERInteger;
import co.org.bouncy.util.io.pem.PemObject;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$DSAKeyPairParser extends PEMReader.KeyPairParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            ASN1Sequence seq = readKeyPair(obj);
            if(seq.size() != 6)
            {
                throw new PEMException("malformed sequence in DSA private key");
            } else
            {
                DERInteger p = (DERInteger)seq.getObjectAt(1);
                DERInteger q = (DERInteger)seq.getObjectAt(2);
                DERInteger g = (DERInteger)seq.getObjectAt(3);
                DERInteger y = (DERInteger)seq.getObjectAt(4);
                DERInteger x = (DERInteger)seq.getObjectAt(5);
                DSAPrivateKeySpec privSpec = new DSAPrivateKeySpec(x.getValue(), p.getValue(), q.getValue(), g.getValue());
                DSAPublicKeySpec pubSpec = new DSAPublicKeySpec(y.getValue(), p.getValue(), q.getValue(), g.getValue());
                KeyFactory fact = KeyFactory.getInstance("DSA", asymProvider);
                return new KeyPair(fact.generatePublic(pubSpec), fact.generatePrivate(privSpec));
            }
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem creating DSA private key: ").append(e.toString()).toString(), e);
        }
    }

    private String asymProvider;
    final PEMReader this$0;

    public PEMReader$DSAKeyPairParser(String symProvider, String asymProvider)
    {
        this$0 = PEMReader.this;
        super(PEMReader.this, symProvider);
        this.asymProvider = asymProvider;
    }
}
