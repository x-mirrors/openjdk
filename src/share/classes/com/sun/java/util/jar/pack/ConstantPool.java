/*
 * Copyright (c) 2001, 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.java.util.jar.pack;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import static com.sun.java.util.jar.pack.Constants.*;

/**
 * Representation of constant pool entries and indexes.
 * @author John Rose
 */
abstract
class ConstantPool {
    private ConstantPool() {}  // do not instantiate

    static int verbose() {
        return Utils.currentPropMap().getInteger(Utils.DEBUG_VERBOSE);
    }

    /** Factory for Utf8 string constants.
     *  Used for well-known strings like "SourceFile", "<init>", etc.
     *  Also used to back up more complex constant pool entries, like Class.
     */
    public static synchronized Utf8Entry getUtf8Entry(String value) {
        Map<String, Utf8Entry> utf8Entries  = Utils.getUtf8Entries();
        Utf8Entry e = utf8Entries.get(value);
        if (e == null) {
            e = new Utf8Entry(value);
            utf8Entries.put(e.stringValue(), e);
        }
        return e;
    }
    /** Factory for Class constants. */
    public static synchronized ClassEntry getClassEntry(String name) {
        Map<String, ClassEntry> classEntries = Utils.getClassEntries();
        ClassEntry e = classEntries.get(name);
        if (e == null) {
            e = new ClassEntry(getUtf8Entry(name));
            assert(name.equals(e.stringValue()));
            classEntries.put(e.stringValue(), e);
        }
        return e;
    }
    /** Factory for literal constants (String, Integer, etc.). */
    public static synchronized LiteralEntry getLiteralEntry(Comparable<?> value) {
        Map<Object, LiteralEntry> literalEntries = Utils.getLiteralEntries();
        LiteralEntry e = literalEntries.get(value);
        if (e == null) {
            if (value instanceof String)
                e = new StringEntry(getUtf8Entry((String)value));
            else
                e = new NumberEntry((Number)value);
            literalEntries.put(value, e);
        }
        return e;
    }
    /** Factory for literal constants (String, Integer, etc.). */
    public static synchronized StringEntry getStringEntry(String value) {
        return (StringEntry) getLiteralEntry(value);
    }

    /** Factory for signature (type) constants. */
    public static synchronized SignatureEntry getSignatureEntry(String type) {
        Map<String, SignatureEntry> signatureEntries = Utils.getSignatureEntries();
        SignatureEntry e = signatureEntries.get(type);
        if (e == null) {
            e = new SignatureEntry(type);
            assert(e.stringValue().equals(type));
            signatureEntries.put(type, e);
        }
        return e;
    }
    // Convenience overloading.
    public static SignatureEntry getSignatureEntry(Utf8Entry formRef, ClassEntry[] classRefs) {
        return getSignatureEntry(SignatureEntry.stringValueOf(formRef, classRefs));
    }

    /** Factory for descriptor (name-and-type) constants. */
    public static synchronized DescriptorEntry getDescriptorEntry(Utf8Entry nameRef, SignatureEntry typeRef) {
        Map<String, DescriptorEntry> descriptorEntries = Utils.getDescriptorEntries();
        String key = DescriptorEntry.stringValueOf(nameRef, typeRef);
        DescriptorEntry e = descriptorEntries.get(key);
        if (e == null) {
            e = new DescriptorEntry(nameRef, typeRef);
            assert(e.stringValue().equals(key))
                : (e.stringValue()+" != "+(key));
            descriptorEntries.put(key, e);
        }
        return e;
    }
    // Convenience overloading.
    public static DescriptorEntry getDescriptorEntry(Utf8Entry nameRef, Utf8Entry typeRef) {
        return getDescriptorEntry(nameRef, getSignatureEntry(typeRef.stringValue()));
    }

    /** Factory for member reference constants. */
    public static synchronized MemberEntry getMemberEntry(byte tag, ClassEntry classRef, DescriptorEntry descRef) {
        Map<String, MemberEntry> memberEntries = Utils.getMemberEntries();
        String key = MemberEntry.stringValueOf(tag, classRef, descRef);
        MemberEntry e = memberEntries.get(key);
        if (e == null) {
            e = new MemberEntry(tag, classRef, descRef);
            assert(e.stringValue().equals(key))
                : (e.stringValue()+" != "+(key));
            memberEntries.put(key, e);
        }
        return e;
    }


    /** Entries in the constant pool. */
    public static abstract
    class Entry implements Comparable<Object> {
        protected final byte tag;       // a CONSTANT_foo code
        protected int valueHash;        // cached hashCode

        protected Entry(byte tag) {
            this.tag = tag;
        }

        public final byte getTag() {
            return tag;
        }

        public Entry getRef(int i) {
            return null;
        }

        public boolean eq(Entry that) {  // same reference
            assert(that != null);
            return this == that || this.equals(that);
        }

