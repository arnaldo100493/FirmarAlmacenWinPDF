// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileExistsException.java

package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

public class FileExistsException extends IOException
{

    public FileExistsException()
    {
    }

    public FileExistsException(String message)
    {
        super(message);
    }

    public FileExistsException(File file)
    {
        super((new StringBuilder()).append("File ").append(file).append(" exists").toString());
    }

    private static final long serialVersionUID = 1L;
}
