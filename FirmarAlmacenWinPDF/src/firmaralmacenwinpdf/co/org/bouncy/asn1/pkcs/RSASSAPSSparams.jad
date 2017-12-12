// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSASSAPSSparams.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            PKCSObjectIdentifiers

public class RSASSAPSSparams extends ASN1Object
{

    public static RSASSAPSSparams getInstance(Object obj)
    {
        if(obj instanceof RSASSAPSSparams)
            return (RSASSAPSSparams)obj;
        if(obj != null)
            return new RSASSAPSSparams(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public RSASSAPSSparams()
    {
        hashAlgorithm = DEFAULT_HASH_ALGORITHM;
        maskGenAlgorithm = DEFAULT_MASK_GEN_FUNCTION;
        saltLength = DEFAULT_SALT_LENGTH;
        trailerField = DEFAULT_TRAILER_FIELD;
    }

    public RSASSAPSSparams(AlgorithmIdentifier hashAlgorithm, AlgorithmIdentifier maskGenAlgorithm, ASN1Integer saltLength, ASN1Integer trailerField)
    {
        this.hashAlgorithm = hashAlgorithm;
        this.maskGenAlgorithm = maskGenAlgorithm;
        this.saltLength = saltLength;
        this.trailerField = trailerField;
    }

    private RSASSAPSSparams(ASN1Sequence seq)
    {
        hashAlgorithm = DEFAULT_HASH_ALGORITHM;
        maskGenAlgorithm = DEFAULT_MASK_GEN_FUNCTION;
        saltLength = DEFAULT_SALT_LENGTH;
        trailerField = DEFAULT_TRAILER_FIELD;
        for(int i = 0; i != seq.size(); i++)
        {
            ASN1TaggedObject o = (ASN1TaggedObject)seq.getObjectAt(i);
            switch(o.getTagNo())
            {
            case 0: // '\0'
                hashAlgorithm = AlgorithmIdentifier.getInstance(o, true);
                break;

            case 1: // '\001'
                maskGenAlgorithm = AlgorithmIdentifier.getInstance(o, true);
                break;

            case 2: // '\002'
                saltLength = ASN1Integer.getInstance(o, true);
                break;

            case 3: // '\003'
                trailerField = ASN1Integer.getInstance(o, true);
                break;

            default:
                throw new IllegalArgumentException("unknown tag");
            }
        }

    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public AlgorithmIdentifier getMaskGenAlgorithm()
    {
        return maskGenAlgorithm;
    }

    public BigInteger getSaltLength()
    {
        return saltLength.getValue();
    }

    public BigInteger getTrailerField()
    {
        return trailerField.getValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(!hashAlgorithm.equals(DEFAULT_HASH_ALGORITHM))
            v.add(new DERTaggedObject(true, 0, hashAlgorithm));
        if(!maskGenAlgorithm.equals(DEFAULT_MASK_GEN_FUNCTION))
            v.add(new DERTaggedObject(true, 1, maskGenAlgorithm));
        if(!saltLength.equals(DEFAULT_SALT_LENGTH))
            v.add(new DERTaggedObject(true, 2, saltLength));
        if(!trailerField.equals(DEFAULT_TRAILER_FIELD))
            v.add(new DERTaggedObject(true, 3, trailerField));
        return new DERSequence(v);
    }

    private AlgorithmIdentifier hashAlgorithm;
    private AlgorithmIdentifier maskGenAlgorithm;
    private ASN1Integer saltLength;
    private ASN1Integer trailerField;
    public static final AlgorithmIdentifier DEFAULT_HASH_ALGORITHM;
    public static final AlgorithmIdentifier DEFAULT_MASK_GEN_FUNCTION;
    public static final ASN1Integer DEFAULT_SALT_LENGTH = new ASN1Integer(20L);
    public static final ASN1Integer DEFAULT_TRAILER_FIELD = new ASN1Integer(1L);

    static 
    {
        DEFAULT_HASH_ALGORITHM = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        DEFAULT_MASK_GEN_FUNCTION = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, DEFAULT_HASH_ALGORITHM);
    }
}
