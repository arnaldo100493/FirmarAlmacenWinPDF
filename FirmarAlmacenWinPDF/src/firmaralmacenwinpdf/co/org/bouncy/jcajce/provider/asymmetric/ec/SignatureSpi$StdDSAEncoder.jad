// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.asn1.*;
import co.org.bouncy.jcajce.provider.asymmetric.util.DSAEncoder;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            SignatureSpi

private static class SignatureSpi$StdDSAEncoder
    implements DSAEncoder
{

    public byte[] encode(BigInteger r, BigInteger s)
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        return (new DERSequence(v)).getEncoded("DER");
    }

    public BigInteger[] decode(byte encoding[])
        throws IOException
    {
        ASN1Sequence s = (ASN1Sequence)ASN1Primitive.fromByteArray(encoding);
        BigInteger sig[] = new BigInteger[2];
        sig[0] = ASN1Integer.getInstance(s.getObjectAt(0)).getValue();
        sig[1] = ASN1Integer.getInstance(s.getObjectAt(1)).getValue();
        return sig;
    }

    private SignatureSpi$StdDSAEncoder()
    {
    }

    SignatureSpi$StdDSAEncoder(SignatureSpi._cls1 x0)
    {
        this();
    }
}
