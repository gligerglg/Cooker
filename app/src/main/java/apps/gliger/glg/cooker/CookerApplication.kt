package apps.gliger.glg.cooker

import android.app.Application
import apps.gliger.glg.cooker.data.FoodDatabase
import apps.gliger.glg.cooker.data.FoodDatabaseImpl
import apps.gliger.glg.cooker.network.NetworkService
import apps.gliger.glg.cooker.network.RetrofitFactry
import apps.gliger.glg.cooker.repository.Repository
import apps.gliger.glg.cooker.repository.RepositoryImpl
import apps.gliger.glg.cooker.ui.error_ui.ErrorUIViewModel
import apps.gliger.glg.cooker.ui.location_ui.LocationUIViewModel
import apps.gliger.glg.cooker.ui.main_menu.MainMenuViewModel
import apps.gliger.glg.cooker.ui.people_ui.PeopleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CookerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CookerApplication)
            modules(appModule)
        }
    }

    private val appModule = module {
        single<NetworkService> { RetrofitFactry.getService() }
        single<FoodDatabase> { FoodDatabaseImpl.get(get()) }
        single<Repository>{RepositoryImpl(get(),get())}

        viewModel {
            MainMenuViewModel(get())
        }

        viewModel {
            LocationUIViewModel(get())
        }

        viewModel {
            PeopleViewModel(get())
        }

        viewModel {
            MainActivityViewModel(get())
        }

        viewModel {
            ErrorUIViewModel(get())
        }
    }

}