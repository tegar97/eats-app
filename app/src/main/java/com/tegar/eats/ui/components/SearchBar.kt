package com.tegar.eats.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tegar.eats.R
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CostumeForm(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onImeActionPerformed: (ImeAction) -> Unit, // Ubah parameter onImeActionPerformed
    modifier: Modifier = Modifier,
    label: String = "Craving Something Special?",
    leadingIcon: ImageVector = Icons.Filled.Search,
    backgroundColor: Color = Color(0xFFFCFCFD),
    borderColor: Color = Color.Gray,
    borderRadius: Dp = 50.dp,
    padding: Dp = 16.dp
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        textStyle = MaterialTheme.typography.bodyLarge,
        shape = RoundedCornerShape(borderRadius),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Orange,
            unfocusedBorderColor = Color(0xffE9EBEE)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeActionPerformed(ImeAction.Done)
            }
        )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun CostumeFormPreview() {
//    EatsTheme {
//        CostumeForm(
//            value = TextFieldValue(""),
//            onValueChange = {},
//        )
//    }
//}
