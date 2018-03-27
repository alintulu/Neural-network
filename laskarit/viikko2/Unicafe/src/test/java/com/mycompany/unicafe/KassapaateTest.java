/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alintulu
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void StartsCorrectly() {
        assertTrue(kassa != null);
    }

    @Test
    public void StartSumIsCorrect() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void StartBoughtEdullisestiIsCorrect() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void StartBoughtMaukkastiIsCorrect() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void BuyingEdullisestiWorksCorrectly() {
        assertEquals(10, kassa.syoEdullisesti(250));
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void BuyingMaukkastiWorksCorrectly() {
        assertEquals(10, kassa.syoMaukkaasti(410));
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void BuyingEdullisestiCorrectChangesTheAmountOfLunch() {
        kassa.syoEdullisesti(250);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void BuyingMaukkastiCorrectChangesTheAmountOfLunch() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void BuyingEdullisestiWorksInCorrectly() {
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void BuyingMaukkastiWorksInCorrectly() {
        assertEquals(250, kassa.syoMaukkaasti(250));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void BuyingEdullisestiInCorrectChangesTheAmountOfLunch() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void BuyingMaukkastiInCorrectChangesTheAmountOfLunch() {
        kassa.syoMaukkaasti(250);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void BuyingEdullisestiWorksWithCard() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void BuyingMaukkastiWorksWithCard() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void BuyingEdullisestiChangesTheLunchCorrectly() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void BuyingMaukkastiChangesTheLunchCorrectly() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void BuyingEdullisestiWithoutEnoughMoneyOnCardDoesntWork() {
        kortti.otaRahaa(800);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void BuyingMaukkastiWithourEnoughMoneyOnCardDoesntWork() {
        kortti.otaRahaa(800);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void PayingForEdullisestiByyCardDoesntChangeCashier() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void PayingforMaukastiByCardDoesntChangeCashier() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void LoadingMoneyToCardWorksCorrectly() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
        @Test
    public void LoadingNegativeMoneyToCardDoesntWork() {
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
