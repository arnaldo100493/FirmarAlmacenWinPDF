// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitiesToSymbol.java

package co.com.pdf.text.xml.simpleparser;

import co.com.pdf.text.Chunk;
import co.com.pdf.text.Font;
import java.util.HashMap;
import java.util.Map;

public class EntitiesToSymbol
{

    public EntitiesToSymbol()
    {
    }

    public static Chunk get(String e, Font font)
    {
        char s = getCorrespondingSymbol(e);
        if(s == 0)
        {
            try
            {
                return new Chunk(String.valueOf((char)Integer.parseInt(e)), font);
            }
            catch(Exception exception)
            {
                return new Chunk(e, font);
            }
        } else
        {
            Font symbol = new Font(co.com.pdf.text.Font.FontFamily.SYMBOL, font.getSize(), font.getStyle(), font.getColor());
            return new Chunk(String.valueOf(s), symbol);
        }
    }

    public static char getCorrespondingSymbol(String name)
    {
        Character symbol = (Character)MAP.get(name);
        if(symbol == null)
            return '\0';
        else
            return symbol.charValue();
    }

    private static final Map MAP;

    static 
    {
        MAP = new HashMap();
        MAP.put("169", Character.valueOf('\343'));
        MAP.put("172", Character.valueOf('\330'));
        MAP.put("174", Character.valueOf('\322'));
        MAP.put("177", Character.valueOf('\261'));
        MAP.put("215", Character.valueOf('\264'));
        MAP.put("247", Character.valueOf('\270'));
        MAP.put("8230", Character.valueOf('\274'));
        MAP.put("8242", Character.valueOf('\242'));
        MAP.put("8243", Character.valueOf('\262'));
        MAP.put("8260", Character.valueOf('\244'));
        MAP.put("8364", Character.valueOf('\360'));
        MAP.put("8465", Character.valueOf('\301'));
        MAP.put("8472", Character.valueOf('\303'));
        MAP.put("8476", Character.valueOf('\302'));
        MAP.put("8482", Character.valueOf('\324'));
        MAP.put("8501", Character.valueOf('\300'));
        MAP.put("8592", Character.valueOf('\254'));
        MAP.put("8593", Character.valueOf('\255'));
        MAP.put("8594", Character.valueOf('\256'));
        MAP.put("8595", Character.valueOf('\257'));
        MAP.put("8596", Character.valueOf('\253'));
        MAP.put("8629", Character.valueOf('\277'));
        MAP.put("8656", Character.valueOf('\334'));
        MAP.put("8657", Character.valueOf('\335'));
        MAP.put("8658", Character.valueOf('\336'));
        MAP.put("8659", Character.valueOf('\337'));
        MAP.put("8660", Character.valueOf('\333'));
        MAP.put("8704", Character.valueOf('"'));
        MAP.put("8706", Character.valueOf('\266'));
        MAP.put("8707", Character.valueOf('$'));
        MAP.put("8709", Character.valueOf('\306'));
        MAP.put("8711", Character.valueOf('\321'));
        MAP.put("8712", Character.valueOf('\316'));
        MAP.put("8713", Character.valueOf('\317'));
        MAP.put("8717", Character.valueOf('\''));
        MAP.put("8719", Character.valueOf('\325'));
        MAP.put("8721", Character.valueOf('\345'));
        MAP.put("8722", Character.valueOf('-'));
        MAP.put("8727", Character.valueOf('*'));
        MAP.put("8729", Character.valueOf('\267'));
        MAP.put("8730", Character.valueOf('\326'));
        MAP.put("8733", Character.valueOf('\265'));
        MAP.put("8734", Character.valueOf('\245'));
        MAP.put("8736", Character.valueOf('\320'));
        MAP.put("8743", Character.valueOf('\331'));
        MAP.put("8744", Character.valueOf('\332'));
        MAP.put("8745", Character.valueOf('\307'));
        MAP.put("8746", Character.valueOf('\310'));
        MAP.put("8747", Character.valueOf('\362'));
        MAP.put("8756", Character.valueOf('\\'));
        MAP.put("8764", Character.valueOf('~'));
        MAP.put("8773", Character.valueOf('@'));
        MAP.put("8776", Character.valueOf('\273'));
        MAP.put("8800", Character.valueOf('\271'));
        MAP.put("8801", Character.valueOf('\272'));
        MAP.put("8804", Character.valueOf('\243'));
        MAP.put("8805", Character.valueOf('\263'));
        MAP.put("8834", Character.valueOf('\314'));
        MAP.put("8835", Character.valueOf('\311'));
        MAP.put("8836", Character.valueOf('\313'));
        MAP.put("8838", Character.valueOf('\315'));
        MAP.put("8839", Character.valueOf('\312'));
        MAP.put("8853", Character.valueOf('\305'));
        MAP.put("8855", Character.valueOf('\304'));
        MAP.put("8869", Character.valueOf('^'));
        MAP.put("8901", Character.valueOf('\327'));
        MAP.put("8992", Character.valueOf('\363'));
        MAP.put("8993", Character.valueOf('\365'));
        MAP.put("9001", Character.valueOf('\341'));
        MAP.put("9002", Character.valueOf('\361'));
        MAP.put("913", Character.valueOf('A'));
        MAP.put("914", Character.valueOf('B'));
        MAP.put("915", Character.valueOf('G'));
        MAP.put("916", Character.valueOf('D'));
        MAP.put("917", Character.valueOf('E'));
        MAP.put("918", Character.valueOf('Z'));
        MAP.put("919", Character.valueOf('H'));
        MAP.put("920", Character.valueOf('Q'));
        MAP.put("921", Character.valueOf('I'));
        MAP.put("922", Character.valueOf('K'));
        MAP.put("923", Character.valueOf('L'));
        MAP.put("924", Character.valueOf('M'));
        MAP.put("925", Character.valueOf('N'));
        MAP.put("926", Character.valueOf('X'));
        MAP.put("927", Character.valueOf('O'));
        MAP.put("928", Character.valueOf('P'));
        MAP.put("929", Character.valueOf('R'));
        MAP.put("931", Character.valueOf('S'));
        MAP.put("932", Character.valueOf('T'));
        MAP.put("933", Character.valueOf('U'));
        MAP.put("934", Character.valueOf('F'));
        MAP.put("935", Character.valueOf('C'));
        MAP.put("936", Character.valueOf('Y'));
        MAP.put("937", Character.valueOf('W'));
        MAP.put("945", Character.valueOf('a'));
        MAP.put("946", Character.valueOf('b'));
        MAP.put("947", Character.valueOf('g'));
        MAP.put("948", Character.valueOf('d'));
        MAP.put("949", Character.valueOf('e'));
        MAP.put("950", Character.valueOf('z'));
        MAP.put("951", Character.valueOf('h'));
        MAP.put("952", Character.valueOf('q'));
        MAP.put("953", Character.valueOf('i'));
        MAP.put("954", Character.valueOf('k'));
        MAP.put("955", Character.valueOf('l'));
        MAP.put("956", Character.valueOf('m'));
        MAP.put("957", Character.valueOf('n'));
        MAP.put("958", Character.valueOf('x'));
        MAP.put("959", Character.valueOf('o'));
        MAP.put("960", Character.valueOf('p'));
        MAP.put("961", Character.valueOf('r'));
        MAP.put("962", Character.valueOf('V'));
        MAP.put("963", Character.valueOf('s'));
        MAP.put("964", Character.valueOf('t'));
        MAP.put("965", Character.valueOf('u'));
        MAP.put("966", Character.valueOf('f'));
        MAP.put("967", Character.valueOf('c'));
        MAP.put("9674", Character.valueOf('\340'));
        MAP.put("968", Character.valueOf('y'));
        MAP.put("969", Character.valueOf('w'));
        MAP.put("977", Character.valueOf('J'));
        MAP.put("978", Character.valueOf('\241'));
        MAP.put("981", Character.valueOf('j'));
        MAP.put("982", Character.valueOf('v'));
        MAP.put("9824", Character.valueOf('\252'));
        MAP.put("9827", Character.valueOf('\247'));
        MAP.put("9829", Character.valueOf('\251'));
        MAP.put("9830", Character.valueOf('\250'));
        MAP.put("Alpha", Character.valueOf('A'));
        MAP.put("Beta", Character.valueOf('B'));
        MAP.put("Chi", Character.valueOf('C'));
        MAP.put("Delta", Character.valueOf('D'));
        MAP.put("Epsilon", Character.valueOf('E'));
        MAP.put("Eta", Character.valueOf('H'));
        MAP.put("Gamma", Character.valueOf('G'));
        MAP.put("Iota", Character.valueOf('I'));
        MAP.put("Kappa", Character.valueOf('K'));
        MAP.put("Lambda", Character.valueOf('L'));
        MAP.put("Mu", Character.valueOf('M'));
        MAP.put("Nu", Character.valueOf('N'));
        MAP.put("Omega", Character.valueOf('W'));
        MAP.put("Omicron", Character.valueOf('O'));
        MAP.put("Phi", Character.valueOf('F'));
        MAP.put("Pi", Character.valueOf('P'));
        MAP.put("Prime", Character.valueOf('\262'));
        MAP.put("Psi", Character.valueOf('Y'));
        MAP.put("Rho", Character.valueOf('R'));
        MAP.put("Sigma", Character.valueOf('S'));
        MAP.put("Tau", Character.valueOf('T'));
        MAP.put("Theta", Character.valueOf('Q'));
        MAP.put("Upsilon", Character.valueOf('U'));
        MAP.put("Xi", Character.valueOf('X'));
        MAP.put("Zeta", Character.valueOf('Z'));
        MAP.put("alefsym", Character.valueOf('\300'));
        MAP.put("alpha", Character.valueOf('a'));
        MAP.put("and", Character.valueOf('\331'));
        MAP.put("ang", Character.valueOf('\320'));
        MAP.put("asymp", Character.valueOf('\273'));
        MAP.put("beta", Character.valueOf('b'));
        MAP.put("cap", Character.valueOf('\307'));
        MAP.put("chi", Character.valueOf('c'));
        MAP.put("clubs", Character.valueOf('\247'));
        MAP.put("cong", Character.valueOf('@'));
        MAP.put("copy", Character.valueOf('\323'));
        MAP.put("crarr", Character.valueOf('\277'));
        MAP.put("cup", Character.valueOf('\310'));
        MAP.put("dArr", Character.valueOf('\337'));
        MAP.put("darr", Character.valueOf('\257'));
        MAP.put("delta", Character.valueOf('d'));
        MAP.put("diams", Character.valueOf('\250'));
        MAP.put("divide", Character.valueOf('\270'));
        MAP.put("empty", Character.valueOf('\306'));
        MAP.put("epsilon", Character.valueOf('e'));
        MAP.put("equiv", Character.valueOf('\272'));
        MAP.put("eta", Character.valueOf('h'));
        MAP.put("euro", Character.valueOf('\360'));
        MAP.put("exist", Character.valueOf('$'));
        MAP.put("forall", Character.valueOf('"'));
        MAP.put("frasl", Character.valueOf('\244'));
        MAP.put("gamma", Character.valueOf('g'));
        MAP.put("ge", Character.valueOf('\263'));
        MAP.put("hArr", Character.valueOf('\333'));
        MAP.put("harr", Character.valueOf('\253'));
        MAP.put("hearts", Character.valueOf('\251'));
        MAP.put("hellip", Character.valueOf('\274'));
        MAP.put("horizontal arrow extender", Character.valueOf('\276'));
        MAP.put("image", Character.valueOf('\301'));
        MAP.put("infin", Character.valueOf('\245'));
        MAP.put("int", Character.valueOf('\362'));
        MAP.put("iota", Character.valueOf('i'));
        MAP.put("isin", Character.valueOf('\316'));
        MAP.put("kappa", Character.valueOf('k'));
        MAP.put("lArr", Character.valueOf('\334'));
        MAP.put("lambda", Character.valueOf('l'));
        MAP.put("lang", Character.valueOf('\341'));
        MAP.put("large brace extender", Character.valueOf('\357'));
        MAP.put("large integral extender", Character.valueOf('\364'));
        MAP.put("large left brace (bottom)", Character.valueOf('\356'));
        MAP.put("large left brace (middle)", Character.valueOf('\355'));
        MAP.put("large left brace (top)", Character.valueOf('\354'));
        MAP.put("large left bracket (bottom)", Character.valueOf('\353'));
        MAP.put("large left bracket (extender)", Character.valueOf('\352'));
        MAP.put("large left bracket (top)", Character.valueOf('\351'));
        MAP.put("large left parenthesis (bottom)", Character.valueOf('\350'));
        MAP.put("large left parenthesis (extender)", Character.valueOf('\347'));
        MAP.put("large left parenthesis (top)", Character.valueOf('\346'));
        MAP.put("large right brace (bottom)", Character.valueOf('\376'));
        MAP.put("large right brace (middle)", Character.valueOf('\375'));
        MAP.put("large right brace (top)", Character.valueOf('\374'));
        MAP.put("large right bracket (bottom)", Character.valueOf('\373'));
        MAP.put("large right bracket (extender)", Character.valueOf('\372'));
        MAP.put("large right bracket (top)", Character.valueOf('\371'));
        MAP.put("large right parenthesis (bottom)", Character.valueOf('\370'));
        MAP.put("large right parenthesis (extender)", Character.valueOf('\367'));
        MAP.put("large right parenthesis (top)", Character.valueOf('\366'));
        MAP.put("larr", Character.valueOf('\254'));
        MAP.put("le", Character.valueOf('\243'));
        MAP.put("lowast", Character.valueOf('*'));
        MAP.put("loz", Character.valueOf('\340'));
        MAP.put("minus", Character.valueOf('-'));
        MAP.put("mu", Character.valueOf('m'));
        MAP.put("nabla", Character.valueOf('\321'));
        MAP.put("ne", Character.valueOf('\271'));
        MAP.put("not", Character.valueOf('\330'));
        MAP.put("notin", Character.valueOf('\317'));
        MAP.put("nsub", Character.valueOf('\313'));
        MAP.put("nu", Character.valueOf('n'));
        MAP.put("omega", Character.valueOf('w'));
        MAP.put("omicron", Character.valueOf('o'));
        MAP.put("oplus", Character.valueOf('\305'));
        MAP.put("or", Character.valueOf('\332'));
        MAP.put("otimes", Character.valueOf('\304'));
        MAP.put("part", Character.valueOf('\266'));
        MAP.put("perp", Character.valueOf('^'));
        MAP.put("phi", Character.valueOf('f'));
        MAP.put("pi", Character.valueOf('p'));
        MAP.put("piv", Character.valueOf('v'));
        MAP.put("plusmn", Character.valueOf('\261'));
        MAP.put("prime", Character.valueOf('\242'));
        MAP.put("prod", Character.valueOf('\325'));
        MAP.put("prop", Character.valueOf('\265'));
        MAP.put("psi", Character.valueOf('y'));
        MAP.put("rArr", Character.valueOf('\336'));
        MAP.put("radic", Character.valueOf('\326'));
        MAP.put("radical extender", Character.valueOf('`'));
        MAP.put("rang", Character.valueOf('\361'));
        MAP.put("rarr", Character.valueOf('\256'));
        MAP.put("real", Character.valueOf('\302'));
        MAP.put("reg", Character.valueOf('\322'));
        MAP.put("rho", Character.valueOf('r'));
        MAP.put("sdot", Character.valueOf('\327'));
        MAP.put("sigma", Character.valueOf('s'));
        MAP.put("sigmaf", Character.valueOf('V'));
        MAP.put("sim", Character.valueOf('~'));
        MAP.put("spades", Character.valueOf('\252'));
        MAP.put("sub", Character.valueOf('\314'));
        MAP.put("sube", Character.valueOf('\315'));
        MAP.put("sum", Character.valueOf('\345'));
        MAP.put("sup", Character.valueOf('\311'));
        MAP.put("supe", Character.valueOf('\312'));
        MAP.put("tau", Character.valueOf('t'));
        MAP.put("there4", Character.valueOf('\\'));
        MAP.put("theta", Character.valueOf('q'));
        MAP.put("thetasym", Character.valueOf('J'));
        MAP.put("times", Character.valueOf('\264'));
        MAP.put("trade", Character.valueOf('\324'));
        MAP.put("uArr", Character.valueOf('\335'));
        MAP.put("uarr", Character.valueOf('\255'));
        MAP.put("upsih", Character.valueOf('\241'));
        MAP.put("upsilon", Character.valueOf('u'));
        MAP.put("vertical arrow extender", Character.valueOf('\275'));
        MAP.put("weierp", Character.valueOf('\303'));
        MAP.put("xi", Character.valueOf('x'));
        MAP.put("zeta", Character.valueOf('z'));
    }
}
