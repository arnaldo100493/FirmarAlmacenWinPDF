// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMKeyPair, PEMKeyPairParser, PEMParser

private class PEMParser$DSAKeyPairParser
    implements PEMKeyPairParser
{

    public PEMKeyPair parse(byte encoding[])
        throws IOException
    {
        try
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(encoding);
            if(seq.size() != 6)
            {
                throw new PEMException("malformed sequence in DSA private key");
            } else
            {
                ASN1Integer p = ASN1Integer.getInstance(seq.getObjectAt(1));
                ASN1Integer q = ASN1Integer.getInstance(seq.getObjectAt(2));
                ASN1Integer g = ASN1Integer.getInstance(seq.getObjectAt(3));
                ASN1Integer y = ASN1Integer.getInstance(seq.getObjectAt(4));
                ASN1Integer x = ASN1Integer.getInstance(seq.getObjectAt(5));
                return new PEMKeyPair(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(p.getValue(), q.getValue(), g.getValue())), y), new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(p.getValue(), q.getValue(), g.getValue())), x));
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

    final PEMParser this$0;

    private PEMParser$DSAKeyPairParser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$DSAKeyPairParser(PEMParser._cls1 x1)
    {
        this();
    }
}
