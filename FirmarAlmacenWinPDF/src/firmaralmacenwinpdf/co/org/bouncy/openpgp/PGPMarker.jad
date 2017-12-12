// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPMarker.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGInputStream;
import co.org.bouncy.bcpg.MarkerPacket;
import java.io.IOException;

public class PGPMarker
{

    public PGPMarker(BCPGInputStream in)
        throws IOException
    {
        p = (MarkerPacket)in.readPacket();
    }

    private MarkerPacket p;
}
