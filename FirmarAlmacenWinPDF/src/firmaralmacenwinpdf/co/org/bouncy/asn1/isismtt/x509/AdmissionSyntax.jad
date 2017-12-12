// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdmissionSyntax.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.isismtt.x509:
//            Admissions

public class AdmissionSyntax extends ASN1Object
{

    public static AdmissionSyntax getInstance(Object obj)
    {
        if(obj == null || (obj instanceof AdmissionSyntax))
            return (AdmissionSyntax)obj;
        if(obj instanceof ASN1Sequence)
            return new AdmissionSyntax((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private AdmissionSyntax(ASN1Sequence seq)
    {
        switch(seq.size())
        {
        case 1: // '\001'
            contentsOfAdmissions = DERSequence.getInstance(seq.getObjectAt(0));
            break;

        case 2: // '\002'
            admissionAuthority = GeneralName.getInstance(seq.getObjectAt(0));
            contentsOfAdmissions = DERSequence.getInstance(seq.getObjectAt(1));
            break;

        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        }
    }

    public AdmissionSyntax(GeneralName admissionAuthority, ASN1Sequence contentsOfAdmissions)
    {
        this.admissionAuthority = admissionAuthority;
        this.contentsOfAdmissions = contentsOfAdmissions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        if(admissionAuthority != null)
            vec.add(admissionAuthority);
        vec.add(contentsOfAdmissions);
        return new DERSequence(vec);
    }

    public GeneralName getAdmissionAuthority()
    {
        return admissionAuthority;
    }

    public Admissions[] getContentsOfAdmissions()
    {
        Admissions admissions[] = new Admissions[contentsOfAdmissions.size()];
        int count = 0;
        for(Enumeration e = contentsOfAdmissions.getObjects(); e.hasMoreElements();)
            admissions[count++] = Admissions.getInstance(e.nextElement());

        return admissions;
    }

    private GeneralName admissionAuthority;
    private ASN1Sequence contentsOfAdmissions;
}
