// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthEnvelopedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Set;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSUtils, CMSEnvelopedHelper, RecipientInformationStore, 
//            CMSSecureReadable

class CMSAuthEnvelopedData
{

    public CMSAuthEnvelopedData(byte authEnvData[])
        throws CMSException
    {
        this(CMSUtils.readContentInfo(authEnvData));
    }

    public CMSAuthEnvelopedData(InputStream authEnvData)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(authEnvData));
    }

    public CMSAuthEnvelopedData(ContentInfo contentInfo)
        throws CMSException
    {
        this.contentInfo = contentInfo;
        AuthEnvelopedData authEnvData = AuthEnvelopedData.getInstance(contentInfo.getContent());
        originator = authEnvData.getOriginatorInfo();
        ASN1Set recipientInfos = authEnvData.getRecipientInfos();
        EncryptedContentInfo authEncInfo = authEnvData.getAuthEncryptedContentInfo();
        authEncAlg = authEncInfo.getContentEncryptionAlgorithm();
        CMSSecureReadable secureReadable = new CMSSecureReadable() {

            public InputStream getInputStream()
                throws IOException, CMSException
            {
                return null;
            }

            final CMSAuthEnvelopedData this$0;

            
            {
                this$0 = CMSAuthEnvelopedData.this;
                super();
            }
        }
;
        recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, authEncAlg, secureReadable);
        authAttrs = authEnvData.getAuthAttrs();
        mac = authEnvData.getMac().getOctets();
        unauthAttrs = authEnvData.getUnauthAttrs();
    }

    RecipientInformationStore recipientInfoStore;
    ContentInfo contentInfo;
    private OriginatorInfo originator;
    private AlgorithmIdentifier authEncAlg;
    private ASN1Set authAttrs;
    private byte mac[];
    private ASN1Set unauthAttrs;
}
