// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHKEKGenerator.java

package co.org.bouncy.crypto.agreement.kdf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.util.Pack;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.agreement.kdf:
//            DHKDFParameters

public class DHKEKGenerator
    implements DerivationFunction
{

    public DHKEKGenerator(Digest digest)
    {
        this.digest = digest;
    }

    public void init(DerivationParameters param)
    {
        DHKDFParameters params = (DHKDFParameters)param;
        algorithm = params.getAlgorithm();
        keySize = params.getKeySize();
        z = params.getZ();
        partyAInfo = params.getExtraInfo();
    }

    public Digest getDigest()
    {
        return digest;
    }

    public int generateBytes(byte out[], int outOff, int len)
        throws DataLengthException, IllegalArgumentException
    {
        if(out.length - len < outOff)
            throw new DataLengthException("output buffer too small");
        long oBytes = len;
        int outLen = digest.getDigestSize();
        if(oBytes > 0x1ffffffffL)
            throw new IllegalArgumentException("Output length too large");
        int cThreshold = (int)(((oBytes + (long)outLen) - 1L) / (long)outLen);
        byte dig[] = new byte[digest.getDigestSize()];
        int counter = 1;
        for(int i = 0; i < cThreshold; i++)
        {
            digest.update(z, 0, z.length);
            ASN1EncodableVector v1 = new ASN1EncodableVector();
            ASN1EncodableVector v2 = new ASN1EncodableVector();
            v2.add(algorithm);
            v2.add(new DEROctetString(Pack.intToBigEndian(counter)));
            v1.add(new DERSequence(v2));
            if(partyAInfo != null)
                v1.add(new DERTaggedObject(true, 0, new DEROctetString(partyAInfo)));
            v1.add(new DERTaggedObject(true, 2, new DEROctetString(Pack.intToBigEndian(keySize))));
            try
            {
                byte other[] = (new DERSequence(v1)).getEncoded("DER");
                digest.update(other, 0, other.length);
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unable to encode parameter info: ").append(e.getMessage()).toString());
            }
            digest.doFinal(dig, 0);
            if(len > outLen)
            {
                System.arraycopy(dig, 0, out, outOff, outLen);
                outOff += outLen;
                len -= outLen;
            } else
            {
                System.arraycopy(dig, 0, out, outOff, len);
            }
            counter++;
        }

        digest.reset();
        return (int)oBytes;
    }

    private final Digest digest;
    private DERObjectIdentifier algorithm;
    private int keySize;
    private byte z[];
    private byte partyAInfo[];
}
