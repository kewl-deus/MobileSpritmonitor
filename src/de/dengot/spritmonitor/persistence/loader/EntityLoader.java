package de.dengot.spritmonitor.persistence.loader;

import android.content.Context;
import android.support.v4.content.Loader;
import de.dengot.spritmonitor.model.Identifyable;
import de.dengot.spritmonitor.persistence.repository.DbRepository;

public class EntityLoader<E extends Identifyable> extends Loader<E> {

    private long id;
    private DbRepository<E> repository;

    public EntityLoader(Context context, DbRepository<E> repository, long id) {
        super(context);
        this.repository = repository;
        this.id = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        E entity = repository.findByKey(id);
        deliverResult(entity);
    }

}
