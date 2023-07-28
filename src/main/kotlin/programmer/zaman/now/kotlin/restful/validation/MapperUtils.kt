package programmer.zaman.now.kotlin.restful.validation

import ma.glasnost.orika.MapperFacade
import ma.glasnost.orika.MapperFactory
import ma.glasnost.orika.impl.DefaultMapperFactory

class MapperUtils() {

    private val mapperFactory: MapperFactory = DefaultMapperFactory.Builder().build()
    private val mapperFacade: MapperFacade = mapperFactory.getMapperFacade()

    fun <S, C> map(source: S?, clazz: Class<C>?): C? {
        return if (source == null) {
            null
        } else mapperFacade.map(source, clazz)
    }

    fun <S, C> mapAsList(source: Iterable<S>?, clazz: Class<C>?): List<C>? {
        return if (source == null) {
            null
        } else {
            return mapperFacade.mapAsList(source, clazz)
        }
    }

}