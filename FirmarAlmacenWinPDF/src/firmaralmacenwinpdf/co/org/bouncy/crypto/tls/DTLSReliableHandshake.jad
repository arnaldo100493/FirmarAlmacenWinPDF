// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSReliableHandshake.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Integers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DeferredHash, DTLSReassembler, TlsFatalAlert, DTLSHandshakeRetransmit, 
//            TlsHandshakeHash, DTLSRecordLayer, TlsUtils, TlsContext

class DTLSReliableHandshake
{
    static class Message
    {

        public int getSeq()
        {
            return message_seq;
        }

        public short getType()
        {
            return msg_type;
        }

        public byte[] getBody()
        {
            return body;
        }

        private final int message_seq;
        private final short msg_type;
        private final byte body[];

        private Message(int message_seq, short msg_type, byte body[])
        {
            this.message_seq = message_seq;
            this.msg_type = msg_type;
            this.body = body;
        }

    }


    DTLSReliableHandshake(TlsContext context, DTLSRecordLayer transport)
    {
        hash = new DeferredHash();
        currentInboundFlight = new Hashtable();
        previousInboundFlight = null;
        outboundFlight = new Vector();
        sending = true;
        message_seq = 0;
        next_receive_seq = 0;
        recordLayer = transport;
        hash.init(context);
    }

    void notifyHelloComplete()
    {
        hash = hash.commit();
    }

    byte[] getCurrentHash()
    {
        TlsHandshakeHash copyOfHash = hash.fork();
        byte result[] = new byte[copyOfHash.getDigestSize()];
        copyOfHash.doFinal(result, 0);
        return result;
    }

    void sendMessage(short msg_type, byte body[])
        throws IOException
    {
        if(!sending)
        {
            checkInboundFlight();
            sending = true;
            outboundFlight.removeAllElements();
        }
        Message message = new Message(message_seq++, msg_type, body);
        outboundFlight.addElement(message);
        writeMessage(message);
        updateHandshakeMessagesDigest(message);
    }

