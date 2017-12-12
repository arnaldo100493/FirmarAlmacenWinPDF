// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMKeyPair, PEMKeyPairParser, PEMParser

private class PEMParser$RSAKeyPairParser
    implements PEMKeyPairParser
{

    public PEMKeyPair parse(byte encoding[])
        throws IOException
    {
        try
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(encoding);
            if(seq.size() != 9)
            {
                throw new PEMException("malformed sequence in RSA private key");
            } else
            {
                RSAPrivateKey keyStruct = RSAPrivateKey.getInstance(seq);
                RSAPublicKey pubSpec = new RSAPublicKey(keyStruct.getModulus(), keyStruct.getPublicExponent());
                AlgorithmIdentifier algId = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE);
                return new PEMKeyPair(new SubjectPublicKeyInfo(algId, pubSpec), new PrivateKeyInfo(algId, keyStruct));
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

    final PEMParser this$0;

    private PEMParser$RSAKeyPairParser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$RSAKeyPairParser(PEMParser._cls1 x1)
    {
        this();
    }
}
