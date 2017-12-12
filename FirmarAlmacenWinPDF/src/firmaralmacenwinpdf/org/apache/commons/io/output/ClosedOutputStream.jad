// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClosedOutputStream.java

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class ClosedOutputStream extends OutputStream
{

    public ClosedOutputStream()
    {
    }

    public void write(int b)
        throws IOException
    {
        throw new IOException((new StringBuilder()).append("write(").append(b).append(") failed: stream is closed").toString());
    }

    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM = new ClosedOutputStream();

}
