// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   multipart_signed.java

package co.org.bouncy.mail.smime.handlers;

import co.org.bouncy.mail.smime.SMIMEStreamingProcessor;
import java.awt.datatransfer.DataFlavor;
import java.io.*;
import java.util.Enumeration;
import javax.activation.*;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;

public class multipart_signed
    implements DataContentHandler
{
    private static class LineOutputStream extends FilterOutputStream
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

        public LineOutputStream(OutputStream outputstream)
        {
            super(outputstream);
        }
    }


    public multipart_signed()
    {
    }

    public Object getContent(DataSource ds)
        throws IOException
    {
        try
        {
            return new MimeMultipart(ds);
        }
        catch(MessagingException ex)
        {
            return null;
        }
    }

    public Object getTransferData(DataFlavor df, DataSource ds)
        throws IOException
    {
        if(ADF.equals(df))
            return getContent(ds);
        else
            return null;
    }

    public DataFlavor[] getTransferDataFlavors()
    {
        return DFS;
    }

    public void writeTo(Object obj, String _mimeType, OutputStream os)
        throws IOException
    {
        if(obj instanceof MimeMultipart)
            try
            {
                outputBodyPart(os, obj);
            }
            catch(MessagingException ex)
            {
                throw new IOException(ex.getMessage());
            }
        else
        if(obj instanceof byte[])
            os.write((byte[])(byte[])obj);
        else
        if(obj instanceof InputStream)
        {
            InputStream in = (InputStream)obj;
            if(!(in instanceof BufferedInputStream))
                in = new BufferedInputStream(in);
            int b;
            while((b = in.read()) >= 0) 
                os.write(b);
        } else
        if(obj instanceof SMIMEStreamingProcessor)
        {
            SMIMEStreamingProcessor processor = (SMIMEStreamingProcessor)obj;
            processor.write(os);
        } else
        {
            throw new IOException((new StringBuilder()).append("unknown object in writeTo ").append(obj).toString());
        }
    }

    private void outputBodyPart(OutputStream out, Object bodyPart)
        throws MessagingException, IOException
    {
        if(bodyPart instanceof Multipart)
        {
            Multipart mp = (Multipart)bodyPart;
            ContentType contentType = new ContentType(mp.getContentType());
            String boundary = (new StringBuilder()).append("--").append(contentType.getParameter("boundary")).toString();
            LineOutputStream lOut = new LineOutputStream(out);
            for(int i = 0; i < mp.getCount(); i++)
            {
                lOut.writeln(boundary);
                outputBodyPart(out, mp.getBodyPart(i));
                lOut.writeln();
            }

            lOut.writeln((new StringBuilder()).append(boundary).append("--").toString());
            return;
        }
        MimeBodyPart mimePart = (MimeBodyPart)bodyPart;
        if(mimePart.getContent() instanceof Multipart)
        {
            Multipart mp = (Multipart)mimePart.getContent();
            ContentType contentType = new ContentType(mp.getContentType());
            String boundary = (new StringBuilder()).append("--").append(contentType.getParameter("boundary")).toString();
            LineOutputStream lOut = new LineOutputStream(out);
            for(Enumeration headers = mimePart.getAllHeaderLines(); headers.hasMoreElements(); lOut.writeln((String)headers.nextElement()));
            lOut.writeln();
            outputPreamble(lOut, mimePart, boundary);
            outputBodyPart(out, mp);
            return;
        } else
        {
            mimePart.writeTo(out);
            return;
        }
    }

    static void outputPreamble(LineOutputStream lOut, MimeBodyPart part, String boundary)
        throws MessagingException, IOException
    {
        InputStream in;
        try
        {
            in = part.getRawInputStream();
        }
        catch(MessagingException e)
        {
            return;
        }
        String line;
        for(; (line = readLine(in)) != null && !line.equals(boundary); lOut.writeln(line));
        in.close();
        if(line == null)
            throw new MessagingException("no boundary found");
        else
            return;
    }

    private static String readLine(InputStream in)
        throws IOException
    {
        StringBuffer b = new StringBuffer();
        int ch;
        do
        {
            if((ch = in.read()) < 0 || ch == 10)
                break;
            if(ch != 13)
                b.append((char)ch);
        } while(true);
        if(ch < 0)
            return null;
        else
            return b.toString();
    }

    private static final ActivationDataFlavor ADF;
    private static final DataFlavor DFS[];

    static 
    {
        ADF = new ActivationDataFlavor(javax/mail/internet/MimeMultipart, "multipart/signed", "Multipart Signed");
        DFS = (new DataFlavor[] {
            ADF
        });
    }
}
