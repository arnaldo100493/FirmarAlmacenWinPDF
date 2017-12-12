// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirectoryWalker.java

package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

// Referenced classes of package org.apache.commons.io:
//            DirectoryWalker

public static class DirectoryWalker$CancelException extends IOException
{

    public File getFile()
    {
        return file;
    }

    public int getDepth()
    {
        return depth;
    }

    private static final long serialVersionUID = 0x12b2b63ef9f577f0L;
    private final File file;
    private final int depth;

    public DirectoryWalker$CancelException(File file, int depth)
    {
        this("Operation Cancelled", file, depth);
    }

    public DirectoryWalker$CancelException(String message, File file, int depth)
    {
        super(message);
        this.file = file;
        this.depth = depth;
    }
}
