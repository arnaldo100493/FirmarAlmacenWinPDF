// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BidirectionalMap.java

package co.org.bouncy.asn1.eac;

import java.util.Hashtable;

public class BidirectionalMap extends Hashtable
{

    public BidirectionalMap()
    {
        reverseMap = new Hashtable();
    }

    public Object getReverse(Object o)
    {
        return reverseMap.get(o);
    }

    public Object put(Object key, Object o)
    {
        reverseMap.put(o, key);
        return super.put(key, o);
    }

    private static final long serialVersionUID = 0x98826247ea12e613L;
    Hashtable reverseMap;
}
