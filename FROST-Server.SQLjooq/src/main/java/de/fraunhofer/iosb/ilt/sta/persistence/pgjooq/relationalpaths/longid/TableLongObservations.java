package de.fraunhofer.iosb.ilt.sta.persistence.pgjooq.relationalpaths.longid;

import de.fraunhofer.iosb.ilt.sta.persistence.pgjooq.relationalpaths.AbstractTableObservations;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;

public class TableLongObservations extends AbstractTableObservations<Long> {

    private static final long serialVersionUID = -1104422281;

    /**
     * The reference instance of <code>public.OBSERVATIONS</code>
     */
    public static final TableLongObservations OBSERVATIONS = new TableLongObservations();

    /**
     * @return The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, Long> getId() {
        return ID;
    }

    @Override
    public TableField<Record, Long> getDatastreamId() {
        return DATASTREAM_ID;
    }

    @Override
    public TableField<Record, Long> getFeatureId() {
        return FEATURE_ID;
    }

    @Override
    public TableField<Record, Long> getMultiDatastreamId() {
        return MULTI_DATASTREAM_ID;
    }

    /**
     * The column <code>public.OBSERVATIONS.ID</code>.
     */
    public final TableField<Record, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('\"OBSERVATIONS_ID_seq\"'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.OBSERVATIONS.DATASTREAM_ID</code>.
     */
    public final TableField<Record, Long> DATASTREAM_ID = createField("DATASTREAM_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.OBSERVATIONS.FEATURE_ID</code>.
     */
    public final TableField<Record, Long> FEATURE_ID = createField("FEATURE_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.OBSERVATIONS.MULTI_DATASTREAM_ID</code>.
     */
    public final TableField<Record, Long> MULTI_DATASTREAM_ID = createField("MULTI_DATASTREAM_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>public.OBSERVATIONS</code> table reference
     */
    public TableLongObservations() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS</code> table reference
     */
    public TableLongObservations(String alias) {
        this(DSL.name(alias), OBSERVATIONS);
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS</code> table reference
     */
    public TableLongObservations(Name alias) {
        this(alias, OBSERVATIONS);
    }

    private TableLongObservations(Name alias, TableLongObservations aliased) {
        super(alias, aliased);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservations as(String alias) {
        return new TableLongObservations(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservations as(Name alias) {
        return new TableLongObservations(alias, this);
    }

}
