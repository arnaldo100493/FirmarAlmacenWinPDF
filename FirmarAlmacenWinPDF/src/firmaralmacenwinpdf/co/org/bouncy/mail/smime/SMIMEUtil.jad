// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEUtil.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.cms.IssuerAndSerialNumber;
import co.org.bouncy.cms.CMSTypedStream;
import co.org.bouncy.jce.PrincipalUtil;
import co.org.bouncy.mail.smime.util.CRLFOutputStream;
import co.org.bouncy.mail.smime.util.FileBackedMimeBodyPart;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.*;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEException

public class SMIMEUtil
{
    static class Base64CRLFOutputStream extends FilterOutputStream
    {

        public void write(int i)
            throws IOException
        {
            if(i == 13)
                out.write(newline);
            else
            if(i == 10)
            {
                if(lastb != 13)
                {
                    if(!isCrlfStream || lastb != 10)
                        out.write(newline);
                } else
                {
                    isCrlfStream = true;
                }
            } else
            {
                out.write(i);
            }
            lastb = i;
        }

        public void write(byte buf[])
            throws IOException
        {
            write(buf, 0, buf.length);
        }

        public void write(byte buf[], int off, int len)
            throws IOException
        {
            for(int i = off; i != off + len; i++)
                write(buf[i]);

        }

        public void writeln()
            throws IOException
        {
            super.out.write(newline);
        }

        protected int lastb;
        protected static byte newline[];
        private boolean isCrlfStream;

        static 
        {
            newline = new byte[2];
            newline[0] = 13;
            newline[1] = 10;
        }

        public Base64CRLFOutputStream(OutputStream outputstream)
        {
            super(outputstream);
            lastb = -1;
        }
    }

    private static class WriteOnceFileBackedMimeBodyPart extends FileBackedMimeBodyPart
    {

        public void writeTo(OutputStream out)
            throws MessagingException, IOException
        {
            super.writeTo(out);
            dispose();
        }

        public WriteOnceFileBackedMimeBodyPart(InputStream content, File file)
            throws MessagingException, IOException
        {
            super(content, file);
        }
    }

    static class LineOutputStream extends FilterOutputStream
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


    public SMIMEUtil()
    {
    }

    static boolean isCanonicalisationRequired(MimeBodyPart bodyPart, String defaultContentTransferEncoding)
        throws MessagingException
    {
        String cte[] = bodyPart.getHeader("Content-Transfer-Encoding");
        String contentTransferEncoding;
        if(cte == null)
            contentTransferEncoding = defaultContentTransferEncoding;
        else
            contentTransferEncoding = cte[0];
        return !contentTransferEncoding.equalsIgnoreCase("binary");
    }

