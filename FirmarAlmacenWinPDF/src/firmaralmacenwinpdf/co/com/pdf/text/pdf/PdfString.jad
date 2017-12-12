// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfString.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, ByteBuffer, PdfEncryption, PdfEncodings, 
//            PdfWriter, PdfContentByte, PdfReader

public class PdfString extends PdfObject
{

    public PdfString()
    {
        super(3);
        value = "";
        originalValue = null;
        encoding = "PDF";
        objNum = 0;
        objGen = 0;
        hexWriting = false;
    }

    public PdfString(String value)
    {
        super(3);
        this.value = "";
        originalValue = null;
        encoding = "PDF";
        objNum = 0;
        objGen = 0;
        hexWriting = false;
        this.value = value;
    }

    public PdfString(String value, String encoding)
    {
        super(3);
        this.value = "";
        originalValue = null;
        this.encoding = "PDF";
        objNum = 0;
        objGen = 0;
        hexWriting = false;
        this.value = value;
        this.encoding = encoding;
    }

    public PdfString(byte bytes[])
    {
        super(3);
        value = "";
        originalValue = null;
        encoding = "PDF";
        objNum = 0;
        objGen = 0;
        hexWriting = false;
        value = PdfEncodings.convertToString(bytes, null);
        encoding = "";
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 11, this);
        byte b[] = getBytes();
        PdfEncryption crypto = null;
        if(writer != null)
            crypto = writer.getEncryption();
        if(crypto != null && !crypto.isEmbeddedFilesOnly())
            b = crypto.encryptByteArray(b);
        if(hexWriting)
        {
            ByteBuffer buf = new ByteBuffer();
            buf.append('<');
            int len = b.length;
            for(int k = 0; k < len; k++)
                buf.appendHex(b[k]);

            buf.append('>');
            os.write(buf.toByteArray());
        } else
        {
            os.write(PdfContentByte.escapeString(b));
        }
    }

    public String toString()
    {
        return value;
    }

    public byte[] getBytes()
    {
        if(bytes == null)
            if(encoding != null && encoding.equals("UnicodeBig") && PdfEncodings.isPdfDocEncoding(value))
                bytes = PdfEncodings.convertToBytes(value, "PDF");
            else
                bytes = PdfEncodings.convertToBytes(value, encoding);
        return bytes;
    }

    public String toUnicodeString()
    {
        if(encoding != null && encoding.length() != 0)
            return value;
        getBytes();
        if(bytes.length >= 2 && bytes[0] == -2 && bytes[1] == -1)
            return PdfEncodings.convertToString(bytes, "UnicodeBig");
        else
            return PdfEncodings.convertToString(bytes, "PDF");
    }

    public String getEncoding()
    {
        return encoding;
    }

    void setObjNum(int objNum, int objGen)
    {
        this.objNum = objNum;
        this.objGen = objGen;
    }

    void decrypt(PdfReader reader)
    {
        PdfEncryption decrypt = reader.getDecrypt();
        if(decrypt != null)
        {
            originalValue = value;
            decrypt.setHashKey(objNum, objGen);
            bytes = PdfEncodings.convertToBytes(value, null);
            bytes = decrypt.decryptByteArray(bytes);
            value = PdfEncodings.convertToString(bytes, null);
        }
    }

    public byte[] getOriginalBytes()
    {
        if(originalValue == null)
            return getBytes();
        else
            return PdfEncodings.convertToBytes(originalValue, null);
    }

    public PdfString setHexWriting(boolean hexWriting)
    {
        this.hexWriting = hexWriting;
        return this;
    }

    public boolean isHexWriting()
    {
        return hexWriting;
    }

    protected String value;
    protected String originalValue;
    protected String encoding;
    protected int objNum;
    protected int objGen;
    protected boolean hexWriting;
}
