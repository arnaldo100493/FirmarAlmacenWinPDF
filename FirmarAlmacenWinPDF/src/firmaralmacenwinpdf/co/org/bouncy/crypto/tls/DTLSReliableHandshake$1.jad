// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSReliableHandshake.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Integers;
import java.io.IOException;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DTLSReassembler, DTLSHandshakeRetransmit, DTLSReliableHandshake, TlsUtils

class DTLSReliableHandshake$1
    implements DTLSHandshakeRetransmit
{

    public void receivedHandshakeRecord(int epoch, byte buf[], int off, int len)
        throws IOException
    {
        if(len < 12)
            return;
        int fragment_length = TlsUtils.readUint24(buf, off + 9);
        if(len != fragment_length + 12)
            return;
        int seq = TlsUtils.readUint16(buf, off + 4);
        if(seq >= DTLSReliableHandshake.access$100(DTLSReliableHandshake.this))
            return;
        short msg_type = TlsUtils.readUint8(buf, off);
        int expectedEpoch = msg_type != 20 ? 0 : 1;
        if(epoch != expectedEpoch)
            return;
        int length = TlsUtils.readUint24(buf, off + 1);
        int fragment_offset = TlsUtils.readUint24(buf, off + 6);
        if(fragment_offset + fragment_length > length)
            return;
        DTLSReassembler reassembler = (DTLSReassembler)DTLSReliableHandshake.access$200(DTLSReliableHandshake.this).get(Integers.valueOf(seq));
        if(reassembler != null)
        {
            reassembler.contributeFragment(msg_type, length, buf, off + 12, fragment_offset, fragment_length);
            if(DTLSReliableHandshake.access$300(DTLSReliableHandshake.access$200(DTLSReliableHandshake.this)))
            {
                DTLSReliableHandshake.access$400(DTLSReliableHandshake.this);
                DTLSReliableHandshake.access$500(DTLSReliableHandshake.access$200(DTLSReliableHandshake.this));
            }
        }
    }

    final DTLSReliableHandshake this$0;

    DTLSReliableHandshake$1()
    {
        this$0 = DTLSReliableHandshake.this;
        super();
    }
}
