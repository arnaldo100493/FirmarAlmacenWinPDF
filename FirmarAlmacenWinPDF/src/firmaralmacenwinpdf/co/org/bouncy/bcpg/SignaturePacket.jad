// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignaturePacket.java

package co.org.bouncy.bcpg;

import co.org.bouncy.bcpg.sig.IssuerKeyID;
import co.org.bouncy.bcpg.sig.SignatureCreationTime;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.util.Date;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, SignatureSubpacketInputStream, SignatureSubpacket, MPInteger, 
//            BCPGOutputStream, PublicKeyAlgorithmTags, BCPGInputStream

public class SignaturePacket extends ContainedPacket
    implements PublicKeyAlgorithmTags
{

    SignaturePacket(BCPGInputStream in)
        throws IOException
    {
        version = in.read();
        if(version == 3 || version == 2)
        {
            int l = in.read();
            signatureType = in.read();
            creationTime = ((long)in.read() << 24 | (long)(in.read() << 16) | (long)(in.read() << 8) | (long)in.read()) * 1000L;
            keyID |= (long)in.read() << 56;
            keyID |= (long)in.read() << 48;
            keyID |= (long)in.read() << 40;
            keyID |= (long)in.read() << 32;
            keyID |= (long)in.read() << 24;
            keyID |= (long)in.read() << 16;
            keyID |= (long)in.read() << 8;
            keyID |= in.read();
            keyAlgorithm = in.read();
            hashAlgorithm = in.read();
        } else
        if(version == 4)
        {
            signatureType = in.read();
            keyAlgorithm = in.read();
            hashAlgorithm = in.read();
            int hashedLength = in.read() << 8 | in.read();
            byte hashed[] = new byte[hashedLength];
            in.readFully(hashed);
            SignatureSubpacketInputStream sIn = new SignatureSubpacketInputStream(new ByteArrayInputStream(hashed));
            Vector v = new Vector();
            SignatureSubpacket sub;
            while((sub = sIn.readPacket()) != null) 
                v.addElement(sub);
            hashedData = new SignatureSubpacket[v.size()];
            for(int i = 0; i != hashedData.length; i++)
            {
                SignatureSubpacket p = (SignatureSubpacket)v.elementAt(i);
                if(p instanceof IssuerKeyID)
                    keyID = ((IssuerKeyID)p).getKeyID();
                else
                if(p instanceof SignatureCreationTime)
                    creationTime = ((SignatureCreationTime)p).getTime().getTime();
                hashedData[i] = p;
            }

            int unhashedLength = in.read() << 8 | in.read();
            byte unhashed[] = new byte[unhashedLength];
            in.readFully(unhashed);
            sIn = new SignatureSubpacketInputStream(new ByteArrayInputStream(unhashed));
            v.removeAllElements();
            while((sub = sIn.readPacket()) != null) 
                v.addElement(sub);
            unhashedData = new SignatureSubpacket[v.size()];
            for(int i = 0; i != unhashedData.length; i++)
            {
                SignatureSubpacket p = (SignatureSubpacket)v.elementAt(i);
                if(p instanceof IssuerKeyID)
                    keyID = ((IssuerKeyID)p).getKeyID();
                unhashedData[i] = p;
            }

        } else
        {
            throw new RuntimeException((new StringBuilder()).append("unsupported version: ").append(version).toString());
        }
        fingerPrint = new byte[2];
        in.readFully(fingerPrint);
        switch(keyAlgorithm)
        {
        case 1: // '\001'
        case 3: // '\003'
            MPInteger v = new MPInteger(in);
            signature = new MPInteger[1];
            signature[0] = v;
            break;

        case 17: // '\021'
            MPInteger r = new MPInteger(in);
            MPInteger s = new MPInteger(in);
            signature = new MPInteger[2];
            signature[0] = r;
            signature[1] = s;
            break;

        case 16: // '\020'
        case 20: // '\024'
            MPInteger p = new MPInteger(in);
            MPInteger g = new MPInteger(in);
            MPInteger y = new MPInteger(in);
            signature = new MPInteger[3];
            signature[0] = p;
            signature[1] = g;
            signature[2] = y;
            break;

        default:
            if(keyAlgorithm >= 100 && keyAlgorithm <= 110)
            {
                signature = null;
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                int ch;
                while((ch = in.read()) >= 0) 
                    bOut.write(ch);
                signatureEncoding = bOut.toByteArray();
            } else
            {
                throw new IOException((new StringBuilder()).append("unknown signature key algorithm: ").append(keyAlgorithm).toString());
            }
            break;
        }
    }

    public SignaturePacket(int signatureType, long keyID, int keyAlgorithm, int hashAlgorithm, SignatureSubpacket hashedData[], SignatureSubpacket unhashedData[], 
            byte fingerPrint[], MPInteger signature[])
    {
        this(4, signatureType, keyID, keyAlgorithm, hashAlgorithm, hashedData, unhashedData, fingerPrint, signature);
    }

    public SignaturePacket(int version, int signatureType, long keyID, int keyAlgorithm, int hashAlgorithm, long creationTime, byte fingerPrint[], MPInteger signature[])
    {
        this(version, signatureType, keyID, keyAlgorithm, hashAlgorithm, null, null, fingerPrint, signature);
        this.creationTime = creationTime;
    }

    public SignaturePacket(int version, int signatureType, long keyID, int keyAlgorithm, int hashAlgorithm, SignatureSubpacket hashedData[], 
            SignatureSubpacket unhashedData[], byte fingerPrint[], MPInteger signature[])
    {
        this.version = version;
        this.signatureType = signatureType;
        this.keyID = keyID;
        this.keyAlgorithm = keyAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
        this.hashedData = hashedData;
        this.unhashedData = unhashedData;
        this.fingerPrint = fingerPrint;
        this.signature = signature;
        if(hashedData != null)
            setCreationTime();
    }

    public int getVersion()
    {
        return version;
    }

    public int getSignatureType()
    {
        return signatureType;
    }

    public long getKeyID()
    {
        return keyID;
    }

    public byte[] getSignatureTrailer()
    {
        byte trailer[] = null;
        if(version == 3 || version == 2)
        {
            trailer = new byte[5];
            long time = creationTime / 1000L;
            trailer[0] = (byte)signatureType;
            trailer[1] = (byte)(int)(time >> 24);
            trailer[2] = (byte)(int)(time >> 16);
            trailer[3] = (byte)(int)(time >> 8);
            trailer[4] = (byte)(int)time;
        } else
        {
            ByteArrayOutputStream sOut = new ByteArrayOutputStream();
            try
            {
                sOut.write((byte)getVersion());
                sOut.write((byte)getSignatureType());
                sOut.write((byte)getKeyAlgorithm());
                sOut.write((byte)getHashAlgorithm());
                ByteArrayOutputStream hOut = new ByteArrayOutputStream();
                SignatureSubpacket hashed[] = getHashedSubPackets();
                for(int i = 0; i != hashed.length; i++)
                    hashed[i].encode(hOut);

                byte data[] = hOut.toByteArray();
                sOut.write((byte)(data.length >> 8));
                sOut.write((byte)data.length);
                sOut.write(data);
                byte hData[] = sOut.toByteArray();
                sOut.write((byte)getVersion());
                sOut.write(-1);
                sOut.write((byte)(hData.length >> 24));
                sOut.write((byte)(hData.length >> 16));
                sOut.write((byte)(hData.length >> 8));
                sOut.write((byte)hData.length);
            }
            catch(IOException e)
            {
                throw new RuntimeException((new StringBuilder()).append("exception generating trailer: ").append(e).toString());
            }
            trailer = sOut.toByteArray();
        }
        return trailer;
    }

    public int getKeyAlgorithm()
    {
        return keyAlgorithm;
    }

    public int getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public MPInteger[] getSignature()
    {
        return signature;
    }

    public byte[] getSignatureBytes()
    {
        if(signatureEncoding == null)
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            BCPGOutputStream bcOut = new BCPGOutputStream(bOut);
            for(int i = 0; i != signature.length; i++)
                try
                {
                    bcOut.writeObject(signature[i]);
                }
                catch(IOException e)
                {
                    throw new RuntimeException((new StringBuilder()).append("internal error: ").append(e).toString());
                }

            return bOut.toByteArray();
        } else
        {
            return Arrays.clone(signatureEncoding);
        }
    }

    public SignatureSubpacket[] getHashedSubPackets()
    {
        return hashedData;
    }

    public SignatureSubpacket[] getUnhashedSubPackets()
    {
        return unhashedData;
    }

    public long getCreationTime()
    {
        return creationTime;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        BCPGOutputStream pOut = new BCPGOutputStream(bOut);
        pOut.write(version);
        if(version == 3 || version == 2)
        {
            pOut.write(5);
            long time = creationTime / 1000L;
            pOut.write(signatureType);
            pOut.write((byte)(int)(time >> 24));
            pOut.write((byte)(int)(time >> 16));
            pOut.write((byte)(int)(time >> 8));
            pOut.write((byte)(int)time);
            pOut.write((byte)(int)(keyID >> 56));
            pOut.write((byte)(int)(keyID >> 48));
            pOut.write((byte)(int)(keyID >> 40));
            pOut.write((byte)(int)(keyID >> 32));
            pOut.write((byte)(int)(keyID >> 24));
            pOut.write((byte)(int)(keyID >> 16));
            pOut.write((byte)(int)(keyID >> 8));
            pOut.write((byte)(int)keyID);
            pOut.write(keyAlgorithm);
            pOut.write(hashAlgorithm);
        } else
        if(version == 4)
        {
            pOut.write(signatureType);
            pOut.write(keyAlgorithm);
            pOut.write(hashAlgorithm);
            ByteArrayOutputStream sOut = new ByteArrayOutputStream();
            for(int i = 0; i != hashedData.length; i++)
                hashedData[i].encode(sOut);

            byte data[] = sOut.toByteArray();
            pOut.write(data.length >> 8);
            pOut.write(data.length);
            pOut.write(data);
            sOut.reset();
            for(int i = 0; i != unhashedData.length; i++)
                unhashedData[i].encode(sOut);

            data = sOut.toByteArray();
            pOut.write(data.length >> 8);
            pOut.write(data.length);
            pOut.write(data);
        } else
        {
            throw new IOException((new StringBuilder()).append("unknown version: ").append(version).toString());
        }
        pOut.write(fingerPrint);
        if(signature != null)
        {
            for(int i = 0; i != signature.length; i++)
                pOut.writeObject(signature[i]);

        } else
        {
            pOut.write(signatureEncoding);
        }
        out.writePacket(2, bOut.toByteArray(), true);
    }

    private void setCreationTime()
    {
        int i = 0;
        do
        {
            if(i == hashedData.length)
                break;
            if(hashedData[i] instanceof SignatureCreationTime)
            {
                creationTime = ((SignatureCreationTime)hashedData[i]).getTime().getTime();
                break;
            }
            i++;
        } while(true);
    }

    private int version;
    private int signatureType;
    private long creationTime;
    private long keyID;
    private int keyAlgorithm;
    private int hashAlgorithm;
    private MPInteger signature[];
    private byte fingerPrint[];
    private SignatureSubpacket hashedData[];
    private SignatureSubpacket unhashedData[];
    private byte signatureEncoding[];
}
