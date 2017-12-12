// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfVersionImp.java

package co.com.pdf.text.pdf.internal;

import co.com.pdf.text.DocWriter;
import co.com.pdf.text.pdf.*;
import co.com.pdf.text.pdf.interfaces.PdfVersion;
import java.io.IOException;

public class PdfVersionImp
    implements PdfVersion
{

    public PdfVersionImp()
    {
        headerWasWritten = false;
        appendmode = false;
        header_version = '4';
        catalog_version = null;
        version = '4';
        extensions = null;
    }

    public void setPdfVersion(char version)
    {
        this.version = version;
        if(headerWasWritten || appendmode)
            setPdfVersion(getVersionAsName(version));
        else
            header_version = version;
    }

    public void setAtLeastPdfVersion(char version)
    {
        if(version > header_version)
            setPdfVersion(version);
    }

    public void setPdfVersion(PdfName version)
    {
        if(catalog_version == null || catalog_version.compareTo(version) < 0)
            catalog_version = version;
    }

    public void setAppendmode(boolean appendmode)
    {
        this.appendmode = appendmode;
    }

    public void writeHeader(OutputStreamCounter os)
        throws IOException
    {
        if(appendmode)
        {
            os.write(HEADER[0]);
        } else
        {
            os.write(HEADER[1]);
            os.write(getVersionAsByteArray(header_version));
            os.write(HEADER[2]);
            headerWasWritten = true;
        }
    }

    public PdfName getVersionAsName(char version)
    {
        switch(version)
        {
        case 50: // '2'
            return PdfWriter.PDF_VERSION_1_2;

        case 51: // '3'
            return PdfWriter.PDF_VERSION_1_3;

        case 52: // '4'
            return PdfWriter.PDF_VERSION_1_4;

        case 53: // '5'
            return PdfWriter.PDF_VERSION_1_5;

        case 54: // '6'
            return PdfWriter.PDF_VERSION_1_6;

        case 55: // '7'
            return PdfWriter.PDF_VERSION_1_7;
        }
        return PdfWriter.PDF_VERSION_1_4;
    }

    public byte[] getVersionAsByteArray(char version)
    {
        return DocWriter.getISOBytes(getVersionAsName(version).toString().substring(1));
    }

    public void addToCatalog(PdfDictionary catalog)
    {
        if(catalog_version != null)
            catalog.put(PdfName.VERSION, catalog_version);
        if(extensions != null)
            catalog.put(PdfName.EXTENSIONS, extensions);
    }

    public void addDeveloperExtension(PdfDeveloperExtension de)
    {
        if(extensions == null)
        {
            extensions = new PdfDictionary();
        } else
        {
            PdfDictionary extension = extensions.getAsDict(de.getPrefix());
            if(extension != null)
            {
                int diff = de.getBaseversion().compareTo(extension.getAsName(PdfName.BASEVERSION));
                if(diff < 0)
                    return;
                diff = de.getExtensionLevel() - extension.getAsNumber(PdfName.EXTENSIONLEVEL).intValue();
                if(diff <= 0)
                    return;
            }
        }
        extensions.put(de.getPrefix(), de.getDeveloperExtensions());
    }

    public char getVersion()
    {
        return version;
    }

    public static final byte HEADER[][] = {
        DocWriter.getISOBytes("\n"), DocWriter.getISOBytes("%PDF-"), DocWriter.getISOBytes("\n%\342\343\317\323\n")
    };
    protected boolean headerWasWritten;
    protected boolean appendmode;
    protected char header_version;
    protected PdfName catalog_version;
    protected char version;
    protected PdfDictionary extensions;

}
