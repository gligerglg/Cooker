package apps.gliger.glg.cooker

import android.app.Application
import apps.gliger.glg.cooker.repository.Repository
import apps.gliger.glg.cooker.repository.RepositoryImpl
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
            modules(listOf(appModule,locationVM,peopleVM))
        }
    }

    private val appModule = module {
        single<Repository>{RepositoryImpl(get())}

        viewModel {
            MainMenuViewModel(get())
        }
    }

    private val locationVM = module {
        viewModel {
            LocationUIViewModel(get())
        }
    }

    private val peopleVM = module {
        viewModel {
            PeopleViewModel(get())
        }
    }
}