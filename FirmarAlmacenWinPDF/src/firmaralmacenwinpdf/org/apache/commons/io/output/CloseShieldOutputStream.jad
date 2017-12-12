// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CloseShieldOutputStream.java

package org.apache.commons.io.output;

import java.io.OutputStream;

// Referenced classes of package org.apache.commons.io.output:
//            ProxyOutputStream, ClosedOutputStream

public class CloseShieldOutputStream extends ProxyOutputStream
{

    public CloseShieldOutputStream(OutputStream out)
    {
        super(out);
    }

    public void close()
    {
        out = new ClosedOutputStream();
    }
}
