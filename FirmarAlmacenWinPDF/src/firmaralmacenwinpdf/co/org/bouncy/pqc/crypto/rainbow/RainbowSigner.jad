// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowSigner.java

package co.org.bouncy.pqc.crypto.rainbow;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.MessageSigner;
import co.org.bouncy.pqc.crypto.rainbow.util.ComputeInField;
import co.org.bouncy.pqc.crypto.rainbow.util.GF2Field;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.rainbow:
//            RainbowPrivateKeyParameters, RainbowPublicKeyParameters, RainbowKeyParameters, Layer

public class RainbowSigner
    implements MessageSigner
{

    public RainbowSigner()
    {
        cf = new ComputeInField();
    }

    public void init(boolean forSigning, CipherParameters param)
    {
        if(forSigning)
        {
            if(param instanceof ParametersWithRandom)
            {
                ParametersWithRandom rParam = (ParametersWithRandom)param;
                random = rParam.getRandom();
                key = (RainbowPrivateKeyParameters)rParam.getParameters();
            } else
            {
                random = new SecureRandom();
                key = (RainbowPrivateKeyParameters)param;
            }
        } else
        {
            key = (RainbowPublicKeyParameters)param;
        }
        signableDocumentLength = key.getDocLength();
    }

    private short[] initSign(Layer layer[], short msg[])
    {
        short tmpVec[] = new short[msg.length];
        tmpVec = cf.addVect(((RainbowPrivateKeyParameters)key).getB1(), msg);
        short Y_[] = cf.multiplyMatrix(((RainbowPrivateKeyParameters)key).getInvA1(), tmpVec);
        for(int i = 0; i < layer[0].getVi(); i++)
        {
            x[i] = (short)random.nextInt();
            x[i] = (short)(x[i] & 0xff);
        }

        return Y_;
    }

    public byte[] generateSignature(byte message[])
    {
        Layer layer[] = ((RainbowPrivateKeyParameters)key).getLayers();
        int numberOfLayers = layer.length;
        x = new short[((RainbowPrivateKeyParameters)key).getInvA2().length];
        byte S[] = new byte[layer[numberOfLayers - 1].getViNext()];
        short msgHashVals[] = makeMessageRepresentative(message);
        boolean ok;
        do
        {
            ok = true;
            int counter = 0;
            try
            {
                short Y_[] = initSign(layer, msgHashVals);
                for(int i = 0; i < numberOfLayers; i++)
                {
                    short y_i[] = new short[layer[i].getOi()];
                    short solVec[] = new short[layer[i].getOi()];
                    for(int k = 0; k < layer[i].getOi(); k++)
                    {
                        y_i[k] = Y_[counter];
                        counter++;
                    }

                    solVec = cf.solveEquation(layer[i].plugInVinegars(x), y_i);
                    if(solVec == null)
                        throw new Exception("LES is not solveable!");
                    for(int j = 0; j < solVec.length; j++)
                        x[layer[i].getVi() + j] = solVec[j];

                }

                short tmpVec[] = cf.addVect(((RainbowPrivateKeyParameters)key).getB2(), x);
                short signature[] = cf.multiplyMatrix(((RainbowPrivateKeyParameters)key).getInvA2(), tmpVec);
                for(int i = 0; i < S.length; i++)
                    S[i] = (byte)signature[i];

            }
            catch(Exception se)
            {
                ok = false;
            }
        } while(!ok);
        return S;
    }

    public boolean verifySignature(byte message[], byte signature[])
    {
        short sigInt[] = new short[signature.length];
        for(int i = 0; i < signature.length; i++)
        {
            short tmp = signature[i];
            tmp &= 0xff;
            sigInt[i] = tmp;
        }

        short msgHashVal[] = makeMessageRepresentative(message);
        short verificationResult[] = verifySignatureIntern(sigInt);
        boolean verified = true;
        if(msgHashVal.length != verificationResult.length)
            return false;
        for(int i = 0; i < msgHashVal.length; i++)
            verified = verified && msgHashVal[i] == verificationResult[i];

        return verified;
    }

    private short[] verifySignatureIntern(short signature[])
    {
        short coeff_quadratic[][] = ((RainbowPublicKeyParameters)key).getCoeffQuadratic();
        short coeff_singular[][] = ((RainbowPublicKeyParameters)key).getCoeffSingular();
        short coeff_scalar[] = ((RainbowPublicKeyParameters)key).getCoeffScalar();
        short rslt[] = new short[coeff_quadratic.length];
        int n = coeff_singular[0].length;
        int offset = 0;
        short tmp = 0;
        for(int p = 0; p < coeff_quadratic.length; p++)
        {
            offset = 0;
            for(int x = 0; x < n; x++)
            {
                for(int y = x; y < n; y++)
                {
                    tmp = GF2Field.multElem(coeff_quadratic[p][offset], GF2Field.multElem(signature[x], signature[y]));
                    rslt[p] = GF2Field.addElem(rslt[p], tmp);
                    offset++;
                }

                tmp = GF2Field.multElem(coeff_singular[p][x], signature[x]);
                rslt[p] = GF2Field.addElem(rslt[p], tmp);
            }

            rslt[p] = GF2Field.addElem(rslt[p], coeff_scalar[p]);
        }

        return rslt;
    }

    private short[] makeMessageRepresentative(byte message[])
    {
        short output[] = new short[signableDocumentLength];
        int h = 0;
        int i = 0;
        do
        {
            if(i >= message.length)
                break;
            output[i] = message[h];
            output[i] &= 0xff;
            h++;
        } while(++i < output.length);
        return output;
    }

    private SecureRandom random;
    int signableDocumentLength;
    private short x[];
    private ComputeInField cf;
    RainbowKeyParameters key;
}
