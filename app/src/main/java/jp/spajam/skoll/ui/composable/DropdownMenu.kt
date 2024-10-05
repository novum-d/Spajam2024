package jp.spajam.skoll.ui.composable

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    title: String,
    keyword: String,
    expanded: Boolean,
    menuItems: List<String>,
    onExpanded: (Boolean) -> Unit,
    onKeywordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) = ExposedDropdownMenuBox(
    modifier = modifier,
    expanded = expanded,
    onExpandedChange = {
        onExpanded(it)
    },
) {
    OutlinedTextField(
        value = keyword,
        label = {
            Text(text = title)
        },
        onValueChange = {},
        readOnly = true,
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        modifier = Modifier.menuAnchor(),
    )
    ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpanded(false) },
    ) {
        menuItems.forEach { value ->
            DropdownMenuItem(
                text = { Text(text = value) },
                onClick = {
                    onExpanded(false)
                    onKeywordChange(value)
                }
            )
        }
    }
}


@Preview
@Composable
private fun DropdownMenuPreview() = DropdownMenu(
    title = "タイトル",
    keyword = "",
    expanded = true,
    menuItems = listOf(
        "item1",
        "item2",
        "item3",
        "item4",
    ),
    onExpanded = {},
    onKeywordChange = {},
)
