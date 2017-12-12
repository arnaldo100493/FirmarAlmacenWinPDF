// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Layer.java

package co.org.bouncy.pqc.crypto.rainbow;

import co.org.bouncy.pqc.crypto.rainbow.util.GF2Field;
import co.org.bouncy.pqc.crypto.rainbow.util.RainbowUtil;
import co.org.bouncy.util.Arrays;
import java.security.SecureRandom;

public class Layer
{

    public Layer(byte vi, byte viNext, short coeffAlpha[][][], short coeffBeta[][][], short coeffGamma[][], short coeffEta[])
    {
        this.vi = vi & 0xff;
        this.viNext = viNext & 0xff;
        oi = this.viNext - this.vi;
        coeff_alpha = coeffAlpha;
        coeff_beta = coeffBeta;
        coeff_gamma = coeffGamma;
        coeff_eta = coeffEta;
    }

    public Layer(int vi, int viNext, SecureRandom sr)
    {
        this.vi = vi;
        this.viNext = viNext;
        oi = viNext - vi;
        coeff_alpha = new short[oi][oi][this.vi];
        coeff_beta = new short[oi][this.vi][this.vi];
        coeff_gamma = new short[oi][this.viNext];
        coeff_eta = new short[oi];
        int numOfPoly = oi;
        for(int k = 0; k < numOfPoly; k++)
        {
            for(int i = 0; i < oi; i++)
            {
                for(int j = 0; j < this.vi; j++)
                    coeff_alpha[k][i][j] = (short)(sr.nextInt() & 0xff);

            }

        }

        for(int k = 0; k < numOfPoly; k++)
        {
            for(int i = 0; i < this.vi; i++)
            {
                for(int j = 0; j < this.vi; j++)
                    coeff_beta[k][i][j] = (short)(sr.nextInt() & 0xff);

            }

        }

        for(int k = 0; k < numOfPoly; k++)
        {
            for(int i = 0; i < this.viNext; i++)
                coeff_gamma[k][i] = (short)(sr.nextInt() & 0xff);

        }

        for(int k = 0; k < numOfPoly; k++)
            coeff_eta[k] = (short)(sr.nextInt() & 0xff);

    }

    public short[][] plugInVinegars(short x[])
    {
        short tmpMult = 0;
        short coeff[][] = new short[oi][oi + 1];
        short sum[] = new short[oi];
        for(int k = 0; k < oi; k++)
        {
            for(int i = 0; i < vi; i++)
            {
                for(int j = 0; j < vi; j++)
                {
                    tmpMult = GF2Field.multElem(coeff_beta[k][i][j], x[i]);
                    tmpMult = GF2Field.multElem(tmpMult, x[j]);
                    sum[k] = GF2Field.addElem(sum[k], tmpMult);
                }

            }

        }

        for(int k = 0; k < oi; k++)
        {
            for(int i = 0; i < oi; i++)
            {
                for(int j = 0; j < vi; j++)
                {
                    tmpMult = GF2Field.multElem(coeff_alpha[k][i][j], x[j]);
                    coeff[k][i] = GF2Field.addElem(coeff[k][i], tmpMult);
                }

            }

        }

        for(int k = 0; k < oi; k++)
        {
            for(int i = 0; i < vi; i++)
            {
                tmpMult = GF2Field.multElem(coeff_gamma[k][i], x[i]);
                sum[k] = GF2Field.addElem(sum[k], tmpMult);
            }

        }

        for(int k = 0; k < oi; k++)
        {
            for(int i = vi; i < viNext; i++)
                coeff[k][i - vi] = GF2Field.addElem(coeff_gamma[k][i], coeff[k][i - vi]);

        }

        for(int k = 0; k < oi; k++)
            sum[k] = GF2Field.addElem(sum[k], coeff_eta[k]);

        for(int k = 0; k < oi; k++)
            coeff[k][oi] = sum[k];

        return coeff;
    }

    public int getVi()
    {
        return vi;
    }

    public int getViNext()
    {
        return viNext;
    }

    public int getOi()
    {
        return oi;
    }

    public short[][][] getCoeffAlpha()
    {
        return coeff_alpha;
    }

    public short[][][] getCoeffBeta()
    {
        return coeff_beta;
    }

    public short[][] getCoeffGamma()
    {
        return coeff_gamma;
    }

    public short[] getCoeffEta()
    {
        return coeff_eta;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof Layer))
        {
            return false;
        } else
        {
            Layer otherLayer = (Layer)other;
            return vi == otherLayer.getVi() && viNext == otherLayer.getViNext() && oi == otherLayer.getOi() && RainbowUtil.equals(coeff_alpha, otherLayer.getCoeffAlpha()) && RainbowUtil.equals(coeff_beta, otherLayer.getCoeffBeta()) && RainbowUtil.equals(coeff_gamma, otherLayer.getCoeffGamma()) && RainbowUtil.equals(coeff_eta, otherLayer.getCoeffEta());
        }
    }

    public int hashCode()
    {
        int hash = vi;
        hash = hash * 37 + viNext;
        hash = hash * 37 + oi;
        hash = hash * 37 + Arrays.hashCode(coeff_alpha);
        hash = hash * 37 + Arrays.hashCode(coeff_beta);
        hash = hash * 37 + Arrays.hashCode(coeff_gamma);
        hash = hash * 37 + Arrays.hashCode(coeff_eta);
        return hash;
    }

    private int vi;
    private int viNext;
    private int oi;
    private short coeff_alpha[][][];
    private short coeff_beta[][][];
    private short coeff_gamma[][];
    private short coeff_eta[];
}
