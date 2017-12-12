// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JPAKERound2Payload.java

package co.org.bouncy.crypto.agreement.jpake;

import co.org.bouncy.util.Arrays;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.agreement.jpake:
//            JPAKEUtil

public class JPAKERound2Payload
{

    public JPAKERound2Payload(String participantId, BigInteger a, BigInteger knowledgeProofForX2s[])
    {
        JPAKEUtil.validateNotNull(participantId, "participantId");
        JPAKEUtil.validateNotNull(a, "a");
        JPAKEUtil.validateNotNull(knowledgeProofForX2s, "knowledgeProofForX2s");
        this.participantId = participantId;
        this.a = a;
        this.knowledgeProofForX2s = Arrays.copyOf(knowledgeProofForX2s, knowledgeProofForX2s.length);
    }

    public String getParticipantId()
    {
        return participantId;
    }

    public BigInteger getA()
    {
        return a;
    }

    public BigInteger[] getKnowledgeProofForX2s()
    {
        return Arrays.copyOf(knowledgeProofForX2s, knowledgeProofForX2s.length);
    }

    private final String participantId;
    private final BigInteger a;
    private final BigInteger knowledgeProofForX2s[];
}
