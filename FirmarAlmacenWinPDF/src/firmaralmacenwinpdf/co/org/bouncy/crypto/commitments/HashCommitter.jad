// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HashCommitter.java

package co.org.bouncy.crypto.commitments;

import co.org.bouncy.crypto.*;
import co.org.bouncy.util.Arrays;
import java.security.SecureRandom;

public class HashCommitter
    implements Committer
{

    public HashCommitter(ExtendedDigest digest, SecureRandom random)
    {
        this.digest = digest;
        byteLength = digest.getByteLength();
        this.random = random;
    }

    public Commitment commit(byte message[])
    {
        if(message.length > byteLength / 2)
        {
            throw new DataLengthException("Message to be committed to too large for digest.");
        } else
        {
            byte w[] = new byte[byteLength - message.length];
            random.nextBytes(w);
            return new Commitment(w, calculateCommitment(w, message));
        }
    }

    public boolean isRevealed(Commitment commitment, byte message[])
    {
        byte calcCommitment[] = calculateCommitment(commitment.getSecret(), message);
        return Arrays.constantTimeAreEqual(commitment.getCommitment(), calcCommitment);
    }

    private byte[] calculateCommitment(byte w[], byte message[])
    {
        byte commitment[] = new byte[digest.getDigestSize()];
        digest.update(w, 0, w.length);
        digest.update(message, 0, message.length);
        digest.doFinal(commitment, 0);
        return commitment;
    }

    private final Digest digest;
    private final int byteLength;
    private final SecureRandom random;
}
