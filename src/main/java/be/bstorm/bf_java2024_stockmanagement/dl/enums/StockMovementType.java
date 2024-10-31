package be.bstorm.bf_java2024_stockmanagement.dl.enums;

/**
 * Represents the types of stock movements within the stock management system.
 * Each enum value indicates a specific action or adjustment related to stock levels.
 *
 * <p>Enum Values:
 * <ul>
 * <li>{@link #STOCK_IN} - Represents incoming stock, such as new inventory received.</li>
 * <li>{@link #STOCK_OUT} - Represents outgoing stock, such as items sold or shipped out.</li>
 * <li>{@link #STOCK_POSITIVE_CORRECTION} - A positive adjustment to increase stock, often used for manual corrections.</li>
 * <li>{@link #STOCK_NEGATIVE_CORRECTION} - A negative adjustment to decrease stock, often used for manual corrections.</li>
 * <li>{@link #STOCK_RETURN} - Represents returned stock, items that are returned to inventory.</li>
 * <li>{@link #STOCK_RECALL} - Represents a stock recall, typically for items that must be removed for quality or safety reasons.</li>
 * <li>{@link #STOCK_MISSING} - Indicates missing stock, typically for items not accounted for in inventory.</li>
 * </ul>
 * </p>
 */
public enum StockMovementType {
    /** Represents incoming stock, such as new inventory received. */
    STOCK_IN,

    /** Represents outgoing stock, such as items sold or shipped out. */
    STOCK_OUT,

    /** A positive adjustment to increase stock, often used for manual corrections. */
    STOCK_POSITIVE_CORRECTION,

    /** A negative adjustment to decrease stock, often used for manual corrections. */
    STOCK_NEGATIVE_CORRECTION,

    /** Represents returned stock, items that are returned to inventory. */
    STOCK_RETURN,

    /** Represents a stock recall, typically for items that must be removed for quality or safety reasons. */
    STOCK_RECALL,

    /** Indicates missing stock, typically for items not accounted for in inventory. */
    STOCK_MISSING
}
