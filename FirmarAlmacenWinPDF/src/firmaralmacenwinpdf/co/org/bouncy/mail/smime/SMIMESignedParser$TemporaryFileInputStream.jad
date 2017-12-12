// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMESignedParser.java

package co.org.bouncy.mail.smime;

import java.io.*;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMESignedParser

private static class SMIMESignedParser$TemporaryFileInputStream extends BufferedInputStream
{

    public void close()
        throws IOException
    {
        super.close();
        _file.delete();
    }

    private final File _file;

    SMIMESignedParser$TemporaryFileInputStream(File file)
        throws FileNotFoundException
    {
        super(new FileInputStream(file));
        _file = file;
    }
}
