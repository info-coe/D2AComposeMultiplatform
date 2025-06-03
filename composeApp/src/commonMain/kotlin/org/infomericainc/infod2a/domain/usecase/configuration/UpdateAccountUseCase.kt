package org.infomericainc.infod2a.domain.usecase.configuration

import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

//class UpdateAccountUseCase : KoinComponent {
//    private val sharedPrefManager: SharedPrefManager by inject()
//    suspend operator fun invoke(
//        type: AccountType
//    ) = flow {
//        sharedPrefManager.saveToPreference(
//            key = SharedConstants.ACCOUNT_TYPE,
//            type.name.actualPathName()
//        )
//
//        emit(true)
//    }
//}