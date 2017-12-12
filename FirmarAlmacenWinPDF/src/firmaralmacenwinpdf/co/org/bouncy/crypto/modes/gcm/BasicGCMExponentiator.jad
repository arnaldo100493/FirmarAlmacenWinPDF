// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicGCMExponentiator.java

package co.org.bouncy.crypto.modes.gcm;

import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.crypto.modes.gcm:
//            GCMExponentiator, GCMUtil

public class BasicGCMExponentiator
    implements GCMExponentiator
{

    public BasicGCMExponentiator()
    {
    }

    public void init(byte x[])
    {
        this.x = Arrays.clone(x);
    }

    public void exponentiateX(long pow, byte output[])
    {
        byte y[] = GCMUtil.oneAsBytes();
        if(pow > 0L)
        {
            byte powX[] = Arrays.clone(x);
            do
            {
                if((pow & 1L) != 0L)
                    GCMUtil.multiply(y, powX);
                GCMUtil.multiply(powX, powX);
                pow >>>= 1;
            } while(pow > 0L);
        }
        System.arraycopy(y, 0, output, 0, 16);
    }

    private byte x[];
}
