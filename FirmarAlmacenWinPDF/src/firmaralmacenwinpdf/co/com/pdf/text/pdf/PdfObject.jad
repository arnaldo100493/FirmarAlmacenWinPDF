// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfObject.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfEncodings, PdfWriter, PRIndirectReference

public abstract class PdfObject
{

    protected PdfObject(int type)
    {
        this.type = type;
    }

    protected PdfObject(int type, String content)
    {
        this.type = type;
        bytes = PdfEncodings.convertToBytes(content, null);
    }

    protected PdfObject(int type, byte bytes[])
    {
        this.bytes = bytes;
        this.type = type;
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        if(bytes != null)
        {
            PdfWriter.checkPdfIsoConformance(writer, 11, this);
            os.write(bytes);
        }
    }

    public String toString()
    {
        if(bytes == null)
            return super.toString();
        else
            return PdfEncodings.convertToString(bytes, null);
    }

    public byte[] getBytes()
    {
        return bytes;
    }

    public boolean canBeInObjStm()
    {
        switch(type)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
            return true;

        case 7: // '\007'
        case 9: // '\t'
        case 10: // '\n'
        default:
            return false;
        }
    }

    public int length()
    {
        return toString().length();
    }

    protected void setContent(String content)
    {
        bytes = PdfEncodings.convertToBytes(content, null);
    }

    public int type()
    {
        return type;
    }

    public boolean isNull()
    {
        return type == 8;
    }

    public boolean isBoolean()
    {
        return type == 1;
    }

    public boolean isNumber()
    {
        return type == 2;
    }

    public boolean isString()
    {
        return type == 3;
    }

    public boolean isName()
    {
        return type == 4;
    }

    public boolean isArray()
    {
        return type == 5;
    }

    public boolean isDictionary()
    {
        return type == 6;
    }

    public boolean isStream()
    {
        return type == 7;
    }

    public boolean isIndirect()
    {
        return type == 10;
    }

    public PRIndirectReference getIndRef()
    {
        return indRef;
    }

    public void setIndRef(PRIndirectReference indRef)
    {
        this.indRef = indRef;
    }

    public static final int BOOLEAN = 1;
    public static final int NUMBER = 2;
    public static final int STRING = 3;
    public static final int NAME = 4;
    public static final int ARRAY = 5;
    public static final int DICTIONARY = 6;
    public static final int STREAM = 7;
    public static final int NULL = 8;
    public static final int INDIRECT = 10;
    public static final String NOTHING = "";
    public static final String TEXT_PDFDOCENCODING = "PDF";
    public static final String TEXT_UNICODE = "UnicodeBig";
    protected byte bytes[];
    protected int type;
    protected PRIndirectReference indRef;
}
