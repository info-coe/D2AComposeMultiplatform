package org.infomericainc.infod2a.di

import org.infomericainc.infod2a.core.D2APreferences
import org.infomericainc.infod2a.core.getPreferences
import org.infomericainc.infod2a.data.remote.service.RemoteChatCompletionService
import org.infomericainc.infod2a.data.repository.ChatCompletionRepositoryImpl
import org.infomericainc.infod2a.domain.repository.ChatCompletionRepository
import org.infomericainc.infod2a.domain.usecase.chatcompletion.GetResponseUseCase
import org.infomericainc.infod2a.presentation.chat.viewmodel.ChatCompletionViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.getValue

val dataModule = module {
    single<RemoteChatCompletionService> { RemoteChatCompletionService() }
}


val domainModule = module {
    single<ChatCompletionRepository> {
        ChatCompletionRepositoryImpl(
            remoteChatCompletionService = get()
        )
    }

    factory { GetResponseUseCase() }
}

val commonModule = module {
    single<D2APreferences> { getPreferences() }
}

val presentationModule = module {
    factory {
        ChatCompletionViewModel(
            getResponseUseCase = get()
        )
    }

}

private val commonModules = dataModule + domainModule + presentationModule + commonModule


fun getCommonModules() = commonModules
