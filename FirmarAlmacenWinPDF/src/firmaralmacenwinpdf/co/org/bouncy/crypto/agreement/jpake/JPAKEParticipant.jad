// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JPAKEParticipant.java

package co.org.bouncy.crypto.agreement.jpake;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.agreement.jpake:
//            JPAKERound1Payload, JPAKERound2Payload, JPAKERound3Payload, JPAKEPrimeOrderGroup, 
//            JPAKEPrimeOrderGroups, JPAKEUtil

public class JPAKEParticipant
{

    public JPAKEParticipant(String participantId, char password[])
    {
        this(participantId, password, JPAKEPrimeOrderGroups.NIST_3072);
    }

    public JPAKEParticipant(String participantId, char password[], JPAKEPrimeOrderGroup group)
    {
        this(participantId, password, group, ((Digest) (new SHA256Digest())), new SecureRandom());
    }

    public JPAKEParticipant(String participantId, char password[], JPAKEPrimeOrderGroup group, Digest digest, SecureRandom random)
    {
        JPAKEUtil.validateNotNull(participantId, "participantId");
        JPAKEUtil.validateNotNull(password, "password");
        JPAKEUtil.validateNotNull(group, "p");
        JPAKEUtil.validateNotNull(digest, "digest");
        JPAKEUtil.validateNotNull(random, "random");
        if(password.length == 0)
        {
            throw new IllegalArgumentException("Password must not be empty.");
        } else
        {
            this.participantId = participantId;
            this.password = Arrays.copyOf(password, password.length);
            p = group.getP();
            q = group.getQ();
            g = group.getG();
            this.digest = digest;
            this.random = random;
            state = 0;
            return;
        }
    }

    public int getState()
    {
        return state;
    }

    public JPAKERound1Payload createRound1PayloadToSend()
    {
        if(state >= 10)
        {
            throw new IllegalStateException((new StringBuilder()).append("Round1 payload already created for ").append(participantId).toString());
        } else
        {
            x1 = JPAKEUtil.generateX1(q, random);
            x2 = JPAKEUtil.generateX2(q, random);
            gx1 = JPAKEUtil.calculateGx(p, g, x1);
            gx2 = JPAKEUtil.calculateGx(p, g, x2);
            BigInteger knowledgeProofForX1[] = JPAKEUtil.calculateZeroKnowledgeProof(p, q, g, gx1, x1, participantId, digest, random);
            BigInteger knowledgeProofForX2[] = JPAKEUtil.calculateZeroKnowledgeProof(p, q, g, gx2, x2, participantId, digest, random);
            state = 10;
            return new JPAKERound1Payload(participantId, gx1, gx2, knowledgeProofForX1, knowledgeProofForX2);
        }
    }

    public void validateRound1PayloadReceived(JPAKERound1Payload round1PayloadReceived)
        throws CryptoException
    {
        if(state >= 20)
        {
            throw new IllegalStateException((new StringBuilder()).append("Validation already attempted for round1 payload for").append(participantId).toString());
        } else
        {
            partnerParticipantId = round1PayloadReceived.getParticipantId();
            gx3 = round1PayloadReceived.getGx1();
            gx4 = round1PayloadReceived.getGx2();
            BigInteger knowledgeProofForX3[] = round1PayloadReceived.getKnowledgeProofForX1();
            BigInteger knowledgeProofForX4[] = round1PayloadReceived.getKnowledgeProofForX2();
            JPAKEUtil.validateParticipantIdsDiffer(participantId, round1PayloadReceived.getParticipantId());
            JPAKEUtil.validateGx4(gx4);
            JPAKEUtil.validateZeroKnowledgeProof(p, q, g, gx3, knowledgeProofForX3, round1PayloadReceived.getParticipantId(), digest);
            JPAKEUtil.validateZeroKnowledgeProof(p, q, g, gx4, knowledgeProofForX4, round1PayloadReceived.getParticipantId(), digest);
            state = 20;
            return;
        }
    }

    public JPAKERound2Payload createRound2PayloadToSend()
    {
        if(state >= 30)
            throw new IllegalStateException((new StringBuilder()).append("Round2 payload already created for ").append(participantId).toString());
        if(state < 20)
        {
            throw new IllegalStateException((new StringBuilder()).append("Round1 payload must be validated prior to creating Round2 payload for ").append(participantId).toString());
        } else
        {
            BigInteger gA = JPAKEUtil.calculateGA(p, gx1, gx3, gx4);
            BigInteger s = JPAKEUtil.calculateS(password);
            BigInteger x2s = JPAKEUtil.calculateX2s(q, x2, s);
            BigInteger A = JPAKEUtil.calculateA(p, q, gA, x2s);
            BigInteger knowledgeProofForX2s[] = JPAKEUtil.calculateZeroKnowledgeProof(p, q, gA, A, x2s, participantId, digest, random);
            state = 30;
            return new JPAKERound2Payload(participantId, A, knowledgeProofForX2s);
        }
    }

