package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKassapaateOlemassa() {
        assertTrue(kassapaate != null);
    }

    @Test
    public void kassassaRahaaAlussaOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void lounaitaEiOleMyytyAlussa() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kateisostoOnnistuuKunKaiteinenRiittaa() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());

        assertEquals(100, kassapaate.syoMaukkaasti(500));
        assertEquals(100640, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kateisostoEiOnnistuKunKaiteinenEiRiita() {
        assertEquals(100, kassapaate.syoEdullisesti(100));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());

        assertEquals(300, kassapaate.syoMaukkaasti(300));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttiostoOnnistuuKunSaldoRiittaa() {
        assertEquals(true, kassapaate.syoEdullisesti(maksukortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());

        assertEquals(true, kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttiostoEiOnnistuKunSaldoEiRiita() {
        maksukortti = new Maksukortti(100);

        assertEquals(false, kassapaate.syoEdullisesti(maksukortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());

        assertEquals(false, kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void lataaRahaaKortilleKasvattaaKassassaOlevaaRahamaara() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());

    }

    @Test
    public void lataaRahaaKortilleEiTeeMitaanJosLadattuSummaNegatiivinen() {
        kassapaate.lataaRahaaKortille(maksukortti, -100);
        assertEquals(100000, kassapaate.kassassaRahaa());

    }
}
