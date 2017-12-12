// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Dump.java

package co.org.bouncy.asn1.util;

import co.org.bouncy.asn1.ASN1InputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

// Referenced classes of package co.org.bouncy.asn1.util:
//            ASN1Dump

public class Dump
{

    public Dump()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        FileInputStream fIn = new FileInputStream(args[0]);
        ASN1InputStream bIn = new ASN1InputStream(fIn);
        for(Object obj = null; (obj = bIn.readObject()) != null;)
            System.out.println(ASN1Dump.dumpAsString(obj));

    }
}
