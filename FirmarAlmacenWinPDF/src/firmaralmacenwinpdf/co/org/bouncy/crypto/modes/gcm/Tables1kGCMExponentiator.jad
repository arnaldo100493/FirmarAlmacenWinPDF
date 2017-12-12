// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tables1kGCMExponentiator.java

package co.org.bouncy.crypto.modes.gcm;

import co.org.bouncy.util.Arrays;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.modes.gcm:
//            GCMExponentiator, GCMUtil

public class Tables1kGCMExponentiator
    implements GCMExponentiator
{

    public Tables1kGCMExponentiator()
    {
    }

    public void init(byte x[])
    {
        if(lookupPowX2 != null && Arrays.areEqual(x, (byte[])(byte[])lookupPowX2.elementAt(0)))
        {
            return;
        } else
        {
            lookupPowX2 = new Vector(8);
            lookupPowX2.addElement(Arrays.clone(x));
            return;
        }
    }

    public void exponentiateX(long pow, byte output[])
    {
        byte y[] = GCMUtil.oneAsBytes();
        int bit = 0;
        for(; pow > 0L; pow >>>= 1)
        {
            if((pow & 1L) != 0L)
            {
                ensureAvailable(bit);
                GCMUtil.multiply(y, (byte[])(byte[])lookupPowX2.elementAt(bit));
            }
            bit++;
        }

        System.arraycopy(y, 0, output, 0, 16);
    }

    private void ensureAvailable(int bit)
    {
        int count = lookupPowX2.size();
        if(count <= bit)
        {
            byte tmp[] = (byte[])(byte[])lookupPowX2.elementAt(count - 1);
            do
            {
                tmp = Arrays.clone(tmp);
                GCMUtil.multiply(tmp, tmp);
                lookupPowX2.addElement(tmp);
            } while(++count <= bit);
        }
    }

    private Vector lookupPowX2;
}
