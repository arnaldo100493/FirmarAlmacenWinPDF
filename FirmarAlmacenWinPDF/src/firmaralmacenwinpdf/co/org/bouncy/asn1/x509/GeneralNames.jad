// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralNames.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralName, Extensions

public class GeneralNames extends ASN1Object
{

    public static GeneralNames getInstance(Object obj)
    {
        if(obj instanceof GeneralNames)
            return (GeneralNames)obj;
        if(obj != null)
            return new GeneralNames(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static GeneralNames getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static GeneralNames fromExtensions(Extensions extensions, ASN1ObjectIdentifier extOID)
    {
        return getInstance(extensions.getExtensionParsedValue(extOID));
    }

    public GeneralNames(GeneralName name)
    {
        names = (new GeneralName[] {
            name
        });
    }

    public GeneralNames(GeneralName names[])
    {
        this.names = names;
    }

    private GeneralNames(ASN1Sequence seq)
    {
        names = new GeneralName[seq.size()];
        for(int i = 0; i != seq.size(); i++)
            names[i] = GeneralName.getInstance(seq.getObjectAt(i));

    }

    public GeneralName[] getNames()
    {
        GeneralName tmp[] = new GeneralName[names.length];
        System.arraycopy(names, 0, tmp, 0, names.length);
        return tmp;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERSequence(names);
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String sep = System.getProperty("line.separator");
        buf.append("GeneralNames:");
        buf.append(sep);
        for(int i = 0; i != names.length; i++)
        {
            buf.append("    ");
            buf.append(names[i]);
            buf.append(sep);
        }

        return buf.toString();
    }

    private final GeneralName names[];
}