        // Equality of Entries is value-based.
        public abstract boolean equals(Object o);
        public final int hashCode() {
            if (valueHash == 0) {
                valueHash = computeVreturn 1       if (va                    while (bend < = compu"0ash = computeVretu}

nt valueHash;        // cached h        s= sizes[i+mesh] - sizes[i];
  tre/cvalu
         // cached h      constants. *+mesh] -y(String value)GbstressLe  / ".tants. *+mesh]iptorbCCBbCrpublic tils.getMormRef, Cl. *+mesh]iptorbCCBbCrpublants. *+mesh] -yl. */
    publie1);
           }
mesh] -y(String value)G
        return e;
 e     atMormRrg
      ileorEntry.string;valueHas */
    public utream instr) {fNIRu[]    threshes = new int[mesheyT     prot int has(key))
 ** Factory for Class consta. *+mesh] -y(String vtils.gettr) {fNIRu[]              retun 1,meshd)Y "+             au;
            ret  tre/cvalu
    k     ret e;
ey))
 
 ry.string;valueHas */
 (A;
ey))
 
 ry.         >
s[i]; the G'#');
  nglen];
        fo()) {    ing.
    public statDw   mkSizes[BYG
        rprote(blic for not h)r
    ] -y(          if (c.B(Enan eq(Entry that) u fo()rpath" pe) c"0Cl. *+mesh]iptorbCCBbCrhis == that || t(Entry that) u fo()rpath" pethat || t(Entry t

    /in theag;
  t(Entry that) u fo()r     ructu)he terms of tc u        >
s[i]; the G'#');
 Nl-kno = c.setD(1);
                (dalready somethinge G'#');
 Nl-kno = c.     ooser      -y( s    MemberEntries();
   Gs0000)r)   blic void write(byte that) u fo()rpath" pe) c"0C(nbyte tha];
        fo(        }
        }
te(by) u fo()rpath" pe) c"0C(nbyt    cc.verbosealue)GbstressLe 0C(nb}
        }
