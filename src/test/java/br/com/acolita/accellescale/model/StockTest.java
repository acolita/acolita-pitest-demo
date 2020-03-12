package br.com.acolita.accellescale.model;

import br.com.acolita.accellescale.model.error.IncompleteObjectConstructionException;
import br.com.acolita.accellescale.model.error.StringIsAllBlankException;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class StockTest {
    static final String PROG_40 = "PROG40";

    @Test
    public void StockBuild() {
        final Stock stock = buildSimpleStock();
        assertNotNull(stock);
    }

    @Test(expected = IncompleteObjectConstructionException.class)
    public void SymbolNotSetStockBuild() {
        new Stock.Builder().build();
    }

    @Test(expected = StringIsAllBlankException.class)
    public void NullSymbolStockBuild() {
        new Stock.Builder().setSymbol(null).build();
    }

    @Test(expected = StringIsAllBlankException.class)
    public void EmptyStringStockBuild() {
        new Stock.Builder().setSymbol("").build();
    }

    @Test(expected = StringIsAllBlankException.class)
    public void AllBlankStringStockBuild() {
        new Stock.Builder().setSymbol("     ").build();
    }

    @Test
    public void StockSymbolIsTrimmed() {
        assertEquals(PROG_40, new Stock.Builder().setSymbol("  " + PROG_40 + "    ").build().getSymbol());
    }

    @Test
    public void getSymbol() {
        assertEquals(PROG_40, buildSimpleStock().getSymbol());
    }

    @Test
    public void isNegotiable() {
        assertTrue(buildSimpleStock().isNegotiable());
    }

    @Test
    public void isNegotiableAt() {
        assertTrue(buildSimpleStock().isNegotiableAt(LocalDate.now(ZoneOffset.UTC).plus(1, ChronoUnit.DAYS)));
    }

    /**
     * Creates the most simple {@link Stock} for testing purpose
     *
     * @return {@link Stock}
     */
    private Stock buildSimpleStock() {
        return new Stock.Builder().setSymbol(PROG_40).build();
    }

}