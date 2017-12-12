// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3410ParameterSpec.java

package co.org.bouncy.jce.spec;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cryptopro.*;
import co.org.bouncy.jce.interfaces.GOST3410Params;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.jce.spec:
//            GOST3410PublicKeyParameterSetSpec

public class GOST3410ParameterSpec
    implements AlgorithmParameterSpec, GOST3410Params
{

    public GOST3410ParameterSpec(String keyParamSetID, String digestParamSetOID, String encryptionParamSetOID)
    {
        GOST3410ParamSetParameters ecP = null;
        try
        {
            ecP = GOST3410NamedParameters.getByOID(new ASN1ObjectIdentifier(keyParamSetID));
        }
        catch(IllegalArgumentException e)
        {
            ASN1ObjectIdentifier oid = GOST3410NamedParameters.getOID(keyParamSetID);
            if(oid != null)
            {
                keyParamSetID = oid.getId();
                ecP = GOST3410NamedParameters.getByOID(oid);
            }
        }
        if(ecP == null)
        {
            throw new IllegalArgumentException("no key parameter set for passed in name/OID.");
        } else
        {
            keyParameters = new GOST3410PublicKeyParameterSetSpec(ecP.getP(), ecP.getQ(), ecP.getA());
            keyParamSetOID = keyParamSetID;
            this.digestParamSetOID = digestParamSetOID;
            this.encryptionParamSetOID = encryptionParamSetOID;
            return;
        }
    }

    public GOST3410ParameterSpec(String keyParamSetID, String digestParamSetOID)
    {
        this(keyParamSetID, digestParamSetOID, null);
    }

    public GOST3410ParameterSpec(String keyParamSetID)
    {
        this(keyParamSetID, CryptoProObjectIdentifiers.gostR3411_94_CryptoProParamSet.getId(), null);
    }

    public GOST3410ParameterSpec(GOST3410PublicKeyParameterSetSpec spec)
    {
        keyParameters = spec;
        digestParamSetOID = CryptoProObjectIdentifiers.gostR3411_94_CryptoProParamSet.getId();
        encryptionParamSetOID = null;
    }

    public String getPublicKeyParamSetOID()
    {
        return keyParamSetOID;
    }

    public GOST3410PublicKeyParameterSetSpec getPublicKeyParameters()
    {
        return keyParameters;
    }

    public String getDigestParamSetOID()
    {
        return digestParamSetOID;
    }

    public String getEncryptionParamSetOID()
    {
        return encryptionParamSetOID;
    }

    public boolean equals(Object o)
    {
        if(o instanceof GOST3410ParameterSpec)
        {
            GOST3410ParameterSpec other = (GOST3410ParameterSpec)o;
            return keyParameters.equals(other.keyParameters) && digestParamSetOID.equals(other.digestParamSetOID) && (encryptionParamSetOID == other.encryptionParamSetOID || encryptionParamSetOID != null && encryptionParamSetOID.equals(other.encryptionParamSetOID));
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return keyParameters.hashCode() ^ digestParamSetOID.hashCode() ^ (encryptionParamSetOID == null ? 0 : encryptionParamSetOID.hashCode());
    }

    public static GOST3410ParameterSpec fromPublicKeyAlg(GOST3410PublicKeyAlgParameters params)
    {
        if(params.getEncryptionParamSet() != null)
            return new GOST3410ParameterSpec(params.getPublicKeyParamSet().getId(), params.getDigestParamSet().getId(), params.getEncryptionParamSet().getId());
        else
            return new GOST3410ParameterSpec(params.getPublicKeyParamSet().getId(), params.getDigestParamSet().getId());
    }

    private GOST3410PublicKeyParameterSetSpec keyParameters;
    private String keyParamSetOID;
    private String digestParamSetOID;
    private String encryptionParamSetOID;
}
