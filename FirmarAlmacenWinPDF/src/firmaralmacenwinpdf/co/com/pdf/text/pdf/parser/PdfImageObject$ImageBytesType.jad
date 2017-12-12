// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfImageObject.java

package co.com.pdf.text.pdf.parser;


// Referenced classes of package co.com.pdf.text.pdf.parser:
//            PdfImageObject

public static final class PdfImageObject$ImageBytesType extends Enum
{

    public static PdfImageObject$ImageBytesType[] values()
    {
        return (PdfImageObject$ImageBytesType[])$VALUES.clone();
    }

    public static PdfImageObject$ImageBytesType valueOf(String name)
    {
        return (PdfImageObject$ImageBytesType)Enum.valueOf(co/com/pdf/text/pdf/parser/PdfImageObject$ImageBytesType, name);
    }

    public String getFileExtension()
    {
        return fileExtension;
    }

    public static final PdfImageObject$ImageBytesType PNG;
    public static final PdfImageObject$ImageBytesType JPG;
    public static final PdfImageObject$ImageBytesType JP2;
    public static final PdfImageObject$ImageBytesType CCITT;
    public static final PdfImageObject$ImageBytesType JBIG2;
    private final String fileExtension;
    private static final PdfImageObject$ImageBytesType $VALUES[];

    static 
    {
        PNG = new PdfImageObject$ImageBytesType("PNG", 0, "png");
        JPG = new PdfImageObject$ImageBytesType("JPG", 1, "jpg");
        JP2 = new PdfImageObject$ImageBytesType("JP2", 2, "jp2");
        CCITT = new PdfImageObject$ImageBytesType("CCITT", 3, "tif");
        JBIG2 = new PdfImageObject$ImageBytesType("JBIG2", 4, "jbig2");
        $VALUES = (new PdfImageObject$ImageBytesType[] {
            PNG, JPG, JP2, CCITT, JBIG2
        });
    }

    private PdfImageObject$ImageBytesType(String s, int i, String fileExtension)
    {
        super(s, i);
        this.fileExtension = fileExtension;
    }
}
