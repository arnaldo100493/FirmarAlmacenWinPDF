// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBES2Parameters.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            KeyDerivationFunc, PKCSObjectIdentifiers, PBKDF2Params, EncryptionScheme

public class PBES2Parameters extends ASN1Object
    implements PKCSObjectIdentifiers
{

    public static PBES2Parameters getInstance(Object obj)
    {
        if(obj instanceof PBES2Parameters)
            return (PBES2Parameters)obj;
        if(obj != null)
            return new PBES2Parameters(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public PBES2Parameters(KeyDerivationFunc keyDevFunc, EncryptionScheme encScheme)
    {
        func = keyDevFunc;
        scheme = encScheme;
    }

    private PBES2Parameters(ASN1Sequence obj)
    {
        Enumeration e = obj.getObjects();
        ASN1Sequence funcSeq = ASN1Sequence.getInstance(((ASN1Encodable)e.nextElement()).toASN1Primitive());
        if(funcSeq.getObjectAt(0).equals(id_PBKDF2))
            func = new KeyDerivationFunc(id_PBKDF2, PBKDF2Params.getInstance(funcSeq.getObjectAt(1)));
        else
            func = KeyDerivationFunc.getInstance(funcSeq);
        scheme = EncryptionScheme.getInstance(e.nextElement());
    }

    public KeyDerivationFunc getKeyDerivationFunc()
    {
        return func;
    }

    public EncryptionScheme getEncryptionScheme()
    {
        return scheme;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(func);
        v.add(scheme);
        return new DERSequence(v);
    }

    private KeyDerivationFunc func;
    private EncryptionScheme scheme;
}
