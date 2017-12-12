// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PublicKeyKeyEncryptionMethodGenerator.java

package co.org.bouncy.openpgp.operator;

import co.org.bouncy.bcpg.ContainedPacket;
import co.org.bouncy.bcpg.PublicKeyEncSessionPacket;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPublicKey;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.openpgp.operator:
//            PGPKeyEncryptionMethodGenerator

public abstract class PublicKeyKeyEncryptionMethodGenerator extends PGPKeyEncryptionMethodGenerator
{

    protected PublicKeyKeyEncryptionMethodGenerator(PGPPublicKey pubKey)
    {
        this.pubKey = pubKey;
        switch(pubKey.getAlgorithm())
        {
        case 17: // '\021'
            throw new IllegalArgumentException("Can't use DSA for encryption.");

        case 19: // '\023'
            throw new IllegalArgumentException("Can't use ECDSA for encryption.");

        case 3: // '\003'
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
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("unknown asymmetric algorithm: ").append(pubKey.getAlgorithm()).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 16: // '\020'
        case 20: // '\024'
            return;
        }
    }

    public BigInteger[] processSessionInfo(byte encryptedSessionInfo[])
        throws PGPException
    {
        BigInteger data[];
        switch(pubKey.getAlgorithm())
        {
        case 1: // '\001'
        case 2: // '\002'
            data = new BigInteger[1];
            data[0] = new BigInteger(1, encryptedSessionInfo);
            break;

        case 16: // '\020'
        case 20: // '\024'
            byte b1[] = new byte[encryptedSessionInfo.length / 2];
            byte b2[] = new byte[encryptedSessionInfo.length / 2];
            System.arraycopy(encryptedSessionInfo, 0, b1, 0, b1.length);
            System.arraycopy(encryptedSessionInfo, b1.length, b2, 0, b2.length);
            data = new BigInteger[2];
            data[0] = new BigInteger(1, b1);
            data[1] = new BigInteger(1, b2);
            break;

        default:
            throw new PGPException((new StringBuilder()).append("unknown asymmetric algorithm: ").append(pubKey.getAlgorithm()).toString());
        }
        return data;
    }

    public ContainedPacket generate(int encAlgorithm, byte sessionInfo[])
        throws PGPException
    {
        return new PublicKeyEncSessionPacket(pubKey.getKeyID(), pubKey.getAlgorithm(), processSessionInfo(encryptSessionInfo(pubKey, sessionInfo)));
    }

    protected abstract byte[] encryptSessionInfo(PGPPublicKey pgppublickey, byte abyte0[])
        throws PGPException;

    private PGPPublicKey pubKey;
}