    public static Provider getProvider(String providerName)
        throws NoSuchProviderException
    {
        if(providerName != null)
        {
            Provider prov = Security.getProvider(providerName);
            if(prov != null)
                return prov;
            else
                throw new NoSuchProviderException((new StringBuilder()).append("provider ").append(providerName).append(" not found.").toString());
        } else
        {
            return null;
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

    static void outputPostamble(LineOutputStream lOut, MimeBodyPart part, int count, String boundary)
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
        int boundaries;
        for(boundaries = count + 1; (line = readLine(in)) != null && (!line.startsWith(boundary) || --boundaries != 0););
        while((line = readLine(in)) != null) 
            lOut.writeln(line);
        in.close();
        if(boundaries != 0)
            throw new MessagingException((new StringBuilder()).append("all boundaries not found for: ").append(boundary).toString());
        else
            return;
    }

    static void outputPostamble(LineOutputStream lOut, BodyPart parent, String parentBoundary, BodyPart part)
        throws MessagingException, IOException
    {
        InputStream in;
        try
        {
            in = ((MimeBodyPart)parent).getRawInputStream();
        }
        catch(MessagingException e)
        {
            return;
        }
        MimeMultipart multipart = (MimeMultipart)part.getContent();
        ContentType contentType = new ContentType(multipart.getContentType());
        String boundary = (new StringBuilder()).append("--").append(contentType.getParameter("boundary")).toString();
        int count = multipart.getCount() + 1;
        do
        {
            String line;
            if(count == 0 || (line = readLine(in)) == null)
                break;
            if(line.startsWith(boundary))
                count--;
        } while(true);
        String line;
        for(; (line = readLine(in)) != null && !line.startsWith(parentBoundary); lOut.writeln(line));
        in.close();
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
        if(ch < 0 && b.length() == 0)
            return null;
        else
            return b.toString();
    }

    static void outputBodyPart(OutputStream out, BodyPart bodyPart, String defaultContentTransferEncoding)
        throws MessagingException, IOException
    {
        if(bodyPart instanceof MimeBodyPart)
        {
            MimeBodyPart mimePart = (MimeBodyPart)bodyPart;
            String cte[] = mimePart.getHeader("Content-Transfer-Encoding");
            if(mimePart.getContent() instanceof MimeMultipart)
            {
                MimeMultipart mp = (MimeMultipart)bodyPart.getContent();
                ContentType contentType = new ContentType(mp.getContentType());
                String boundary = (new StringBuilder()).append("--").append(contentType.getParameter("boundary")).toString();
                LineOutputStream lOut = new LineOutputStream(out);
                String header;
                for(Enumeration headers = mimePart.getAllHeaderLines(); headers.hasMoreElements(); lOut.writeln(header))
                    header = (String)headers.nextElement();

                lOut.writeln();
                outputPreamble(lOut, mimePart, boundary);
                for(int i = 0; i < mp.getCount(); i++)
                {
                    lOut.writeln(boundary);
                    BodyPart part = mp.getBodyPart(i);
                    outputBodyPart(out, part, defaultContentTransferEncoding);
                    if(!(part.getContent() instanceof MimeMultipart))
                        lOut.writeln();
                    else
                        outputPostamble(lOut, mimePart, boundary, part);
                }

                lOut.writeln((new StringBuilder()).append(boundary).append("--").toString());
                outputPostamble(lOut, mimePart, mp.getCount(), boundary);
                return;
            }
            String contentTransferEncoding;
            if(cte == null)
                contentTransferEncoding = defaultContentTransferEncoding;
            else
                contentTransferEncoding = cte[0];
            if(!contentTransferEncoding.equalsIgnoreCase("base64") && !contentTransferEncoding.equalsIgnoreCase("quoted-printable"))
            {
                if(!contentTransferEncoding.equalsIgnoreCase("binary"))
                    out = new CRLFOutputStream(out);
                bodyPart.writeTo(out);
                out.flush();
                return;
            }
            boolean base64 = contentTransferEncoding.equalsIgnoreCase("base64");
            InputStream inRaw;
            try
            {
                inRaw = mimePart.getRawInputStream();
            }
            catch(MessagingException e)
            {
                out = new CRLFOutputStream(out);
                bodyPart.writeTo(out);
                out.flush();
                return;
            }
            LineOutputStream outLine = new LineOutputStream(out);
            String header;
            for(Enumeration e = mimePart.getAllHeaderLines(); e.hasMoreElements(); outLine.writeln(header))
                header = (String)e.nextElement();

            outLine.writeln();
            outLine.flush();
            OutputStream outCRLF;
            if(base64)
                outCRLF = new Base64CRLFOutputStream(out);
            else
                outCRLF = new CRLFOutputStream(out);
            byte buf[] = new byte[32760];
            int len;
            while((len = inRaw.read(buf, 0, buf.length)) > 0) 
                outCRLF.write(buf, 0, len);
            outCRLF.flush();
        } else
        {
            if(!defaultContentTransferEncoding.equalsIgnoreCase("binary"))
                out = new CRLFOutputStream(out);
            bodyPart.writeTo(out);
            out.flush();
        }
    }

    public static MimeBodyPart toMimeBodyPart(byte content[])
        throws SMIMEException
    {
        return toMimeBodyPart(((InputStream) (new ByteArrayInputStream(content))));
    }

    public static MimeBodyPart toMimeBodyPart(InputStream content)
        throws SMIMEException
    {
        try
        {
            return new MimeBodyPart(content);
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception creating body part.", e);
        }
    }

    static FileBackedMimeBodyPart toWriteOnceBodyPart(CMSTypedStream content)
        throws SMIMEException
    {
        try
        {
            return new WriteOnceFileBackedMimeBodyPart(content.getContentStream(), File.createTempFile("bcMail", ".mime"));
        }
        catch(IOException e)
        {
            throw new SMIMEException((new StringBuilder()).append("IOException creating tmp file:").append(e.getMessage()).toString(), e);
        }
        catch(MessagingException e)
        {
            throw new SMIMEException((new StringBuilder()).append("can't create part: ").append(e).toString(), e);
        }
    }

    public static FileBackedMimeBodyPart toMimeBodyPart(CMSTypedStream content)
        throws SMIMEException
    {
        try
        {
            return toMimeBodyPart(content, File.createTempFile("bcMail", ".mime"));
        }
        catch(IOException e)
        {
            throw new SMIMEException((new StringBuilder()).append("IOException creating tmp file:").append(e.getMessage()).toString(), e);
        }
    }

    public static FileBackedMimeBodyPart toMimeBodyPart(CMSTypedStream content, File file)
        throws SMIMEException
    {
        try
        {
            return new FileBackedMimeBodyPart(content.getContentStream(), file);
        }
        catch(IOException e)
        {
            throw new SMIMEException((new StringBuilder()).append("can't save content to file: ").append(e).toString(), e);
        }
        catch(MessagingException e)
        {
            throw new SMIMEException((new StringBuilder()).append("can't create part: ").append(e).toString(), e);
        }
    }

    public static IssuerAndSerialNumber createIssuerAndSerialNumberFor(X509Certificate cert)
        throws CertificateParsingException
    {
        try
        {
            return new IssuerAndSerialNumber(PrincipalUtil.getIssuerX509Principal(cert), cert.getSerialNumber());
        }
        catch(Exception e)
        {
            throw new CertificateParsingException((new StringBuilder()).append("exception extracting issuer and serial number: ").append(e).toString());
        }
    }

    private static final int BUF_SIZE = 32760;
}
