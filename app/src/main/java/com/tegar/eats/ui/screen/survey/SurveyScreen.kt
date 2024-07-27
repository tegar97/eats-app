package com.tegar.eats.ui.screen.survey

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.Typography
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.tegar.eats.R
import com.tegar.eats.ui.theme.Orange
import com.tegar.eats.ui.theme.Typography
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.*
import androidx.compose.material3.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyScreen(
    modifier: Modifier = Modifier,
    navigateToHome: (Long) -> Unit,

    ) {
    var name by remember { mutableStateOf("") }
    var favoriteCategory by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val categories = listOf("Pizza", "Sushi", "Burger", "Salad")

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 2000..2024,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val dayOfWeek = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                        .toLocalDate().dayOfWeek
                    dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY
                } else {
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.timeInMillis = utcTimeMillis
                    calendar[Calendar.DAY_OF_WEEK] != Calendar.SUNDAY &&
                            calendar[Calendar.DAY_OF_WEEK] != Calendar.SATURDAY
                }
            }

            override fun isSelectableYear(year: Int): Boolean {
                return true
            }
        }
    )
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf("") }


            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // logo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                     Image(
                         painter = painterResource(id = R.drawable.logo),
                         contentDescription = "Logo",
                         modifier = Modifier.fillMaxWidth()
                     )

                }
                Text("Eats",   style =  Typography.labelMedium.copy(
                    fontSize = 24.sp,
                ),
                    color = Orange,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Welcome to Tieats where flavor meets your doorstep",
                    style = Typography.bodyMedium.copy(
                        fontSize = 14.sp,
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Orange,
                        unfocusedBorderColor = Color(0xffE9EBEE)
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Favorite Food Category:", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                categories.forEach { category ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Orange,

                            ),
                            selected = favoriteCategory == category,
                            onClick = { favoriteCategory = category }
                        )
                        Text(text = category)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        birthDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    },
                    year,
                    month,
                    day
                )

                // generate button when click show date picker
                OutlinedTextField(
                    value = birthDate,
                    onValueChange = {},
                    label = { Text("Birth Date") },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Orange,
                        unfocusedBorderColor = Color(0xffE9EBEE)
                    ),
                    interactionSource =  remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        datePickerDialog.show()
                                    }
                                }
                            }
                        },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                val timePickerDialog = TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        selectedTime = "$hourOfDay:$minute"
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                )
                OutlinedTextField(
                    value = selectedTime,
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.bodyLarge,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Orange,
                        unfocusedBorderColor = Color(0xffE9EBEE)
                    ),
                    label = { Text("Select Time") },
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        timePickerDialog.show()
                                    }
                                }
                            }
                        },
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color.White,
                            checkedColor = Orange
                        ),
                        checked = acceptedTerms,
                        onCheckedChange = { acceptedTerms = it }
                    )
                    Text(text = "I accept the terms and conditions")
                }
                Button(
                    modifier =  Modifier.fillMaxWidth(),
                    colors =  ButtonDefaults.buttonColors(
                        backgroundColor = Orange,
                        contentColor = Color.White
                    ),
                    onClick = {
                        if (name.isNotBlank() && favoriteCategory.isNotBlank() && birthDate.isNotBlank() && selectedTime.isNotBlank() && acceptedTerms ) {

                                navigateToHome(0)


                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Submit" ,
                        style =  Typography.labelMedium,
                        color = Color.White
                        )
                }
            }


}