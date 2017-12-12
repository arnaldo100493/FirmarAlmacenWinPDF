// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CidResource.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.RandomAccessSourceFactory;
import co.com.pdf.text.io.StreamUtil;
import co.com.pdf.text.pdf.PRTokeniser;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            CidLocation

public class CidResource
    implements CidLocation
{

    public CidResource()
    {
    }

    public PRTokeniser getLocation(String location)
        throws IOException
    {
        String fullName = (new StringBuilder()).append("co/com/pdf/text/pdf/fonts/cmaps/").append(location).toString();
        InputStream inp = StreamUtil.getResourceStream(fullName);
        if(inp == null)
            throw new IOException(MessageLocalization.getComposedMessage("the.cmap.1.was.not.found", new Object[] {
                fullName
            }));
        else
            return new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(inp)));
    }
}
