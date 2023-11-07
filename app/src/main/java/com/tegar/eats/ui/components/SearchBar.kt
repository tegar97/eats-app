package com.tegar.eats.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tegar.eats.R
import com.tegar.eats.ui.theme.EatsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeForm(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Craving Something Special?",
    leadingIcon: ImageVector = Icons.Filled.Search,
    backgroundColor: Color = Color(0xFFFCFCFD),
    borderColor: Color = Color.Gray,
    borderRadius: Dp = 8.dp
) {
    OutlinedTextField(

        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        textStyle =  MaterialTheme.typography.bodyLarge,

        
    )
}

@Preview(showBackground = true)
@Composable
fun CostumeFormPreview() {
    EatsTheme {
        CostumeForm(
            value = TextFieldValue(""),
            onValueChange = {},
        )
    }
}
