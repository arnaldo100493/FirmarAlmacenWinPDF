// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS7ContentHandler.java

package co.org.bouncy.mail.smime.handlers;

import co.org.bouncy.mail.smime.SMIMEStreamingProcessor;
import java.awt.datatransfer.DataFlavor;
import java.io.*;
import javax.activation.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class PKCS7ContentHandler
    implements DataContentHandler
{

    PKCS7ContentHandler(ActivationDataFlavor adf, DataFlavor dfs[])
    {
        _adf = adf;
        _dfs = dfs;
    }

    public Object getContent(DataSource ds)
        throws IOException
    {
        return ds.getInputStream();
    }

    public Object getTransferData(DataFlavor df, DataSource ds)
        throws IOException
    {
        if(_adf.equals(df))
            return getContent(ds);
        else
            return null;
    }

    public DataFlavor[] getTransferDataFlavors()
    {
        return _dfs;
    }

    public void writeTo(Object obj, String mimeType, OutputStream os)
        throws IOException
    {
        if(obj instanceof MimeBodyPart)
            try
            {
                ((MimeBodyPart)obj).writeTo(os);
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

    private final ActivationDataFlavor _adf;
    private final DataFlavor _dfs[];
}
