// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEUtil.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.mail.smime.util.FileBackedMimeBodyPart;
import java.io.*;
import javax.mail.MessagingException;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEUtil

private static class SMIMEUtil$WriteOnceFileBackedMimeBodyPart extends FileBackedMimeBodyPart
{

    public void writeTo(OutputStream out)
        throws MessagingException, IOException
    {
        super.writeTo(out);
        dispose();
    }

    public SMIMEUtil$WriteOnceFileBackedMimeBodyPart(InputStream content, File file)
        throws MessagingException, IOException
    {
        super(content, file);
    }
}
