package br.com.acolita.accellescale.model;

import br.com.acolita.accellescale.model.error.IncompleteObjectConstructionException;
import br.com.acolita.accellescale.model.error.NullReferenceDateException;
import br.com.acolita.accellescale.model.error.StringIsAllBlankException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * Represents the entity of a 'Stock'
 */
public class Stock {
    /**
     * The symbol by which this stock is known
     */
    protected final String symbol;
    
    protected final LocalDate registeredAt;

    protected LocalDate dueAt;

    /**
     * Creates a {@link Stock}
     * @param symbol {@code symbol}
     */
    private Stock(String symbol) {
        this.symbol = symbol;
        this.registeredAt = LocalDate.now(ZoneOffset.UTC);
    }

    /**
     * Gets the {@link Stock}'s {@code symbol}
     * @return non null or all blank string
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * The stock is negotiable if the current
     * @return {@link Boolean}
     */
    public boolean isNegotiable() {
        return isNegotiableAt(LocalDate.now(ZoneOffset.UTC));
    }

    /**
     * Check if the {@link Stock} is negotiable at a reference date
     * @param referenceDate {@link LocalDate} @NotNull, UTC date
     * @return {@link Boolean}
     */
    public boolean isNegotiableAt(final LocalDate referenceDate) {
        if(referenceDate == null) {
            throw new NullReferenceDateException("Reference date cannot be null.");
        }
        return !(referenceDate.isBefore(this.registeredAt) || (dueAt != null && referenceDate.isAfter(this.dueAt)));
    }

    /**
     * A builder for the {@link Stock} entity
     */
    public static class Builder {
        Stock stock;

        /**
         * Sets the symbol of the stock.
         * @param symbol The string must not be null nor empty nor all blank otherwise a Exception is thrown
         * @return {@link Builder}
         */
        public Builder setSymbol(String symbol) {
            if(StringUtils.isAllBlank(symbol)) {
                throw new StringIsAllBlankException("Stock symbol must not be all blank.");
            }
            this.stock = new Stock(StringUtils.trim(symbol));
            return this;
        }

        /**
         * Builds a {@link Stock} entity
         * @return {@link Stock}
         */
        public Stock build() {
            if(stock == null) {
                throw new IncompleteObjectConstructionException("Stock object is not complete built.");
            }
            return this.stock;
        }
    }
}
