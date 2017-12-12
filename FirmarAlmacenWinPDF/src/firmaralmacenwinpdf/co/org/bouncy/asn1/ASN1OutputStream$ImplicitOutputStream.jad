// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1OutputStream.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OutputStream

private class ASN1OutputStream$ImplicitOutputStream extends ASN1OutputStream
{

    public void write(int b)
        throws IOException
    {
        if(first)
            first = false;
        else
            super.write(b);
    }

    private boolean first;
    final ASN1OutputStream this$0;

    public ASN1OutputStream$ImplicitOutputStream(OutputStream os)
    {
        this$0 = ASN1OutputStream.this;
        super(os);
        first = true;
    }
}
