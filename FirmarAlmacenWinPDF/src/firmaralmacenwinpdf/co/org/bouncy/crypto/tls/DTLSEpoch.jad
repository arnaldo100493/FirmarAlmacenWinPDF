// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSEpoch.java

package co.org.bouncy.crypto.tls;


// Referenced classes of package co.org.bouncy.crypto.tls:
//            DTLSReplayWindow, TlsCipher

class DTLSEpoch
{

    DTLSEpoch(int epoch, TlsCipher cipher)
    {
        sequence_number = 0L;
        if(epoch < 0)
            throw new IllegalArgumentException("'epoch' must be >= 0");
        if(cipher == null)
        {
            throw new IllegalArgumentException("'cipher' cannot be null");
        } else
        {
            this.epoch = epoch;
            this.cipher = cipher;
            return;
        }
    }

    long allocateSequenceNumber()
    {
        return sequence_number++;
    }

    TlsCipher getCipher()
    {
        return cipher;
    }

    int getEpoch()
    {
        return epoch;
    }

    DTLSReplayWindow getReplayWindow()
    {
        return replayWindow;
    }

    long getSequence_number()
    {
        return sequence_number;
    }

    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private final int epoch;
    private final TlsCipher cipher;
    private long sequence_number;
}
