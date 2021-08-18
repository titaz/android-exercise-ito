package lt.ito.mycooking.di.modules

import dagger.Module
import dagger.Provides
import lt.ito.components.respositories.DishesRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDishesRepository(): DishesRepository {
        return DishesRepository()
    }

}