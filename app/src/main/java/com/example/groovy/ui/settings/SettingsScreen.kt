package com.example.groovy.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groovy.R
import com.example.groovy.ui.home.HomeEvent
import com.example.groovy.ui.home.HomeUiState
import com.example.groovy.ui.theme.monterrat

@Composable
fun SettingsScreen(onEvent: (HomeEvent) -> Unit, uiState: HomeUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.icons8_flat_music),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Groovy",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = monterrat,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
        Text(
            text = "Если хотите слушать долго",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.queen),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Queen",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = monterrat,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Forward",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {

                    }
            )
        }
    }
}