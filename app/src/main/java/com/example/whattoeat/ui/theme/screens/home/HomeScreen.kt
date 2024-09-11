package com.example.whattoeat.ui.theme.screens.home

import android.icu.text.CaseMap.Title
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whattoeat.R
import com.example.whattoeat.core.Constants.COST_POINT_RANDOM_RECIPE_REFRESH
import com.example.whattoeat.core.Constants.COST_POINT_SAVE_RECIPE
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.setting.SettingUIState
import com.example.whattoeat.ui.theme.screens.setting.SettingViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingsScreenEvent
import com.example.whattoeat.ui.theme.theme.Typography

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Welcome : ",
                    style = Typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth(0.45f)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    singleLine = true,
                    enabled = currentUsernameSetting.value,
                    value = username,
                    onValueChange = { username = it },

                    )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        modifier = Modifier
                            .padding(4.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxWidth()
                            .clickable {
                                currentUsernameSetting.value = !currentUsernameSetting.value;
                                if (!currentUsernameSetting.value && username.isNotBlank()) {
                                    settingViewModel.handleScreenEvents(
                                        SettingsScreenEvent.SaveSetting(
                                            username = username
                                        )
                                    )
                                }
                            },
                        painter = painterResource(id = R.drawable.baseline_mode_edit_24),
                        contentDescription = ""
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(0.dp, 12.dp),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )

            Row {
                Text(
                    text = "${stringResource(R.string.pts_left)} ${appSetting.pointLeft} ${
                        stringResource(
                            R.string.pts
                        )
                    }", style = Typography.titleMedium
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(0.dp, 12.dp),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )


            Column(horizontalAlignment = Alignment.Start) {
                Text("Point Cost per request", style = Typography.titleMedium)
                Spacer(modifier = Modifier.padding(4.dp))

                Text("Save recipe : $COST_POINT_SAVE_RECIPE pts", style = Typography.titleSmall)
                Text(
                    "Refresh random recipe : $COST_POINT_RANDOM_RECIPE_REFRESH pts",
                    style = Typography.titleSmall
                )
            }


        }
    }
}