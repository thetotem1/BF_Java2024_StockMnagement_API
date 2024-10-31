package be.bstorm.bf_java2024_stockmanagement.dl.enums;

/**
 * Represents the Value-Added Tax (VAT) rates applicable within the stock management system.
 * Each enum value corresponds to a specific VAT percentage.
 *
 * <p>Enum Values:
 * <ul>
 * <li>{@link #SIX} - Represents a VAT rate of 6%.</li>
 * <li>{@link #TWELVE} - Represents a VAT rate of 12%.</li>
 * <li>{@link #TWENTY_ONE} - Represents a VAT rate of 21%.</li>
 * </ul>
 * </p>
 *
 * <p>Each VAT rate has an associated integer {@code value} representing the percentage.</p>
 */
public enum VAT {

    /** Represents a VAT rate of 6%. */
    SIX(6),

    /** Represents a VAT rate of 12%. */
    TWELVE(12),

    /** Represents a VAT rate of 21%. */
    TWENTY_ONE(21);

    /**
     * The integer value of the VAT rate, representing the percentage.
     */
    public final int value;

    /**
     * Constructs a VAT rate with the specified percentage value.
     *
     * @param value The integer value of the VAT rate.
     */
    VAT(int value) {
        this.value = value;
    }
}
