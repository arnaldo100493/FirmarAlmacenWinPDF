// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBES2Algorithms.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            KeyDerivationFunc, PKCSObjectIdentifiers, PBKDF2Params, EncryptionScheme

/**
 * @deprecated Class PBES2Algorithms is deprecated
 */

public class PBES2Algorithms extends AlgorithmIdentifier
    implements PKCSObjectIdentifiers
{

    public PBES2Algorithms(ASN1Sequence obj)
    {
        super(obj);
        Enumeration e = obj.getObjects();
        objectId = (ASN1ObjectIdentifier)e.nextElement();
        ASN1Sequence seq = (ASN1Sequence)e.nextElement();
        e = seq.getObjects();
        ASN1Sequence funcSeq = (ASN1Sequence)e.nextElement();
        if(funcSeq.getObjectAt(0).equals(id_PBKDF2))
            func = new KeyDerivationFunc(id_PBKDF2, PBKDF2Params.getInstance(funcSeq.getObjectAt(1)));
        else
            func = KeyDerivationFunc.getInstance(funcSeq);
        scheme = EncryptionScheme.getInstance(e.nextElement());
    }

    public ASN1ObjectIdentifier getObjectId()
    {
        return objectId;
    }

    public KeyDerivationFunc getKeyDerivationFunc()
    {
        return func;
    }

    public EncryptionScheme getEncryptionScheme()
    {
        return scheme;
    }

    public ASN1Primitive getASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        ASN1EncodableVector subV = new ASN1EncodableVector();
        v.add(objectId);
        subV.add(func);
        subV.add(scheme);
        v.add(new DERSequence(subV));
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier objectId;
    private KeyDerivationFunc func;
    private EncryptionScheme scheme;
}