    Message receiveMessage()
        throws IOException
    {
        byte buf[];
        int readTimeoutMillis;
        if(sending)
        {
            sending = false;
            prepareInboundFlight();
        }
        DTLSReassembler next = (DTLSReassembler)currentInboundFlight.get(Integers.valueOf(next_receive_seq));
        if(next != null)
        {
            byte body[] = next.getBodyIfComplete();
            if(body != null)
            {
                previousInboundFlight = null;
                return updateHandshakeMessagesDigest(new Message(next_receive_seq++, next.getType(), body));
            }
        }
        buf = null;
        readTimeoutMillis = 1000;
_L4:
        int receiveLimit;
        receiveLimit = recordLayer.getReceiveLimit();
        if(buf == null || buf.length < receiveLimit)
            buf = new byte[receiveLimit];
_L2:
        int received = recordLayer.receive(buf, 0, receiveLimit, readTimeoutMillis);
        if(received < 0)
            break; /* Loop/switch isn't completed */
        if(received >= 12)
        {
            int fragment_length = TlsUtils.readUint24(buf, 9);
            if(received == fragment_length + 12)
            {
                int seq = TlsUtils.readUint16(buf, 4);
                if(seq <= next_receive_seq + 10)
                {
                    short msg_type = TlsUtils.readUint8(buf, 0);
                    int length = TlsUtils.readUint24(buf, 1);
                    int fragment_offset = TlsUtils.readUint24(buf, 6);
                    if(fragment_offset + fragment_length <= length)
                        if(seq < next_receive_seq)
                        {
                            if(previousInboundFlight != null)
                            {
                                DTLSReassembler reassembler = (DTLSReassembler)previousInboundFlight.get(Integers.valueOf(seq));
                                if(reassembler != null)
                                {
                                    reassembler.contributeFragment(msg_type, length, buf, 12, fragment_offset, fragment_length);
                                    if(checkAll(previousInboundFlight))
                                    {
                                        resendOutboundFlight();
                                        readTimeoutMillis = Math.min(readTimeoutMillis * 2, 60000);
                                        resetAll(previousInboundFlight);
                                    }
                                }
                            }
                        } else
                        {
                            DTLSReassembler reassembler = (DTLSReassembler)currentInboundFlight.get(Integers.valueOf(seq));
                            if(reassembler == null)
                            {
                                reassembler = new DTLSReassembler(msg_type, length);
                                currentInboundFlight.put(Integers.valueOf(seq), reassembler);
                            }
                            reassembler.contributeFragment(msg_type, length, buf, 12, fragment_offset, fragment_length);
                            if(seq == next_receive_seq)
                            {
                                byte body[] = reassembler.getBodyIfComplete();
                                if(body != null)
                                {
                                    previousInboundFlight = null;
                                    return updateHandshakeMessagesDigest(new Message(next_receive_seq++, reassembler.getType(), body));
                                }
                            }
                        }
                }
            }
        }
        continue; /* Loop/switch isn't completed */
        IOException e;
        e;
        break; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
        resendOutboundFlight();
        readTimeoutMillis = Math.min(readTimeoutMillis * 2, 60000);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void finish()
    {
        DTLSHandshakeRetransmit retransmit = null;
        if(!sending)
            checkInboundFlight();
        else
        if(currentInboundFlight != null)
            retransmit = new DTLSHandshakeRetransmit() {

                public void receivedHandshakeRecord(int epoch, byte buf[], int off, int len)
                    throws IOException
                {
                    if(len < 12)
                        return;
                    int fragment_length = TlsUtils.readUint24(buf, off + 9);
                    if(len != fragment_length + 12)
                        return;
                    int seq = TlsUtils.readUint16(buf, off + 4);
                    if(seq >= next_receive_seq)
                        return;
                    short msg_type = TlsUtils.readUint8(buf, off);
                    int expectedEpoch = msg_type != 20 ? 0 : 1;
                    if(epoch != expectedEpoch)
                        return;
                    int length = TlsUtils.readUint24(buf, off + 1);
                    int fragment_offset = TlsUtils.readUint24(buf, off + 6);
                    if(fragment_offset + fragment_length > length)
                        return;
                    DTLSReassembler reassembler = (DTLSReassembler)currentInboundFlight.get(Integers.valueOf(seq));
                    if(reassembler != null)
                    {
                        reassembler.contributeFragment(msg_type, length, buf, off + 12, fragment_offset, fragment_length);
                        if(DTLSReliableHandshake.checkAll(currentInboundFlight))
                        {
                            resendOutboundFlight();
                            DTLSReliableHandshake.resetAll(currentInboundFlight);
                        }
                    }
                }

                final DTLSReliableHandshake this$0;

            
            {
                this$0 = DTLSReliableHandshake.this;
                super();
            }
            }
;
        recordLayer.handshakeSuccessful(retransmit);
    }

    void resetHandshakeMessagesDigest()
    {
        hash.reset();
    }

    private void checkInboundFlight()
    {
        for(Enumeration e = currentInboundFlight.keys(); e.hasMoreElements();)
        {
            Integer key = (Integer)e.nextElement();
            if(key.intValue() < next_receive_seq);
        }

    }

    private void prepareInboundFlight()
    {
        resetAll(currentInboundFlight);
        previousInboundFlight = currentInboundFlight;
        currentInboundFlight = new Hashtable();
    }

    private void resendOutboundFlight()
        throws IOException
    {
        recordLayer.resetWriteEpoch();
        for(int i = 0; i < outboundFlight.size(); i++)
            writeMessage((Message)outboundFlight.elementAt(i));

    }

    private Message updateHandshakeMessagesDigest(Message message)
        throws IOException
    {
        if(message.getType() != 0)
        {
            byte body[] = message.getBody();
            byte buf[] = new byte[12];
            TlsUtils.writeUint8(message.getType(), buf, 0);
            TlsUtils.writeUint24(body.length, buf, 1);
            TlsUtils.writeUint16(message.getSeq(), buf, 4);
            TlsUtils.writeUint24(0, buf, 6);
            TlsUtils.writeUint24(body.length, buf, 9);
            hash.update(buf, 0, buf.length);
            hash.update(body, 0, body.length);
        }
        return message;
    }

    private void writeMessage(Message message)
        throws IOException
    {
        int sendLimit = recordLayer.getSendLimit();
        int fragmentLimit = sendLimit - 12;
        if(fragmentLimit < 1)
            throw new TlsFatalAlert((short)80);
        int length = message.getBody().length;
        int fragment_offset = 0;
        do
        {
            int fragment_length = Math.min(length - fragment_offset, fragmentLimit);
            writeHandshakeFragment(message, fragment_offset, fragment_length);
            fragment_offset += fragment_length;
        } while(fragment_offset < length);
    }

    private void writeHandshakeFragment(Message message, int fragment_offset, int fragment_length)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeUint8(message.getType(), buf);
        TlsUtils.writeUint24(message.getBody().length, buf);
        TlsUtils.writeUint16(message.getSeq(), buf);
        TlsUtils.writeUint24(fragment_offset, buf);
        TlsUtils.writeUint24(fragment_length, buf);
        buf.write(message.getBody(), fragment_offset, fragment_length);
        byte fragment[] = buf.toByteArray();
        recordLayer.send(fragment, 0, fragment.length);
    }

    private static boolean checkAll(Hashtable inboundFlight)
    {
        for(Enumeration e = inboundFlight.elements(); e.hasMoreElements();)
            if(((DTLSReassembler)e.nextElement()).getBodyIfComplete() == null)
                return false;

        return true;
    }

    private static void resetAll(Hashtable inboundFlight)
    {
        for(Enumeration e = inboundFlight.elements(); e.hasMoreElements(); ((DTLSReassembler)e.nextElement()).reset());
    }

    private static final int MAX_RECEIVE_AHEAD = 10;
    private final DTLSRecordLayer recordLayer;
    private TlsHandshakeHash hash;
    private Hashtable currentInboundFlight;
    private Hashtable previousInboundFlight;
    private Vector outboundFlight;
    private boolean sending;
    private int message_seq;
    private int next_receive_seq;





}
