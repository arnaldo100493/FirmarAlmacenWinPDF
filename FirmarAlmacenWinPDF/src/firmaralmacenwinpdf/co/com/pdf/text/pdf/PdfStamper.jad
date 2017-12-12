// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStamper.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.collection.PdfCollection;
import co.com.pdf.text.pdf.interfaces.PdfEncryptionSettings;
import co.com.pdf.text.pdf.interfaces.PdfViewerPreferences;
import co.com.pdf.text.pdf.security.LtvVerification;
import co.com.pdf.text.xml.xmp.XmpWriter;
import java.io.*;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStamperImp, ByteBuffer, PdfSignatureAppearance, PdfDictionary, 
//            XmlSignatureAppearance, PdfException, PdfFormField, PdfAcroForm, 
//            PdfEncodings, PdfFileSpecification, PdfName, PdfReader, 
//            PdfContentByte, PdfImportedPage, PdfWriter, AcroFields, 
//            PdfAnnotation, FdfReader, PdfObject, PdfAction, 
//            PdfTransition

public class PdfStamper
    implements PdfViewerPreferences, PdfEncryptionSettings
{

    public PdfStamper(PdfReader reader, OutputStream os)
        throws DocumentException, IOException
    {
        stamper = new PdfStamperImp(reader, os, '\0', false);
    }

    public PdfStamper(PdfReader reader, OutputStream os, char pdfVersion)
        throws DocumentException, IOException
    {
        stamper = new PdfStamperImp(reader, os, pdfVersion, false);
    }

    public PdfStamper(PdfReader reader, OutputStream os, char pdfVersion, boolean append)
        throws DocumentException, IOException
    {
        stamper = new PdfStamperImp(reader, os, pdfVersion, append);
    }

    public Map getMoreInfo()
    {
        return moreInfo;
    }

    public void setMoreInfo(Map moreInfo)
    {
        this.moreInfo = moreInfo;
    }

    public void replacePage(PdfReader r, int pageImported, int pageReplaced)
    {
        stamper.replacePage(r, pageImported, pageReplaced);
    }

    public void insertPage(int pageNumber, Rectangle mediabox)
    {
        stamper.insertPage(pageNumber, mediabox);
    }

    public PdfSignatureAppearance getSignatureAppearance()
    {
        return sigApp;
    }

    public XmlSignatureAppearance getXmlSignatureAppearance()
    {
        return sigXmlApp;
    }

    public void close()
        throws DocumentException, IOException
    {
        if(stamper.closed)
            return;
        if(!hasSignature)
        {
            mergeVerification();
            stamper.close(moreInfo);
        } else
        {
            throw new DocumentException("Signature defined. Must be closed in PdfSignatureAppearance.");
        }
    }

    public PdfContentByte getUnderContent(int pageNum)
    {
        return stamper.getUnderContent(pageNum);
    }

    public PdfContentByte getOverContent(int pageNum)
    {
        return stamper.getOverContent(pageNum);
    }

    public boolean isRotateContents()
    {
        return stamper.isRotateContents();
    }

    public void setRotateContents(boolean rotateContents)
    {
        stamper.setRotateContents(rotateContents);
    }

    public void setEncryption(byte userPassword[], byte ownerPassword[], int permissions, boolean strength128Bits)
        throws DocumentException
    {
        if(stamper.isAppend())
            throw new DocumentException(MessageLocalization.getComposedMessage("append.mode.does.not.support.changing.the.encryption.status", new Object[0]));
        if(stamper.isContentWritten())
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("content.was.already.written.to.the.output", new Object[0]));
        } else
        {
            stamper.setEncryption(userPassword, ownerPassword, permissions, strength128Bits ? 1 : 0);
            return;
        }
    }

    public void setEncryption(byte userPassword[], byte ownerPassword[], int permissions, int encryptionType)
        throws DocumentException
    {
        if(stamper.isAppend())
            throw new DocumentException(MessageLocalization.getComposedMessage("append.mode.does.not.support.changing.the.encryption.status", new Object[0]));
        if(stamper.isContentWritten())
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("content.was.already.written.to.the.output", new Object[0]));
        } else
        {
            stamper.setEncryption(userPassword, ownerPassword, permissions, encryptionType);
            return;
        }
    }

    public void setEncryption(boolean strength, String userPassword, String ownerPassword, int permissions)
        throws DocumentException
    {
        setEncryption(DocWriter.getISOBytes(userPassword), DocWriter.getISOBytes(ownerPassword), permissions, strength);
    }

    public void setEncryption(int encryptionType, String userPassword, String ownerPassword, int permissions)
        throws DocumentException
    {
        setEncryption(DocWriter.getISOBytes(userPassword), DocWriter.getISOBytes(ownerPassword), permissions, encryptionType);
    }

    public void setEncryption(Certificate certs[], int permissions[], int encryptionType)
        throws DocumentException
    {
        if(stamper.isAppend())
            throw new DocumentException(MessageLocalization.getComposedMessage("append.mode.does.not.support.changing.the.encryption.status", new Object[0]));
        if(stamper.isContentWritten())
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("content.was.already.written.to.the.output", new Object[0]));
        } else
        {
            stamper.setEncryption(certs, permissions, encryptionType);
            return;
        }
    }

    public PdfImportedPage getImportedPage(PdfReader reader, int pageNumber)
    {
        return stamper.getImportedPage(reader, pageNumber);
    }

    public PdfWriter getWriter()
    {
        return stamper;
    }

    public PdfReader getReader()
    {
        return stamper.reader;
    }

    public AcroFields getAcroFields()
    {
        return stamper.getAcroFields();
    }

    public void setFormFlattening(boolean flat)
    {
        stamper.setFormFlattening(flat);
    }

    public void setFreeTextFlattening(boolean flat)
    {
        stamper.setFreeTextFlattening(flat);
    }

    public void addAnnotation(PdfAnnotation annot, int page)
    {
        stamper.addAnnotation(annot, page);
    }

    public PdfFormField addSignature(String name, int page, float llx, float lly, float urx, float ury)
    {
        PdfAcroForm acroForm = stamper.getAcroForm();
        PdfFormField signature = PdfFormField.createSignature(stamper);
        acroForm.setSignatureParams(signature, name, llx, lly, urx, ury);
        acroForm.drawSignatureAppearences(signature, llx, lly, urx, ury);
        addAnnotation(signature, page);
        return signature;
    }

    public void addComments(FdfReader fdf)
        throws IOException
    {
        stamper.addComments(fdf);
    }

    public void setOutlines(List outlines)
    {
        stamper.setOutlines(outlines);
    }

    public void setThumbnail(Image image, int page)
        throws PdfException, DocumentException
    {
        stamper.setThumbnail(image, page);
    }

    public boolean partialFormFlattening(String name)
    {
        return stamper.partialFormFlattening(name);
    }

    public void addJavaScript(String js)
    {
        stamper.addJavaScript(js, !PdfEncodings.isPdfDocEncoding(js));
    }

    public void addFileAttachment(String description, byte fileStore[], String file, String fileDisplay)
        throws IOException
    {
        addFileAttachment(description, PdfFileSpecification.fileEmbedded(stamper, file, fileDisplay, fileStore));
    }

    public void addFileAttachment(String description, PdfFileSpecification fs)
        throws IOException
    {
        stamper.addFileAttachment(description, fs);
    }

    public void makePackage(PdfName initialView)
    {
        PdfCollection collection = new PdfCollection(0);
        collection.put(PdfName.VIEW, initialView);
        stamper.makePackage(collection);
    }

    public void makePackage(PdfCollection collection)
    {
        stamper.makePackage(collection);
    }

    public void setViewerPreferences(int preferences)
    {
        stamper.setViewerPreferences(preferences);
    }

    public void addViewerPreference(PdfName key, PdfObject value)
    {
        stamper.addViewerPreference(key, value);
    }

    public void setXmpMetadata(byte xmp[])
    {
        stamper.setXmpMetadata(xmp);
    }

    public void createXmpMetadata()
    {
        stamper.createXmpMetadata();
    }

    public XmpWriter getXmpWriter()
    {
        return stamper.getXmpWriter();
    }

    public boolean isFullCompression()
    {
        return stamper.isFullCompression();
    }

    public void setFullCompression()
    {
        if(stamper.isAppend())
        {
            return;
        } else
        {
            stamper.setFullCompression();
            return;
        }
    }

    public void setPageAction(PdfName actionType, PdfAction action, int page)
        throws PdfException
    {
        stamper.setPageAction(actionType, action, page);
    }

    public void setDuration(int seconds, int page)
    {
        stamper.setDuration(seconds, page);
    }

    public void setTransition(PdfTransition transition, int page)
    {
        stamper.setTransition(transition, page);
    }

    public static PdfStamper createSignature(PdfReader reader, OutputStream os, char pdfVersion, File tempFile, boolean append)
        throws DocumentException, IOException
    {
        PdfStamper stp;
        if(tempFile == null)
        {
            ByteBuffer bout = new ByteBuffer();
            stp = new PdfStamper(reader, bout, pdfVersion, append);
            stp.sigApp = new PdfSignatureAppearance(stp.stamper);
            stp.sigApp.setSigout(bout);
        } else
        {
            if(tempFile.isDirectory())
                tempFile = File.createTempFile("pdf", null, tempFile);
            FileOutputStream fout = new FileOutputStream(tempFile);
            stp = new PdfStamper(reader, fout, pdfVersion, append);
            stp.sigApp = new PdfSignatureAppearance(stp.stamper);
            stp.sigApp.setTempFile(tempFile);
        }
        stp.sigApp.setOriginalout(os);
        stp.sigApp.setStamper(stp);
        stp.hasSignature = true;
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary acroForm = (PdfDictionary)PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), catalog);
        if(acroForm != null)
        {
            acroForm.remove(PdfName.NEEDAPPEARANCES);
            stp.stamper.markUsed(acroForm);
        }
        return stp;
    }

    public static PdfStamper createSignature(PdfReader reader, OutputStream os, char pdfVersion)
        throws DocumentException, IOException
    {
        return createSignature(reader, os, pdfVersion, null, false);
    }

    public static PdfStamper createSignature(PdfReader reader, OutputStream os, char pdfVersion, File tempFile)
        throws DocumentException, IOException
    {
        return createSignature(reader, os, pdfVersion, tempFile, false);
    }

    public static PdfStamper createXmlSignature(PdfReader reader, OutputStream os)
        throws IOException, DocumentException
    {
        PdfStamper stp = new PdfStamper(reader, os);
        stp.sigXmlApp = new XmlSignatureAppearance(stp.stamper);
        stp.sigXmlApp.setStamper(stp);
        return stp;
    }

    public Map getPdfLayers()
    {
        return stamper.getPdfLayers();
    }

    public void markUsed(PdfObject obj)
    {
        stamper.markUsed(obj);
    }

    public LtvVerification getLtvVerification()
    {
        if(verification == null)
            verification = new LtvVerification(this);
        return verification;
    }

    void mergeVerification()
        throws IOException
    {
        if(verification == null)
        {
            return;
        } else
        {
            verification.merge();
            return;
        }
    }

    protected PdfStamper()
    {
    }

    protected PdfStamperImp stamper;
    private Map moreInfo;
    protected boolean hasSignature;
    protected PdfSignatureAppearance sigApp;
    protected XmlSignatureAppearance sigXmlApp;
    private LtvVerification verification;
}
