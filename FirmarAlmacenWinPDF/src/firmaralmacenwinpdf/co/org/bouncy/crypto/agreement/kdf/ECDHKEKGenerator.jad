// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECDHKEKGenerator.java

package co.org.bouncy.crypto.agreement.kdf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.generators.KDF2BytesGenerator;
import co.org.bouncy.crypto.params.KDFParameters;
import co.org.bouncy.crypto.util.Pack;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.agreement.kdf:
//            DHKDFParameters

public class ECDHKEKGenerator
    implements DerivationFunction
{

    public ECDHKEKGenerator(Digest digest)
    {
        kdf = new KDF2BytesGenerator(digest);
    }

    public void init(DerivationParameters param)
    {
        DHKDFParameters params = (DHKDFParameters)param;
        algorithm = params.getAlgorithm();
        keySize = params.getKeySize();
        z = params.getZ();
    }

    public Digest getDigest()
    {
        return kdf.getDigest();
    }

    public int generateBytes(byte out[], int outOff, int len)
        throws DataLengthException, IllegalArgumentException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new AlgorithmIdentifier(algorithm, DERNull.INSTANCE));
        v.add(new DERTaggedObject(true, 2, new DEROctetString(Pack.intToBigEndian(keySize))));
        try
        {
            kdf.init(new KDFParameters(z, (new DERSequence(v)).getEncoded("DER")));
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unable to initialise kdf: ").append(e.getMessage()).toString());
        }
        return kdf.generateBytes(out, outOff, len);
    }

    private DerivationFunction kdf;
    private ASN1ObjectIdentifier algorithm;
    private int keySize;
    private byte z[];
}
