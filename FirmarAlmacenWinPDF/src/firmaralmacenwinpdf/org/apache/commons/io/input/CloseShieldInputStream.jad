// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CloseShieldInputStream.java

package org.apache.commons.io.input;

import java.io.InputStream;

// Referenced classes of package org.apache.commons.io.input:
//            ProxyInputStream, ClosedInputStream

public class CloseShieldInputStream extends ProxyInputStream
{

    public CloseShieldInputStream(InputStream in)
    {
        super(in);
    }

    public void close()
    {
        in = new ClosedInputStream();
    }
}
