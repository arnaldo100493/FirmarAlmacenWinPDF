// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TargetInformation.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Targets, Target

public class TargetInformation extends ASN1Object
{

    public static TargetInformation getInstance(Object obj)
    {
        if(obj instanceof TargetInformation)
            return (TargetInformation)obj;
        if(obj != null)
            return new TargetInformation(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private TargetInformation(ASN1Sequence seq)
    {
        targets = seq;
    }

    public Targets[] getTargetsObjects()
    {
        Targets copy[] = new Targets[targets.size()];
        int count = 0;
        for(Enumeration e = targets.getObjects(); e.hasMoreElements();)
            copy[count++] = Targets.getInstance(e.nextElement());

        return copy;
    }

    public TargetInformation(Targets targets)
    {
        this.targets = new DERSequence(targets);
    }

    public TargetInformation(Target targets[])
    {
        this(new Targets(targets));
    }

    public ASN1Primitive toASN1Primitive()
    {
        return targets;
    }

    private ASN1Sequence targets;
}
