// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKeyStoreSpi.java

package co.org.bouncy.jcajce.provider.keystore.bc;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.io.DigestInputStream;
import co.org.bouncy.crypto.io.DigestOutputStream;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.io.Streams;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.*;
import java.security.SecureRandom;
import java.util.Hashtable;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.keystore.bc:
//            BcKeyStoreSpi

public static class BcKeyStoreSpi$BouncyCastleStore extends BcKeyStoreSpi
{

    public void engineLoad(InputStream stream, char password[])
        throws IOException
    {
        table.clear();
        if(stream == null)
            return;
        DataInputStream dIn = new DataInputStream(stream);
        int version = dIn.readInt();
        if(version != 2 && version != 0 && version != 1)
            throw new IOException("Wrong version of key store.");
        byte salt[] = new byte[dIn.readInt()];
        if(salt.length != 20)
            throw new IOException("Key store corrupted.");
        dIn.readFully(salt);
        int iterationCount = dIn.readInt();
        if(iterationCount < 0 || iterationCount > 4096)
            throw new IOException("Key store corrupted.");
        String cipherAlg;
        if(version == 0)
            cipherAlg = "OldPBEWithSHAAndTwofish-CBC";
        else
            cipherAlg = "PBEWithSHAAndTwofish-CBC";
        Cipher cipher = makePBECipher(cipherAlg, 2, password, salt, iterationCount);
        CipherInputStream cIn = new CipherInputStream(dIn, cipher);
        Digest dig = new SHA1Digest();
        DigestInputStream dgIn = new DigestInputStream(cIn, dig);
        loadStore(dgIn);
        byte hash[] = new byte[dig.getDigestSize()];
        dig.doFinal(hash, 0);
        byte oldHash[] = new byte[dig.getDigestSize()];
        Streams.readFully(cIn, oldHash);
        if(!Arrays.constantTimeAreEqual(hash, oldHash))
        {
            table.clear();
            throw new IOException("KeyStore integrity check failed.");
        } else
        {
            return;
        }
    }

    public void engineStore(OutputStream stream, char password[])
        throws IOException
    {
        DataOutputStream dOut = new DataOutputStream(stream);
        byte salt[] = new byte[20];
        int iterationCount = 1024 + (random.nextInt() & 0x3ff);
        random.nextBytes(salt);
        dOut.writeInt(version);
        dOut.writeInt(salt.length);
        dOut.write(salt);
        dOut.writeInt(iterationCount);
        Cipher cipher = makePBECipher("PBEWithSHAAndTwofish-CBC", 1, password, salt, iterationCount);
        CipherOutputStream cOut = new CipherOutputStream(dOut, cipher);
        DigestOutputStream dgOut = new DigestOutputStream(new SHA1Digest());
        saveStore(new TeeOutputStream(cOut, dgOut));
        byte dig[] = dgOut.getDigest();
        cOut.write(dig);
        cOut.close();
    }

    public BcKeyStoreSpi$BouncyCastleStore()
    {
        super(1);
    }
}
