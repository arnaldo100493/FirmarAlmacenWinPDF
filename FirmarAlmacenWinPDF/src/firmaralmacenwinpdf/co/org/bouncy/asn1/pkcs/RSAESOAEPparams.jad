// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAESOAEPparams.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            PKCSObjectIdentifiers

public class RSAESOAEPparams extends ASN1Object
{

    public static RSAESOAEPparams getInstance(Object obj)
    {
        if(obj instanceof RSAESOAEPparams)
            return (RSAESOAEPparams)obj;
        if(obj != null)
            return new RSAESOAEPparams(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public RSAESOAEPparams()
    {
        hashAlgorithm = DEFAULT_HASH_ALGORITHM;
        maskGenAlgorithm = DEFAULT_MASK_GEN_FUNCTION;
        pSourceAlgorithm = DEFAULT_P_SOURCE_ALGORITHM;
    }

    public RSAESOAEPparams(AlgorithmIdentifier hashAlgorithm, AlgorithmIdentifier maskGenAlgorithm, AlgorithmIdentifier pSourceAlgorithm)
    {
        this.hashAlgorithm = hashAlgorithm;
        this.maskGenAlgorithm = maskGenAlgorithm;
        this.pSourceAlgorithm = pSourceAlgorithm;
    }

    public RSAESOAEPparams(ASN1Sequence seq)
    {
        hashAlgorithm = DEFAULT_HASH_ALGORITHM;
        maskGenAlgorithm = DEFAULT_MASK_GEN_FUNCTION;
        pSourceAlgorithm = DEFAULT_P_SOURCE_ALGORITHM;
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
                pSourceAlgorithm = AlgorithmIdentifier.getInstance(o, true);
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

    public AlgorithmIdentifier getPSourceAlgorithm()
    {
        return pSourceAlgorithm;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(!hashAlgorithm.equals(DEFAULT_HASH_ALGORITHM))
            v.add(new DERTaggedObject(true, 0, hashAlgorithm));
        if(!maskGenAlgorithm.equals(DEFAULT_MASK_GEN_FUNCTION))
            v.add(new DERTaggedObject(true, 1, maskGenAlgorithm));
        if(!pSourceAlgorithm.equals(DEFAULT_P_SOURCE_ALGORITHM))
            v.add(new DERTaggedObject(true, 2, pSourceAlgorithm));
        return new DERSequence(v);
    }

    private AlgorithmIdentifier hashAlgorithm;
    private AlgorithmIdentifier maskGenAlgorithm;
    private AlgorithmIdentifier pSourceAlgorithm;
    public static final AlgorithmIdentifier DEFAULT_HASH_ALGORITHM;
    public static final AlgorithmIdentifier DEFAULT_MASK_GEN_FUNCTION;
    public static final AlgorithmIdentifier DEFAULT_P_SOURCE_ALGORITHM;

    static 
    {
        DEFAULT_HASH_ALGORITHM = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        DEFAULT_MASK_GEN_FUNCTION = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, DEFAULT_HASH_ALGORITHM);
        DEFAULT_P_SOURCE_ALGORITHM = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_pSpecified, new DEROctetString(new byte[0]));
    }
}
