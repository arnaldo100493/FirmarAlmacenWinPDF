// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCGMSSPublicKey.java

package co.org.bouncy.pqc.jcajce.provider.gmss;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.pqc.asn1.*;
import co.org.bouncy.pqc.crypto.gmss.GMSSParameters;
import co.org.bouncy.pqc.crypto.gmss.GMSSPublicKeyParameters;
import co.org.bouncy.pqc.jcajce.provider.util.KeyUtil;
import co.org.bouncy.pqc.jcajce.spec.GMSSPublicKeySpec;
import co.org.bouncy.util.encoders.Hex;
import java.security.PublicKey;

public class BCGMSSPublicKey
    implements CipherParameters, PublicKey
{

    public BCGMSSPublicKey(byte pub[], GMSSParameters gmssParameterSet)
    {
        this.gmssParameterSet = gmssParameterSet;
        publicKeyBytes = pub;
    }

    protected BCGMSSPublicKey(GMSSPublicKeySpec keySpec)
    {
        this(keySpec.getPublicKey(), keySpec.getParameters());
    }

    public BCGMSSPublicKey(GMSSPublicKeyParameters params)
    {
        this(params.getPublicKey(), params.getParameters());
    }

    public String getAlgorithm()
    {
        return "GMSS";
    }

    public byte[] getPublicKeyBytes()
    {
        return publicKeyBytes;
    }

    public GMSSParameters getParameterSet()
    {
        return gmssParameterSet;
    }

    public String toString()
    {
        String out = (new StringBuilder()).append("GMSS public key : ").append(new String(Hex.encode(publicKeyBytes))).append("\n").append("Height of Trees: \n").toString();
        for(int i = 0; i < gmssParameterSet.getHeightOfTrees().length; i++)
            out = (new StringBuilder()).append(out).append("Layer ").append(i).append(" : ").append(gmssParameterSet.getHeightOfTrees()[i]).append(" WinternitzParameter: ").append(gmssParameterSet.getWinternitzParameter()[i]).append(" K: ").append(gmssParameterSet.getK()[i]).append("\n").toString();

        return out;
    }

    public byte[] getEncoded()
    {
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.gmss, (new ParSet(gmssParameterSet.getNumOfLayers(), gmssParameterSet.getHeightOfTrees(), gmssParameterSet.getWinternitzParameter(), gmssParameterSet.getK())).toASN1Primitive()), new GMSSPublicKey(publicKeyBytes));
    }

    public String getFormat()
    {
        return "X.509";
    }

    private static final long serialVersionUID = 1L;
    private byte publicKeyBytes[];
    private GMSSParameters gmssParameterSet;
    private GMSSParameters gmssParams;
}
