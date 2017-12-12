// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfICCBased.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, PdfException, PdfNumber, ICC_Profile, 
//            PdfName

public class PdfICCBased extends PdfStream
{

    public PdfICCBased(ICC_Profile profile)
    {
        this(profile, -1);
    }

    public PdfICCBased(ICC_Profile profile, int compressionLevel)
    {
        try
        {
            int numberOfComponents = profile.getNumComponents();
            switch(numberOfComponents)
            {
            case 1: // '\001'
                put(PdfName.ALTERNATE, PdfName.DEVICEGRAY);
                break;

            case 3: // '\003'
                put(PdfName.ALTERNATE, PdfName.DEVICERGB);
                break;

            case 4: // '\004'
                put(PdfName.ALTERNATE, PdfName.DEVICECMYK);
                break;

            case 2: // '\002'
            default:
                throw new PdfException(MessageLocalization.getComposedMessage("1.component.s.is.not.supported", numberOfComponents));
            }
            put(PdfName.N, new PdfNumber(numberOfComponents));
            bytes = profile.getData();
            put(PdfName.LENGTH, new PdfNumber(bytes.length));
            flateCompress(compressionLevel);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }
}