    public void validateRound2PayloadReceived(JPAKERound2Payload round2PayloadReceived)
        throws CryptoException
    {
        if(state >= 40)
            throw new IllegalStateException((new StringBuilder()).append("Validation already attempted for round2 payload for").append(participantId).toString());
        if(state < 20)
        {
            throw new IllegalStateException((new StringBuilder()).append("Round1 payload must be validated prior to validating Round2 payload for ").append(participantId).toString());
        } else
        {
            BigInteger gB = JPAKEUtil.calculateGA(p, gx3, gx1, gx2);
            b = round2PayloadReceived.getA();
            BigInteger knowledgeProofForX4s[] = round2PayloadReceived.getKnowledgeProofForX2s();
            JPAKEUtil.validateParticipantIdsDiffer(participantId, round2PayloadReceived.getParticipantId());
            JPAKEUtil.validateParticipantIdsEqual(partnerParticipantId, round2PayloadReceived.getParticipantId());
            JPAKEUtil.validateGa(gB);
            JPAKEUtil.validateZeroKnowledgeProof(p, q, gB, b, knowledgeProofForX4s, round2PayloadReceived.getParticipantId(), digest);
            state = 40;
            return;
        }
    }

    public BigInteger calculateKeyingMaterial()
    {
        if(state >= 50)
            throw new IllegalStateException((new StringBuilder()).append("Key already calculated for ").append(participantId).toString());
        if(state < 40)
        {
            throw new IllegalStateException((new StringBuilder()).append("Round2 payload must be validated prior to creating key for ").append(participantId).toString());
        } else
        {
            BigInteger s = JPAKEUtil.calculateS(password);
            Arrays.fill(password, '\0');
            password = null;
            BigInteger keyingMaterial = JPAKEUtil.calculateKeyingMaterial(p, q, gx4, x2, s, b);
            x1 = null;
            x2 = null;
            b = null;
            state = 50;
            return keyingMaterial;
        }
    }

    public JPAKERound3Payload createRound3PayloadToSend(BigInteger keyingMaterial)
    {
        if(state >= 60)
            throw new IllegalStateException((new StringBuilder()).append("Round3 payload already created for ").append(participantId).toString());
        if(state < 50)
        {
            throw new IllegalStateException((new StringBuilder()).append("Keying material must be calculated prior to creating Round3 payload for ").append(participantId).toString());
        } else
        {
            BigInteger macTag = JPAKEUtil.calculateMacTag(participantId, partnerParticipantId, gx1, gx2, gx3, gx4, keyingMaterial, digest);
            state = 60;
            return new JPAKERound3Payload(participantId, macTag);
        }
    }

    public void validateRound3PayloadReceived(JPAKERound3Payload round3PayloadReceived, BigInteger keyingMaterial)
        throws CryptoException
    {
        if(state >= 70)
            throw new IllegalStateException((new StringBuilder()).append("Validation already attempted for round3 payload for").append(participantId).toString());
        if(state < 50)
        {
            throw new IllegalStateException((new StringBuilder()).append("Keying material must be calculated validated prior to validating Round3 payload for ").append(participantId).toString());
        } else
        {
            JPAKEUtil.validateParticipantIdsDiffer(participantId, round3PayloadReceived.getParticipantId());
            JPAKEUtil.validateParticipantIdsEqual(partnerParticipantId, round3PayloadReceived.getParticipantId());
            JPAKEUtil.validateMacTag(participantId, partnerParticipantId, gx1, gx2, gx3, gx4, keyingMaterial, digest, round3PayloadReceived.getMacTag());
            gx1 = null;
            gx2 = null;
            gx3 = null;
            gx4 = null;
            state = 70;
            return;
        }
    }

    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_ROUND_1_CREATED = 10;
    public static final int STATE_ROUND_1_VALIDATED = 20;
    public static final int STATE_ROUND_2_CREATED = 30;
    public static final int STATE_ROUND_2_VALIDATED = 40;
    public static final int STATE_KEY_CALCULATED = 50;
    public static final int STATE_ROUND_3_CREATED = 60;
    public static final int STATE_ROUND_3_VALIDATED = 70;
    private final String participantId;
    private char password[];
    private final Digest digest;
    private final SecureRandom random;
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger g;
    private String partnerParticipantId;
    private BigInteger x1;
    private BigInteger x2;
    private BigInteger gx1;
    private BigInteger gx2;
    private BigInteger gx3;
    private BigInteger gx4;
    private BigInteger b;
    private int state;
}
