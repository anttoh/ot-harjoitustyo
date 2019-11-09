package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void lataaRahaaKasvattaaSaldoa() {
        kortti.lataaRahaa(100);
        assertEquals(110, kortti.saldo());
    }

    @Test
    public void otaRahaaVahentaaSaldoaJosSaldoaTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }

    @Test
    public void otaRahaaEiMuutaSaldoaJosSaldoaEiTarpeeksi() {
        kortti.otaRahaa(100);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void otaRahaaPalauttaaTrueJosSaldoRiittaa() {
        assertTrue(kortti.otaRahaa(5));
    }

    @Test
    public void otaRahaaPalauttaaFalseJosSaldoEiRiitta() {
        assertFalse(kortti.otaRahaa(100));
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
