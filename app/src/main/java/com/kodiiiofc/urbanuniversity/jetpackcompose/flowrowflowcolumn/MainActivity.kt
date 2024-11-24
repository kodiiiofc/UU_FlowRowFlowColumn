package com.kodiiiofc.urbanuniversity.jetpackcompose.flowrowflowcolumn

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodiiiofc.urbanuniversity.jetpackcompose.flowrowflowcolumn.ui.theme.FlowRowFlowColumnTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowRowFlowColumnTheme {

                FlowColumn(
                    maxItemsInEachColumn = 3,
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    employeeList
                        .sortedWith (
                            compareBy<Employee> {it.occupation}
                                .thenBy { it.firstName }
                        ).forEach {
                        EmployeeItem(it)
                    }
                }

            }
        }
    }
}

private val employeeList = listOf(
    Employee("Ричард", "Хендрикс", "программист", 250000, R.drawable.richard_hendrix),
    Employee("Майк", "Эрмантраут", "инженер", 200000, R.drawable.mike_er),
    Employee("Уолтер", "О'Брайен", "инженер", 300000, R.drawable.obraen),
    Employee("Бернадетт", "Ростенковски-Воловиц", "инженер", 120000, R.drawable.rostenovski),
    Employee("Грегори", "Хаус", "врач", 500000, R.drawable.gregory_house),
    Employee("Джон", "Дориан", "врач", 150000, R.drawable.jhon_dorian),
    Employee("Шон", "Мерфи", "врач", 250000, R.drawable.shaun_m),
    Employee("Бертрам", "Гилфойл", "программист", 300000, R.drawable.gilfoy),
    Employee("Генри", "Джонс", "преподаватель", 600000, R.drawable.indianajons),
    Employee("Северус", "Снегг", "преподаватель", 250000, R.drawable.snape),
    Employee("Динеш", "Чугтай", "программист", 200000, R.drawable.dinesh),
    Employee("Уолтер", "Уайт", "преподаватель", 1000000,R.drawable.white),
)

data class Employee(
    val firstName: String,
    val lastName: String,
    var occupation: String,
    var salary: Int,
    var photoResource : Int = R.mipmap.ic_launcher_round,
    )

@Composable
fun EmployeeItem(employee: Employee) {
        Column(
            modifier = Modifier
                .width(360.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(24.dp))
                .background(Color.Gray)
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(employee.photoResource),
                    contentDescription = "Фото сотрудника",
                    modifier = Modifier
                        .size(100.dp))
            }
            Text(text = "Имя: ${employee.firstName}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 2.dp))
            Text(text = "Фамилия: ${employee.lastName}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 2.dp))
            Text(text = "Должность: ${employee.occupation}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 2.dp))
            Text(text = "Зарплата: ${employee.salary} $ в год",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 2.dp))
        }

}

@Preview
@Composable
fun EmployeeItemPreview() {
    EmployeeItem(employeeList[6])
}