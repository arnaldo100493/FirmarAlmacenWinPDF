// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitiesToUnicode.java

package co.com.pdf.text.xml.simpleparser;

import java.util.HashMap;
import java.util.Map;

public class EntitiesToUnicode
{

    public EntitiesToUnicode()
    {
    }

    public static char decodeEntity(String name)
    {
        if(name.startsWith("#x"))
            try
            {
                return (char)Integer.parseInt(name.substring(2), 16);
            }
            catch(NumberFormatException nfe)
            {
                return '\0';
            }
        if(name.startsWith("#"))
            try
            {
                return (char)Integer.parseInt(name.substring(1));
            }
            catch(NumberFormatException nfe)
            {
                return '\0';
            }
        Character c = (Character)MAP.get(name);
        if(c == null)
            return '\0';
        else
            return c.charValue();
    }

    public static String decodeString(String s)
    {
        int pos_amp = s.indexOf('&');
        if(pos_amp == -1)
            return s;
        StringBuffer buf = new StringBuffer(s.substring(0, pos_amp));
        do
        {
            int pos_sc = s.indexOf(';', pos_amp);
            if(pos_sc == -1)
            {
                buf.append(s.substring(pos_amp));
                return buf.toString();
            }
            for(int pos_a = s.indexOf('&', pos_amp + 1); pos_a != -1 && pos_a < pos_sc; pos_a = s.indexOf('&', pos_amp + 1))
            {
                buf.append(s.substring(pos_amp, pos_a));
                pos_amp = pos_a;
            }

            char replace = decodeEntity(s.substring(pos_amp + 1, pos_sc));
            if(s.length() < pos_sc + 1)
                return buf.toString();
            if(replace == 0)
                buf.append(s.substring(pos_amp, pos_sc + 1));
            else
                buf.append(replace);
            pos_amp = s.indexOf('&', pos_sc);
            if(pos_amp == -1)
            {
                buf.append(s.substring(pos_sc + 1));
                return buf.toString();
            }
            buf.append(s.substring(pos_sc + 1, pos_amp));
        } while(true);
    }

    private static final Map MAP;

