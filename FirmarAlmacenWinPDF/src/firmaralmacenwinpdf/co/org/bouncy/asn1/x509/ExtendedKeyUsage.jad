// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtendedKeyUsage.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            KeyPurposeId, Extension, Extensions

public class ExtendedKeyUsage extends ASN1Object
{

    public static ExtendedKeyUsage getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ExtendedKeyUsage getInstance(Object obj)
    {
        if(obj instanceof ExtendedKeyUsage)
            return (ExtendedKeyUsage)obj;
        if(obj != null)
            return new ExtendedKeyUsage(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static ExtendedKeyUsage fromExtensions(Extensions extensions)
    {
        return getInstance(extensions.getExtensionParsedValue(Extension.extendedKeyUsage));
    }

    public ExtendedKeyUsage(KeyPurposeId usage)
    {
        usageTable = new Hashtable();
        seq = new DERSequence(usage);
        usageTable.put(usage, usage);
    }

    private ExtendedKeyUsage(ASN1Sequence seq)
    {
        usageTable = new Hashtable();
        this.seq = seq;
        ASN1Encodable o;
        for(Enumeration e = seq.getObjects(); e.hasMoreElements(); usageTable.put(o, o))
        {
            o = (ASN1Encodable)e.nextElement();
            if(!(o.toASN1Primitive() instanceof ASN1ObjectIdentifier))
                throw new IllegalArgumentException("Only ASN1ObjectIdentifiers allowed in ExtendedKeyUsage.");
        }

    }

    public ExtendedKeyUsage(KeyPurposeId usages[])
    {
        usageTable = new Hashtable();
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != usages.length; i++)
        {
            v.add(usages[i]);
            usageTable.put(usages[i], usages[i]);
        }

        seq = new DERSequence(v);
    }

    /**
     * @deprecated Method ExtendedKeyUsage is deprecated
     */

    public ExtendedKeyUsage(Vector usages)
    {
        usageTable = new Hashtable();
        ASN1EncodableVector v = new ASN1EncodableVector();
        ASN1Primitive o;
        for(Enumeration e = usages.elements(); e.hasMoreElements(); usageTable.put(o, o))
        {
            o = (ASN1Primitive)e.nextElement();
            v.add(o);
        }

        seq = new DERSequence(v);
    }

    public boolean hasKeyPurposeId(KeyPurposeId keyPurposeId)
    {
        return usageTable.get(keyPurposeId) != null;
    }

    public KeyPurposeId[] getUsages()
    {
        KeyPurposeId temp[] = new KeyPurposeId[seq.size()];
        int i = 0;
        for(Enumeration it = seq.getObjects(); it.hasMoreElements();)
            temp[i++] = KeyPurposeId.getInstance(it.nextElement());

        return temp;
    }

    public int size()
    {
        return usageTable.size();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    Hashtable usageTable;
    ASN1Sequence seq;
}
