// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModularResultant.java

package co.org.bouncy.pqc.math.ntru.polynomial;

import co.org.bouncy.pqc.math.ntru.euclid.BigIntEuclidean;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.pqc.math.ntru.polynomial:
//            Resultant, BigIntPolynomial

public class ModularResultant extends Resultant
{

    ModularResultant(BigIntPolynomial rho, BigInteger res, BigInteger modulus)
    {
        super(rho, res);
        this.modulus = modulus;
    }

    static ModularResultant combineRho(ModularResultant modRes1, ModularResultant modRes2)
    {
        BigInteger mod1 = modRes1.modulus;
        BigInteger mod2 = modRes2.modulus;
        BigInteger prod = mod1.multiply(mod2);
        BigIntEuclidean er = BigIntEuclidean.calculate(mod2, mod1);
        BigIntPolynomial rho1 = (BigIntPolynomial)modRes1.rho.clone();
        rho1.mult(er.x.multiply(mod2));
        BigIntPolynomial rho2 = (BigIntPolynomial)modRes2.rho.clone();
        rho2.mult(er.y.multiply(mod1));
        rho1.add(rho2);
        rho1.mod(prod);
        return new ModularResultant(rho1, null, prod);
    }

    BigInteger modulus;
}