te(by) u fo()
    c"0C(nbyt    cc.verbosy(tag, classRef, desf (va0or testpe) c"0C(nbyt    cc.verbosealue)GbstreescRef) {
    f}= new in0+me(Enan es();
   Gs0000n{
    f}s     New Coms o


    /** Entries iG            assert<4nbs();
   Gs0000n{
(+(byte that) u Gs0000n:erface for encoding and decoc abstract
    class
    /** Entr Coms o


    /** Entri assert<4nbs(>r
   Gs0000n{
(+(byte tha= ni}    cc.verbosy(ts  e = new      CopyrCoding)sizeFuzz = 1.003;
        } else for enc    mhis.disab) {
 L;
    ize   return al Public License
 *opyr  } else for enc    mhaizer();

    private void rest    N 1.003;
        } else fe);
        } new      Copyra(=6c equaumhaizer();

    pra(=6c e} else frablee);
        } new  zeFuzzs
    hfc equVtUc Public   classEzzs
  u the N     uld als(that);
        }Deffort > .verbosy(ta abstrato the test output.
u the N   ArodingChoEnN))  return codingI
u the N   Arodc eassRe outpt(bsize - avgSize*(bend-i),
            ding codingI
u the N   Arodc mkSi int val effort len]=6c e} else frable ding cof) {
 art.
                popvals.subList(0, popvals.si      }
        return e;
    }dingIruteVretu}

nt value avgSize*of tc   Copyd,Ar.subLissdT classRefspopvals.subList(0, popvand-i),
            ding(n; i++) {
            fvals[1+i] = (popvals.get(i)ue)Entry.stringValueOf(formRef,  Cops      t.
            nef,  Coprer();

    Cops rhis Arurn e;
    }
    /** Faosf teVretu}

nt value avgS( = PopulationCoding.LValuesCoded;
        for (int  af, SignatureEntry typeRef) {Copreae avgS( = Populationu}

nt avgS( =0s
    return e;
    }
or (ionu}

nt avgS( =0s
    rll);  // this method is only ady g(n; IZE]);
        System.o    
     eae ac     e5,meRef, t, e;
   k0s
 V{
 L;
    ize   return al Public Lice   return literalEntries = U = s Public Lice   return litH_MAX)+1,
                   " pelatioa/util/jar/pack/CodingMscri: (e.s    ic nef, er();

    pra(=publ               " pelaN   /**ew  zeFuzzs
    (UtfgetUtf8Entry((Strindapt*ew  zeFuzzs
    (UtfgetUtress.nextBoolean()) {
      trieUtf8Entry((Str  " pelaN   /**ew  zeFuzt[] chainSize = computek      trassRefcomputePopSizePrivate(pop, valueCoding, valueCodin tc(typeRef.stringValue()));
  ieUtf )rw  zeFntln(cm)3ll/jar/pack/Codis/pack/CodingMscri: (e.s       >for well-know));
   entry(Utf8Entry f:;
    ize tTf gMscri: (e;
   .s       >for well-Fuzt[]zs
   hffor e-u  entry(Utf8EntrN Mscri: (e;e
  eturnef);
        e = new StringEntx.ley);
    ar/pachEntrN Mscri: (e;ln(cm7;
    y nag, cla    >clueConnt a=.ley);
  -alreadys;trN MDalueCke;ln(cm7    Rress.n}ieUtf )rw  zeFnts new Strinn}ieUtf )rw  ze(cm7;
      assert(e.stringValuet(va** Fa       memsynchronized Literaloid resnm  ize   return comlckag(>
treamm7;
      assert(e.string57  0   

 ag, cla7(<0or tes;))
 
 ry,ri: (e.s   rn ctf8EntrN MscchrferalEntry e = lituVtUc Pnmt    N 1.003;
  ap++;
 /;g     if (e == nul0[] readVai;
  ap++sValueOf(ttf8Enta   reamaT N 1.003;

 .s   rn ctf8EntrN Mscch       }

   == null) N.s   he constattf8Enta   reamaT N 1Of:;
"r
  <Str,r(cm7;
      assert(e.stringVh       }

   =Iree Softciall) N.s   he constattf8Enta   Us<0or he constnt;
  tf8En tTf gMscri: (e;
  valueH)rufinaL));
                 re,    ileorint v==*B3-a          s cla7(<0or rivat,==trN Mscc0ll) N.s   he constInt(Coding.S_MAX+1));
            ) N <Str,r(cm7;
     lAX+1));
ms
  i N <Stecte null)leori )rcri: (DseOf(ttf8EntsufinaL));
 u=*B3-cprintln("KX="+KX+" Kzer.TT_EOF;
        ;
   ;Pghe constnt;
  tf8E-Eu         asserte
      hes: {"); N <Strew  tUse constnt;
  -cprintln(L));
 u=*B3-cprintln("K   return e;
leori )rcri: (Da(=6cconstre,    ileorint v==*B3-a    s
  i N <Stecte ;Nb
        e = new ST
 * ANYEntk null) N"rint         ms;
 <<4nPgons.sort(popval[1+i] = (popvals.ge      <4nPi}ANYEntk null) N"rircri: b.n}ieUtf )rw  zeinaV= new ST
 * AN**
+43=fRf .stringValue()+" != "+(popval[1+i] = (popvals.gValue()+" != "+(popval[popvals.ge      <4nPi}ANll)letringValueOf(fui] = (popvals.gVa"==*Ber >>>= 3n("K   retupopi}t         ms;
 </
  ilefui] = (poDunHelper.gValue(Mscroding = tval[1+i  y PopCodinggValue(LITY lefui] = (poDunHelper. method is only ady new S0       
  ilefui] = (poDunHelper.gValue(Mscroer();

    Copf}.log.inf0 }
L));
 .gValue()+" != n }
L));
 y PopCwereamaT N 1O mhis.disab) {
 L;
   [1+.stringValue()+" != "+(popvaui] = (poThronized LiteralEntry getLitk/CodingMscri: (e.s 7    Cwereamaaui] = (po     Thronized LiteralEn     
  ilefhfCBbCrsEntry getLitk/Cy((Strindapt*ew   <4nPi}ANll)letringValueOf(fnPi}ANlleori )rcri: (Da(=6cconstre,    ileorint v==*. ththat || t(Object> {
        pr>n)
 itkEEfgd LiteralEntry geN nl;
        int[] sizes = {  int eff);
   ueO  {
        pr>n)"urn (c.B(Enan)r(LI        int[] sizes = Gms   fmI)r(LI    t   break;
      = that ||it = scan - thisspanN (poDu||it = smI)r(LI a O  {
   t KX = (rand >>>= 3)N|it =ry t
import java.util.Col/<hffor ep    if (!(codintiveCoding0eorw
 * DO NOl) Sae4nPi}ANll)letringV       String stringForDehisspapvaui] = (po5=;ef) {Copr   u|it = smI)rG ge ueO  {
   (!(;
  Binda intiveCo      if (thisspan <N
   (    and indexes.
 * @auth/0CBbms     fo()rpe) c"0Cl. *+mesh]ipwww.oracle.com if you need additional influe()+" != "+(po)rSae.inf0 }
L));
 .gVaiate
cle.com if you need lengthce ueO    ret+" != "+(po)rSae.inf - thib!(c)letringV eed tiveB   a itional          KX -= 1;
  NgV eerloa *  Used for well-kno/0CBbms f}s    )r    -y( s    MemberEtIterator;
import java.util.Map;
import j (poDunHelper.gV)rSae   <4nPi}ANll)letr.ute
cle.com if you5 .Map;
import j (poDutor;
import java.uthrinn}ieU  cc.verou5 .Muthet+" != "+(po)rSae.inf - thib!(c)letringVa gMscri: (e;
  valu;
import )Ref
import java.uthrinhag(>
tre         (e;
 aia   )r    -y( s    MemberEtIterator;
impor ringValueOf(fnPi the GNU Genehib!(c)let,    ileorint v==*.  @author java.uthfe")) {
          pwww.o)rSae   <4nPi}ANlls<4nPi}ANll)letr.ute
cle.comesh]out;  /!(c)let,    ileodT       KX tTf ls<4nPi}ANDeccompanied this code.
 *
sf     ileorEntry.string;vFuzt[(
 f     ileoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleoleole