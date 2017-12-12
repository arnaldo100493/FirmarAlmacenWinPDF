// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaXmlLocator.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.pdf.security.XmlLocator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm, PdfStream, PdfStamper, PdfWriter, 
//            PdfIndirectObject, PdfReader, PdfName, PRAcroForm

public class XfaXmlLocator
    implements XmlLocator
{

    public XfaXmlLocator(PdfStamper stamper)
        throws DocumentException, IOException
    {
        this.stamper = stamper;
        try
        {
            createXfaForm();
        }
        catch(ParserConfigurationException e)
        {
            throw new DocumentException(e);
        }
        catch(SAXException e)
        {
            throw new DocumentException(e);
        }
    }

    protected void createXfaForm()
        throws ParserConfigurationException, SAXException, IOException
    {
        xfaForm = new XfaForm(stamper.getReader());
    }

    public Document getDocument()
    {
        return xfaForm.getDomDocument();
    }

    public void setDocument(Document document)
        throws IOException, DocumentException
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(document), new StreamResult(outputStream));
            PdfIndirectReference iref = stamper.getWriter().addToBody(new PdfStream(outputStream.toByteArray())).getIndirectReference();
            stamper.getReader().getAcroForm().put(PdfName.XFA, iref);
        }
        catch(TransformerConfigurationException e)
        {
            throw new DocumentException(e);
        }
        catch(TransformerException e)
        {
            throw new DocumentException(e);
        }
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    private PdfStamper stamper;
    private XfaForm xfaForm;
    private String encoding;
}
