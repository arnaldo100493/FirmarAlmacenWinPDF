// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OIDTokenizer.java

package co.org.bouncy.asn1;


public class OIDTokenizer
{

    public OIDTokenizer(String oid)
    {
        this.oid = oid;
        index = 0;
    }

    public boolean hasMoreTokens()
    {
        return index != -1;
    }

    public String nextToken()
    {
        if(index == -1)
            return null;
        int end = oid.indexOf('.', index);
        if(end == -1)
        {
            String token = oid.substring(index);
            index = -1;
            return token;
        } else
        {
            String token = oid.substring(index, end);
            index = end + 1;
            return token;
        }
    }

    private String oid;
    private int index;
}
