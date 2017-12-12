// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Admissions.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.isismtt.x509:
//            ProfessionInfo, NamingAuthority

public class Admissions extends ASN1Object
{

    public static Admissions getInstance(Object obj)
    {
        if(obj == null || (obj instanceof Admissions))
            return (Admissions)obj;
        if(obj instanceof ASN1Sequence)
            return new Admissions((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private Admissions(ASN1Sequence seq)
    {
        if(seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        ASN1Encodable o = (ASN1Encodable)e.nextElement();
        if(o instanceof ASN1TaggedObject)
        {
            switch(((ASN1TaggedObject)o).getTagNo())
            {
            case 0: // '\0'
                admissionAuthority = GeneralName.getInstance((ASN1TaggedObject)o, true);
                break;

            case 1: // '\001'
                namingAuthority = NamingAuthority.getInstance((ASN1TaggedObject)o, true);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(((ASN1TaggedObject)o).getTagNo()).toString());
            }
            o = (ASN1Encodable)e.nextElement();
        }
        if(o instanceof ASN1TaggedObject)
        {
            switch(((ASN1TaggedObject)o).getTagNo())
            {
            case 1: // '\001'
                namingAuthority = NamingAuthority.getInstance((ASN1TaggedObject)o, true);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(((ASN1TaggedObject)o).getTagNo()).toString());
            }
            o = (ASN1Encodable)e.nextElement();
        }
        professionInfos = ASN1Sequence.getInstance(o);
        if(e.hasMoreElements())
            throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(e.nextElement().getClass()).toString());
        else
            return;
    }

    public Admissions(GeneralName admissionAuthority, NamingAuthority namingAuthority, ProfessionInfo professionInfos[])
    {
        this.admissionAuthority = admissionAuthority;
        this.namingAuthority = namingAuthority;
        this.professionInfos = new DERSequence(professionInfos);
    }

    public GeneralName getAdmissionAuthority()
    {
        return admissionAuthority;
    }

    public NamingAuthority getNamingAuthority()
    {
        return namingAuthority;
    }

    public ProfessionInfo[] getProfessionInfos()
    {
        ProfessionInfo infos[] = new ProfessionInfo[professionInfos.size()];
        int count = 0;
        for(Enumeration e = professionInfos.getObjects(); e.hasMoreElements();)
            infos[count++] = ProfessionInfo.getInstance(e.nextElement());

        return infos;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        if(admissionAuthority != null)
            vec.add(new DERTaggedObject(true, 0, admissionAuthority));
        if(namingAuthority != null)
            vec.add(new DERTaggedObject(true, 1, namingAuthority));
        vec.add(professionInfos);
        return new DERSequence(vec);
    }

    private GeneralName admissionAuthority;
    private NamingAuthority namingAuthority;
    private ASN1Sequence professionInfos;
}
