// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileDeleteStrategy.java

package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

// Referenced classes of package org.apache.commons.io:
//            FileDeleteStrategy, FileUtils

static class FileDeleteStrategy$ForceFileDeleteStrategy extends FileDeleteStrategy
{

    protected boolean doDelete(File fileToDelete)
        throws IOException
    {
        FileUtils.forceDelete(fileToDelete);
        return true;
    }

    FileDeleteStrategy$ForceFileDeleteStrategy()
    {
        super("Force");
    }
}
