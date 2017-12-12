// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PublicKeyPacket.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, RSAPublicBCPGKey, DSAPublicBCPGKey, ElGamalPublicBCPGKey, 
//            BCPGOutputStream, BCPGObject, PublicKeyAlgorithmTags, BCPGInputStream, 
//            BCPGKey

public class PublicKeyPacket extends ContainedPacket
    implements PublicKeyAlgorithmTags
{

    PublicKeyPacket(BCPGInputStream in)
        throws IOException
    {
        version = in.read();
        time = (long)in.read() << 24 | (long)(in.read() << 16) | (long)(in.read() << 8) | (long)in.read();
        if(version <= 3)
            validDays = in.read() << 8 | in.read();
        algorithm = (byte)in.read();
        switch(algorithm)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            key = new RSAPublicBCPGKey(in);
            break;

        case 17: // '\021'
            key = new DSAPublicBCPGKey(in);
            break;

        case 16: // '\020'
        case 20: // '\024'
            key = new ElGamalPublicBCPGKey(in);
            break;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 18: // '\022'
        case 19: // '\023'
        default:
            throw new IOException("unknown PGP public key algorithm encountered");
        }
    }

    public PublicKeyPacket(int algorithm, Date time, BCPGKey key)
    {
        version = 4;
        this.time = time.getTime() / 1000L;
        this.algorithm = algorithm;
        this.key = key;
    }

    public int getVersion()
    {
        return version;
    }

    public int getAlgorithm()
    {
        return algorithm;
    }

    public int getValidDays()
    {
        return validDays;
    }

    public Date getTime()
    {
        return new Date(time * 1000L);
    }

    public BCPGKey getKey()
    {
        return key;
    }

    public byte[] getEncodedContents()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        BCPGOutputStream pOut = new BCPGOutputStream(bOut);
        pOut.write(version);
        pOut.write((byte)(int)(time >> 24));
        pOut.write((byte)(int)(time >> 16));
        pOut.write((byte)(int)(time >> 8));
        pOut.write((byte)(int)time);
        if(version <= 3)
        {
            pOut.write((byte)(validDays >> 8));
            pOut.write((byte)validDays);
        }
        pOut.write(algorithm);
        pOut.writeObject((BCPGObject)key);
        return bOut.toByteArray();
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(6, getEncodedContents(), true);
    }

    private int version;
    private long time;
    private int validDays;
    private int algorithm;
    private BCPGKey key;
}
