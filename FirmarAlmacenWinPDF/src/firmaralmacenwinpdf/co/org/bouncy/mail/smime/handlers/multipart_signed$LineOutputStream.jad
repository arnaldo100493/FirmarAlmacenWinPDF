// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   multipart_signed.java

package co.org.bouncy.mail.smime.handlers;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import javax.mail.MessagingException;

// Referenced classes of package co.org.bouncy.mail.smime.handlers:
//            multipart_signed

private static class multipart_signed$LineOutputStream extends FilterOutputStream
{

    public void writeln(String s)
        throws MessagingException
    {
        try
        {
            byte abyte0[] = getBytes(s);
            super.out.write(abyte0);
            super.out.write(newline);
        }
        catch(Exception exception)
        {
            throw new MessagingException("IOException", exception);
        }
    }

    public void writeln()
        throws MessagingException
    {
        try
        {
            super.out.write(newline);
        }
        catch(Exception exception)
        {
            throw new MessagingException("IOException", exception);
        }
    }

    private static byte[] getBytes(String s)
    {
        char ac[] = s.toCharArray();
        int i = ac.length;
        byte abyte0[] = new byte[i];
        for(int j = 0; j < i;)
            abyte0[j] = (byte)ac[j++];

        return abyte0;
    }

    private static byte newline[];

    static 
    {
        newline = new byte[2];
        newline[0] = 13;
        newline[1] = 10;
    }

    public multipart_signed$LineOutputStream(OutputStream outputstream)
    {
        super(outputstream);
    }
}
