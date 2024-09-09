package com.example.whattoeat.ui.theme.screens.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whattoeat.R
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.setting.SettingUIState
import com.example.whattoeat.ui.theme.screens.setting.SettingViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingsScreenEvent

@Composable
fun HomeScreen(settingViewModel: SettingViewModel = hiltViewModel()) {

    val homeUIState by settingViewModel.homeUIState.collectAsState()
    LaunchedEffect({}) {
        settingViewModel.handleScreenEvents(
            SettingsScreenEvent.GetSetting
        )
    }
    when (val state = homeUIState) {
        is SettingUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        SettingUIState.Loading -> {
            LoadingSpinner()
        }

        is SettingUIState.Success -> {
            homeWelcomeScreen(state.appSetting, settingViewModel)
        }
    }
}


@Composable
fun homeWelcomeScreen(appSetting: AppSetting, settingViewModel: SettingViewModel) {
    var currentUsernameSetting = remember {
        mutableStateOf(false)
    }
    var username by remember {
        mutableStateOf(appSetting.username)
    }

    Surface(Modifier.fillMaxSize()) {

        Column(Modifier.padding(12.dp)) {
            Text(text = "Welcome : ")
            Row {

                TextField(
                    enabled = currentUsernameSetting.value,
                    value = username,
                    onValueChange = { username = it })

                Icon(
                    modifier = Modifier.clickable {
                        currentUsernameSetting.value = !currentUsernameSetting.value;
                        if (!currentUsernameSetting.value && username.isNotBlank()) {
                            settingViewModel.handleScreenEvents(
                                SettingsScreenEvent.SaveSetting(
                                    username=username
                                )
                            )
                        }
                    },
                    painter = painterResource(id = R.drawable.baseline_mode_edit_24),
                    contentDescription = ""
                )
            }


            Text(text = stringResource(R.string.pts_left))
            Text(text = appSetting.pointLeft.toString())

        }
    }
}