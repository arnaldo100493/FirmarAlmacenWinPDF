// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSReplayWindow.java

package co.org.bouncy.crypto.tls;


class DTLSReplayWindow
{

    DTLSReplayWindow()
    {
        latestConfirmedSeq = -1L;
        bitmap = 0L;
    }

    boolean shouldDiscard(long seq)
    {
        if((seq & 0xffffffffffffL) != seq)
            return true;
        if(seq <= latestConfirmedSeq)
        {
            long diff = latestConfirmedSeq - seq;
            if(diff >= 64L)
                return true;
            if((bitmap & 1L << (int)diff) != 0L)
                return true;
        }
        return false;
    }

    void reportAuthenticated(long seq)
    {
        if((seq & 0xffffffffffffL) != seq)
            throw new IllegalArgumentException("'seq' out of range");
        if(seq <= latestConfirmedSeq)
        {
            long diff = latestConfirmedSeq - seq;
            if(diff < 64L)
                bitmap |= 1L << (int)diff;
        } else
        {
            long diff = seq - latestConfirmedSeq;
            if(diff >= 64L)
            {
                bitmap = 1L;
            } else
            {
                bitmap <<= (int)diff;
                bitmap |= 1L;
            }
            latestConfirmedSeq = seq;
        }
    }

    void reset()
    {
        latestConfirmedSeq = -1L;
        bitmap = 0L;
    }

    private static final long VALID_SEQ_MASK = 0xffffffffffffL;
    private static final long WINDOW_SIZE = 64L;
    private long latestConfirmedSeq;
    private long bitmap;
}
