package com.heavycoffee.shared.di

import com.heavycoffee.feature.newuser.di.koinNewUserFeatureModule
import com.heavycoffee.feature.userinfo.di.koinUserInfoFeatureModule
import com.heavycoffee.feature.userlist.di.koinUserListFeatureModule

internal fun featureModules() = listOf(
    koinNewUserFeatureModule,
    koinUserInfoFeatureModule,
    koinUserListFeatureModule
)