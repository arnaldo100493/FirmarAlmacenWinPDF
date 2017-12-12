// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageAttribute.java

package co.org.bouncy.bcpg.attr;

import co.org.bouncy.bcpg.UserAttributeSubpacket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageAttribute extends UserAttributeSubpacket
{

    public ImageAttribute(byte data[])
    {
        super(1, data);
        hdrLength = (data[1] & 0xff) << 8 | data[0] & 0xff;
        version = data[2] & 0xff;
        encoding = data[3] & 0xff;
        imageData = new byte[data.length - hdrLength];
        System.arraycopy(data, hdrLength, imageData, 0, imageData.length);
    }

    public ImageAttribute(int imageType, byte imageData[])
    {
        this(toByteArray(imageType, imageData));
    }

    private static byte[] toByteArray(int imageType, byte imageData[])
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            bOut.write(16);
            bOut.write(0);
            bOut.write(1);
            bOut.write(imageType);
            bOut.write(ZEROES);
            bOut.write(imageData);
        }
        catch(IOException e)
        {
            throw new RuntimeException("unable to encode to byte array!");
        }
        return bOut.toByteArray();
    }

    public int version()
    {
        return version;
    }

    public int getEncoding()
    {
        return encoding;
    }

    public byte[] getImageData()
    {
        return imageData;
    }

    public static final int JPEG = 1;
    private static final byte ZEROES[] = new byte[12];
    private int hdrLength;
    private int version;
    private int encoding;
    private byte imageData[];

}
