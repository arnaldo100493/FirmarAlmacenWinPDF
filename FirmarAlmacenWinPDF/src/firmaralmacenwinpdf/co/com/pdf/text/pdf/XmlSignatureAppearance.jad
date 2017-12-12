// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlSignatureAppearance.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.pdf.security.XmlLocator;
import co.com.pdf.text.pdf.security.XpathConstructor;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Calendar;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStamper, PdfStamperImp

public class XmlSignatureAppearance
{

    XmlSignatureAppearance(PdfStamperImp writer)
    {
        mimeType = "text/xml";
        this.writer = writer;
    }

    public PdfStamperImp getWriter()
    {
        return writer;
    }

    public PdfStamper getStamper()
    {
        return stamper;
    }

    public void setStamper(PdfStamper stamper)
    {
        this.stamper = stamper;
    }

    public void setCertificate(Certificate signCertificate)
    {
        this.signCertificate = signCertificate;
    }

    public Certificate getCertificate()
    {
        return signCertificate;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    public Calendar getSignDate()
    {
        if(signDate == null)
            signDate = Calendar.getInstance();
        return signDate;
    }

    public void setSignDate(Calendar signDate)
    {
        this.signDate = signDate;
    }

    public XmlLocator getXmlLocator()
    {
        return xmlLocator;
    }

    public void setXmlLocator(XmlLocator xmlLocator)
    {
        this.xmlLocator = xmlLocator;
    }

    public XpathConstructor getXpathConstructor()
    {
        return xpathConstructor;
    }

    public void setXpathConstructor(XpathConstructor xpathConstructor)
    {
        this.xpathConstructor = xpathConstructor;
    }

    public void close()
        throws IOException, DocumentException
    {
        writer.close(stamper.getMoreInfo());
    }

    private PdfStamperImp writer;
    private PdfStamper stamper;
    private Certificate signCertificate;
    private XmlLocator xmlLocator;
    private XpathConstructor xpathConstructor;
    private Calendar signDate;
    private String description;
    private String mimeType;
}
