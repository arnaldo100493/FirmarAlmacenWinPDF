// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.sec.ECPrivateKey;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMKeyPair, PEMException, PEMKeyPairParser, PEMParser

private class PEMParser$ECDSAKeyPairParser
    implements PEMKeyPairParser
{

    public PEMKeyPair parse(byte encoding[])
        throws IOException
    {
        try
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(encoding);
            ECPrivateKey pKey = ECPrivateKey.getInstance(seq);
            AlgorithmIdentifier algId = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, pKey.getParameters());
            PrivateKeyInfo privInfo = new PrivateKeyInfo(algId, pKey);
            SubjectPublicKeyInfo pubInfo = new SubjectPublicKeyInfo(algId, pKey.getPublicKey().getBytes());
            return new PEMKeyPair(pubInfo, privInfo);
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem creating EC private key: ").append(e.toString()).toString(), e);
        }
    }

    final PEMParser this$0;

    private PEMParser$ECDSAKeyPairParser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$ECDSAKeyPairParser(PEMParser._cls1 x1)
    {
        this();
    }
}
