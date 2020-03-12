package br.com.acolita.accellescale.service;

import br.com.acolita.accellescale.model.Stock;

import java.time.LocalDate;
import java.util.stream.Stream;

public class StockService {
    public Stream<Stock> listNegotiableStocksAt(final LocalDate date) {
        return Stream.empty();
    }


}
