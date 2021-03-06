package de.fraunhofer.iosb.ilt.sta.persistence.pgjooq.tables;

import java.time.OffsetDateTime;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

public abstract class AbstractTableTasks<J> extends TableImpl<Record> implements StaTable<J> {

    private static final long serialVersionUID = -1457801967;

    @Override
    public abstract TableField<Record, J> getId();

    public abstract TableField<Record, J> getTaskingCapabilityId();

    /**
     * The column <code>public.TASKS.CREATION_TIME</code>.
     */
    public final TableField<Record, OffsetDateTime> creationTime = createField("CREATION_TIME", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.TASKINGCAPABILITIES.PROPERTIES</code>.
     */
    public final TableField<Record, String> taskingParameters = createField("TASKING_PARAMETERS", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>public.TASKS</code> table reference
     */
    protected AbstractTableTasks() {
        this(DSL.name("TASKS"), null);
    }

    protected AbstractTableTasks(Name alias, AbstractTableTasks<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableTasks(Name alias, AbstractTableTasks<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    @Override
    public abstract AbstractTableTasks<J> as(Name as);

    @Override
    public abstract AbstractTableTasks<J> as(String alias);

}
