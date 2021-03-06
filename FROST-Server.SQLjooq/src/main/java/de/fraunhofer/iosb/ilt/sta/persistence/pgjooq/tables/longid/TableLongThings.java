package de.fraunhofer.iosb.ilt.sta.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.sta.persistence.pgjooq.tables.AbstractTableThings;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;

public class TableLongThings extends AbstractTableThings<Long> {

    private static final long serialVersionUID = -729589982;

    /**
     * The reference instance of <code>public.THINGS</code>
     */
    public static final TableLongThings THINGS = new TableLongThings();

    /**
     * @return The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, Long> getId() {
        return id;
    }

    /**
     * The column <code>public.THINGS.ID</code>.
     */
    public final TableField<Record, Long> id = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('\"THINGS_ID_seq\"'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.THINGS</code> table reference
     */
    public TableLongThings() {
        super();
    }

    /**
     * Create an aliased <code>public.THINGS</code> table reference
     */
    public TableLongThings(String alias) {
        this(DSL.name(alias), THINGS);
    }

    /**
     * Create an aliased <code>public.THINGS</code> table reference
     */
    public TableLongThings(Name alias) {
        this(alias, THINGS);
    }

    private TableLongThings(Name alias, TableLongThings aliased) {
        super(alias, aliased);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongThings as(String alias) {
        return new TableLongThings(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongThings as(Name alias) {
        return new TableLongThings(alias, this);
    }

}
