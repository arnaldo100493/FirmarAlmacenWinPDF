// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCStrictStyle.java

package co.org.bouncy.asn1.x500.style;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x500.X500NameStyle;

// Referenced classes of package co.org.bouncy.asn1.x500.style:
//            BCStyle

public class BCStrictStyle extends BCStyle
{

    public BCStrictStyle()
    {
    }

    public boolean areEqual(X500Name name1, X500Name name2)
    {
        co.org.bouncy.asn1.x500.RDN rdns1[] = name1.getRDNs();
        co.org.bouncy.asn1.x500.RDN rdns2[] = name2.getRDNs();
        if(rdns1.length != rdns2.length)
            return false;
        for(int i = 0; i != rdns1.length; i++)
            if(!rdnAreEqual(rdns1[i], rdns2[i]))
                return false;

        return true;
    }

    public static final X500NameStyle INSTANCE = new BCStrictStyle();

}
