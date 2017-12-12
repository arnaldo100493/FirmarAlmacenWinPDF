// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameConstraints.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralSubtree

public class NameConstraints extends ASN1Object
{

    public static NameConstraints getInstance(Object obj)
    {
        if(obj instanceof NameConstraints)
            return (NameConstraints)obj;
        if(obj != null)
            return new NameConstraints(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private NameConstraints(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(e.nextElement());
            switch(o.getTagNo())
            {
            case 0: // '\0'
                permitted = createArray(ASN1Sequence.getInstance(o, false));
                break;

            case 1: // '\001'
                excluded = createArray(ASN1Sequence.getInstance(o, false));
                break;
            }
        } while(true);
    }

    public NameConstraints(GeneralSubtree permitted[], GeneralSubtree excluded[])
    {
        if(permitted != null)
            this.permitted = permitted;
        if(excluded != null)
            this.excluded = excluded;
    }

    private GeneralSubtree[] createArray(ASN1Sequence subtree)
    {
        GeneralSubtree ar[] = new GeneralSubtree[subtree.size()];
        for(int i = 0; i != ar.length; i++)
            ar[i] = GeneralSubtree.getInstance(subtree.getObjectAt(i));

        return ar;
    }

    public GeneralSubtree[] getPermittedSubtrees()
    {
        return permitted;
    }

    public GeneralSubtree[] getExcludedSubtrees()
    {
        return excluded;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(permitted != null)
            v.add(new DERTaggedObject(false, 0, new DERSequence(permitted)));
        if(excluded != null)
            v.add(new DERTaggedObject(false, 1, new DERSequence(excluded)));
        return new DERSequence(v);
    }

    private GeneralSubtree permitted[];
    private GeneralSubtree excluded[];
}
