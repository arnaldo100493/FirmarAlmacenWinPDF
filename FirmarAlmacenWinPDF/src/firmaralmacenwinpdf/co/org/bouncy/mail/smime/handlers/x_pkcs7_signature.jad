// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   x_pkcs7_signature.java

package co.org.bouncy.mail.smime.handlers;

import java.awt.datatransfer.DataFlavor;
import java.io.*;
import javax.activation.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class x_pkcs7_signature
    implements DataContentHandler
{

    public x_pkcs7_signature()
    {
    }

    public Object getContent(DataSource _ds)
        throws IOException
    {
        return _ds.getInputStream();
    }

    public Object getTransferData(DataFlavor _df, DataSource _ds)
        throws IOException
    {
        if(ADF.equals(_df))
            return getContent(_ds);
        else
            return null;
    }

    public DataFlavor[] getTransferDataFlavors()
    {
        return ADFs;
    }

    public void writeTo(Object _obj, String _mimeType, OutputStream _os)
        throws IOException
    {
        if(_obj instanceof MimeBodyPart)
            try
            {
                ((MimeBodyPart)_obj).writeTo(_os);
            }
            catch(MessagingException ex)
            {
                throw new IOException(ex.getMessage());
            }
        else
        if(_obj instanceof byte[])
            _os.write((byte[])(byte[])_obj);
        else
        if(_obj instanceof InputStream)
        {
            InputStream in = (InputStream)_obj;
            int b;
            while((b = in.read()) >= 0) 
                _os.write(b);
        } else
        {
            throw new IOException((new StringBuilder()).append("unknown object in writeTo ").append(_obj).toString());
        }
    }

    private static final ActivationDataFlavor ADF;
    private static final DataFlavor ADFs[];

    static 
    {
        ADF = new ActivationDataFlavor(javax/mail/internet/MimeBodyPart, "application/x-pkcs7-signature", "Signature");
        ADFs = (new DataFlavor[] {
            ADF
        });
    }
}
