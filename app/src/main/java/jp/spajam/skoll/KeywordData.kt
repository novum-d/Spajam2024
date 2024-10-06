package jp.spajam.skoll

import androidx.compose.ui.graphics.Color

data class KeywordItem(
    val keyword: String = "",
    val color: Color = Color.Transparent,
)

object KeywordData {
    var keywordData1: KeywordItem = KeywordItem()
    var keywordData2: KeywordItem = KeywordItem()
    var keywordData3: KeywordItem = KeywordItem()

    fun saveKeywordData(data1: KeywordItem, data2: KeywordItem, data3: KeywordItem) {
        keywordData1 = data1
        keywordData2 = data2
        keywordData3 = data3
    }
}