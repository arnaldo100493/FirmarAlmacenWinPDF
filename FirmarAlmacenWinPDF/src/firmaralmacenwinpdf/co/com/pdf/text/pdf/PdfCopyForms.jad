// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopyForms.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.pdf.interfaces.PdfEncryptionSettings;
import co.com.pdf.text.pdf.interfaces.PdfViewerPreferences;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.Certificate;
import java.util.List;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfCopyFormsImp, PdfReader, SequenceList, PdfEncodings, 
//            PdfWriter, PdfName, PdfObject

public class PdfCopyForms
    implements PdfViewerPreferences, PdfEncryptionSettings
{

    public PdfCopyForms(OutputStream os)
        throws DocumentException
    {
        fc = new PdfCopyFormsImp(os);
    }

    public void addDocument(PdfReader reader)
        throws DocumentException, IOException
    {
        fc.addDocument(reader);
    }

    public void addDocument(PdfReader reader, List pagesToKeep)
        throws DocumentException, IOException
    {
        fc.addDocument(reader, pagesToKeep);
    }

    public void addDocument(PdfReader reader, String ranges)
        throws DocumentException, IOException
    {
        fc.addDocument(reader, SequenceList.expand(ranges, reader.getNumberOfPages()));
    }

    public void copyDocumentFields(PdfReader reader)
        throws DocumentException
    {
        fc.copyDocumentFields(reader);
    }

    public void setEncryption(byte userPassword[], byte ownerPassword[], int permissions, boolean strength128Bits)
        throws DocumentException
    {
        fc.setEncryption(userPassword, ownerPassword, permissions, strength128Bits ? 1 : 0);
    }

    public void setEncryption(boolean strength, String userPassword, String ownerPassword, int permissions)
        throws DocumentException
    {
        setEncryption(DocWriter.getISOBytes(userPassword), DocWriter.getISOBytes(ownerPassword), permissions, strength);
    }

    public void close()
    {
        fc.close();
    }

    public void open()
    {
        fc.openDoc();
    }

    public void addJavaScript(String js)
    {
        fc.addJavaScript(js, !PdfEncodings.isPdfDocEncoding(js));
    }

    public void setOutlines(List outlines)
    {
        fc.setOutlines(outlines);
    }

    public PdfWriter getWriter()
    {
        return fc;
    }

    public boolean isFullCompression()
    {
        return fc.isFullCompression();
    }

    public void setFullCompression()
    {
        fc.setFullCompression();
    }

    public void setEncryption(byte userPassword[], byte ownerPassword[], int permissions, int encryptionType)
        throws DocumentException
    {
        fc.setEncryption(userPassword, ownerPassword, permissions, encryptionType);
    }

    public void addViewerPreference(PdfName key, PdfObject value)
    {
        fc.addViewerPreference(key, value);
    }

    public void setViewerPreferences(int preferences)
    {
        fc.setViewerPreferences(preferences);
    }

    public void setEncryption(Certificate certs[], int permissions[], int encryptionType)
        throws DocumentException
    {
        fc.setEncryption(certs, permissions, encryptionType);
    }

    private PdfCopyFormsImp fc;
}
