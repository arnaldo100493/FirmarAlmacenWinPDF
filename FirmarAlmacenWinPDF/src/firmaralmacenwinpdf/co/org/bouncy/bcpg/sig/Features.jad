// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Features.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class Features extends SignatureSubpacket
{

    private static final byte[] featureToByteArray(byte feature)
    {
        byte data[] = new byte[1];
        data[0] = feature;
        return data;
    }

    public Features(boolean critical, byte data[])
    {
        super(30, critical, data);
    }

    public Features(boolean critical, byte feature)
    {
        super(30, critical, featureToByteArray(feature));
    }

    public boolean supportsModificationDetection()
    {
        return supportsFeature((byte)1);
    }

    public boolean supportsFeature(byte feature)
    {
        for(int i = 0; i < data.length; i++)
            if(data[i] == feature)
                return true;

        return false;
    }

    private void setSupportsFeature(byte feature, boolean support)
    {
        if(feature == 0)
            throw new IllegalArgumentException("feature == 0");
        if(supportsFeature(feature) != support)
            if(support)
            {
                byte temp[] = new byte[data.length + 1];
                System.arraycopy(data, 0, temp, 0, data.length);
                temp[data.length] = feature;
                data = temp;
            } else
            {
                int i = 0;
                do
                {
                    if(i >= data.length)
                        break;
                    if(data[i] == feature)
                    {
                        byte temp[] = new byte[data.length - 1];
                        System.arraycopy(data, 0, temp, 0, i);
                        System.arraycopy(data, i + 1, temp, i, temp.length - i);
                        data = temp;
                        break;
                    }
                    i++;
                } while(true);
            }
    }

    public static final byte FEATURE_MODIFICATION_DETECTION = 1;
}