    static 
    {
        MAP = new HashMap();
        MAP.put("nbsp", Character.valueOf('\240'));
        MAP.put("iexcl", Character.valueOf('\241'));
        MAP.put("cent", Character.valueOf('\242'));
        MAP.put("pound", Character.valueOf('\243'));
        MAP.put("curren", Character.valueOf('\244'));
        MAP.put("yen", Character.valueOf('\245'));
        MAP.put("brvbar", Character.valueOf('\246'));
        MAP.put("sect", Character.valueOf('\247'));
        MAP.put("uml", Character.valueOf('\250'));
        MAP.put("copy", Character.valueOf('\251'));
        MAP.put("ordf", Character.valueOf('\252'));
        MAP.put("laquo", Character.valueOf('\253'));
        MAP.put("not", Character.valueOf('\254'));
        MAP.put("shy", Character.valueOf('\255'));
        MAP.put("reg", Character.valueOf('\256'));
        MAP.put("macr", Character.valueOf('\257'));
        MAP.put("deg", Character.valueOf('\260'));
        MAP.put("plusmn", Character.valueOf('\261'));
        MAP.put("sup2", Character.valueOf('\262'));
        MAP.put("sup3", Character.valueOf('\263'));
        MAP.put("acute", Character.valueOf('\264'));
        MAP.put("micro", Character.valueOf('\265'));
        MAP.put("para", Character.valueOf('\266'));
        MAP.put("middot", Character.valueOf('\267'));
        MAP.put("cedil", Character.valueOf('\270'));
        MAP.put("sup1", Character.valueOf('\271'));
        MAP.put("ordm", Character.valueOf('\272'));
        MAP.put("raquo", Character.valueOf('\273'));
        MAP.put("frac14", Character.valueOf('\274'));
        MAP.put("frac12", Character.valueOf('\275'));
        MAP.put("frac34", Character.valueOf('\276'));
        MAP.put("iquest", Character.valueOf('\277'));
        MAP.put("Agrave", Character.valueOf('\300'));
        MAP.put("Aacute", Character.valueOf('\301'));
        MAP.put("Acirc", Character.valueOf('\302'));
        MAP.put("Atilde", Character.valueOf('\303'));
        MAP.put("Auml", Character.valueOf('\304'));
        MAP.put("Aring", Character.valueOf('\305'));
        MAP.put("AElig", Character.valueOf('\306'));
        MAP.put("Ccedil", Character.valueOf('\307'));
        MAP.put("Egrave", Character.valueOf('\310'));
        MAP.put("Eacute", Character.valueOf('\311'));
        MAP.put("Ecirc", Character.valueOf('\312'));
        MAP.put("Euml", Character.valueOf('\313'));
        MAP.put("Igrave", Character.valueOf('\314'));
        MAP.put("Iacute", Character.valueOf('\315'));
        MAP.put("Icirc", Character.valueOf('\316'));
        MAP.put("Iuml", Character.valueOf('\317'));
        MAP.put("ETH", Character.valueOf('\320'));
        MAP.put("Ntilde", Character.valueOf('\321'));
        MAP.put("Ograve", Character.valueOf('\322'));
        MAP.put("Oacute", Character.valueOf('\323'));
        MAP.put("Ocirc", Character.valueOf('\324'));
        MAP.put("Otilde", Character.valueOf('\325'));
        MAP.put("Ouml", Character.valueOf('\326'));
        MAP.put("times", Character.valueOf('\327'));
        MAP.put("Oslash", Character.valueOf('\330'));
        MAP.put("Ugrave", Character.valueOf('\331'));
        MAP.put("Uacute", Character.valueOf('\332'));
        MAP.put("Ucirc", Character.valueOf('\333'));
        MAP.put("Uuml", Character.valueOf('\334'));
        MAP.put("Yacute", Character.valueOf('\335'));
        MAP.put("THORN", Character.valueOf('\336'));
        MAP.put("szlig", Character.valueOf('\337'));
        MAP.put("agrave", Character.valueOf('\340'));
        MAP.put("aacute", Character.valueOf('\341'));
        MAP.put("acirc", Character.valueOf('\342'));
        MAP.put("atilde", Character.valueOf('\343'));
        MAP.put("auml", Character.valueOf('\344'));
        MAP.put("aring", Character.valueOf('\345'));
        MAP.put("aelig", Character.valueOf('\346'));
        MAP.put("ccedil", Character.valueOf('\347'));
        MAP.put("egrave", Character.valueOf('\350'));
        MAP.put("eacute", Character.valueOf('\351'));
        MAP.put("ecirc", Character.valueOf('\352'));
        MAP.put("euml", Character.valueOf('\353'));
        MAP.put("igrave", Character.valueOf('\354'));
        MAP.put("iacute", Character.valueOf('\355'));
        MAP.put("icirc", Character.valueOf('\356'));
        MAP.put("iuml", Character.valueOf('\357'));
        MAP.put("eth", Character.valueOf('\360'));
        MAP.put("ntilde", Character.valueOf('\361'));
        MAP.put("ograve", Character.valueOf('\362'));
        MAP.put("oacute", Character.valueOf('\363'));
        MAP.put("ocirc", Character.valueOf('\364'));
        MAP.put("otilde", Character.valueOf('\365'));
        MAP.put("ouml", Character.valueOf('\366'));
        MAP.put("divide", Character.valueOf('\367'));
        MAP.put("oslash", Character.valueOf('\370'));
        MAP.put("ugrave", Character.valueOf('\371'));
        MAP.put("uacute", Character.valueOf('\372'));
        MAP.put("ucirc", Character.valueOf('\373'));
        MAP.put("uuml", Character.valueOf('\374'));
        MAP.put("yacute", Character.valueOf('\375'));
        MAP.put("thorn", Character.valueOf('\376'));
        MAP.put("yuml", Character.valueOf('\377'));
        MAP.put("fnof", Character.valueOf('\u0192'));
        MAP.put("Alpha", Character.valueOf('\u0391'));
        MAP.put("Beta", Character.valueOf('\u0392'));
        MAP.put("Gamma", Character.valueOf('\u0393'));
        MAP.put("Delta", Character.valueOf('\u0394'));
        MAP.put("Epsilon", Character.valueOf('\u0395'));
        MAP.put("Zeta", Character.valueOf('\u0396'));
        MAP.put("Eta", Character.valueOf('\u0397'));
        MAP.put("Theta", Character.valueOf('\u0398'));
        MAP.put("Iota", Character.valueOf('\u0399'));
        MAP.put("Kappa", Character.valueOf('\u039A'));
        MAP.put("Lambda", Character.valueOf('\u039B'));
        MAP.put("Mu", Character.valueOf('\u039C'));
        MAP.put("Nu", Character.valueOf('\u039D'));
        MAP.put("Xi", Character.valueOf('\u039E'));
        MAP.put("Omicron", Character.valueOf('\u039F'));
        MAP.put("Pi", Character.valueOf('\u03A0'));
        MAP.put("Rho", Character.valueOf('\u03A1'));
        MAP.put("Sigma", Character.valueOf('\u03A3'));
        MAP.put("Tau", Character.valueOf('\u03A4'));
        MAP.put("Upsilon", Character.valueOf('\u03A5'));
        MAP.put("Phi", Character.valueOf('\u03A6'));
        MAP.put("Chi", Character.valueOf('\u03A7'));
        MAP.put("Psi", Character.valueOf('\u03A8'));
        MAP.put("Omega", Character.valueOf('\u03A9'));
        MAP.put("alpha", Character.valueOf('\u03B1'));
        MAP.put("beta", Character.valueOf('\u03B2'));
        MAP.put("gamma", Character.valueOf('\u03B3'));
        MAP.put("delta", Character.valueOf('\u03B4'));
        MAP.put("epsilon", Character.valueOf('\u03B5'));
        MAP.put("zeta", Character.valueOf('\u03B6'));
        MAP.put("eta", Character.valueOf('\u03B7'));
        MAP.put("theta", Character.valueOf('\u03B8'));
        MAP.put("iota", Character.valueOf('\u03B9'));
        MAP.put("kappa", Character.valueOf('\u03BA'));
        MAP.put("lambda", Character.valueOf('\u03BB'));
        MAP.put("mu", Character.valueOf('\u03BC'));
        MAP.put("nu", Character.valueOf('\u03BD'));
        MAP.put("xi", Character.valueOf('\u03BE'));
        MAP.put("omicron", Character.valueOf('\u03BF'));
        MAP.put("pi", Character.valueOf('\u03C0'));
        MAP.put("rho", Character.valueOf('\u03C1'));
        MAP.put("sigmaf", Character.valueOf('\u03C2'));
        MAP.put("sigma", Character.valueOf('\u03C3'));
        MAP.put("tau", Character.valueOf('\u03C4'));
        MAP.put("upsilon", Character.valueOf('\u03C5'));
        MAP.put("phi", Character.valueOf('\u03C6'));
        MAP.put("chi", Character.valueOf('\u03C7'));
        MAP.put("psi", Character.valueOf('\u03C8'));
        MAP.put("omega", Character.valueOf('\u03C9'));
        MAP.put("thetasym", Character.valueOf('\u03D1'));
        MAP.put("upsih", Character.valueOf('\u03D2'));
        MAP.put("piv", Character.valueOf('\u03D6'));
        MAP.put("bull", Character.valueOf('\u2022'));
        MAP.put("hellip", Character.valueOf('\u2026'));
        MAP.put("prime", Character.valueOf('\u2032'));
        MAP.put("Prime", Character.valueOf('\u2033'));
        MAP.put("oline", Character.valueOf('\u203E'));
        MAP.put("frasl", Character.valueOf('\u2044'));
        MAP.put("weierp", Character.valueOf('\u2118'));
        MAP.put("image", Character.valueOf('\u2111'));
        MAP.put("real", Character.valueOf('\u211C'));
        MAP.put("trade", Character.valueOf('\u2122'));
        MAP.put("alefsym", Character.valueOf('\u2135'));
        MAP.put("larr", Character.valueOf('\u2190'));
        MAP.put("uarr", Character.valueOf('\u2191'));
        MAP.put("rarr", Character.valueOf('\u2192'));
        MAP.put("darr", Character.valueOf('\u2193'));
        MAP.put("harr", Character.valueOf('\u2194'));
        MAP.put("crarr", Character.valueOf('\u21B5'));
        MAP.put("lArr", Character.valueOf('\u21D0'));
        MAP.put("uArr", Character.valueOf('\u21D1'));
        MAP.put("rArr", Character.valueOf('\u21D2'));
        MAP.put("dArr", Character.valueOf('\u21D3'));
        MAP.put("hArr", Character.valueOf('\u21D4'));
        MAP.put("forall", Character.valueOf('\u2200'));
        MAP.put("part", Character.valueOf('\u2202'));
        MAP.put("exist", Character.valueOf('\u2203'));
        MAP.put("empty", Character.valueOf('\u2205'));
        MAP.put("nabla", Character.valueOf('\u2207'));
        MAP.put("isin", Character.valueOf('\u2208'));
        MAP.put("notin", Character.valueOf('\u2209'));
        MAP.put("ni", Character.valueOf('\u220B'));
        MAP.put("prod", Character.valueOf('\u220F'));
        MAP.put("sum", Character.valueOf('\u2211'));
        MAP.put("minus", Character.valueOf('\u2212'));
        MAP.put("lowast", Character.valueOf('\u2217'));
        MAP.put("radic", Character.valueOf('\u221A'));
        MAP.put("prop", Character.valueOf('\u221D'));
        MAP.put("infin", Character.valueOf('\u221E'));
        MAP.put("ang", Character.valueOf('\u2220'));
        MAP.put("and", Character.valueOf('\u2227'));
        MAP.put("or", Character.valueOf('\u2228'));
        MAP.put("cap", Character.valueOf('\u2229'));
        MAP.put("cup", Character.valueOf('\u222A'));
        MAP.put("int", Character.valueOf('\u222B'));
        MAP.put("there4", Character.valueOf('\u2234'));
        MAP.put("sim", Character.valueOf('\u223C'));
        MAP.put("cong", Character.valueOf('\u2245'));
        MAP.put("asymp", Character.valueOf('\u2248'));
        MAP.put("ne", Character.valueOf('\u2260'));
        MAP.put("equiv", Character.valueOf('\u2261'));
        MAP.put("le", Character.valueOf('\u2264'));
        MAP.put("ge", Character.valueOf('\u2265'));
        MAP.put("sub", Character.valueOf('\u2282'));
        MAP.put("sup", Character.valueOf('\u2283'));
        MAP.put("nsub", Character.valueOf('\u2284'));
        MAP.put("sube", Character.valueOf('\u2286'));
        MAP.put("supe", Character.valueOf('\u2287'));
        MAP.put("oplus", Character.valueOf('\u2295'));
        MAP.put("otimes", Character.valueOf('\u2297'));
        MAP.put("perp", Character.valueOf('\u22A5'));
        MAP.put("sdot", Character.valueOf('\u22C5'));
        MAP.put("lceil", Character.valueOf('\u2308'));
        MAP.put("rceil", Character.valueOf('\u2309'));
        MAP.put("lfloor", Character.valueOf('\u230A'));
        MAP.put("rfloor", Character.valueOf('\u230B'));
        MAP.put("lang", Character.valueOf('\u2329'));
        MAP.put("rang", Character.valueOf('\u232A'));
        MAP.put("loz", Character.valueOf('\u25CA'));
        MAP.put("spades", Character.valueOf('\u2660'));
        MAP.put("clubs", Character.valueOf('\u2663'));
        MAP.put("hearts", Character.valueOf('\u2665'));
        MAP.put("diams", Character.valueOf('\u2666'));
        MAP.put("quot", Character.valueOf('"'));
        MAP.put("amp", Character.valueOf('&'));
        MAP.put("apos", Character.valueOf('\''));
        MAP.put("lt", Character.valueOf('<'));
        MAP.put("gt", Character.valueOf('>'));
        MAP.put("OElig", Character.valueOf('\u0152'));
        MAP.put("oelig", Character.valueOf('\u0153'));
        MAP.put("Scaron", Character.valueOf('\u0160'));
        MAP.put("scaron", Character.valueOf('\u0161'));
        MAP.put("Yuml", Character.valueOf('\u0178'));
        MAP.put("circ", Character.valueOf('\u02C6'));
        MAP.put("tilde", Character.valueOf('\u02DC'));
        MAP.put("ensp", Character.valueOf('\u2002'));
        MAP.put("emsp", Character.valueOf('\u2003'));
        MAP.put("thinsp", Character.valueOf('\u2009'));
        MAP.put("zwnj", Character.valueOf('\u200C'));
        MAP.put("zwj", Character.valueOf('\u200D'));
        MAP.put("lrm", Character.valueOf('\u200E'));
        MAP.put("rlm", Character.valueOf('\u200F'));
        MAP.put("ndash", Character.valueOf('\u2013'));
        MAP.put("mdash", Character.valueOf('\u2014'));
        MAP.put("lsquo", Character.valueOf('\u2018'));
        MAP.put("rsquo", Character.valueOf('\u2019'));
        MAP.put("sbquo", Character.valueOf('\u201A'));
        MAP.put("ldquo", Character.valueOf('\u201C'));
        MAP.put("rdquo", Character.valueOf('\u201D'));
        MAP.put("bdquo", Character.valueOf('\u201E'));
        MAP.put("dagger", Character.valueOf('\u2020'));
        MAP.put("Dagger", Character.valueOf('\u2021'));
        MAP.put("permil", Character.valueOf('\u2030'));
        MAP.put("lsaquo", Character.valueOf('\u2039'));
        MAP.put("rsaquo", Character.valueOf('\u203A'));
        MAP.put("euro", Character.valueOf('\u20AC'));
    }
}
