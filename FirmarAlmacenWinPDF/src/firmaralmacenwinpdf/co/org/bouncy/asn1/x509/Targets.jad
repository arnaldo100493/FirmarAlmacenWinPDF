// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Targets.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Target

public class Targets extends ASN1Object
{

    public static Targets getInstance(Object obj)
    {
        if(obj instanceof Targets)
            return (Targets)obj;
        if(obj != null)
            return new Targets(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private Targets(ASN1Sequence targets)
    {
        this.targets = targets;
    }

    public Targets(Target targets[])
    {
        this.targets = new DERSequence(targets);
    }

    public Target[] getTargets()
    {
        Target targs[] = new Target[targets.size()];
        int count = 0;
        for(Enumeration e = targets.getObjects(); e.hasMoreElements();)
            targs[count++] = Target.getInstance(e.nextElement());

        return targs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return targets;
    }

    private ASN1Sequence targets;
}
