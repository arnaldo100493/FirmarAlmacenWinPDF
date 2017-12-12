// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreeRecipientInfoGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.GenericKey;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, RecipientInfoGenerator

public abstract class KeyAgreeRecipientInfoGenerator
    implements RecipientInfoGenerator
{

    protected KeyAgreeRecipientInfoGenerator(ASN1ObjectIdentifier keyAgreementOID, SubjectPublicKeyInfo originatorKeyInfo, ASN1ObjectIdentifier keyEncryptionOID)
    {
        this.originatorKeyInfo = originatorKeyInfo;
        this.keyAgreementOID = keyAgreementOID;
        this.keyEncryptionOID = keyEncryptionOID;
    }

    public RecipientInfo generate(GenericKey contentEncryptionKey)
        throws CMSException
    {
        OriginatorIdentifierOrKey originator = new OriginatorIdentifierOrKey(createOriginatorPublicKey(originatorKeyInfo));
        ASN1EncodableVector params = new ASN1EncodableVector();
        params.add(keyEncryptionOID);
        params.add(DERNull.INSTANCE);
        AlgorithmIdentifier keyEncAlg = new AlgorithmIdentifier(keyEncryptionOID, DERNull.INSTANCE);
        AlgorithmIdentifier keyAgreeAlg = new AlgorithmIdentifier(keyAgreementOID, keyEncAlg);
        ASN1Sequence recipients = generateRecipientEncryptedKeys(keyAgreeAlg, keyEncAlg, contentEncryptionKey);
        ASN1Encodable userKeyingMaterial = getUserKeyingMaterial(keyAgreeAlg);
        if(userKeyingMaterial != null)
            try
            {
                return new RecipientInfo(new KeyAgreeRecipientInfo(originator, new DEROctetString(userKeyingMaterial), keyAgreeAlg, recipients));
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("unable to encode userKeyingMaterial: ").append(e.getMessage()).toString(), e);
            }
        else
            return new RecipientInfo(new KeyAgreeRecipientInfo(originator, null, keyAgreeAlg, recipients));
    }

    protected OriginatorPublicKey createOriginatorPublicKey(SubjectPublicKeyInfo originatorKeyInfo)
    {
        return new OriginatorPublicKey(new AlgorithmIdentifier(originatorKeyInfo.getAlgorithm().getAlgorithm(), DERNull.INSTANCE), originatorKeyInfo.getPublicKeyData().getBytes());
    }

    protected abstract ASN1Sequence generateRecipientEncryptedKeys(AlgorithmIdentifier algorithmidentifier, AlgorithmIdentifier algorithmidentifier1, GenericKey generickey)
        throws CMSException;

    protected abstract ASN1Encodable getUserKeyingMaterial(AlgorithmIdentifier algorithmidentifier)
        throws CMSException;

    private ASN1ObjectIdentifier keyAgreementOID;
    private ASN1ObjectIdentifier keyEncryptionOID;
    private SubjectPublicKeyInfo originatorKeyInfo;
}
